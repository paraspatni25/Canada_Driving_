package com.example.canadadrivingquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class State_name extends AppCompatActivity {

    TextView State_Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_name);

        State_Name = findViewById(R.id.state_name);
        State_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(State_name.this, Quiz_questions.class);
                startActivity(i);
            }
        });
    }
}