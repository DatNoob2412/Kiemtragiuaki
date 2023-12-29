package com.example.kiemtragiuaki;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<Sanpham> {

    private Context context;
    private int resource;
    private List<Sanpham> sanPhamList;

    public SanPhamAdapter(Context context, int resource, List<Sanpham> sanPhamList) {
        super(context, resource, sanPhamList);
        this.context = context;
        this.resource = resource;
        this.sanPhamList = sanPhamList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(resource, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageViewSanPham = rowView.findViewById(R.id.imageViewDetail);
            viewHolder.textViewTenSP = rowView.findViewById(R.id.textViewTenSPDetail);
            viewHolder.textViewGiaTien = rowView.findViewById(R.id.textViewGiaTienDetail);

            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

        Sanpham sanPham = sanPhamList.get(position);

        // Hiển thị hình ảnh từ mảng byte
        byte[] imageBytes = sanPham.getImage();
        if (imageBytes != null && imageBytes.length > 0) {
            holder.imageViewSanPham.setImageBitmap(convertByteArrayToBitmap(imageBytes));
        } else {
            // Nếu không có hình ảnh, có thể hiển thị một hình ảnh mặc định
            holder.imageViewSanPham.setImageResource(R.drawable.ic_launcher_foreground);
        }

        holder.textViewTenSP.setText(sanPham.getTenSP());
        holder.textViewGiaTien.setText("Giá tiền: $" + sanPham.getGiaTien());

        return rowView;
    }

    private static class ViewHolder {
        ImageView imageViewSanPham;
        TextView textViewTenSP;
        TextView textViewGiaTien;
    }

    // Hàm chuyển đổi mảng byte thành hình ảnh
    private Bitmap convertByteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}


