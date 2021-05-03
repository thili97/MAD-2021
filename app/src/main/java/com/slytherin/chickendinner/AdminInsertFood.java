package com.slytherin.chickendinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminInsertFood extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    Button insert;

    DatabaseReference dbRef;
    FoodCategory fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert_food);

        ed1 = (EditText) findViewById(R.id.insert_code);
        ed2 = (EditText) findViewById(R.id.insert_name);
        ed3 = (EditText) findViewById(R.id.insert_price);
        ed4 = (EditText) findViewById(R.id.insert_status);
        insert = (Button) findViewById(R.id.admin_btn_insert);

        fc = new FoodCategory();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Category");

                fc.setCode(ed1.getText().toString().trim());
                fc.setName(ed2.getText().toString().trim());
                fc.setPrice(ed3.getText().toString().trim());
                fc.setStatus(ed4.getText().toString().trim());

                String f1 = ed1.getText().toString().trim();
                String f2 = ed2.getText().toString().trim();
                String f3 = ed3.getText().toString().trim();
                String f4 = ed4.getText().toString().trim();

                if(f1.isEmpty() || f2.isEmpty() || f3.isEmpty() || f4.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill all the fields!",Toast.LENGTH_LONG).show();
                }
                else if(!f1.matches("[CD0-9]+")) {
                    Toast.makeText(getApplicationContext(), "Invalid Food Cord!",Toast.LENGTH_LONG).show();
                    ed1.setText(null);
                }
                else if(!f3.matches("[0-9+.A-Za-z]+")) {
                    Toast.makeText(getApplicationContext(), "Invalid Food Price!",Toast.LENGTH_LONG).show();
                    ed3.setText(null);
                }
                else {
                    dbRef.child(ed1.getText().toString().trim()).setValue(fc);
                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                    ed1.setText(null);
                    ed2.setText(null);
                    ed3.setText(null);
                    ed4.setText(null);
                }

            }
        });

    }
}
