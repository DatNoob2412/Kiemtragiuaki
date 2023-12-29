package com.example.kiemtragiuaki;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SanPhamDAO sanPhamDAO;
    private ListView listViewSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sanPhamDAO = new SanPhamDAO(this);

        // Mở cơ sở dữ liệu
        try {
            sanPhamDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Thêm dữ liệu mẫu
        sanPhamDAO.themSanPham("Sản phẩm 1", 100.0, convertImageToByteArray(R.drawable.image));
        sanPhamDAO.themSanPham("Sản phẩm 2", 150.0, convertImageToByteArray(R.drawable.image));
        sanPhamDAO.themSanPham("Sản phẩm 3", 200.0, convertImageToByteArray(R.drawable.image));

        // Hiển thị danh sách sản phẩm
        List<Sanpham> sanPhamList = sanPhamDAO.layDanhSachSanPham();
        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(this, R.layout.list_item_sanpham, sanPhamList);
        listViewSanPham = findViewById(R.id.listViewSanPham);
        listViewSanPham.setAdapter(sanPhamAdapter);

        // Xử lý sự kiện khi người dùng chọn một sản phẩm
        listViewSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sanpham selectedSanPham = (Sanpham) parent.getItemAtPosition(position);
                showDetailInformation(selectedSanPham);
            }

            private void showDetailInformation(Sanpham sanPham) {
                String detailText = "Tên sản phẩm: " + sanPham.getTenSP() + "\n"
                        + "Giá tiền: $" + sanPham.getGiaTien();

                Toast.makeText(MainActivity.this, detailText, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Hàm chuyển đổi hình ảnh thành mảng byte[]
    private byte[] convertImageToByteArray(int imageResId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}


