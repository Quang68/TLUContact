package com.example.btdanhsach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.btdanhsach.Contact.CanBo;
import com.example.btdanhsach.SQLite.SQLite_HelperCanBo;
import com.example.btdanhsach.SQLite.SQLite_HelperDonVi;

import java.util.ArrayList;
import java.util.List;

public class CanBoDAO {
    private SQLite_HelperCanBo helper;
    private SQLiteDatabase db;
    private Context context;
    // gọi Hàm tạo Database và bảng dữ liệu
    public CanBoDAO(Context context) {
        this.context = context;
        helper = new SQLite_HelperCanBo(context);
        db = helper.getWritableDatabase();
    }
    // inert dữ liệu vào bảng
    public int insert(CanBo canBo){
        ContentValues values = new ContentValues(); // Tạo 1 đối tượng ContentValues để chứa dữ liệu
        values.put("id", canBo.getId()); // Đưa dữ liệu vào ContentValues
        values.put("name", canBo.getName());
        values.put("chucvu", canBo.getChucvu());
        values.put("sdt", canBo.getSdt());
        values.put("email", canBo.getEmail());
        values.put("donvicongtac", canBo.getDonvicongtac());
        values.put("avatar", canBo.getAvatar());
        if (db.insert("CanBo", null, values) < 0){ // Thực hiện câu lệnh insert
            return -1;
        }else {
            return 1;
        }
    }
    public int update(CanBo canBo){
        ContentValues values = new ContentValues();
        values.put("id", canBo.getId());
        values.put("name", canBo.getName());
        values.put("chucvu", canBo.getChucvu());
        values.put("sdt", canBo.getSdt());
        values.put("email", canBo.getEmail());
        values.put("donvicongtac", canBo.getDonvicongtac());
        values.put("avatar", canBo.getAvatar());
        if (db.update("CanBo", values, "id = ?", new String[]{canBo.getId()}) < 0){
            return -1;
        }else {
            return 1;
        }
    }
    public void delete(String id){
        db.delete("CanBo", "id = ?", new String[]{id});
    }
    public List<CanBo> getAll(){
        List<CanBo> dsCanBo = new ArrayList<>();
        Cursor c = db.query("CanBo", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            String id = c.getString(0);
            String name = c.getString(1);
            String chucvu = c.getString(2);
            String sdt = c.getString(3);
            String email = c.getString(4);
            String donvicongtac = c.getString(5);
            int avatar = c.getInt(6);
            CanBo canBo = new CanBo(id, name, chucvu, sdt, email, donvicongtac, avatar);
            dsCanBo.add(canBo);
            c.moveToNext();
        }
        c.close();
        return dsCanBo;
    }

    public List<CanBo> find(String Ten){
        List<CanBo> dsCanBo = new ArrayList<>();
        Cursor c = db.query("CanBo", null, "name LIKE ?", new String[]{Ten}, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            String id = c.getString(0);
            String name = c.getString(1);
            String chucvu = c.getString(2);
            String sdt = c.getString(3);
            String email = c.getString(4);
            String donvicongtac = c.getString(5);
            int avatar = c.getInt(6);
            CanBo canBo = new CanBo(id, name, chucvu, sdt, email, donvicongtac, avatar);
            dsCanBo.add(canBo);
            c.moveToNext();
        }
        c.close();
        return dsCanBo;
    }
}
