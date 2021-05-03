package com.slytherin.chickendinner;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class ForgetPassword extends AppCompatActivity {

    EditText up_Password, up_phone, up_con_password;
    Button btn_resetPassword;

    DatabaseReference dbRef;

    ChickenDinner ckdn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        up_Password = findViewById(R.id.resetPassword);
        up_con_password = findViewById(R.id.resetConfirmPassword);
        up_phone = findViewById(R.id.resetPhone);
        btn_resetPassword = findViewById(R.id.btn_resetPassword);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        ckdn = new ChickenDinner();

        btn_resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(ForgetPassword.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String UphoneID = up_phone.getText().toString().trim();
                        String UpasswordID = up_Password.getText().toString().trim();
                        String UConfirmPasswordID = up_con_password.getText().toString().trim();

                        //Check if user not exist in Database
                        if(dataSnapshot.child(up_phone.getText().toString()).exists()) {
                            //Get user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(up_phone.getText().toString()).getValue(User.class);
                            user.setPhone(up_phone.getText().toString());//set phone
                            if (user.getPhone().equals(up_phone.getText().toString())) {


                                if(UpasswordID.equals(UConfirmPasswordID) && !UpasswordID.isEmpty() && !UphoneID.isEmpty() && !UConfirmPasswordID.isEmpty()) {
                                    if(UphoneID.matches("[0-9]+")) {

                                        FirebaseDatabase databaseup;
                                        databaseup = FirebaseDatabase.getInstance();
                                        DatabaseReference dbRefup;
                                        dbRefup = databaseup.getReference();

                                        String UphoneIDNew = up_phone.getText().toString().trim();


                                        dbRefup.child("User").child(UphoneIDNew).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                String UConfirmPasswordIDNew = up_con_password.getText().toString().trim();
                                                dataSnapshot.getRef().child("password").setValue(UConfirmPasswordIDNew);
                                                Toast.makeText(getApplicationContext(), "Password Update Successfully!", Toast.LENGTH_LONG).show();

                                                Intent signIntent = new Intent(ForgetPassword.this, SignIn.class);
                                                startActivity(signIntent);


                                            }
                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Incorrect Phone number!", Toast.LENGTH_LONG).show();
                                        up_phone.setText(null);
                                        up_Password.setText(null);
                                        up_con_password.setText(null);
                                    }
                                }
                                else if(!UpasswordID.equals(UConfirmPasswordID) && !UpasswordID.isEmpty() && !UphoneID.isEmpty() && !UConfirmPasswordID.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Passwords did not match!", Toast.LENGTH_LONG).show();
                                    up_Password.setText(null);
                                    up_con_password.setText(null);
                                }

                                else if(UpasswordID.isEmpty() || UphoneID.isEmpty() || UConfirmPasswordID.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_LONG).show();
                                    up_phone.setText(null);
                                    up_Password.setText(null);
                                    up_con_password.setText(null);
                                }

                            }
                            else {
                                Toast.makeText(ForgetPassword.this, "Password update unsuccessfully. Try again!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            mDialog.dismiss();
                            Toast.makeText(ForgetPassword.this, "User not exist in database!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }


}
