package com.slytherin.chickendinner;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CDFoodMenuList extends AppCompatActivity {

    ListView CDFoodMenuList;

    FirebaseDatabase fDatabase;
    DatabaseReference dbRef;

    ArrayList<String> list1;
    ArrayAdapter<String> adapter1;
    HomeFoods homeFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdfood_menu_list);

        CDFoodMenuList = (ListView)findViewById(R.id.CD_FOOD_MENU);

        homeFoods = new HomeFoods();

        fDatabase = FirebaseDatabase.getInstance();

        dbRef = fDatabase.getReference("Category");
        list1 = new ArrayList<>();

        adapter1 = new ArrayAdapter<String>(this,R.layout.chicken_dinner_home_foodlist,R.id.foodCode_information, list1);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    homeFoods = ds.getValue(HomeFoods.class);
                    list1.add("Food Code: "+homeFoods.getCode().toString()+"\nFood Name: "+homeFoods.getName().toString()+"\nPrice: "+homeFoods.getPrice().toString()+"\nStatus: "+homeFoods.getStatus().toString());
                }
                CDFoodMenuList.setAdapter(adapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
