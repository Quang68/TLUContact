package com.example.btdanhsach.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite_HelperCanBo extends SQLiteOpenHelper {
    public SQLite_HelperCanBo(Context context) {
        super(context, "CSDLCanBo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE CanBo (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT, " +
                "chucvu TEXT, " +
                "sdt TEXT, " +
                "email TEXT, " +
                "donvicongtac TEXT, " +
                "avatar INTEGER)";
        db.execSQL(sql);
    }
    // name, chucvu, sdt, email, donvicongtac, id

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CanBo");
        onCreate(db);
    }
}
