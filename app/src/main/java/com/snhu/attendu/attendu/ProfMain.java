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


        Course math=new Course("Math");
        Course cs= new Course("Lab");
        Course english= new Course("English");

        List<Course> classes= new ArrayList<>();

        classes.add(math);
        classes.add(cs);
        classes.add(english);

        Professor prof=new Professor("Tyler","prof.test@snhu.edu","94dcc9b1e739b7ca13d0e390d3d719ddaa223156",classes,"p");
        Student student=new Student("Tom","Tom.Brady@snhu.edu","b240a280af5b8e08ca06917dae3fd0f1a7fae49b",classes,"p");
        Admin ad=new Admin("Dan","prof.test@snhu.edu","ec159d78f6c5de4c7c2d5f4933ce76b9583e1022");
        ITUser it=new ITUser("Jeff","prof.test@snhu.edu","41589fdd0f4220c50eab22259d45629b5bb0848f");

        databaseReference.child("Professor").child("Professor_ID").push().setValue(prof);
        databaseReference.child("Student").child("Student_ID").push().setValue(student);
        databaseReference.child("Admin").child("Admin_ID").push().setValue(ad);
        databaseReference.child("IT_user").child("IT_User_ID").push().setValue(it);





//This pulls the professor object from the database and uses that object to load buttons based on the Professors courses
        databaseReference.child("Professor").child("Professor_ID").child("-L6SwYHVXCEmbaDIPWfG").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Professor value = dataSnapshot.getValue(Professor.class);
                newUser=value;
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
