package com.slytherin.chickendinner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsPanel extends AppCompatActivity {

    Button clearFeedbacks, clearOrders, Deactivate;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_panel);

        clearOrders = (Button)findViewById(R.id.Btn_Settings_ClearOrders);
        clearFeedbacks = (Button)findViewById(R.id.Btn_Settings_ClearFeedbacks);
        Deactivate = (Button)findViewById(R.id.Btn_Settings_Deactivate);

        Deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("User");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(Common.currentUser.getPhone());
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(),"Account deleted!",Toast.LENGTH_LONG).show();

                        Intent signIn = new Intent(SettingsPanel.this, SignIn.class);
                        signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(signIn);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        clearOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("MyOrders");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("MyOrders").child(Common.currentUser.getPhone());
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(),"Clear Successfully!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        clearFeedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Feedback");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child(Common.currentUser.getPhone());
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(),"Clear Successfully!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
