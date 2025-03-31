package com.example.btdanhsach;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btdanhsach.Contact.CanBo;

public class CanBoViewHolder extends RecyclerView.ViewHolder {
    ImageView imvAvatar;
    TextView txtName, txtChucvu, txtSdt, txtEmail, txtDonvicongtac;
    public CanBoViewHolder(@NonNull View itemView) { // Hàm khỏi tạo nhận vào itemView chứa các ảnh và tên
        super(itemView);
        imvAvatar = itemView.findViewById(R.id.imv_cbnv);
        txtName = itemView.findViewById(R.id.txt_name);
//        txtChucvu = itemView.findViewById(R.id.txt_chucvu);
        txtSdt = itemView.findViewById(R.id.txt_sdt);
        txtEmail = itemView.findViewById(R.id.txt_email);
//        txtDonvicongtac = itemView.findViewById(R.id.txt_donvicongtac);
    }
}
