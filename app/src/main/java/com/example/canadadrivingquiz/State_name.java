package com.example.canadadrivingquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class State_name extends AppCompatActivity {

    TextView State_Name;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_name);

        State_Name = findViewById(R.id.state_name);
        State_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.name_prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to alert_dialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.user_name_editable);

                String user_name = userInput.getText().toString();

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

//                                        if (user_name.isEmpty()){
//                                            userInput.requestFocus();
//                                            Toast.makeText(State_name.this, "Please enter Name", Toast.LENGTH_SHORT).show();
//                                        }
//                                        else {
//                                            Intent i = new Intent(State_name.this, Quiz_questions.class);
//                                            startActivity(i);
//                                        }

                                        Intent i = new Intent(State_name.this, Quiz_questions.class);
                                        startActivity(i);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }
}