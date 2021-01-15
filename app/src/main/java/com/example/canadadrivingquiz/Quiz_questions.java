package com.example.canadadrivingquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Quiz_questions extends AppCompatActivity {


    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    ArrayList<Questions> questions = new ArrayList<>();
    FirebaseFirestore firestore;
    private int totalQuestion;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);

        Intent stateIntent = getIntent();
        id = stateIntent.getStringExtra("state");
        totalQuestion = stateIntent.getIntExtra("totalQuestion", 0);
        recyclerView = findViewById(R.id.questions_recycler_view);
        getData();

    }

    public void getData() {
        firestore = FirebaseFirestore.getInstance();
        questions.clear();
        firestore.collection("state name").document(id).collection("questions").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                        Questions item = queryDocumentSnapshot.toObject(Questions.class);
                        //item.setName(queryDocumentSnapshot.getId());
                        questions.add(item);
                    }
                    generateRecyclerView(questions);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void generateRecyclerView(ArrayList<Questions> questions) {

        questionAdapter = new QuestionAdapter(questions, totalQuestion);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(questionAdapter);
        //questionAdapter.setOnItemClickListener(onBookItemClick);
    }
}