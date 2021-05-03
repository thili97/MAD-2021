package com.slytherin.chickendinner;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FeedbackList extends AppCompatActivity {

    ListView FeedbackList;

    FirebaseDatabase fDatabase;
    DatabaseReference dbRef;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    MyFeedbacks myFeedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        myFeedbacks = new MyFeedbacks();
        FeedbackList = (ListView)findViewById(R.id.FlistView);
        fDatabase = FirebaseDatabase.getInstance();

        dbRef = fDatabase.getReference("Feedback");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.my_feedback_list,R.id.feedback_information, list);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    myFeedbacks = ds.getValue(MyFeedbacks.class);
                    list.add("Name: "+myFeedbacks.getfName().toString() + "\nEmail: " + myFeedbacks.getfEmail().toString() + "\nComment: " + myFeedbacks.getfNote().toString());
                }
                FeedbackList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
