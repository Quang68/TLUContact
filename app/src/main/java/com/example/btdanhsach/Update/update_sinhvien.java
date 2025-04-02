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

import com.example.btdanhsach.DAO.DonViDAO;
import com.example.btdanhsach.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class update_sinhvien extends AppCompatActivity {
    EditText txtName, txtAddress, txtSdt, txtId, txtAvatar, txtLop;
    Button btnUpdate;
    Context context = this;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_sinhvien);

        txtName = findViewById(R.id.edit_namesuaSinVien);
        txtAddress = findViewById(R.id.edit_addresssuaSinhVien);
        txtSdt = findViewById(R.id.edit_sdtsuaSinhVien);
        txtId = findViewById(R.id.edit_IDsuaSinhVien);
        txtAvatar = findViewById(R.id.edit_AvatarSuaSinhVien);
        txtLop = findViewById(R.id.edit_LopSuaSinhVien);
        btnUpdate = findViewById(R.id.btn_updateSinhvien);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("diachi");
        String sdt = intent.getStringExtra("sdt");
        String avatar = intent.getStringExtra("avatar");
        String lop = intent.getStringExtra("lop");
        String userID = intent.getStringExtra("userID");
        String email = intent.getStringExtra("email");

        txtId.setText(id);
        txtName.setText(name);
        txtAddress.setText(address);
        txtSdt.setText(sdt);
        txtAvatar.setText(avatar);
        txtLop.setText(lop);


        btnUpdate.setOnClickListener(v -> {
            String id1 = txtId.getText().toString().trim();
            String name1 = txtName.getText().toString().trim();
            String address1 = txtAddress.getText().toString().trim();
            String sdt1 = txtSdt.getText().toString().trim();
            String avatar1 = txtAvatar.getText().toString().trim();
            String lop1 = txtLop.getText().toString().trim();

            if (id1.isEmpty() || name1.isEmpty() || address1.isEmpty() || sdt1.isEmpty() || avatar1.isEmpty() || lop1.isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }


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
                        Toast.makeText(context, "Cập nhật user sinh viên thành công!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Nếu có lỗi xảy ra khi cập nhật dữ liệu
                        Toast.makeText(context, "Cập nhật user sinh viên thất bại! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

            // Cập nhật dữ liệu vào Firestore, với collection là 'staff' và documentId là id1
            db.collection("students") // Tên collection là "students"
                    .document(userID) // Document ID là id của cán bộ
                    .update(
                            "fullName", name1,
                            "address", address1,
                            "phoneNumber", sdt1,
                            "lop", lop1,
                            "id", id1,
                            "photoURL", avatar1
                    ) // Thực hiện cập nhật (set) dữ liệu vào document
                    .addOnSuccessListener(aVoid -> {
                        // Nếu cập nhật thành công
                        Toast.makeText(context, "Cập nhật sinh viên thành công!", Toast.LENGTH_SHORT).show();

                        finish();
                    })
                    .addOnFailureListener(e -> {
                        // Nếu có lỗi xảy ra khi cập nhật dữ liệu
                        Toast.makeText(context, "Cập nhật sinh viên thất bại! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

    }
}