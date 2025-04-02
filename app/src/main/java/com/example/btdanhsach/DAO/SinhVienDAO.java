package com.example.btdanhsach.DAO;

import android.content.Context;
import android.util.Log;

import com.example.btdanhsach.Contact.Sinhvien;
import com.example.btdanhsach.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
    private List<Sinhvien> contactList = new ArrayList<>();

    private Context context;

    private FirebaseFirestore dbFireStore;

    public interface StaffListCallback {
        void onCallback(List<Sinhvien> staffList);
    }
    public SinhVienDAO(Context context) {
        this.context = context;
        dbFireStore = FirebaseFirestore.getInstance();
    }
    public void fetchStaffList(StaffListCallback callback) {

        dbFireStore.collection("students") // Truy vấn collection "students"
                .get()
                .addOnSuccessListener(snapshots -> {
                    List<Sinhvien> staffList = new ArrayList<>();

                    if (!snapshots.isEmpty()) {
                        for (QueryDocumentSnapshot document : snapshots) {
                            Sinhvien staff = new Sinhvien(
                                    document.getString("id"),
                                    document.getString("fullName"),
                                    document.getString("phoneNumber"),
                                    document.getString("email"),
                                    document.getString("address"),
                                    document.getString("lop"),
                                    document.getString("photoURL")
                            );
                            staffList.add(staff); // Thêm vào danh sách
                        }
                    }

                    callback.onCallback(staffList); // Gọi callback để trả về dữ liệu
                })
                .addOnFailureListener(e -> Log.e("FirebaseError", "Lỗi khi lấy dữ liệu", e));
    }

    // gọi Hàm tạo Database và bảng dữ liệu
    // // String msv, String name, String sdt, String email, String address, String lop, int avatar
//    public int insert(Sinhvien sinhvien){
//        ContentValues values = new ContentValues(); // Tạo 1 đối tượng ContentValues để chứa dữ liệu
//        values.put("msv", sinhvien.getMsv()); // Đưa dữ liệu vào ContentValues
//        values.put("name", sinhvien.getName());
//        values.put("sdt", sinhvien.getSdt());
//        values.put("email", sinhvien.getEmail());
//        values.put("address", sinhvien.getAddress());
//        values.put("lop", sinhvien.getLop());
//        values.put("avatar", sinhvien.getAvatar());
//        if (db.insert("SinhVien", null, values) < 0){ // Thực hiện câu lệnh insert
//            return -1;
//        }else {
//            return 1;
//        }
//    }




}
