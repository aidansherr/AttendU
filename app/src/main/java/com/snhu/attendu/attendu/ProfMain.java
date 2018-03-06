package com.snhu.attendu.attendu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class
ProfMain extends AppCompatActivity
{

    Professor newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();








//This pulls the professor object from the database and uses that object to load buttons based on the Professors courses
        databaseReference.child("Professor").child("Professor_ID").child("-L6SwYHVXCEmbaDIPWfG").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Professor value = dataSnapshot.getValue(Professor.class);
                newUser=new Professor(value.getUserName(),value.getClassList(),value.getUser());
                makeButtons(newUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prof_main);

    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,SignInMapsActivity.class);
        startActivity(inten);
    }

    public void makeButtons(Professor newUser)
    {
        final Intent inten= new Intent(this,SignInPin.class);

        List<Course> profList= newUser.getClassList();
        for(int i=0;i<profList.size();i++)
        {
            TableRow row = new TableRow(this);
            TableLayout tableLayout = (TableLayout)findViewById(R.id.TableLayout);
            Button btn= new Button(this);
            btn.setText(newUser.getClassName(profList.get(i)));
            btn.setHeight(150);
            btn.setWidth(100);
            btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
            {
                openPinWindow(view);
            }

            });
            tableLayout.addView(row);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,1.0f));
            row.addView(btn);
            if (profList.get(i).getCourseAvailibility() == true)
            {
                btn.setBackgroundColor(Color.GREEN);
            }
        }
    }

}
