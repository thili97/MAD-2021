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

public class Orders extends AppCompatActivity {

    ListView Olistview;

    FirebaseDatabase database;
    DatabaseReference dbRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    Common common;
    MyOrders myOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        myOrders = new MyOrders();
        common = new Common();

        Olistview = (ListView)findViewById(R.id.OrderlistView);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("MyOrders");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.my_order_list, R.id.order_information, list);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    myOrders = ds.getValue(MyOrders.class);
                    list.add("Order: "+myOrders.getOfname()+"\nOrder Food Item Code: "+myOrders.getOfcode()+"\nQuantity: "+myOrders.getOfqty()+"\nAddress: "+myOrders.getOfaddress());
                }
                Olistview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
