package com.example.mukha.picmymedcode.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.R;

import java.util.ArrayList;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.PorblemViewHolder>{



    private ArrayList<Problem> problems;

    public static class PorblemViewHolder extends RecyclerView.ViewHolder{
        TextView problemTitleTextView;
        public PorblemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.problemTitleTextView = itemView.findViewById(R.id.problem_title_text_view);
        }
    }

    public ProblemAdapter(ArrayList<Problem> problemsdata){
        this.problems = problemsdata;
    }


    @NonNull
    @Override
    public PorblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Check if the existing view is being reused, otherwise inflate the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        PorblemViewHolder myViewHolder = new PorblemViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PorblemViewHolder myViewHolder, int listPosition) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView problemTitleTextView = myViewHolder.problemTitleTextView;

        problemTitleTextView.setText(problems.get(listPosition).getTitle());


    }

    @Override
    public int getItemCount() {
        return problems.size();
    }



}
