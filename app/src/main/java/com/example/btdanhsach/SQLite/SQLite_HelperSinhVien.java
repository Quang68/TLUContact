package com.example.btdanhsach.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLite_HelperSinhVien extends SQLiteOpenHelper {
    public SQLite_HelperSinhVien(Context context) {
        super(context, "CSDLSinhVien", null, 1);
    }

//    values.put("msv", sinhvien.getMsv()); // Đưa dữ liệu vào ContentValues
//        values.put("name", sinhvien.getName());
//        values.put("sdt", sinhvien.getSdt());
//        values.put("email", sinhvien.getEmail());
//        values.put("address", sinhvien.getAddress());
//        values.put("lop", sinhvien.getLop());
//        values.put("avatar", sinhvien.getAvatar());

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE SinhVien (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " msv TEXT, " +
                "name TEXT, " +
                "sdt TEXT, " +
                "email TEXT, " +
                "address TEXT, " +
                "lop TEXT, " +
                "avatar INTEGER)";
        db.execSQL(sql);
    }
    // name, chucvu, sdt, email, donvicongtac, id

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SinhVien");
        onCreate(db);
    }
}


