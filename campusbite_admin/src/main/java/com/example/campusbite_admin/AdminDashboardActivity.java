package com.example.campusbite_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDashboardActivity extends AppCompatActivity {
    LinearLayout ordersBtn,userListBtn,OrderHistoryBtn,preOrderBtn;
    Button logoutBtn;
    TextView AdminName;
    FirebaseAuth mAuth;
    FirebaseUser fUser ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);

        ordersBtn = findViewById(R.id.OrdersBtn);
        ordersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TodaysOrdersActivity.class));
                finish();
            }
        });

        userListBtn= findViewById(R.id.UserListBtn);
        userListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), CustomerListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        OrderHistoryBtn= findViewById(R.id.OrderHistoryBtn);
        OrderHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), AllOrdersAcivity.class);
                startActivity(intent);
                finish();
            }
        });

        preOrderBtn = findViewById(R.id.PreOrderBtn);
        preOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), EditMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logoutBtn = findViewById(R.id.LogoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        AdminName = findViewById(R.id.adminName);
        mAuth=FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        String uid =fUser.getUid();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Admin").child("AdminInfo").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name =snapshot.child("adminName").getValue().toString();
                AdminName.setText("Hii "+name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}