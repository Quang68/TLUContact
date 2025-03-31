package com.example.btdanhsach;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btdanhsach.Contact.CanBo;

public class updateCanBo extends AppCompatActivity {

    // String id, String name, String chucvu, String sdt, String email, String donvicongtac, int avatar
    EditText txtName, txtChucvu, txtSdt, txtEmail, txtDonvicongtac, txtId;
    Button btnUpdate;
    Context context = this;
    CanBoDAO dao;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_can_bo);

        //Ánh xạ địa chỉ
        txtName = findViewById(R.id.edit_nameSuaCB);
        txtChucvu = findViewById(R.id.edit_chucvuSuaCB);
        txtSdt = findViewById(R.id.edit_sdtSuaCB);
        txtEmail = findViewById(R.id.edit_emailSuaCB);
        txtDonvicongtac = findViewById(R.id.edit_donvicongtacSuaCB);
        txtId = findViewById(R.id.edit_idSuaCB);
        btnUpdate = findViewById(R.id.btn_updateSuaCB);

        dao = new CanBoDAO(context);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String chucvu = intent.getStringExtra("chucvu");
        String sdt = intent.getStringExtra("sdt");
        String email = intent.getStringExtra("email");
        String donvicongtac = intent.getStringExtra("donvicongtac");

        // Hiển thị thông tin cần sửa
        txtId.setText(id);
        txtId.setEnabled(false);
        txtName.setText(name);
        txtChucvu.setText(chucvu);
        txtSdt.setText(sdt);
        txtEmail.setText(email);
        txtDonvicongtac.setText(donvicongtac);


        // Sự kiện khi nhấn nút cập nhật
        btnUpdate.setOnClickListener(v -> {
            String id1 = txtId.getText().toString().trim();
            String name1 = txtName.getText().toString().trim();
            String chucvu1 = txtChucvu.getText().toString().trim();
            String sdt1 = txtSdt.getText().toString().trim();
            String email1 = txtEmail.getText().toString().trim();
            String donvicongtac1 = txtDonvicongtac.getText().toString().trim();

            if (id1.isEmpty() || name1.isEmpty() || chucvu1.isEmpty() || sdt1.isEmpty() || email1.isEmpty() || donvicongtac1.isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            CanBo canbo = new CanBo(id1, name1, chucvu1, sdt1, email1, donvicongtac1, R.drawable.avata);

            if (dao.update(canbo) > 0) {
                Toast.makeText(context, "Cập nhật cán bộ thành công!", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("id", id1);
                returnIntent.putExtra("name", name1);
                returnIntent.putExtra("chucvu", chucvu1);
                returnIntent.putExtra("sdt", sdt1);
                returnIntent.putExtra("email", email1);
                returnIntent.putExtra("donvicongtac", donvicongtac1);
                setResult(RESULT_OK, returnIntent);
                finish();
            } else {
                Toast.makeText(context, "Cập nhật cán bộ thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}