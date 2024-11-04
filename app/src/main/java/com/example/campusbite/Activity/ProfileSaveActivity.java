package com.example.campusbite.Activity;
import static com.example.campusbite.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.campusbite.Login;
import com.example.campusbite.R;
import com.example.campusbite.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileSaveActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button logoutBtn,saveBtn;
    BottomNavigationView bottomNavigationView;
    TextInputEditText editTextEmail,editTextPassword,editTextFullName,editTextMobileNo;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser fUser ;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_profile_save);

        bottomNavigationView = findViewById(R.id.bn);
        bottomNavigationView.setSelectedItemId(R.id.b_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id== R.id.b_cart)
                {
                    startActivity(new Intent(getApplicationContext(),CartActivity.class));
                    overridePendingTransition(0,0);
                    return  true;

                }
                else if(id == R.id.b_order){
                    startActivity(new Intent(getApplicationContext(),OrderHistoryActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(id == R.id.b_about){
                    startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                    overridePendingTransition(0,0);

                    return  true;
                }
                else if(id == R.id.b_home){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(0,0);
                    return  true;

                }
                else {
                    return true;
                }


            }
        });

        auth=FirebaseAuth.getInstance();
        logoutBtn=findViewById(id.logoutBtn);
        user=auth.getCurrentUser();
        editTextFullName= findViewById(R.id.fullName);
        editTextMobileNo=findViewById(R.id.mobileNo);
        editTextEmail=findViewById(R.id.email);
        saveBtn=findViewById(id.saverBtn);


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
                databaseReference = firebaseDatabase.getReference("User");

                User UserInfoClass = new User(uname,email,mobile);
                databaseReference.child(uid).setValue(UserInfoClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ProfileSaveActivity.this, "User data has been saved successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileSaveActivity.this,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
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
    }


}