package com.example.btdanhsach;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DonViViewHolder extends RecyclerView.ViewHolder {
    ImageView imgAvatar;
    TextView tvName, tvAddress, tvSdt;

    public DonViViewHolder(@NonNull View itemView) {
        super(itemView);
        imgAvatar = itemView.findViewById(R.id.imv_DonviItem);
        tvName = itemView.findViewById(R.id.txt_NameDViItem);
        tvAddress = itemView.findViewById(R.id.txt_Adressdv);
        tvSdt = itemView.findViewById(R.id.txt_sdtdv);
    }
}