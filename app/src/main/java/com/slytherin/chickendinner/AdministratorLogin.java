package com.slytherin.chickendinner;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdministratorLogin extends AppCompatActivity {

    EditText Aid, Apassword;
    Button AsignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_login);

        Aid = (EditText)findViewById(R.id.AdminloginID);
        Apassword = (EditText)findViewById(R.id.AdminloginPassword);
        AsignIn = (Button) findViewById(R.id.btn_AdminsignIn);


        AsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Aid.getText().toString().equals("IT18195330") && Apassword.getText().toString().equals("12345")){
                    Toast.makeText(AdministratorLogin.this, "Sign in success!", Toast.LENGTH_SHORT).show();
                    Intent resetIntent = new Intent(AdministratorLogin.this, AdminHomeScreen.class);
                    startActivity(resetIntent);
                    finish();
                }
                else {
                    Toast.makeText(AdministratorLogin.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                    Aid.setText(null);
                    Apassword.setText(null);
                }
            }
        });

    }
}
