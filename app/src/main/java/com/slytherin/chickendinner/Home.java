package com.slytherin.chickendinner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnHome_Order, btnHome_Cart, btnHome_Profile, btnHome_Feedback, btnHome_Settings, btnHome_Contactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHome_Order = (Button)findViewById(R.id.btnHome_Order);
        btnHome_Cart = (Button)findViewById(R.id.btnHome_Cart);
        btnHome_Profile = (Button)findViewById(R.id.btnHome_Profile);
        btnHome_Feedback = (Button)findViewById(R.id.btnHome_Feedback);
        btnHome_Settings = (Button)findViewById(R.id.btnHome_Settings);
        btnHome_Contactus = (Button)findViewById(R.id.btnHome_Contactus);

        btnHome_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Home.this, CDFoodMenuList.class);
                startActivity(homeIntent);
            }
        });

        btnHome_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback = new Intent(Home.this, AddToCart.class);
                startActivity(feedback);
            }
        });

        btnHome_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback = new Intent(Home.this, Profile.class);
                startActivity(feedback);
            }
        });

        btnHome_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback = new Intent(Home.this, Feedback.class);
                startActivity(feedback);
            }
        });

        btnHome_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback = new Intent(Home.this, SettingsPanel.class);
                startActivity(feedback);
            }
        });

        btnHome_Contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback = new Intent(Home.this, ContactUs.class);
                startActivity(feedback);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(Home.this, Cart.class);
                startActivity(cartIntent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Setting profile
            Intent feedback = new Intent(Home.this, SettingsPanel.class);
            startActivity(feedback);
        }
        if (id == R.id.action_logout) {
            Intent signIn = new Intent(Home.this, SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signIn);
        }
        if (id == R.id.action_profile) {
            Intent profile = new Intent(Home.this, Profile.class);
            startActivity(profile);
        }
        if (id == R.id.action_Admin) {
            Intent profile = new Intent(Home.this, AdministratorLogin.class);
            startActivity(profile);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //Home menu
            Intent homeIntent = new Intent(Home.this, CDFoodMenuList.class);
            startActivity(homeIntent);
        } else if (id == R.id.nav_cart) {
            //Cart
            Intent cartIntent = new Intent(Home.this, Cart.class);
            startActivity(cartIntent);
        } else if (id == R.id.nav_order) {
            //Order list
            Intent MyOrders = new Intent(Home.this, Orders.class);
            startActivity(MyOrders);
        } else if (id == R.id.nav_profile) {
            //profile
            Intent profile = new Intent(Home.this, Profile.class);
            startActivity(profile);
        } else if (id == R.id.nav_logout) {
            //Logout
            Intent signIn = new Intent(Home.this, SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signIn);
        } else if (id == R.id.nav_feedback) {
            //feedback profile
            Intent feedback = new Intent(Home.this, Feedback.class);
            startActivity(feedback);
        } else if (id == R.id.nav_settings) {
            //Setting profile
            Intent feedback = new Intent(Home.this, SettingsPanel.class);
            startActivity(feedback);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}