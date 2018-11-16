package com.example.mukha.picmymedcode.ProblemFile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mukha.picmymedcode.R;
import com.example.mukha.picmymedcode.RecordFile.RecordActivity;

import java.util.ArrayList;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.PorblemViewHolder>{



    private ArrayList<Problem> problems;
    Context context;

    public static class PorblemViewHolder extends RecyclerView.ViewHolder{
        TextView problemTitleTextView;
        public PorblemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.problemTitleTextView = (TextView) itemView.findViewById(R.id.problem_title_text_view);
        }
    }

    public ProblemAdapter(Context context, ArrayList<Problem> problemsdata){
        this.problems = problemsdata;
        this.context = context; //collection of data
    }


    @NonNull
    @Override
    public PorblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Check if the existing view is being reused, otherwise inflate the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.problemcard_layout,parent,false);
        PorblemViewHolder myViewHolder = new PorblemViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PorblemViewHolder myViewHolder, final int listPosition) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        TextView problemTitleTextView = myViewHolder.problemTitleTextView;
        problemTitleTextView.setText(problems.get(listPosition).getTitle());
        myViewHolder.problemTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            //onClick to go to next activity
            public void onClick(View v) {
                Intent Intent = new Intent(context,RecordActivity.class);
                Intent.putExtra("key",listPosition);
                context.startActivity(Intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (problems == null) ? 0 : problems.size();
    }



}