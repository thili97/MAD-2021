package com.slytherin.chickendinner;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {

    EditText phonesignup, namesignup, passwordsignup, emailsignup;
    Button btnSignup;

    DatabaseReference dbRef;

    ChickenDinner ckdn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phonesignup = (EditText)findViewById(R.id.edit_PhoneUp);
        namesignup = (EditText)findViewById(R.id.edit_NameUp);
        passwordsignup = (EditText)findViewById(R.id.edit_PasswordUp);
        emailsignup = (EditText)findViewById(R.id.edit_email);
        btnSignup = (Button)findViewById(R.id.btn_signUp);

        ckdn = new ChickenDinner();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                mDialog.dismiss();
                dbRef = FirebaseDatabase.getInstance().getReference().child("User");

                ckdn.setPhone(phonesignup.getText().toString().trim());
                ckdn.setName(namesignup.getText().toString().trim());
                ckdn.setPassword(passwordsignup.getText().toString().trim());
                ckdn.setEmail(emailsignup.getText().toString().trim());

                String UphoneID = phonesignup.getText().toString().trim();
                String UnameID = namesignup.getText().toString().trim();
                String UpasswordID = passwordsignup.getText().toString().trim();
                String UemailID = emailsignup.getText().toString().trim();

                if(UphoneID.isEmpty() || UnameID.isEmpty() || UpasswordID.isEmpty() || UemailID.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields!",Toast.LENGTH_LONG).show();
                }
                else {
                    if (!UphoneID.matches("[0-9]+")) {
                        phonesignup.setText(null);
                        phonesignup.setHint("Invalid Phone Number");
                        Toast.makeText(getApplicationContext(), "Invalid Phone Number!", Toast.LENGTH_LONG).show();
                    } else if (!UnameID.matches("[A-Za-z ]+")) {
                        namesignup.setText(null);
                        namesignup.setHint("Invalid Name");
                        Toast.makeText(getApplicationContext(), "Invalid Name!", Toast.LENGTH_LONG).show();
                    } else if (!UemailID.matches("[a-z.@0-9]+")) {
                        namesignup.setText(null);
                        namesignup.setHint("Invalid Email");
                        Toast.makeText(getApplicationContext(), "Invalid Email!", Toast.LENGTH_LONG).show();
                    } else {
                        dbRef.child(UphoneID).setValue(ckdn);
                        Toast.makeText(getApplicationContext(), "Profile Saved Successfully!", Toast.LENGTH_LONG).show();

                        phonesignup.setText(null);
                        namesignup.setText(null);
                        passwordsignup.setText(null);
                        emailsignup.setText(null);

                        Intent signIntent = new Intent(SignUp.this, SignIn.class);
                        startActivity(signIntent);
                    }
                }
            }
        });

    }
}
