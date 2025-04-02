package com.example.btdanhsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btdanhsach.Add.AddCanBo;
import com.example.btdanhsach.CanBoAdapter;
import com.example.btdanhsach.DAO.CanBoDAO;
import com.example.btdanhsach.Contact.CanBo;
import com.example.btdanhsach.R;

import java.util.List;

public class CanBoActivity extends AppCompatActivity {
    private RecyclerView rcvCBNV;
    private CanBoAdapter myAdapter;
    private List<CanBo> canBoList;
    private CanBoDAO dao;
    private ImageButton btnAdd;
    private ImageButton btnFind;
    private EditText editFind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cbnv);

        // Ánh xạ view
        rcvCBNV = findViewById(R.id.rcv_cbnv);
        btnAdd = findViewById(R.id.btn_AddCB);
        btnFind = findViewById(R.id.btnfindCB);
        editFind = findViewById(R.id.edit_findCB);


        // Khởi tạo DAO
        dao = new CanBoDAO(this);

        // Lấy danh sách cán bộ từ DB
        loadData();

        // Xử lý sự kiện nhấn nút "Thêm"
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(CanBoActivity.this, AddCanBo.class);
            startActivityForResult(intent, 100);
        });

//        // Xử lý sự kiện nhấn nút "Tìm kiếm"
//        btnFind.setOnClickListener(v -> {
//            String ten = editFind.getText().toString();
//            if (ten.isEmpty()){
//                loadData();
//                return;
//            }
//            FindView(ten);
//        });

    }
    // Load dữ liệu từ Firebase và cập nhật RecyclerView
    private void loadData() {
        dao.fetchStaffList(new CanBoDAO.StaffListCallback(){
            @Override
            public void onCallback(List<CanBo> staffList) {
                if(myAdapter == null){
                    myAdapter = new CanBoAdapter(CanBoActivity.this, staffList);
                    rcvCBNV.setAdapter(myAdapter);
                } else {
                    myAdapter.updateList(staffList);
                }
            }
        });
    }
    // Load dữ liệu từ SQLite và cập nhật RecyclerView
//    private void loadData() {
//        canBoList = dao.getAll();
//        rcvCBNV.setLayoutManager(new LinearLayoutManager(this));
//        myAdapter = new CanBoAdapter(this, canBoList);
//        rcvCBNV.setAdapter(myAdapter);
//    }


//    private void FindView(String ten) {
//        canBoList = dao.find(ten);
//        rcvCBNV.setLayoutManager(new LinearLayoutManager(this));
//        myAdapter = new CanBoAdapter(this, canBoList);
//        rcvCBNV.setAdapter(myAdapter);
//    }

    // Cập nhật danh sách khi quay lại từ AddCanBo hoặc xóa cán bộ
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 || requestCode == 200 || requestCode == 300 && resultCode == RESULT_OK){
            loadData(); // Load lại danh sách khi có thay đổi
        }
    }
}

