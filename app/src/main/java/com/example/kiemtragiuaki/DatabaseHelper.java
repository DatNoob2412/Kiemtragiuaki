package com.example.kiemtragiuaki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuanLySanPham.db";
    private static final int DATABASE_VERSION = 1;

    // Câu lệnh tạo bảng SanPham
    private static final String CREATE_TABLE_SANPHAM = "CREATE TABLE SanPham (" +
            "MaSP INTEGER PRIMARY KEY AUTOINCREMENT," +
            "TenSP TEXT NOT NULL," +
            "GiaTien REAL NOT NULL," +
            "Image BLOB);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng SanPham
        String createTable = "CREATE TABLE SanPham ("
                + "MaSP INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TenSP TEXT NOT NULL, "
                + "GiaTien REAL NOT NULL, "
                + "Image BLOB);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp cơ sở dữ liệu (nếu cần)
    }
}
