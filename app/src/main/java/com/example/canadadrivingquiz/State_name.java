package com.example.canadadrivingquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class State_name extends AppCompatActivity {

    TextView State_Name;
    RecyclerView stateRecyclerView;
    StateAdapter stateAdapter;

    ArrayList<States> states = new ArrayList<>();
    final Context context = this;
    public View.OnClickListener onStateItemClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();

            final States selectedState = states.get(position);

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
                                    i.putExtra("state", selectedState.getId());
                                    i.putExtra("totalQuestion", selectedState.getTotalQuestion());
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
    };
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_name);

        State_Name = findViewById(R.id.state_name);
        stateRecyclerView = findViewById(R.id.states_recycler_view);

        getData();

//        State_Name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // get prompts.xml view
//                LayoutInflater li = LayoutInflater.from(context);
//                View promptsView = li.inflate(R.layout.name_prompt, null);
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//                // set prompts.xml to alert_dialog builder
//                alertDialogBuilder.setView(promptsView);
//
//                final EditText userInput = (EditText) promptsView.findViewById(R.id.user_name_editable);
//
//                String user_name = userInput.getText().toString();
//
//                // set dialog message
//                alertDialogBuilder
//                        .setCancelable(false)
//                        .setPositiveButton("OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//
////                                        if (user_name.isEmpty()){
////                                            userInput.requestFocus();
////                                            Toast.makeText(State_name.this, "Please enter Name", Toast.LENGTH_SHORT).show();
////                                        }
////                                        else {
////                                            Intent i = new Intent(State_name.this, Quiz_questions.class);
////                                            startActivity(i);
////                                        }
//
//                                        Intent i = new Intent(State_name.this, Quiz_questions.class);
//                                        startActivity(i);
//                                    }
//                                })
//                        .setNegativeButton("Cancel",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//
//                // create alert dialog
//                AlertDialog alertDialog = alertDialogBuilder.create();
//
//                // show it
//                alertDialog.show();
//
//            }
//        });
    }

    public void getData() {
        firestore = FirebaseFirestore.getInstance();
        states.clear();
        firestore.collection("state name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                        States item = queryDocumentSnapshot.toObject(States.class);
                        item.setId(queryDocumentSnapshot.getId());
                        states.add(item);
                    }
                    generateRecyclerView(states);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void generateRecyclerView(ArrayList<States> states) {

        Collections.sort(states, new StateComparator());

        stateAdapter = new StateAdapter(states);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        stateRecyclerView.setLayoutManager(linearLayout);
        stateRecyclerView.setAdapter(stateAdapter);
        stateAdapter.setOnItemClickListener(onStateItemClick);
    }

    class StateComparator implements Comparator<States> {
        @Override
        public int compare(States result1, States result2) {
            return result1.getName().compareToIgnoreCase(result2.getName());
        }
    }
}