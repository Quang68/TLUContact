package com.example.btdanhsach.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite_HelperDonVi extends SQLiteOpenHelper {
    // Hamf tạo CSDL
    public SQLite_HelperDonVi(Context context) {
        super(context, "CSDLDonVi", null, 1);
    }
    // Ham tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE DonVi (id INTEGER PRIMARY KEY AUTOINCREMENT, address TEXT, name TEXT, sdt TEXT, avatar INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DonVi");
        onCreate(db);
    }
}
