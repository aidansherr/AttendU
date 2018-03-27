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
import java.util.ArrayList;
import java.util.List;



import static android.R.interpolator.linear;

public class
ProfMain extends AppCompatActivity
{
    List<Course> courses= new ArrayList<Course>();
    Professor newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
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

        courses.get(1).setCourseAvailability(true);

<<<<<<< HEAD
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

        
=======
        newUser= new Professor("Tyler",courses,"P");
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
=======
        newUser= new Professor("Tyler",courses,"P");
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prof_main);
        makeButtons();
    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,SignInMapsActivity.class);
        startActivity(inten);
    }

    public void makeButtons()
    {
        final Intent inten= new Intent(this,SignInPin.class);


        for(int i=0;i<courses.size();i++)
        {
            TableRow row = new TableRow(this);
            TableLayout tableLayout = (TableLayout)findViewById(R.id.TableLayout);
            Button btn= new Button(this);
            btn.setText(newUser.getClassName(courses.get(i)));
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
            if (courses.get(i).getCourseAvailibility() == true)
            {
                btn.setBackgroundColor(Color.GREEN);
            }
        }
    }

}
