package com.example.btdanhsach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btdanhsach.Activity.SinhVienActivity;
import com.example.btdanhsach.Chitiet.SinhVienChiTietActivity;
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

//         Load ảnh từ URL bằng Glide
        Glide.with(holder.itemView.getContext())
                .load(sinhvien.getAvatar()) // Lấy ảnh đầu tiên trong danh sách
                .placeholder(R.drawable.avata) // Ảnh tạm khi load
                .error(R.drawable.avata) // Ảnh hiển thị nếu load lỗi
                .into(holder.imvAvatar);

//        holder.imvAvatar.setImageResource(R.drawable.avata);
        holder.txtName.setText(sinhvien.getName());
        holder.txtSdt.setText(sinhvien.getSdt());
        holder.txtEmail.setText(sinhvien.getEmail());



        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SinhVienChiTietActivity.class); // name, chucvu, sdt, email, donvicongtac, id;
            intent.putExtra("id", sinhvien.getMsv());
            intent.putExtra("name", sinhvien.getName());
            intent.putExtra("sdt", sinhvien.getSdt());
            intent.putExtra("email", sinhvien.getEmail());
            intent.putExtra("diachi", sinhvien.getAddress());
            intent.putExtra("lop", sinhvien.getLop());
            intent.putExtra("avatar", sinhvien.getAvatar());
            ((SinhVienActivity) context).startActivityForResult(intent, 200);
        });
    }

    @Override
    public int getItemCount() {
        return dsSinhVien.size();
    }

    public void updateList(List<Sinhvien> newList) {
        this.dsSinhVien = newList;
        notifyDataSetChanged();
    }
}