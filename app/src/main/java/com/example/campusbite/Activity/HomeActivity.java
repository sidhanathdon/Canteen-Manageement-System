package com.example.campusbite.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.campusbite.Adapter.RecomendedAdapter;
import com.example.campusbite.Domain.foodDomain;
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

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView popularList,snacksList,drinkslist,bhajislist,lunchlist;
    RecyclerView.Adapter adapter1,adapter2,adapter3,adapter4,adapter5;

    TextView userName;
    FirebaseAuth mAuth;
    FirebaseUser fUser ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bn);
        bottomNavigationView.setSelectedItemId(R.id.b_home);
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
        userName = findViewById(R.id.userName);
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
                    userName.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recycleViewPopular();


    }
    private void recycleViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        popularList = findViewById(R.id.popularlist);
        popularList.setLayoutManager(linearLayoutManager);

        ArrayList<foodDomain>foodlist = new ArrayList<foodDomain>();
        foodlist.add(new foodDomain("Lassi","lassi","Lassi, a creamy, frothy yogurt-based drink, blended with water and various fruits or seasonings",40.0,4.5,20));
        foodlist.add(new foodDomain("Vada Pav","batawada","This is vada ",15.0,3.5,20));
        foodlist.add(new foodDomain("Samosa","samosa","This is samosa",15.0,4.5,20));
        foodlist.add(new foodDomain("Gobi Manchurian","gobi_gravy","Gobi Manchurian always fav",40.0,5.0,20));
        foodlist.add(new foodDomain("Fish Thali","fish_thali","This is a fish thali ",80.0,4.5,20));
        foodlist.add(new foodDomain("Veg Thali","veg_thali","This is veg thali",60.0,3.5,20));
        foodlist.add(new foodDomain("Chicken Biryani","non_veg_biryani","this is pizza",50.0,5.0,20));

        adapter1 = new RecomendedAdapter(foodlist);
        popularList.setAdapter(adapter1);


        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        snacksList=findViewById(R.id.snackslist);
        snacksList.setLayoutManager(linearLayoutManager2);

        ArrayList<foodDomain>snackslist = new ArrayList<foodDomain>();
        snackslist.add(new foodDomain("Samosa","samosa","This is samosa",15.0,4.0,20));
        snackslist.add(new foodDomain("Vada","batawada","This is Batatwada",15.0,4.5,20));
        snackslist.add(new foodDomain("Kappa","kappa","This is kappa",15.0,4.5,20));
        snackslist.add(new foodDomain("Poha","poha","This is poha",25.0,3.5,20));
        snackslist.add(new foodDomain("Dosa","dosa","This is dosa with chutney and sambar",30.0,4.5,20));
        snackslist.add(new foodDomain("Upma","upma","This is upma",20.0,5.0,20));

        adapter2 = new RecomendedAdapter(snackslist);
        snacksList.setAdapter(adapter2);


        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        drinkslist=findViewById(R.id.drinkslist);
        drinkslist.setLayoutManager(linearLayoutManager3);

        ArrayList<foodDomain>drinksList = new ArrayList<foodDomain>();
        drinksList.add(new foodDomain("Tea","tea","This is tea",15.0,4.0,20));
        drinksList.add(new foodDomain("Coffee","coffee","This is coffee",15.0,4.5,20));
        drinksList.add(new foodDomain("Lime Soda","lemon_soda","This is lime soda",20.0,4.5,20));
        drinksList.add(new foodDomain("Lime Water","lemon_water","This is lime water",25.0,3.5,20));
        drinksList.add(new foodDomain("Lassi","lassi","This is lassi",30.0,4.5,20));

       adapter3 = new RecomendedAdapter(drinksList);
       drinkslist.setAdapter(adapter3);


        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        bhajislist=findViewById(R.id.bhajislist);
        bhajislist.setLayoutManager(linearLayoutManager4);

        ArrayList<foodDomain>bhajisList = new ArrayList<foodDomain>();
        bhajisList.add(new foodDomain("Sukhi Bhaji","sukhi_bhaji","This is sukhi bhaji",15.0,4.0,20));
        bhajisList.add(new foodDomain("Special Bhaji","special","This is special bhaji",15.0,4.5,20));
        bhajisList.add(new foodDomain("Kurma Bhaji","kurma","This is kurma bhaji",20.0,4.5,20));
        bhajisList.add(new foodDomain("Matar Paneer","matar_panner","This is matar paneer masala",25.0,3.5,20));
        bhajisList.add(new foodDomain("Chicken With Gravy","chicken_gravy","This is chicken gravy",35.0,4.5,20));
        bhajisList.add(new foodDomain("Gobi Manchurian","gobi_gravy","This is gobi manchurian gravy",40.0,4.5,20));
        bhajisList.add(new foodDomain("Egg Masala","egg_curry","This is chicken gravy",30.0,4.5,20));

        adapter4 = new RecomendedAdapter(bhajisList);
        bhajislist.setAdapter(adapter4);


        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        lunchlist=findViewById(R.id.lunchlist);
        lunchlist.setLayoutManager(linearLayoutManager5);

        ArrayList<foodDomain>lunchList = new ArrayList<foodDomain>();
        lunchList.add(new foodDomain("Veg Fried Rice","veg_fried_rice","This is sukhi bhaji",40.0,4.0,20));
        lunchList.add(new foodDomain("Veg Biryani","veg_biryani","This is special bhaji",40.0,4.5,20));
        lunchList.add(new foodDomain("Veg Thali","veg_thali","This is kurma bhaji",70.0,4.5,20));
        lunchList.add(new foodDomain("Fish Thali","fish_thali","This is matar paneer masala",80.0,3.5,20));
        lunchList.add(new foodDomain("Chicken Thali","chicken_thali","This is chicken gravy",85.0,4.5,20));
        lunchList.add(new foodDomain("Chicken Biryani","non_veg_biryani","This is gobi manchurian gravy",80.0,4.5,20));
        lunchList.add(new foodDomain("Egg Masala","egg_curry","This is chicken gravy",30.0,4.5,20));

        adapter5 = new RecomendedAdapter(lunchList);
        lunchlist.setAdapter(adapter5);

    }
}