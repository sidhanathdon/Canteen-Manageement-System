package com.example.campusbite.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.campusbite.Login;
import com.example.campusbite.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView Uname,Uphone,Uemail;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser fUser ;
    Button logoutBtn,editBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigationView = findViewById(R.id.bn);
        bottomNavigationView.setSelectedItemId(R.id.b_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.b_home)
                {
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(id == R.id.b_order){
                    startActivity(new Intent(getApplicationContext(),OrderHistoryActivity.class));
                    overridePendingTransition(0,0);
                    return  true;}
                else if(id == R.id.b_about){
                    startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(id == R.id.b_cart){
                    startActivity(new Intent(getApplicationContext(), CartActivity.class));
                    overridePendingTransition(0,0);
                    return  true;

                }
                else {
                    return true;
                }


            }
        });

        logoutBtn=findViewById(R.id.logoutBtn);
        editBtn= findViewById(R.id.editBtn);
        Uname=findViewById(R.id.UName);
        Uphone=findViewById(R.id.UPhone);
        Uemail=findViewById(R.id.UEmail);



        mAuth=FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        String uid =fUser.getUid();


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("User").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ss:snapshot.getChildren())
                {
                    String name =snapshot.child("fullName").getValue().toString();
                    Uname.setText(name);
                    String email=snapshot.child("email").getValue().toString();
                    Uemail.setText(email);
                    String phone =snapshot.child("mobile").getValue().toString();
                    Uphone.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ProfileSaveActivity.class);
                String uname,email,mobile;
                uname=Uname.getText().toString();
                email=Uemail.getText().toString();
                mobile=Uphone.getText().toString();
                intent.putExtra("uname",uname);
                intent.putExtra("email",email);
                intent.putExtra("mno",mobile);
                startActivity(intent);
                finish();
            }
        });

    }
}