package com.example.btdanhsach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btdanhsach.Activity.CanBoActivity;
import com.example.btdanhsach.Activity.DonViActivity;
import com.example.btdanhsach.Contact.CanBo;
import com.example.btdanhsach.Contact.Donvi;

import java.util.List;

public class CanBoAdapter extends RecyclerView.Adapter<CanBoViewHolder>{
    private Context context;
    private List<CanBo> dsCanBo;
    // Khai báo thuộc tính Constractor

    public CanBoAdapter(Context context, List<CanBo> dsCanBo) {
        this.context = context;
        this.dsCanBo = dsCanBo;
    }

    @NonNull
    @Override
    // Hàm được gọi khi cần tạo 1 ViewHolder mới để hiển thị dữ liệu ( Lúc mới bắt đầu là rỗng)
    public CanBoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cbnv, parent, false);

        // Tạo mới và trả về cán bộ ViewHolder mới
        return new CanBoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CanBoViewHolder holder, int position) {
        CanBo canBo = dsCanBo.get(position);
        holder.imvAvatar.setImageResource(canBo.getAvatar());
        holder.txtName.setText(canBo.getName());
//        holder.txtChucvu.setText(canBo.getChucvu());
        holder.txtSdt.setText(canBo.getSdt());
        holder.txtEmail.setText(canBo.getEmail());
//        holder.txtDonvicongtac.setText(canBo.getDonvicongtac());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, canbo_chitiet.class); // name, chucvu, sdt, email, donvicongtac, id;
            intent.putExtra("id", canBo.getId());
            intent.putExtra("name", canBo.getName());
            intent.putExtra("chucvu", canBo.getChucvu());
            intent.putExtra("sdt", canBo.getSdt());
            intent.putExtra("email", canBo.getEmail());
            intent.putExtra("donvicongtac", canBo.getDonvicongtac());
            intent.putExtra("avatar", canBo.getAvatar());
            ((CanBoActivity) context).startActivityForResult(intent, 200);
        });
    }

    @Override
    public int getItemCount() {
        return dsCanBo.size();
    }
}
