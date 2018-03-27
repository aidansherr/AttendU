package com.snhu.attendu.attendu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminMain extends AppCompatActivity {
     Admin newUser= new Admin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

        Intent i= getIntent();
        newUser= (Admin) i.getSerializableExtra("Admin");
        databaseReference.child("Admin").child("Admin_ID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        setContentView(R.layout.activity_admin_main);
    }
}
