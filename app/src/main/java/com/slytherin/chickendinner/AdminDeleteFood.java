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

public class AdminDeleteFood extends AppCompatActivity {

    EditText searchCode, ud1, ud2, ud3;
    Button delete, search;

    DatabaseReference dbRef;

    FoodCategory fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_food);

        searchCode = (EditText) findViewById(R.id.delete_code);
        ud1 = (EditText) findViewById(R.id.delete_name);
        ud2 = (EditText) findViewById(R.id.delete_price);
        ud3 = (EditText) findViewById(R.id.delete_status);
        delete = (Button) findViewById(R.id.admin_btn_delete);
        search = (Button) findViewById(R.id.delete_search);

        fc = new FoodCategory();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datasearch = searchCode.getText().toString().trim();
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Category").child(datasearch);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            ud1.setText(dataSnapshot.child("name").getValue().toString());
                            ud2.setText(dataSnapshot.child("price").getValue().toString());
                            ud3.setText(dataSnapshot.child("status").getValue().toString());

                        }
                        else{
                            searchCode.setText(null);
                            ud1.setText(null);
                            ud2.setText(null);
                            ud3.setText(null);
                            Toast.makeText(getApplicationContext(),"No Source",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String datasearch = searchCode.getText().toString().trim();
                    DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Category").child(datasearch);
                    delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String datasearch = searchCode.getText().toString().trim();
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Category").child(datasearch);
                            dbRef.removeValue();

                            Toast.makeText(getApplicationContext(), "Food item deleted!", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
        });
    }



}
