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

import static com.slytherin.chickendinner.Common.currentUser;

public class Profile extends AppCompatActivity {

    EditText PName, PEmail, PPhone, PPassword;
    Button UpdateBio, deleteAcc;
    DatabaseReference dbRef;
    ChickenDinner std;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        PName = (EditText)findViewById(R.id.userPro);
        PEmail = (EditText)findViewById(R.id.emailPro);
        PPhone = (EditText)findViewById(R.id.phonePro);
        PPassword = (EditText)findViewById(R.id.passwordPro);
        UpdateBio = (Button)findViewById(R.id.btn_EditBioDetails);
        deleteAcc = (Button)findViewById(R.id.btn_RemoveProfile);

        std = new ChickenDinner();

        DisplayBioDetails();

        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settings = new Intent(Profile.this, SettingsPanel.class);
                startActivity(Settings);
            }
        });

        UpdateBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIntent = new Intent(Profile.this, EditBioDetails.class);
                startActivity(signIntent);
            }
        });

    }

    private void DisplayBioDetails() {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child(Common.currentUser.getPhone());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    PName.setText(dataSnapshot.child("name").getValue().toString());
                    PEmail.setText(dataSnapshot.child("email").getValue().toString());
                    PPhone.setText(dataSnapshot.child("phone").getValue().toString());
                    PPassword.setText(dataSnapshot.child("password").getValue().toString());
                }else{
                    Toast.makeText(getApplicationContext(),"No Source",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
