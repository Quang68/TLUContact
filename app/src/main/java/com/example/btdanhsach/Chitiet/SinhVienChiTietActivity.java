package com.example.btdanhsach.Chitiet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.btdanhsach.R;

public class SinhVienChiTietActivity extends AppCompatActivity {
    private TextView txtName, txtMa, txtSdt, txtEmail, txtDiachi, txtLop, txtId;

    private ImageView imvAvata;
    private ImageButton btnphone, btnchat, btnEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sinh_vien_chi_tiet);

        btnphone = findViewById(R.id.btnPhoneSV);
        btnchat = findViewById(R.id.btnChatSV);
        btnEmail = findViewById(R.id.btnEmailSV);
        txtId = findViewById(R.id.txt_maSV);
        txtName = findViewById(R.id.txt_nameSV);
        txtMa = findViewById(R.id.txt_maSV);
        txtSdt = findViewById(R.id.txt_sdtSV);
        txtEmail = findViewById(R.id.txt_emailSV);
        txtDiachi = findViewById(R.id.txt_diachiSV);
        txtLop = findViewById(R.id.txt_lopSV);
        imvAvata = findViewById(R.id.imv_AvatarSV);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String lop = intent.getStringExtra("lop");
        String sdt = intent.getStringExtra("sdt");
        String email = intent.getStringExtra("email");
        String diachi = intent.getStringExtra("diachi");
        String avatar = intent.getStringExtra("avatar");

        Glide.with(this)
                .load(avatar) // Lấy ảnh đầu tiên trong danh sách
                .placeholder(R.drawable.avata) // Ảnh tạm khi load
                .error(R.drawable.avata) // Ảnh hiển thị nếu load lỗi
                .into(imvAvata);
        txtName.setText("Tên: " +name);
        txtLop.setText("Lớp: " + lop);
        txtSdt.setText("SĐT: "+ sdt);
        txtEmail.setText("Email: "+email);
        txtDiachi.setText("Địa chỉ: "+diachi);
        txtId.setText("MSV: "+id);

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
}