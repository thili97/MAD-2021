package com.slytherin.chickendinner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DeleteOrder extends AppCompatActivity {
    EditText SCode,deQty;
    EditText deName,deAddress ;
    Button deleteOrder, searchOrder;

    DatabaseReference databaseReference;

    FoodCategory fc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_order);

        SCode = (EditText) findViewById(R.id.del_code);
        deName = (EditText) findViewById(R.id.del_name);
        deQty = (EditText) findViewById(R.id.del_quantity);
        deAddress = (EditText) findViewById(R.id.del_address);
        deleteOrder = (Button) findViewById(R.id.btn_delete);
        searchOrder = (Button) findViewById(R.id.del_search);

        fc = new FoodCategory();
        searchOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DSearch = SCode.getText().toString().trim();
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("MyOrders").child(DSearch);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            deName.setText(dataSnapshot.child("order").getValue().toString());


                        }
                        else{
                            SCode.setText(null);
                            deName.setText(null);
                            Toast.makeText(getApplicationContext(),"No Values",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String DSearch = SCode.getText().toString().trim();
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("MyOrders").child(DSearch);
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String datasearch = SCode.getText().toString().trim();
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("MyOrders").child(datasearch);
                        databaseReference.removeValue();

                        Toast.makeText(getApplicationContext(), "Your Order deleted!", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }



}
