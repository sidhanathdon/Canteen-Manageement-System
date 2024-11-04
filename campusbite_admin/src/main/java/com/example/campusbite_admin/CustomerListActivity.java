package com.example.campusbite_admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusbite_admin.Adapter.CustomerAdapter;
import com.example.campusbite_admin.Adapter.FreshOrdersAdapter;
import com.example.campusbite_admin.Domains.Customers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerListActivity extends AppCompatActivity {

    RecyclerView customerList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String i,n,e,p;
    ArrayList customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_list);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("User");
        customers= new ArrayList<Customers>();
        customerList= findViewById(R.id.customerList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot customersSnapshot:snapshot.getChildren()){
                    n =customersSnapshot.child("fullName").getValue().toString();
                    e=customersSnapshot.child("email").getValue().toString();
                    p =customersSnapshot.child("mobile").getValue().toString();
                    i = customersSnapshot.getKey().toString();
                    customers.add(new Customers(i,n,e,p));
                }
                customerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                customerList.setAdapter(new CustomerAdapter(getApplicationContext(),customers));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       /* customers.add(new Customers("nishita","n@gmail.com","76556789"));
        customerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        customerList.setAdapter(new CustomerAdapter(getApplicationContext(),customers));*/



    }
}