package com.example.btdanhsach.DAO;

import android.content.Context;
import android.util.Log;

import com.example.btdanhsach.Contact.Donvi;
import com.example.btdanhsach.R;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DonViDAO {

    private FirebaseFirestore dbFireStore;
    private Context context;
    // gọi Hàm tạo Database và bảng dữ liệu
    public DonViDAO(Context context) {
        this.context = context;
        dbFireStore = FirebaseFirestore.getInstance();
//        helper = new SQLite_HelperDonVi(context);
//        db = helper.getWritableDatabase();
    }

    public interface StaffListCallback {
        void onCallback(List<Donvi> staffList);
    }

    public void fetchDonviList(DonViDAO.StaffListCallback callback) {

        dbFireStore.collection("donvi") // Truy vấn collection "students"
                .get()
                .addOnSuccessListener(snapshots -> {
                    List<Donvi> donviList = new ArrayList<>();

                    if (!snapshots.isEmpty()) {
                        for (QueryDocumentSnapshot document : snapshots) {
                            Donvi donvi = new Donvi(

                                    document.getString("address"),
                                    document.getString("name"),
                                    document.getString("sdt"),
                                    document.getString("photoURL")
                            );
                            donviList.add(donvi); // Thêm vào danh sách
                        }
                    }

                    callback.onCallback(donviList); // Gọi callback để trả về dữ liệu
                })
                .addOnFailureListener(e -> Log.e("FirebaseError", "Lỗi khi lấy dữ liệu", e));
    }

    // inert dữ liệu vào bảng
//    public int insert(Donvi donvi){
//        ContentValues values = new ContentValues(); // Tạo 1 đối tượng ContentValues để chứa dữ liệu
//        values.put("id", donvi.getId()); // Đưa dữ liệu vào ContentValues
//        values.put("address", donvi.getAddress()); // Đưa dữ liệu vào ContentValues
//        values.put("name", donvi.getName());
//        values.put("sdt", donvi.getSdt());
//        values.put("avatar", donvi.getAvatar());
//        if (db.insert("DonVi", null, values) < 0){ // Thực hiện câu lệnh insert
//            return -1;
//        }else {
//            return 1;
//        }
//    }
//    public int update(Donvi donvi){
//        ContentValues values = new ContentValues();
//        values.put("id", donvi.getId());
//        values.put("address", donvi.getAddress());
//        values.put("name", donvi.getName());
//        values.put("sdt", donvi.getSdt());
//        values.put("avatar", donvi.getAvatar());
//        if (db.update("DonVi", values, "id = ?", new String[]{donvi.getId()}) < 0){
//            return -1;
//        }else {
//            return 1;
//        }
//    }
//
//    public List<Donvi> getAll(){
//        List<Donvi> dsDonVi = new ArrayList<>();
//        Cursor c = db.query("DonVi", null, null, null, null, null, null);
//        c.moveToFirst();
//        while (c.isAfterLast() == false){
//            String id = c.getString(0);
//            String address = c.getString(1);
//            String name = c.getString(2);
//            String sdt = c.getString(3);
//            int avatar = c.getInt(4);
//            Donvi donvi = new Donvi(id, address, name, avatar, sdt);
//            dsDonVi.add(donvi);
//            c.moveToNext();
//        }
//        c.close();
//        return dsDonVi;
//    }
//    public List<Donvi> findDV(String ten){
//        List<Donvi> dsDonVi = new ArrayList<>();
//        Cursor c = db.query("DonVi", null, "name LIKE ?", new String[]{ten}, null, null, null, null);
//        c.moveToFirst();
//        while (c.isAfterLast() == false){
//            String id = c.getString(0);
//            String address = c.getString(1);
//            String name = c.getString(2);
//            String sdt = c.getString(3);
//            int avatar = c.getInt(4);
//            Donvi donvi = new Donvi(id, address, name, avatar, sdt);
//            dsDonVi.add(donvi);
//            c.moveToNext();
//        }
//        c.close();
//        return dsDonVi;
//    }
//    public int delete(String id) {
//        return db.delete("DonVi", "id = ?", new String[]{id});
//    }

}
