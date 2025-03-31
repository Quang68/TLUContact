package com.example.btdanhsach.Add;

import android.content.Context;
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
import com.example.btdanhsach.Contact.Sinhvien;
import com.example.btdanhsach.R;
import com.example.btdanhsach.SinhVienDAO;

public class AddSinhVien extends AppCompatActivity {
    EditText editMSV, editName, editSdt, editEmail, editAddress, txtClass;
    Button btnAdd;

    Context context = this;
    SinhVienDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_sinh_vien);

        // Ánh xạ view
        editMSV = findViewById(R.id.edit_msvAddSV);
        editName = findViewById(R.id.edit_nameAddSV);
        editSdt = findViewById(R.id.edit_sdtAddSV);
        editEmail = findViewById(R.id.edit_emailAddSV);
        editAddress = findViewById(R.id.edit_addressAddSV);
        txtClass = findViewById(R.id.edit_lopAddSV);
        btnAdd = findViewById(R.id.btn_AddSV);

        // Khởi tạo DAO
        dao = new SinhVienDAO(context);

        btnAdd.setOnClickListener(v -> {
            String msv = editMSV.getText().toString().trim();
            String name = editName.getText().toString().trim();
            String sdt = editSdt.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String address = editAddress.getText().toString().trim();
            String lop = txtClass.getText().toString().trim();

            if (msv.isEmpty() || name.isEmpty() || address.isEmpty() || sdt.isEmpty() || email.isEmpty() || lop.isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(sdt.length() != 10){
                Toast.makeText(context, "Số điện thoại phải có 10 số!", Toast.LENGTH_SHORT).show();
                return;
            }
            // String msv, String name, String sdt, String email, String address, String lop, int avatar
            Sinhvien sinhvien = new Sinhvien(msv, name, sdt, email, address, lop,R.drawable.avata);

            if (dao.insert(sinhvien) > 0) {
                Toast.makeText(context, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Trả kết quả để cập nhật danh sách
                finish();
            } else {
                Toast.makeText(context, "Thêm sinh viên thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}