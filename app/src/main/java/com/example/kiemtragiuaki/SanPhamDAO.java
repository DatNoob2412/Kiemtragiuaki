package com.example.kiemtragiuaki;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long themSanPham(String tenSP, double giaTien, byte[] image) {
        ContentValues values = new ContentValues();
        values.put("TenSP", tenSP);
        values.put("GiaTien", giaTien);
        values.put("Image", image);

        return database.insert("SanPham", null, values);
    }

    public List<Sanpham> layDanhSachSanPham() {
        List<Sanpham> sanPhamList = new ArrayList<>();
        Cursor cursor = database.query("SanPham", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Sanpham sanPham = cursorToSanPham(cursor);
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        cursor.close();
        return sanPhamList;
    }

    @SuppressLint("Range")
    private Sanpham cursorToSanPham(Cursor cursor) {
        Sanpham sanPham = new Sanpham();
        sanPham.setMaSP(cursor.getInt(cursor.getColumnIndex("MaSP")));
        sanPham.setTenSP(cursor.getString(cursor.getColumnIndex("TenSP")));
        sanPham.setGiaTien(cursor.getDouble(cursor.getColumnIndex("GiaTien")));
        sanPham.setImage(cursor.getBlob(cursor.getColumnIndex("Image")));
        return sanPham;
    }
}

