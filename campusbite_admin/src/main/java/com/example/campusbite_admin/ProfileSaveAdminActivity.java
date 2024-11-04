package com.example.campusbite_admin;

import static com.example.campusbite_admin.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.campusbite_admin.Domains.AdminInfo;
import com.example.campusbite_admin.Domains.Customers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileSaveAdminActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button saveBtn;
    TextInputEditText editTextEmail,editTextPassword,editTextFullName,editTextMobileNo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser fUser ;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_save_admin);



        auth=FirebaseAuth.getInstance();

        user=auth.getCurrentUser();
        editTextFullName= findViewById(R.id.fullName);
        editTextMobileNo=findViewById(R.id.mobileNo);
        editTextEmail=findViewById(R.id.email);
        saveBtn=findViewById(R.id.saverBtn);


        fUser = auth.getCurrentUser();

        String nm, em,ph;
        nm = getIntent().getStringExtra("uname");
        em =  getIntent().getStringExtra("email");
        ph =  getIntent().getStringExtra("mno");

        editTextFullName.setText(nm);
        editTextMobileNo.setText(ph);
        editTextEmail.setText(em);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email,mobile;
                email = String.valueOf(editTextEmail.getText());
                mobile = String.valueOf(editTextMobileNo.getText().toString());
                String uname;
                uname = String.valueOf(editTextFullName.getText().toString());
                String uid =fUser.getUid();


                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Admin").child("AdminInfo");

                AdminInfo UserInfoClass = new AdminInfo(uname,email,mobile);
                databaseReference.child(uid).setValue(UserInfoClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ProfileSaveAdminActivity.this, "User data has been saved successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdminDashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileSaveAdminActivity.this,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }


}