package com.example.btdanhsach;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class donvi_chitiet extends AppCompatActivity {
    private TextView txtNameDV, txtAdressDV, txtSdtDV, txtIdDV;
    private ImageView imvDonvichitiet;
    private String iddonvi;
    private ImageButton btnphone, btnchat, btnvideocall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donvi_chitiet);


        txtNameDV = (TextView) findViewById(R.id.txt_NameDV);
        txtAdressDV = findViewById(R.id.txt_Adressdv);
        txtSdtDV = findViewById(R.id.txt_sdtdv);
        imvDonvichitiet = findViewById(R.id.imv_Donvichitiet);
        txtIdDV = findViewById(R.id.txt_id);
        btnphone = findViewById(R.id.btnphone);
        btnchat = findViewById(R.id.btnchat);
        btnvideocall = findViewById(R.id.btnvideocall);

        Intent intent = getIntent();
        iddonvi = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String sdt = intent.getStringExtra("sdt");
        int avatar = intent.getIntExtra("avatar", R.drawable.avata);

        imvDonvichitiet.setImageResource(avatar);
        txtNameDV.setText(name);
        txtAdressDV.setText(address);
        txtSdtDV.setText(sdt);
        txtIdDV.setText(iddonvi);
        // Sự kiện khi nhấn nút gọi điện
        btnphone.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL); // Mở trình quay số
            callIntent.setData(Uri.parse("tel:" + sdt));
            startActivity(callIntent);
        });

        // Sự kiện khi nhấn nút nhắn tin
        btnchat.setOnClickListener(v -> {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:" + sdt));
            startActivity(smsIntent);
        });

        btnvideocall.setOnClickListener(v -> {
            Intent videoCallIntent = new Intent(Intent.ACTION_VIEW);
            videoCallIntent.setData(Uri.parse("tel:" + sdt));
            videoCallIntent.setPackage("com.google.android.dialer"); // Google Phone (Duo)

            try {
                startActivity(videoCallIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Ứng dụng gọi video không khả dụng!", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.themsua, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            // Mở màn hình chỉnh sửa đơn vị
            Intent intent = new Intent(this, update_donvi.class);
            intent.putExtra("id", iddonvi); // Gửi ID của đơn vị sang EditDonViActivity
            intent.putExtra("name", txtNameDV.getText().toString());
            intent.putExtra("address", txtAdressDV.getText().toString());
            intent.putExtra("sdt", txtSdtDV.getText().toString());
            startActivityForResult(intent, 300);
            return true;
        }

        if (id == R.id.action_delete) {
            // Hiển thị hộp thoại xác nhận xóa
            new AlertDialog.Builder(this)
                    .setTitle("Xóa Đơn Vị")
                    .setMessage("Bạn có chắc chắn muốn xóa không?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        DonViDAO dao = new DonViDAO(this);
                        dao.delete(iddonvi); // Gọi hàm xóa từ DAO
                        setResult(RESULT_OK); // Trả kết quả để cập nhật danh sách
                        Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show();
                        finish(); // Đóng màn hình hiện tại
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 300 && resultCode == RESULT_OK) {
            // Cập nhật lại thông tin đơn vị sau khi chỉnh sửa
            txtNameDV.setText(data.getStringExtra("name"));
            txtAdressDV.setText(data.getStringExtra("address"));
            txtSdtDV.setText(data.getStringExtra("sdt"));
            setResult(RESULT_OK); // Trả kết quả để cập nhật danh sách
        }

    }

}