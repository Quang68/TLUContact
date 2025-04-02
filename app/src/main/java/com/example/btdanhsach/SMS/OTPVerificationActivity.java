package com.example.btdanhsach.SMS;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btdanhsach.MainActivity;
import com.example.btdanhsach.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;

public class OTPVerificationActivity extends AppCompatActivity {
    private TextView txtEmail, txtResendOTP;
    private EditText edtOTP;
    private Button btnVerifyOTP;
    private FirebaseAuth auth;
    private String userEmail, generatedOTP;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        txtEmail = findViewById(R.id.txtEmailOTP);
        edtOTP = findViewById(R.id.edtOTP);
        txtResendOTP = findViewById(R.id.txtResendOTP);
        btnVerifyOTP = findViewById(R.id.btnVerifyOTP);
        auth = FirebaseAuth.getInstance();

        userEmail = getIntent().getStringExtra("userEmail");
        txtEmail.setText("Mã OTP đã gửi về: " + userEmail);

        sendOTPtoEmail();

        btnVerifyOTP.setOnClickListener(v -> {
            String enteredOTP = edtOTP.getText().toString().trim();
            if (enteredOTP.equals(generatedOTP)) {
                startActivity(new Intent(OTPVerificationActivity.this, MainActivity.class));
                finish();
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Mã OTP không chính xác!", Toast.LENGTH_SHORT).show();
            }
        });

        txtResendOTP.setOnClickListener(v -> sendOTPtoEmail());
    }

    private void sendOTPtoEmail() {
        generatedOTP = String.valueOf(new Random().nextInt(900000) + 100000);

        executor.execute(() -> {
            try {
                new MailSender().sendMail(userEmail, generatedOTP);
                runOnUiThread(() -> Toast.makeText(OTPVerificationActivity.this, "Mã OTP đã gửi về email!", Toast.LENGTH_LONG).show());
            } catch (MessagingException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(OTPVerificationActivity.this, "Gửi OTP thất bại!", Toast.LENGTH_LONG).show());
            }
        });
    }
}
