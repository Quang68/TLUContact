package com.example.btdanhsach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.btdanhsach.Contact.CanBo;
import com.example.btdanhsach.Contact.Sinhvien;
import com.example.btdanhsach.SQLite.SQLite_HelperCanBo;
import com.example.btdanhsach.SQLite.SQLite_HelperSinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
    private SQLite_HelperSinhVien helper;
    private SQLiteDatabase db;
    private Context context;
    // gọi Hàm tạo Database và bảng dữ liệu
    public SinhVienDAO(Context context) {
        this.context = context;
        helper = new SQLite_HelperSinhVien(context);
        db = helper.getWritableDatabase();
    }
    // // String msv, String name, String sdt, String email, String address, String lop, int avatar
    public int insert(Sinhvien sinhvien){
        ContentValues values = new ContentValues(); // Tạo 1 đối tượng ContentValues để chứa dữ liệu
        values.put("msv", sinhvien.getMsv()); // Đưa dữ liệu vào ContentValues
        values.put("name", sinhvien.getName());
        values.put("sdt", sinhvien.getSdt());
        values.put("email", sinhvien.getEmail());
        values.put("address", sinhvien.getAddress());
        values.put("lop", sinhvien.getLop());
        values.put("avatar", sinhvien.getAvatar());
        if (db.insert("SinhVien", null, values) < 0){ // Thực hiện câu lệnh insert
            return -1;
        }else {
            return 1;
        }
    }

    public List<Sinhvien> getAll(){
        List<Sinhvien> dsSinhVien = new ArrayList<>();
        Cursor c = db.query("SinhVien", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            String msv = c.getString(0);
            String name = c.getString(1);
            String sdt = c.getString(2);
            String email = c.getString(3);
            String address = c.getString(4);
            String lop = c.getString(5);
            int avatar = c.getInt(6);
            Sinhvien sinhvien = new Sinhvien(msv, name, sdt, email, address, lop,avatar);
            dsSinhVien.add(sinhvien);
            c.moveToNext();
        }
        c.close();
        return dsSinhVien;
    }
}
