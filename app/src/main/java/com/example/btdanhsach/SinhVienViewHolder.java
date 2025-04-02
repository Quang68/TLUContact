package com.example.btdanhsach;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class SinhVienViewHolder extends RecyclerView.ViewHolder {
    ImageView imvAvatar;
    TextView txtName, txtSdt, txtEmail;
    public SinhVienViewHolder(@NonNull View itemView) { // Hàm khỏi tạo nhận vào itemView chứa các ảnh và tên
        super(itemView);
        imvAvatar = itemView.findViewById(R.id.imv_cbnv);
        txtName = itemView.findViewById(R.id.txt_name);
        txtSdt = itemView.findViewById(R.id.txt_sdt);
        txtEmail = itemView.findViewById(R.id.txt_email);
    }
}

