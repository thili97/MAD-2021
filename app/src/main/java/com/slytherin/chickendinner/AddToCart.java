package com.slytherin.chickendinner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class AddToCart extends AppCompatActivity {

    EditText OFcode, OFqty, OFname, OFphone, OFaddress;
    RadioButton RBone, RBfour;
    Button AddOrder, ViewOrders;

    DatabaseReference dbRef;

    OrderChickenDinner ckdn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        OFcode = (EditText)findViewById(R.id.OtxtFoodCode);
        OFqty = (EditText)findViewById(R.id.OtxtFoodQty);
        OFname = (EditText)findViewById(R.id.OtxtUser);
        OFphone = (EditText)findViewById(R.id.OtxtUserPhone);
        OFaddress = (EditText)findViewById(R.id.OtxtAddress);
        RBone = (RadioButton)findViewById(R.id.rbNormal);
        RBfour = (RadioButton)findViewById(R.id.rbFull);
        AddOrder = (Button)findViewById(R.id.btnAddCartNow);
        ViewOrders = (Button)findViewById(R.id.btnViewCartNow);

        ckdn = new OrderChickenDinner();

        AddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(AddToCart.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                mDialog.dismiss();
                dbRef = FirebaseDatabase.getInstance().getReference().child("MyOrders");

                ckdn.setOfcode(OFcode.getText().toString().trim());
                ckdn.setOfqty(OFqty.getText().toString().trim());
                ckdn.setOfname(OFname.getText().toString().trim());
                ckdn.setOfphone(OFphone.getText().toString().trim());
                ckdn.setOfaddress(OFaddress.getText().toString().trim());
                ckdn.setRbone(RBone.getText().toString().trim());
                ckdn.setRbfour(RBfour.getText().toString().trim());

                String OrFcode = OFcode.getText().toString().trim();
                String OrFqty = OFqty.getText().toString().trim();
                String OrFname = OFname.getText().toString().trim();
                String OrFphone = OFphone.getText().toString().trim();
                String OrFaddress = OFaddress.getText().toString().trim();
                String RBrone = RBone.getText().toString().trim();
                String RBrfour = RBfour.getText().toString().trim();

                if(OrFcode.isEmpty() || OrFqty.isEmpty() || OrFname.isEmpty() || OrFphone.isEmpty() || OrFaddress.isEmpty() || (RBrone.isEmpty() || RBrfour.isEmpty())) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields!",Toast.LENGTH_LONG).show();
                }
                else {
                    if (!OrFcode.matches("[0-9A-Z]+")) {
                        OFcode.setText(null);
                        OFcode.setHint("Invalid Food Code");
                        Toast.makeText(getApplicationContext(), "Invalid Food Code!", Toast.LENGTH_LONG).show();
                    } else if (!OrFqty.equals(0)) {
                        OFqty.setText(null);
                        OFqty.setHint("Invalid quantity");
                        Toast.makeText(getApplicationContext(), "Invalid quantity!", Toast.LENGTH_LONG).show();
                    } else if (!OrFaddress.matches("[0-9A-Za-z,''/. ]+")) {
                        OFaddress.setText(null);
                        OFaddress.setHint("Invalid Address");
                        Toast.makeText(getApplicationContext(), "Invalid Address!", Toast.LENGTH_LONG).show();
                    } else {
                        dbRef.child(OrFphone).setValue(ckdn);
                        Toast.makeText(getApplicationContext(), "Order Successfully!", Toast.LENGTH_LONG).show();

                        OFcode.setText(null);
                        OFqty.setText(null);
                        OFname.setText(null);
                        OFphone.setText(null);
                        OFaddress.setText(null);

                    }
                }
            }
        });

        ViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MyOrder = new Intent(AddToCart.this, Orders.class);
                startActivity(MyOrder);
            }
        });


    }
}
