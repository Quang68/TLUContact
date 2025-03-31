package com.example.btdanhsach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btdanhsach.Activity.CanBoActivity;
import com.example.btdanhsach.Contact.CanBo;
import com.example.btdanhsach.Contact.Sinhvien;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienViewHolder>{
    private Context context;
    private List<Sinhvien> dsSinhVien;
    // Khai báo thuộc tính Constractor

    public SinhVienAdapter(Context context, List<Sinhvien> dsSinhVien) {
        this.context = context;
        this.dsSinhVien = dsSinhVien;
    }

    @NonNull
    @Override
    // Hàm được gọi khi cần tạo 1 ViewHolder mới để hiển thị dữ liệu ( Lúc mới bắt đầu là rỗng)
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cbnv, parent, false);

        // Tạo mới và trả về cán bộ ViewHolder mới
        return new SinhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
        Sinhvien sinhvien = dsSinhVien.get(position);
        holder.imvAvatar.setImageResource(sinhvien.getAvatar());
        if (sinhvien.getAvatar() != 0) {
            holder.imvAvatar.setImageResource(sinhvien.getAvatar());
        } else {
            holder.imvAvatar.setImageResource(R.drawable.avata); // Hình mặc định
        }
        holder.txtName.setText(sinhvien.getName());
//        holder.txtChucvu.setText(canBo.getChucvu());
        holder.txtSdt.setText(sinhvien.getSdt());
        holder.txtEmail.setText(sinhvien.getEmail());
//        holder.txtDonvicongtac.sinhvien(canBo.getDonvicongtac());



        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, canbo_chitiet.class); // name, chucvu, sdt, email, donvicongtac, id;
            intent.putExtra("msv", sinhvien.getMsv());
            intent.putExtra("name", sinhvien.getName());
            intent.putExtra("sdt", sinhvien.getSdt());
            intent.putExtra("email", sinhvien.getEmail());
            intent.putExtra("address", sinhvien.getAddress());
            intent.putExtra("lop", sinhvien.getLop());
            intent.putExtra("avatar", sinhvien.getAvatar());
            ((CanBoActivity) context).startActivityForResult(intent, 200);
        });
    }

    @Override
    public int getItemCount() {
        return dsSinhVien.size();
    }
}