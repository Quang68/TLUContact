package com.example.btdanhsach.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.btdanhsach.Activity.CanBoActivity;
import com.example.btdanhsach.Activity.DonViActivity;
import com.example.btdanhsach.Activity.SinhVienActivity;
import com.example.btdanhsach.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        // Sự kiện khi nhấn nút "Danh bạ đơn vị"
        binding.btnDonViHOME.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DonViActivity.class);
            startActivity(intent);
        });

        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid(); // Lấy UID của người dùng từ Firebase Authentication

            // 🔹 Truy vấn dữ liệu từ Firestore
            DocumentReference userRef = db.collection("users").document(userId);
            userRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    if (snapshot.exists()) {
                        // 🔹 Lấy dữ liệu từ Firestore
                        String role = snapshot.getString("role");
                        // Sự kiện khi nhấn nút "Danh bạ CBNV"
                        binding.btnCBNVHOME.setOnClickListener(v -> {
                            if (role.equals("Sinh viên")){
                                Toast.makeText(getActivity(), "Chức năng này chỉ dành cho CBGV", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent = new Intent(getActivity(), CanBoActivity.class);
                                startActivity(intent);
                            }
                        });


                    } else {
                        Toast.makeText(getActivity(), "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Chưa đăng nhập", Toast.LENGTH_SHORT).show();
        }

        // Sự kiện khi nhấn nút "Danh bạ CBNV"
        binding.btnStudentDirectory.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SinhVienActivity.class);
            startActivity(intent);
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
