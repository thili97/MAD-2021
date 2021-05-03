package com.slytherin.chickendinner;

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

public class AdminUpdateFood extends AppCompatActivity {

    EditText searchCode, ud1, ud2, ud3;
    Button Search, Update;

    DatabaseReference dbRef;

    FoodCategory fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_food);

        searchCode = (EditText) findViewById(R.id.update_code);
        ud1 = (EditText) findViewById(R.id.update_name);
        ud2 = (EditText) findViewById(R.id.update_price);
        ud3 = (EditText) findViewById(R.id.update_status);
        Search = (Button) findViewById(R.id.update_search);
        Update = (Button) findViewById(R.id.admin_btn_update);

        fc = new FoodCategory();

        Search.setOnClickListener(new View.OnClickListener() {
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
                        }else{
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

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Category");

                fc.setCode(searchCode.getText().toString().trim());
                fc.setName(ud1.getText().toString().trim());
                fc.setPrice(ud2.getText().toString().trim());
                fc.setStatus(ud3.getText().toString().trim());

                String f1 = ud1.getText().toString().trim();
                String f2 = ud2.getText().toString().trim();
                String f3 = ud3.getText().toString().trim();


                if(f1.isEmpty() || f2.isEmpty() || f3.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill all the fields!",Toast.LENGTH_LONG).show();
                }
                else if(!f3.matches("[0-9+.A-Za-z]+")) {
                    Toast.makeText(getApplicationContext(), "Invalid Food Price!",Toast.LENGTH_LONG).show();
                    ud2.setText(null);
                }
                else {
                    dbRef.child(searchCode.getText().toString().trim()).setValue(fc);
                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                    ud1.setText(null);
                    ud2.setText(null);
                    ud3.setText(null);

                }

            }
        });

    }
}
