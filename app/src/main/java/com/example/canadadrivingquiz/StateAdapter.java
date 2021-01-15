package com.example.canadadrivingquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {
    ArrayList<States> states;

    private View.OnClickListener l_state_listener;

    public StateAdapter(ArrayList<States> states) {
        this.states = states;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_layout, parent, false);
        return new StateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.image.setImageResource(Products.get(position).image);
        //Picasso.get().load(this.states.get(position).getImage()).into(holder.image);
        holder.number_of_question.setText(Integer.toString(states.get(position).getTotalQuestion()));
        holder.state_name.setText(states.get(position).getName());

    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        l_state_listener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView state_name, number_of_question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            state_name = itemView.findViewById(R.id.state_name);
            number_of_question = itemView.findViewById(R.id.number_of_question);


            itemView.setTag(this);
            itemView.setOnClickListener(l_state_listener);
        }
    }
}
