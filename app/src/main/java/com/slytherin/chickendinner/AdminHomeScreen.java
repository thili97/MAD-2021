package com.slytherin.chickendinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomeScreen extends AppCompatActivity {

    Button insert, update, delete, A_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_screen);

        insert = (Button)findViewById(R.id.A_insert);
        update = (Button)findViewById(R.id.A_update);
        delete = (Button)findViewById(R.id.A_delete);
        A_Logout = (Button)findViewById(R.id.A_Logout);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AF1 = new Intent(AdminHomeScreen.this, AdminInsertFood.class);
                startActivity(AF1);
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AF2 = new Intent(AdminHomeScreen.this, AdminUpdateFood.class);
                startActivity(AF2);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AF3 = new Intent(AdminHomeScreen.this, AdminDeleteFood.class);
                startActivity(AF3);
                finish();
            }
        });

        A_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signout = new Intent(AdminHomeScreen.this, SignIn.class);
                signout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(signout);
                finish();
            }
        });
    }
}
