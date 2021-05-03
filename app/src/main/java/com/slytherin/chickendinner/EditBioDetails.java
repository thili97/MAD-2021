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

public class EditBioDetails extends AppCompatActivity {

    EditText PName, PEmail, PPhone, PPassword;
    Button UpdateBio;
    DatabaseReference dbRef;
    ChickenDinner std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bio_details);

        PName = (EditText)findViewById(R.id.userPro);
        PEmail = (EditText)findViewById(R.id.emailPro);
        PPhone = (EditText)findViewById(R.id.phonePro);
        PPassword = (EditText)findViewById(R.id.passwordPro);
        UpdateBio = (Button)findViewById(R.id.btn_UpdateBioDetails);

        std = new ChickenDinner();

        DisplayBioDetails();

        UpdateBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(Common.currentUser.getPhone());

                std.setPhone(PPhone.getText().toString().trim());
                std.setName(PName.getText().toString().trim());
                std.setPassword(PPassword.getText().toString().trim());
                std.setEmail(PEmail.getText().toString().trim());

                String UphoneID = PPhone.getText().toString().trim();
                String UnameID = PName.getText().toString().trim();
                String UpasswordID = PPassword.getText().toString().trim();
                String UemailID = PEmail.getText().toString().trim();

                if(UphoneID.isEmpty() || UnameID.isEmpty() || UpasswordID.isEmpty() || UemailID.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields!",Toast.LENGTH_LONG).show();
                }
                else {
                    if (!UphoneID.matches("[0-9]+")) {
                        PPhone.setText(null);
                        PPhone.setHint("Invalid Phone Number");
                        Toast.makeText(getApplicationContext(), "Invalid Phone Number!", Toast.LENGTH_LONG).show();
                    } else if (!UnameID.matches("[A-Za-z ]+")) {
                        PName.setText(null);
                        PName.setHint("Invalid Name");
                        Toast.makeText(getApplicationContext(), "Invalid Name!", Toast.LENGTH_LONG).show();
                    } else if (!UemailID.matches("[a-z.@0-9]+")) {
                        PEmail.setText(null);
                        PEmail.setHint("Invalid Email");
                        Toast.makeText(getApplicationContext(), "Invalid Email!", Toast.LENGTH_LONG).show();
                    } else {
                        dbRef.child(UphoneID).setValue(std);
                        Toast.makeText(getApplicationContext(), "Profile Saved Successfully!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void DisplayBioDetails() {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child("0769933270");
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
