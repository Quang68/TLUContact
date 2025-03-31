package com.example.btdanhsach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btdanhsach.Activity.DonViActivity;
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
        holder.imgAvatar.setImageResource(donvi.getAvatar());
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

}
