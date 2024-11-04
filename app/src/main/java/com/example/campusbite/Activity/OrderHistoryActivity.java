package com.example.campusbite.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.campusbite.Adapter.HistAdapter;
import com.example.campusbite.Domain.Item;
import com.example.campusbite.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView rv;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView size;
    ArrayList orders;
    TextView textView2;
    String sT,sIT_f,sIt_no,sT_f,sdt,sTm,sP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        bottomNavigationView = findViewById(R.id.bn);
        bottomNavigationView.setSelectedItemId(R.id.b_order);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.b_cart)
                {
                    startActivity(new Intent(getApplicationContext(),CartActivity.class));
                    overridePendingTransition(0,0);
                    return  true;

                }
                else if(id == R.id.b_home){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(id == R.id.b_about){
                    startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(id == R.id.b_profile){
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }
                else {
                    return true;
                }
            }
        });

        rv = findViewById(R.id.rv);

        auth= FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String uid =user.getUid();


        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("User").child(uid).child("Orders");
        orders = new ArrayList<Item>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dateSnapshot:snapshot.getChildren())
                {

                    for (DataSnapshot timeSnapshot:dateSnapshot.getChildren()){

                        for (DataSnapshot titleSnapshot:timeSnapshot.getChildren()){

                            sT = titleSnapshot.child("oh_title").getValue().toString();
                            sIT_f = titleSnapshot.child("oh_itemFee").getValue().toString();
                            sIt_no = titleSnapshot.child("og_itemNo").getValue().toString();
                            sT_f = titleSnapshot.child("oh_totalFee").getValue().toString();
                            sdt = titleSnapshot.child("oh_date").getValue().toString();
                            sTm= titleSnapshot.child("oh_time").getValue().toString();
                            sP = titleSnapshot.child("oh_pic").getValue().toString();
                            orders.add((new Item(sT,sIT_f,sT_f,sdt,sTm,sP,sIt_no)));


                        }

                    }
                }
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv.setAdapter(new HistAdapter(getApplicationContext(),orders));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderHistoryActivity.this, "error", Toast.LENGTH_SHORT).show();

            }



        });

       // textView2= findViewById(R.id.textView2);






       /* List<Item> items = new ArrayList<Item>();
        items.add(new Item("hello","20","40","5/5/5","11:11","matar_panner","2"));
        items.add(new Item("hello","20","40","5/5/5","11:11","matar_panner","2"));
        items.add(new Item("hello","20","40","5/5/5","11:11","matar_panner","2"));*/


       /* size = findViewById(R.id.size);
        int a = items.size();
        size.setText(a);*/

    }
}