package com.example.btdanhsach;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btdanhsach.Contact.Donvi;

public class update_donvi extends AppCompatActivity {
    EditText txtName, txtAddress, txtSdt, txtId;
    Button btnUpdate;
    Context context = this;
    DonViDAO dao;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_donvi);

        txtName = findViewById(R.id.edit_namesua);
        txtAddress = findViewById(R.id.edit_addresssua);
        txtSdt = findViewById(R.id.edit_sdtsua);
        txtId = findViewById(R.id.edit_IDsua);
        btnUpdate = findViewById(R.id.btn_update);

        dao = new DonViDAO(context);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String sdt = intent.getStringExtra("sdt");

        txtId.setText(id);
        txtId.setEnabled(false);
        txtName.setText(name);
        txtAddress.setText(address);
        txtSdt.setText(sdt);

        btnUpdate.setOnClickListener(v -> {
            String id1 = txtId.getText().toString().trim();
            String name1 = txtName.getText().toString().trim();
            String address1 = txtAddress.getText().toString().trim();
            String sdt1 = txtSdt.getText().toString().trim();

            if (id1.isEmpty() || name1.isEmpty() || address1.isEmpty() || sdt1.isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            Donvi donvi = new Donvi(id1, address1, name1, R.drawable.avata, sdt1);

            if (dao.update(donvi) > 0) {
                Toast.makeText(context, "Cập nhật đơn vị thành công!", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("id", id1);
                returnIntent.putExtra("name", name1);
                returnIntent.putExtra("address", address1);
                returnIntent.putExtra("sdt", sdt1);
                setResult(RESULT_OK, returnIntent);
                finish();
            }else{
                Toast.makeText(context, "Cập nhật đơn vị thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}