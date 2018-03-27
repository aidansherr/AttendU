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
<<<<<<< HEAD
=======

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

>>>>>>> 2dea3b17863775da2f07906ee4367ddb48b68e8a
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


>>>>>>> 2dea3b17863775da2f07906ee4367ddb48b68e8a
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
        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();



        Intent i= getIntent();
        newUser= (Professor) i.getSerializableExtra("Professor");
>>>>>>> 2dea3b17863775da2f07906ee4367ddb48b68e8a


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



>>>>>>> 2dea3b17863775da2f07906ee4367ddb48b68e8a
    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,SignInMapsActivity.class);
        startActivity(inten);
    }

    public void makeButtons(Professor newUser)
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

>>>>>>> 2dea3b17863775da2f07906ee4367ddb48b68e8a
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
<<<<<<< HEAD
            tableLayout.addView(row);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,1.0f));
            row.addView(btn);
            if (courses.get(i).getCourseAvailibility() == true)
<<<<<<< HEAD
=======
>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859
>>>>>>> parent of 4bddb3b... Revert "Fixed the database retreival, and merged with master"
=======



            if (profList.get(i).getCourseAvailibility() == true)


            if (profList.get(i).getCourseAvailibility() == true)

            {
                btn.setBackgroundColor(Color.rgb(50, 205, 50)); //GREEN
            }
            else
<<<<<<< HEAD
>>>>>>> 2dea3b17863775da2f07906ee4367ddb48b68e8a
=======
>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859
>>>>>>> parent of 4bddb3b... Revert "Fixed the database retreival, and merged with master"
            {
                btn.setBackgroundColor(Color.GREEN);
            }
        }
    }

}
