package com.example.campusbite.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.campusbite.Adapter.CartAdapter;
import com.example.campusbite.Domain.Item;
import com.example.campusbite.Domain.foodDomain;
import com.example.campusbite.Helper.ManagementCart;
import com.example.campusbite.Interface.ChangeNumberItemsListner;
import com.example.campusbite.R;
import com.example.campusbite.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalItemTxt,totalNoItemTxt,timeTxt,dateTxt,totalFeeTxt,emptyTxt;
    private double tax=0;
    private ScrollView scrollView;

    ConstraintLayout orderBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,adminReference;
    FirebaseAuth auth;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        bottomNavigationView = findViewById(R.id.bn);
        bottomNavigationView.setSelectedItemId(R.id.b_cart);
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

        managementCart = new ManagementCart(this);

        initView();
        calculateCart();
        initList();

        orderBtn = findViewById(R.id.orderBtn);
        recyclerViewList=findViewById(R.id.view);
        auth=FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String uid =user.getUid();
        //String tt,it_f,tf,dt,tm,pc,it_n;
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<foodDomain> listFood = managementCart.getListCart();
                ArrayList<Item> historyList = new ArrayList<>();
                for (int i=0; i<listFood.size();i++){
                    String tt = listFood.get(i).getTitle();
                    String it_f = String.valueOf(listFood.get(i).getFee());
                    String it_no = String.valueOf(listFood.get(i).getNumberInCart());
                    String tf = String.valueOf((listFood.get(i).getNumberInCart() * listFood.get(i).getFee()));
                    String p = listFood.get(i).getPic();

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    String currentTime = dateFormat.format(calendar.getTime());
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                  //  historyList.add(new Item(tt,it_f,tf,currentDate,currentTime,p,it_no));

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("User");

                   // User UserInfoClass = new User(uname,email,mobile);

                    Item order_list = new Item(tt,it_f,tf,currentDate,currentTime,p,it_no);
                    databaseReference.child(uid).child("Orders").child(currentDate).child(currentTime).child(tt).setValue(order_list).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CartActivity.this,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    adminReference = firebaseDatabase.getReference("Admin").child("Orders");
                    adminReference.child(currentDate).child(currentTime).child(uid).child(tt).setValue(order_list).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(CartActivity.this, " data has been saved successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(), OrderSuccess.class);
                            startActivity(intent);
                            finish();
                        }
                    });


                }




            }
        });



    }

    private void initList() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListner() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty())
        {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else
        {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }

    private void calculateCart() {

        double total = managementCart.getTotalFee() ;
        int totalnoItems =managementCart.getToatalnoItems();
        int totalItem =managementCart.getListCart().size();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String currentTime = dateFormat.format(calendar.getTime());
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        totalNoItemTxt.setText(" "+totalnoItems);
        totalItemTxt.setText(" "+totalItem);
        timeTxt.setText(" "+currentTime);
        dateTxt.setText(" "+currentDate);
        totalFeeTxt.setText("₹" + total);
    }

    private void initView() {

        totalItemTxt = findViewById(R.id.totalItemTxt);
        totalNoItemTxt = findViewById(R.id.totalNoItemTxt);
        timeTxt = findViewById(R.id.timeTxt);
        dateTxt=findViewById(R.id.dateTxt);
        totalFeeTxt=findViewById(R.id.totalfeeTxt);
        recyclerViewList = findViewById(R.id.view);
        scrollView = findViewById(R.id.scrollView);
        emptyTxt = findViewById(R.id.emptyTxt);



    }

}