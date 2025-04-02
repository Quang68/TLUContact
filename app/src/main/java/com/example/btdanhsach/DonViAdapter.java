package com.example.btdanhsach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btdanhsach.Activity.DonViActivity;
import com.example.btdanhsach.Chitiet.donvi_chitiet;
import com.example.btdanhsach.Contact.Donvi;

import java.util.List;

public class DonViAdapter extends RecyclerView.Adapter<DonViViewHolder> {
    private Context context;
    private List<Donvi> dsDonVi;

    public DonViAdapter(Context context, List<Donvi> dsDonVi) {
        this.context = context;
        this.dsDonVi = dsDonVi;
    }

    @NonNull
    @Override
    public DonViViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donvi, parent, false);
        return new DonViViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonViViewHolder holder, int position) {
        Donvi donvi = dsDonVi.get(position);

        Glide.with(holder.itemView.getContext())
                .load(donvi.getAvatar()) // Lấy ảnh đầu tiên trong danh sách
                .placeholder(R.drawable.avata) // Ảnh tạm khi load
                .error(R.drawable.avata) // Ảnh hiển thị nếu load lỗi
                .into(holder.imgAvatar);
        holder.tvName.setText(donvi.getName());
        holder.tvAddress.setText(donvi.getAddress());
        holder.tvSdt.setText(donvi.getSdt());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, donvi_chitiet.class);
            intent.putExtra("id", donvi.getId());
            intent.putExtra("name", donvi.getName());
            intent.putExtra("address", donvi.getAddress());
            intent.putExtra("sdt", donvi.getSdt());
            intent.putExtra("avatar", donvi.getAvatar());
            ((DonViActivity) context).startActivityForResult(intent, 200);
        });
    }

    @Override
    public int getItemCount() {
        return dsDonVi.size();
    }

    public void updateList(List<Donvi> newList) {
        this.dsDonVi = newList;
        notifyDataSetChanged();
    }

}
