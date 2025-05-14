package com.example.projectltddclother.Activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectltddclother.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {
    private TextView txtUserName, txtEmail, txtPhone, txtLocation, txtDob, txtOccupation;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        initViews();
        loadUserProfile();
    }

    private void initViews() {
        txtUserName = findViewById(R.id.txtUserName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtLocation = findViewById(R.id.txtLocation);
        txtDob = findViewById(R.id.txtDob);
        txtOccupation = findViewById(R.id.txtOccupation);
    }

    private void loadUserProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "Không tìm thấy người dùng", Toast.LENGTH_SHORT).show();
            return;
        }

        String uid = user.getUid();

        db.collection("users").document(uid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        txtEmail.setText(user.getEmail());
                        txtUserName.setText(documentSnapshot.getString("fullName"));
                        txtPhone.setText(documentSnapshot.getString("phone"));
                        txtLocation.setText(documentSnapshot.getString("location"));
                        txtDob.setText(documentSnapshot.getString("dob"));
                        txtOccupation.setText(documentSnapshot.getString("occupation"));
                    } else {
                        Toast.makeText(this, "Không có dữ liệu người dùng", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
