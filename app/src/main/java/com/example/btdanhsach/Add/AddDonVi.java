package com.example.btdanhsach.Add;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btdanhsach.DAO.DonViDAO;
import com.example.btdanhsach.R;

public class AddDonVi extends AppCompatActivity {
    EditText txtName, txtAddress, txtSdt, txtId;
    Button btnAdd;
    Context context = this;
    DonViDAO dao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_don_vi);

        // Ánh xạ view
        txtName = findViewById(R.id.edit_name);
        txtAddress = findViewById(R.id.edit_address);
        txtSdt = findViewById(R.id.edit_sdt);
        txtId = findViewById(R.id.edit_ID);
        btnAdd = findViewById(R.id.btn_Add);

        // Khởi tạo DAO
        dao = new DonViDAO(context);

        // Sự kiện nút Add
//        btnAdd.setOnClickListener(v -> {
//            String id = txtId.getText().toString().trim();
//            String name = txtName.getText().toString().trim();
//            String address = txtAddress.getText().toString().trim();
//            String sdt = txtSdt.getText().toString().trim();
//
//            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || sdt.isEmpty()) {
//                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if(sdt.length() != 10){
//                Toast.makeText(context, "Số điện thoại phải có 10 số!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            Donvi donvi = new Donvi(id, address, name, R.drawable.office, sdt);
//
//            if (dao.insert(donvi) > 0) {
//                Toast.makeText(context, "Thêm đơn vị thành công!", Toast.LENGTH_SHORT).show();
//                setResult(RESULT_OK); // Trả kết quả để cập nhật danh sách
//                finish(); // Đóng Activity sau khi thêm
//            } else {
//                Toast.makeText(context, "Thêm đơn vị thất bại!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
