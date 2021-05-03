package com.slytherin.chickendinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    EditText foodname, useremail, feedbacknote;
    Button btn_Add, btn_ViewList;
    public int fdbk = 0;
    DatabaseReference dbRef;
    MyFeedbacks ckdn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        foodname = (EditText)findViewById(R.id.feedback_Name);
        useremail = (EditText)findViewById(R.id.feedback_email);
        feedbacknote = (EditText)findViewById(R.id.feedback_Note);
        btn_Add = (Button) findViewById(R.id.btnaddFeedback);
        btn_ViewList = (Button) findViewById(R.id.btnViewFeedbackList);

        ckdn = new MyFeedbacks();

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fdbk = fdbk +1;

                dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback");

                ckdn.setfName(foodname.getText().toString().trim());
                ckdn.setfEmail(useremail.getText().toString().trim());
                ckdn.setfNote(feedbacknote.getText().toString().trim());

                String fname = foodname.getText().toString().trim();
                String femail = useremail.getText().toString().trim();
                String fnote = feedbacknote.getText().toString().trim();


                if(!fname.isEmpty() || !femail.isEmpty() || !fnote.isEmpty()) {
                    if(fname.matches("[A-Za-z ]+")){
                        if(femail.matches("[a-z@.]+")) {
                            dbRef.child(Common.currentUser.getPhone()+fdbk).setValue(ckdn);
                            Toast.makeText(getApplicationContext(), "Feedback Saved Successfully",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Invalid Email!",Toast.LENGTH_LONG).show();
                            useremail.setText(null);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Can not use special characters and numbers!",Toast.LENGTH_LONG).show();
                        foodname.setText(null);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Fill all the fields!",Toast.LENGTH_LONG).show();
                }

                foodname.setText(null);
                useremail.setText(null);
                feedbacknote.setText(null);
            }
        });

        btn_ViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resetIntent = new Intent(Feedback.this, FeedbackList.class);
                startActivity(resetIntent);
            }
        });
    }
}
