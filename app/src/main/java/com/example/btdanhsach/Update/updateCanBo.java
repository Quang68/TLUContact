package com.example.btdanhsach.Update;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btdanhsach.Contact.CanBo;
import com.example.btdanhsach.DAO.CanBoDAO;
import com.example.btdanhsach.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class updateCanBo extends AppCompatActivity {

    // String id, String name, String chucvu, String sdt, String email, String donvicongtac, int avatar
    EditText txtName, txtChucvu, txtSdt, txtEmail, txtDonvicongtac, txtId, txtAvatar;
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
        txtAvatar = findViewById(R.id.edit_AnhCB);
        btnUpdate = findViewById(R.id.btn_updateSuaCB);

        dao = new CanBoDAO(context);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String chucvu = intent.getStringExtra("chucvu");
        String sdt = intent.getStringExtra("sdt");
        String email = intent.getStringExtra("email");
        String donvicongtac = intent.getStringExtra("donvicongtac");
        String avatar = intent.getStringExtra("avatar");
        String userID = intent.getStringExtra("userID");

        // Hiển thị thông tin cần sửa
        txtId.setText(id);
        txtId.setEnabled(false);
        txtName.setText(name);
        txtChucvu.setText(chucvu);
        txtSdt.setText(sdt);
        txtEmail.setText(email);
        txtDonvicongtac.setText(donvicongtac);
        txtAvatar.setText(avatar);


        btnUpdate.setOnClickListener(v -> {
            String id1 = txtId.getText().toString().trim();
            String name1 = txtName.getText().toString().trim();
            String chucvu1 = txtChucvu.getText().toString().trim();
            String sdt1 = txtSdt.getText().toString().trim();
            String email1 = txtEmail.getText().toString().trim();
            String donvicongtac1 = txtDonvicongtac.getText().toString().trim();
            String avatar1 = txtAvatar.getText().toString().trim();

            // Kiểm tra các trường hợp dữ liệu chưa nhập đầy đủ
            if (id1.isEmpty() || name1.isEmpty() || chucvu1.isEmpty() || sdt1.isEmpty() || email1.isEmpty() || donvicongtac1.isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo đối tượng CanBo với dữ liệu nhập và

            // Lấy instance Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("users") // Tên collection là "staff"
                    .document(userID) // Document ID là id của cán bộ
                    .update(
                            "fullName", name1,
                            "phoneNumber", sdt1,
                            "id", id1,
                            "photoURL", avatar1
                    ) // Thực hiện cập nhật (set) dữ liệu vào document
                    .addOnSuccessListener(aVoid -> {
                        // Nếu cập nhật thành công
                        Toast.makeText(context, "Cập nhật user cán bộ thành công!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Nếu có lỗi xảy ra khi cập nhật dữ liệu
                        Toast.makeText(context, "Cập nhật user cán bộ thất bại! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

            // Cập nhật dữ liệu vào Firestore, với collection là 'staff' và documentId là id1
            db.collection("staff") // Tên collection là "staff"
                    .document(userID) // Document ID là id của cán bộ
                    .update(
                            "fullName", name1,
                            "position", chucvu1,
                            "phoneNumber", sdt1,
                            "departmant", donvicongtac1,
                            "id", id1,
                            "photoURL", avatar1
                    ) // Thực hiện cập nhật (set) dữ liệu vào document
                    .addOnSuccessListener(aVoid -> {
                        // Nếu cập nhật thành công
                        Toast.makeText(context, "Cập nhật cán bộ thành công!", Toast.LENGTH_SHORT).show();

                        finish();
                    })
                    .addOnFailureListener(e -> {
                        // Nếu có lỗi xảy ra khi cập nhật dữ liệu
                        Toast.makeText(context, "Cập nhật cán bộ thất bại! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });


    }
}