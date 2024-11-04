package com.example.campusbite_admin;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusbite_admin.Adapter.FreshOrdersAdapter;
import com.example.campusbite_admin.Domains.FreshItems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TodaysOrdersActivity extends AppCompatActivity {
    RecyclerView todayOrderList;
    FirebaseDatabase firebaseDatabase,fd;
    DatabaseReference databaseReference,customer_reference;
    String sT,sIT_f,sIt_no,sT_f,sdt,sTm,sP,cN,c_id;
    ArrayList freshItems ;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todays_orders);

        todayOrderList= findViewById(R.id.todayOrderList);
        firebaseDatabase= FirebaseDatabase.getInstance();
        String todayDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        databaseReference=firebaseDatabase.getReference().child("Admin").child("Orders").child(todayDate);
        fd = FirebaseDatabase.getInstance();
        customer_reference = fd.getReference().child("User");
        freshItems = new ArrayList<FreshItems>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot timeSnapshot:snapshot.getChildren()){

                    for (DataSnapshot userSnapshot:timeSnapshot.getChildren()){
                       /* c_id = userSnapshot.getKey();
                        customer_reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                cN = snapshot1.child(c_id).child("fullName").getValue().toString();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });*/
                        for(DataSnapshot titleSnapshot:userSnapshot.getChildren()){

                            sT = titleSnapshot.child("oh_title").getValue().toString();
                            sIT_f = titleSnapshot.child("oh_itemFee").getValue().toString();
                            sIt_no = titleSnapshot.child("og_itemNo").getValue().toString();
                            sT_f = titleSnapshot.child("oh_totalFee").getValue().toString();
                            sdt = titleSnapshot.child("oh_date").getValue().toString();
                            sTm= titleSnapshot.child("oh_time").getValue().toString();
                            sP = titleSnapshot.child("oh_pic").getValue().toString();
                            cN = userSnapshot.getKey();

                            customer_reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot nSnapshot) {
                                    c_id = nSnapshot.child(cN).child("fullName").getValue().toString();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                            freshItems.add(new FreshItems(sT,sIT_f,sT_f,sdt,sTm,sP,sIt_no,c_id));

                        }
                    }
                }
                todayOrderList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                todayOrderList.setAdapter(new FreshOrdersAdapter(getApplicationContext(),freshItems));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}