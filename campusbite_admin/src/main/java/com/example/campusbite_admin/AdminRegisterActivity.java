package com.example.campusbite_admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminRegisterActivity extends AppCompatActivity {
    Button registerBtn;
    ProgressBar progressBar;
    TextView loginNow;
    TextInputEditText editTextEmail,editTextPassword,editTextFullName,editTextMobileNo;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_register);

        mAuth = FirebaseAuth.getInstance();


        registerBtn=findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progressBar);
        loginNow= findViewById(R.id.loginNow);
        editTextFullName= findViewById(R.id.fullName);
        editTextMobileNo=findViewById(R.id.mobileNo);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String email, password,mobile;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                mobile = String.valueOf(editTextMobileNo.getText().toString());
                String uname;
                uname = String.valueOf(editTextFullName.getText().toString());


                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("Admin").child("AdminInfo");


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(AdminRegisterActivity.this, " Enter Your Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(AdminRegisterActivity.this, "Create the Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AdminRegisterActivity.this, "Authentication is successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ProfileSaveAdminActivity.class);
                            intent.putExtra("uname",uname);
                            intent.putExtra("email",email);
                            intent.putExtra("mno",mobile);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(AdminRegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




    }
}