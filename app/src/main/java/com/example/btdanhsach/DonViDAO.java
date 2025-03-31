package com.example.btdanhsach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.btdanhsach.Contact.Donvi;
import com.example.btdanhsach.SQLite.SQLite_HelperDonVi;

import java.util.ArrayList;
import java.util.List;

public class DonViDAO {
    private SQLite_HelperDonVi helper;
    private SQLiteDatabase db;
    private Context context;
    // gọi Hàm tạo Database và bảng dữ liệu
    public DonViDAO(Context context) {
        this.context = context;
        helper = new SQLite_HelperDonVi(context);
        db = helper.getWritableDatabase();
    }
    // inert dữ liệu vào bảng
    public int insert(Donvi donvi){
        ContentValues values = new ContentValues(); // Tạo 1 đối tượng ContentValues để chứa dữ liệu
        values.put("id", donvi.getId()); // Đưa dữ liệu vào ContentValues
        values.put("address", donvi.getAddress()); // Đưa dữ liệu vào ContentValues
        values.put("name", donvi.getName());
        values.put("sdt", donvi.getSdt());
        values.put("avatar", donvi.getAvatar());
        if (db.insert("DonVi", null, values) < 0){ // Thực hiện câu lệnh insert
            return -1;
        }else {
            return 1;
        }
    }
    public int update(Donvi donvi){
        ContentValues values = new ContentValues();
        values.put("id", donvi.getId());
        values.put("address", donvi.getAddress());
        values.put("name", donvi.getName());
        values.put("sdt", donvi.getSdt());
        values.put("avatar", donvi.getAvatar());
        if (db.update("DonVi", values, "id = ?", new String[]{donvi.getId()}) < 0){
            return -1;
        }else {
            return 1;
        }
    }

    public List<Donvi> getAll(){
        List<Donvi> dsDonVi = new ArrayList<>();
        Cursor c = db.query("DonVi", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            String id = c.getString(0);
            String address = c.getString(1);
            String name = c.getString(2);
            String sdt = c.getString(3);
            int avatar = c.getInt(4);
            Donvi donvi = new Donvi(id, address, name, avatar, sdt);
            dsDonVi.add(donvi);
            c.moveToNext();
        }
        c.close();
        return dsDonVi;
    }
    public List<Donvi> findDV(String ten){
        List<Donvi> dsDonVi = new ArrayList<>();
        Cursor c = db.query("DonVi", null, "name LIKE ?", new String[]{ten}, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            String id = c.getString(0);
            String address = c.getString(1);
            String name = c.getString(2);
            String sdt = c.getString(3);
            int avatar = c.getInt(4);
            Donvi donvi = new Donvi(id, address, name, avatar, sdt);
            dsDonVi.add(donvi);
            c.moveToNext();
        }
        c.close();
        return dsDonVi;
    }
    public int delete(String id) {
        return db.delete("DonVi", "id = ?", new String[]{id});
    }

}
