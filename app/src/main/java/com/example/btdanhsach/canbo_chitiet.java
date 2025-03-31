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

public class canbo_chitiet extends AppCompatActivity {
    // rivate String name, chucvu, sdt, email, donvicongtac, id;
    //    private int avatar;
    private TextView txtName, txtchucvu, txtSdt, txtEmail, txtDonvicongtac, txtId;
    private ImageView imvAvata;

    private ImageButton btnphone, btnchat, btnvideocall, btnEmail;
    private String idcanbo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_canbo_chitiet);

        txtName = findViewById(R.id.txt_nameCB);
        txtchucvu = findViewById(R.id.txt_chucvuCB);
        txtSdt = findViewById(R.id.txt_sdtCB);
        txtEmail = findViewById(R.id.txt_emailCB);
        txtDonvicongtac = findViewById(R.id.txt_donvicongtacCB);
        imvAvata = findViewById(R.id.imv_AvataCB);
        txtId = findViewById(R.id.txt_idCB);
        btnphone = findViewById(R.id.btnPhoneCB);
        btnchat = findViewById(R.id.btnChatCB);
        btnvideocall = findViewById(R.id.btnVideoCallCB);
        btnEmail = findViewById(R.id.btnEmailCB);

        Intent intent = getIntent();
        idcanbo = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String chucvu = intent.getStringExtra("chucvu");
        String sdt = intent.getStringExtra("sdt");
        String email = intent.getStringExtra("email");
        String donvicongtac = intent.getStringExtra("donvicongtac");
        int avatar = intent.getIntExtra("avatar", R.drawable.avata);

        imvAvata.setImageResource(avatar);
        txtName.setText(name);
        txtchucvu.setText(chucvu);
        txtSdt.setText(sdt);
        txtEmail.setText(email);
        txtDonvicongtac.setText(donvicongtac);
        txtId.setText(idcanbo);

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
        // Sự kiện khi nhấn nút gọi video
        btnvideocall.setOnClickListener(v -> {
            Intent videoCallIntent = new Intent(Intent.ACTION_DIAL);
            videoCallIntent.setData(Uri.parse("tel:" + sdt));
            startActivity(videoCallIntent);
        });

        // Xử lý sự kiện khi nhấn nút gửi email
        btnEmail.setOnClickListener(v -> {

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + email)); // Chỉ mở các ứng dụng email
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Liên hệ công tác");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Chào " + txtName.getText().toString() + ",\n\n");

            try {
                startActivity(Intent.createChooser(emailIntent, "Chọn ứng dụng email"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "Không có ứng dụng email nào", Toast.LENGTH_SHORT).show();
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
            // Mở màn hình chỉnh sửa cán bộ
            Intent intent = new Intent(this, updateCanBo.class);
            intent.putExtra("id", idcanbo); // Gửi ID của cán bộ sang EditDonViActivity
            intent.putExtra("name", txtName.getText().toString());
            intent.putExtra("chucvu", txtchucvu.getText().toString());
            intent.putExtra("sdt", txtSdt.getText().toString());
            intent.putExtra("email", txtEmail.getText().toString());
            intent.putExtra("donvicongtac", txtDonvicongtac.getText().toString());
            startActivityForResult(intent, 300);
            return true;
        }

        if (id == R.id.action_delete) {
            // Hiển thị hộp thoại xác nhận xóa
            new AlertDialog.Builder(this)
                    .setTitle("Xóa Cán bộ")
                    .setMessage("Bạn có chắc chắn muốn xóa không?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        CanBoDAO dao = new CanBoDAO(this);
                        dao.delete(idcanbo); // Gọi hàm xóa từ DAO
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
            // Cập nhật lại thông tin đơn vị sau khi chỉnh sửa String name, chucvu, sdt, email, donvicongtac, id;
            txtName.setText(data.getStringExtra("name"));
            txtchucvu.setText(data.getStringExtra("chucvu"));
            txtSdt.setText(data.getStringExtra("sdt"));
            txtEmail.setText(data.getStringExtra("email"));
            txtDonvicongtac.setText(data.getStringExtra("donvicongtac"));
            setResult(RESULT_OK); // Trả kết quả để cập nhật danh sách
        }

    }

}