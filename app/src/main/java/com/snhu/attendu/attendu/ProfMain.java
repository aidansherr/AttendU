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
<<<<<<< HEAD
=======

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859
import java.util.ArrayList;
import java.util.List;



import static android.R.interpolator.linear;

public class
ProfMain extends AppCompatActivity
{
<<<<<<< HEAD
    List<Course> courses= new ArrayList<Course>();
=======


    private View mLayout;
    private TextView mProfLabel;


>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859
    Professor newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
<<<<<<< HEAD

<<<<<<< HEAD

        Course math=new Course("Math");
        Course english=new Course("English");
        Course cs= new Course("Junior lab");
=======
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'

        courses.add(math);
        courses.add(english);
        courses.add(cs);

<<<<<<< HEAD
        courses.get(1).setCourseAvailability(true);

<<<<<<< HEAD
=======
        Course math=new Course("Math");
        Course english=new Course("English");
        Course cs= new Course("Junior lab");

        courses.add(math);
        courses.add(english);
        courses.add(cs);
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
=======
        Intent i= getIntent();
        newUser= (Professor) i.getSerializableExtra("Professor");
>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859

        courses.get(1).setCourseAvailability(true);

<<<<<<< HEAD
//This pulls the professor object from the database and uses that object to load buttons based on the Professors courses
        databaseReference.child("Professor").child("Professor_ID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {

                }

                makeButtons(newUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

<<<<<<< HEAD
        
=======
        newUser= new Professor("Tyler",courses,"P");
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
=======
        newUser= new Professor("Tyler",courses,"P");
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prof_main);
        makeButtons();
=======


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prof_main);
        mLayout = findViewById(R.id.prof_linear);
        mProfLabel = (TextView) findViewById(R.id.prof_label);



>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859
    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,SignInMapsActivity.class);
        startActivity(inten);
    }

    public void makeButtons()
    {
        final Intent inten= new Intent(this,SignInPin.class);
<<<<<<< HEAD


        for(int i=0;i<courses.size();i++)
        {
            TableRow row = new TableRow(this);
            TableLayout tableLayout = (TableLayout)findViewById(R.id.TableLayout);
=======
        LinearLayout mParentLayout = (LinearLayout) findViewById(R.id.prof_linear);
        List<Course> profList= newUser.getClassList();

        for(int i=0;i<profList.size();i++)
            {
            LinearLayout dualView = new LinearLayout(getApplicationContext());
           mParentLayout.addView(dualView);
            dualView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            dualView.setOrientation(LinearLayout.HORIZONTAL);

>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859
            Button btn= new Button(this);
            btn.setText(newUser.getClassName(courses.get(i)));
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
<<<<<<< HEAD
            tableLayout.addView(row);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,1.0f));
            row.addView(btn);
            if (courses.get(i).getCourseAvailibility() == true)
=======



            if (profList.get(i).getCourseAvailibility() == true)


            if (profList.get(i).getCourseAvailibility() == true)

            {
                btn.setBackgroundColor(Color.rgb(50, 205, 50)); //GREEN
            }
            else
>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859
            {
                btn.setBackgroundColor(Color.rgb(220, 220 ,220)); //GRAY
            }
        }
    }

}
