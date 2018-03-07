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
import java.util.ArrayList;
import java.util.List;



import static android.R.interpolator.linear;

public class
ProfMain extends AppCompatActivity
{
    private View mLayout;
    private TextView mProfLabel;

    List<Course> courses= new ArrayList<Course>();
    Professor newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_main);

        mLayout = findViewById(R.id.prof_linear);
        mProfLabel = (TextView) findViewById(R.id.prof_label);

        //TODO load course from database
        Course math=new Course("Math");
        Course english=new Course("English");
        Course cs= new Course("Junior lab");
        courses.add(math);
        courses.add(english);
        courses.add(cs);

        courses.get(1).setCourseAvailability(true);


        newUser= new Professor("Tyler",courses,"P");
        mProfLabel.setText("Professor User"); //TODO load professor name here

        makeButtons();
    }

    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,SignInMapsActivity.class);
        startActivity(inten);
    }

    public void makeButtons()
    {
        LinearLayout mParentLayout = (LinearLayout) findViewById(R.id.prof_linear);

        for(int i=0;i<courses.size();i++)
        {
            LinearLayout dualView = new LinearLayout(getApplicationContext());
            mParentLayout.addView(dualView);
            dualView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            dualView.setOrientation(LinearLayout.HORIZONTAL);

            Button btn= new Button(this);
            LinearLayout.LayoutParams buttonParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);

            buttonParams.weight = 1;
            btn.setLayoutParams(buttonParams);
            buttonParams.setMargins(0,60,0,0);
            btn.setId(i);

            btn.setText(newUser.getClassName(courses.get(i)));

            dualView.addView(btn);

            btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    openPinWindow(view);
                }
            });

            if (courses.get(i).getCourseAvailibility() == true)
            {
                btn.setBackgroundColor(Color.rgb(50, 205, 50)); //GREEN
            }else
            {
                btn.setBackgroundColor(Color.rgb(220, 220 ,220)); //GRAY
            }
        }
    }

}
