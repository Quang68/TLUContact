package com.example.btdanhsach;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btdanhsach.SMS.OTPVerificationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {

    private EditText edtIDMSVorMACB, edtFullName, edtEmail, edtPassword, edtConfirmPassword;
    private Button btnSignUp;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky);

        // Ánh xạ các view
        edtIDMSVorMACB = findViewById(R.id.edtIDMSVorMACB);
        edtFullName = findViewById(R.id.edtFullNameDK);
        edtEmail = findViewById(R.id.edtEmailDK);
        edtPassword = findViewById(R.id.edtPasswordDK);
        edtConfirmPassword = findViewById(R.id.edtConfirmPasswordDK);
        btnSignUp = findViewById(R.id.btnSignUpDK);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnSignUp.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String id = edtIDMSVorMACB.getText().toString().trim();
        String fullName = edtFullName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra định dạng email
        String role;
        if (email.endsWith("@.tlu.edu.vn")) {
            role = "CBGV";
        } else if (email.endsWith("@e.tlu.edu.vn")) {
            role = "Sinh viên";
        } else {
            Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Đăng ký với Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            saveUserToFirestore(userId, id, fullName, email, role);
                        }
                    } else {
                        Toast.makeText(DangKy.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserToFirestore(String userId, String id, String fullName, String email, String role) {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", userId);
        user.put("id", id);
        user.put("fullName", fullName);
        user.put("email", email);
        user.put("role", role);
        user.put("photoURL", ""); // Có thể cập nhật sau
        user.put("phoneNumber", ""); // Có thể cập nhật sau

        // 🔹 Lưu vào collection "users" (để quản lý chung)
        db.collection("users").document(userId).set(user);

        // 🔹 Lưu vào bảng "students" hoặc "staff" tùy theo vai trò
        String collectionName = role.equals("Sinh viên") ? "students" : "staff";
        if(collectionName.equals("students")) {
            user.put("lop", "");
            user.put("address", "");
        } else {
            user.put("position", "");
            user.put("department", "");
        }

        db.collection(collectionName).document(userId)
                .set(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(DangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangKy.this, OTPVerificationActivity.class);
                        intent.putExtra("userEmail", email);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(DangKy.this, "Lỗi lưu dữ liệu: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
