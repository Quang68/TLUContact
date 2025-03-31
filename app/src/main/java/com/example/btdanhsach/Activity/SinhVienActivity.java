package com.example.btdanhsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btdanhsach.Add.AddCanBo;
import com.example.btdanhsach.Add.AddSinhVien;
import com.example.btdanhsach.CanBoAdapter;
import com.example.btdanhsach.CanBoDAO;
import com.example.btdanhsach.Contact.CanBo;
import com.example.btdanhsach.Contact.Sinhvien;
import com.example.btdanhsach.R;
import com.example.btdanhsach.SinhVienAdapter;
import com.example.btdanhsach.SinhVienDAO;

import java.util.List;

public class SinhVienActivity extends AppCompatActivity {

    private RecyclerView rcvCBNV;
    private SinhVienAdapter myAdapter;
    private List<Sinhvien> sinhvienList;
    private SinhVienDAO dao;
    private ImageButton btnAdd;
    private ImageButton btnFind;
    private EditText editFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sinh_vien);

        // Ánh xạ view
        rcvCBNV = findViewById(R.id.rcv_sinhvien);
        btnAdd = findViewById(R.id.btn_AddSinhvien);
        btnFind = findViewById(R.id.btnfindSinhvien);
        editFind = findViewById(R.id.edit_findSinhvien);

        dao = new SinhVienDAO(this);

        loadData();

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(SinhVienActivity.this, AddSinhVien.class);
            startActivityForResult(intent, 100);
        });
    }

    private void loadData() {
        sinhvienList = dao.getAll();
        rcvCBNV.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new SinhVienAdapter(this, sinhvienList);
        rcvCBNV.setAdapter(myAdapter);
    }

    // Cập nhật danh sách khi quay lại từ AddCanBo hoặc xóa cán bộ
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // requestCode == 100 || requestCode == 200 || requestCode == 300 && resultCode == RESULT_OK
        if(requestCode == 100 && resultCode == RESULT_OK){
            loadData(); // Load lại danh sách khi có thay đổi
        }
    }
}