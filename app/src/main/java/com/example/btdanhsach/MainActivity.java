package com.example.btdanhsach;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.btdanhsach.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        loadUserInfo(navigationView);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> handleNavigationItemSelected(item, navController, drawer));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private boolean handleNavigationItemSelected(@NonNull MenuItem item, NavController navController, DrawerLayout drawer) {
        int id = item.getItemId();

        if (id == R.id.nav_home || id == R.id.nav_gallery || id == R.id.nav_slideshow) {
            navController.navigate(id);
        } else if (id == R.id.nav_logout) {
            showLogoutDialog();
        }

        drawer.closeDrawers();
        return true;
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                .setPositiveButton("Đăng xuất", (dialog, which) -> {
                    auth.signOut();
                    Intent intent = new Intent(this, DangNhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
    private void loadUserInfo(NavigationView navigationView) {
        // 🔹 Lấy Header View từ NavigationView
        View headerView = navigationView.getHeaderView(0);

        // 🔹 Tìm TextView trong Header
        TextView nameTextView = headerView.findViewById(R.id.NameNAV);
        TextView emailTextView = headerView.findViewById(R.id.EmailView);

        // 🔹 Lấy thông tin người dùng từ Firebase Authentication
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
                        String name = snapshot.getString("fullName");
                        String email = snapshot.getString("email");

                        // 🔹 Cập nhật UI
                        nameTextView.setText(name != null ? name : "Chưa cập nhật");
                        emailTextView.setText(email != null ? email : "Chưa cập nhật");
                    } else {
                        nameTextView.setText("Không tìm thấy dữ liệu");
                        emailTextView.setText("Không tìm thấy dữ liệu");
                    }
                } else {
                    nameTextView.setText("Lỗi tải dữ liệu");
                    emailTextView.setText("Lỗi tải dữ liệu");
                }
            });
        } else {
            nameTextView.setText("Chưa đăng nhập");
            emailTextView.setText("Chưa đăng nhập");
        }
    }

}
