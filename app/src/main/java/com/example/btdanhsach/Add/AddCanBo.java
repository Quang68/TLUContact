package com.example.btdanhsach.Add;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btdanhsach.DAO.CanBoDAO;
import com.example.btdanhsach.R;

public class AddCanBo extends AppCompatActivity {

    // String name, chucvu, sdt, email, donvicongtac, id;
    EditText editName, editChucvu, editSdt, editEmail, editDonvicongtac, editId;
    Button btnAdd;
    Context context = this;
    CanBoDAO dao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_can_bo);

        // Ánh xạ view
        editName = findViewById(R.id.edit_nameAddCB);
        editChucvu = findViewById(R.id.edit_chucvuAddCB);
        editSdt = findViewById(R.id.edit_sdtAddCB);
        editEmail = findViewById(R.id.edit_emailAddCB);
        editDonvicongtac = findViewById(R.id.edit_donvicongtacAddCB);
        editId = findViewById(R.id.edit_idAddCB);
        btnAdd = findViewById(R.id.btn_AddCBThem);

        // Khởi tạo DAO
        dao = new CanBoDAO(context);

        // Sự kiện nút Add
//        btnAdd.setOnClickListener(v -> {
//            String id = editId.getText().toString().trim();
//            String name = editName.getText().toString().trim();
//            String chucvu = editChucvu.getText().toString().trim();
//            String sdt = editSdt.getText().toString().trim();
//            String email = editEmail.getText().toString().trim();
//            String donvicongtac = editDonvicongtac.getText().toString().trim();
//
//            if (id.isEmpty() || name.isEmpty() || chucvu.isEmpty() || sdt.isEmpty() || email.isEmpty() || donvicongtac.isEmpty()) {
//                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if(sdt.length() != 10){
//                Toast.makeText(context, "Số điện thoại phải có 10 số!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            // String id, String name, String chucvu, String sdt, String email, String donvicongtac, int avatar
//            CanBo canbo = new CanBo(id, name, chucvu, sdt, email, donvicongtac, R.drawable.avata);
//
//            if (dao.insert(canbo) > 0) {
//                Toast.makeText(context, "Thêm cán bộ thành công!", Toast.LENGTH_SHORT).show();
//                setResult(RESULT_OK); // Trả kết quả để cập nhật danh sách
//                finish();
//            } else {
//                Toast.makeText(context, "Thêm cán bộ thất bại!", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}