package com.snhu.attendu.attendu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ITUserMain extends AppCompatActivity {

    private Button mCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ituser_main);

        mCreateButton = (Button) findViewById(R.id.createUserButton);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserTask(v);
            }
        });
    }

    public void createUserTask(View view)
    {
        Intent userCreation = new Intent(this, IT_UserCreation.class);
        startActivity(userCreation);
    }
}