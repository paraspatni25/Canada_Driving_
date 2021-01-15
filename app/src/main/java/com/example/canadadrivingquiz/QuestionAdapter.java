package com.example.canadadrivingquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private final int totalQuestion;
    ArrayList<Questions> questions;
    private View.OnClickListener l_question_listener;

    public QuestionAdapter(ArrayList<Questions> questions, int totalQuestion) {
        this.questions = questions;
        this.totalQuestion = totalQuestion;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_layout, parent, false);
        return new QuestionAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        //holder.image.setImageResource(Products.get(position).image);
        //Picasso.get().load(this.states.get(position).getImage()).into(holder.image);
        holder.total_question.setText(Integer.toString(this.totalQuestion));
        holder.quiz_option1.setText(this.questions.get(position).getOption1());
        holder.quiz_option2.setText(this.questions.get(position).getOption2());
        holder.quiz_option3.setText(this.questions.get(position).getOption3());
        holder.quiz_option4.setText(this.questions.get(position).getOption4());
        holder.quiz_question.setText(this.questions.get(position).getQuestion());
        holder.current_question_number.setText(Integer.toString(position + 1));
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        l_question_listener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView quiz_question, number_of_question, total_question, current_question_number;
        RadioButton quiz_option1, quiz_option2, quiz_option3, quiz_option4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            quiz_question = itemView.findViewById(R.id.quiz_question);
            number_of_question = itemView.findViewById(R.id.number_of_question);

            quiz_option1 = itemView.findViewById(R.id.quiz_option1);
            quiz_option2 = itemView.findViewById(R.id.quiz_option2);
            quiz_option3 = itemView.findViewById(R.id.quiz_option3);
            quiz_option4 = itemView.findViewById(R.id.quiz_option4);
            total_question = itemView.findViewById(R.id.total_question);
            current_question_number = itemView.findViewById(R.id.current_question_number);

            itemView.setTag(this);
            itemView.setOnClickListener(l_question_listener);
        }
    }
}
