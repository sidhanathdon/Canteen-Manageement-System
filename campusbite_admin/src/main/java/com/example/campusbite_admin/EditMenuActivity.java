package com.example.campusbite_admin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.campusbite_admin.Domains.NewMenuItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditMenuActivity extends AppCompatActivity {
    TextInputEditText eTitle,eRating,eDescription,eCost;
    TextView addToMenuBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_menu);

        eTitle = findViewById(R.id.eTitle);
        eRating = findViewById(R.id.eRating);
        eDescription = findViewById(R.id.eDescription);
        eCost = findViewById(R.id.eCost);

        String eT,eR,eD,eC;
        eT = eTitle.getText().toString();
        eR = eRating.getText().toString();
        eD = eDescription.getText().toString();
        eC = eCost.getText().toString();

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference().child("Admin").child("NewMenuItems");

        addToMenuBtn = findViewById(R.id.addToMenuBtn);
        addToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewMenuItem item = new NewMenuItem(eT,eR,eD,eC);
                databaseReference.child(eT).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(EditMenuActivity.this, "Mew Menu Item added to the list", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });




    }
}