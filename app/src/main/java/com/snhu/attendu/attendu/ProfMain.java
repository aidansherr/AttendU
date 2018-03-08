package com.snhu.attendu.attendu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



import static android.R.interpolator.linear;

public class
ProfMain extends AppCompatActivity
{


    private View mLayout;
    private TextView mProfLabel;


    Professor newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();






//This pulls the professor object from the database and uses that object to load buttons based on the Professors courses
        databaseReference.child("Professor").child("Professor_ID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {
                    Professor value = child.getValue(Professor.class);
                    newUser=(value);

                }

                makeButtons(newUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prof_main);
        mLayout = findViewById(R.id.prof_linear);
        mProfLabel = (TextView) findViewById(R.id.prof_label);



    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,SignInMapsActivity.class);
        startActivity(inten);
    }

    public void makeButtons(Professor newUser)
    {
        final Intent inten= new Intent(this,SignInPin.class);
        LinearLayout mParentLayout = (LinearLayout) findViewById(R.id.prof_linear);
        List<Course> profList= newUser.getClassList();

        for(int i=0;i<profList.size();i++)
        {
            LinearLayout dualView = new LinearLayout(getApplicationContext());
            mParentLayout.addView(dualView);
            dualView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            dualView.setOrientation(LinearLayout.HORIZONTAL);

            Button btn= new Button(this);
            btn.setText(newUser.getClassName(profList.get(i)));
            btn.setHeight(150);
            btn.setWidth(100);

            LinearLayout.LayoutParams buttonParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);

            buttonParams.weight = 1;
            btn.setLayoutParams(buttonParams);
            buttonParams.setMargins(0,60,0,0);
            btn.setId(i);

            btn.setText(newUser.getClassName(profList.get(i)));

            dualView.addView(btn);


            btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    openPinWindow(view);
                }
            });



            if (profList.get(i).getCourseAvailibility() == true)


            if (profList.get(i).getCourseAvailibility() == true)

            {
                btn.setBackgroundColor(Color.rgb(50, 205, 50)); //GREEN
            }
            else
            {
                btn.setBackgroundColor(Color.rgb(220, 220 ,220)); //GRAY
            }
        }
    }

}
