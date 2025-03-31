package com.example.btdanhsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btdanhsach.Add.AddDonVi;
import com.example.btdanhsach.Contact.Donvi;
import com.example.btdanhsach.DonViAdapter;
import com.example.btdanhsach.DonViDAO;
import com.example.btdanhsach.R;

import java.util.List;

public class DonViActivity extends AppCompatActivity {
    private RecyclerView rcvDonVi;
    private DonViAdapter myAdapter;
    private DonViDAO dao;
    private List<Donvi> donviList;
    private ImageButton btnAdd, btnFindDV;

    private EditText editFindDV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_don_vi);

        // Ánh xạ view
        rcvDonVi = findViewById(R.id.rcv_donvi);
        btnAdd = findViewById(R.id.btn_AddDV);
        btnFindDV = findViewById(R.id.btn_findDV);
        editFindDV = findViewById(R.id.edit_findDV);

        // Khởi tạo DAO
        dao = new DonViDAO(this);

        // Lấy danh sách đơn vị từ DB
        loadData();

        // Xử lý sự kiện nhấn nút "Thêm"
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(DonViActivity.this, AddDonVi.class);
            startActivityForResult(intent, 100);
        });

        // Xử lý sự kiện nhấn nút "Tìm kiếm"
        btnFindDV.setOnClickListener(v -> {
            String ten = editFindDV.getText().toString();
            if (ten.isEmpty()) {
                loadData();
            } else {
                donviList = dao.findDV(ten);
                rcvDonVi.setLayoutManager(new LinearLayoutManager(this));
                myAdapter = new DonViAdapter(this, donviList);
                rcvDonVi.setAdapter(myAdapter);
            }
        });
    }

    // Load dữ liệu từ SQLite và cập nhật RecyclerView
    private void loadData() {
        donviList = dao.getAll();
        rcvDonVi.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new DonViAdapter(this, donviList);
        rcvDonVi.setAdapter(myAdapter);
    }

    // Cập nhật danh sách khi quay lại từ AddDonVi hoặc xóa đơn vị
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 100 || requestCode == 200 || requestCode == 300) && resultCode == RESULT_OK) {
            loadData(); // Load lại danh sách khi có thay đổi
        }
    }
}
