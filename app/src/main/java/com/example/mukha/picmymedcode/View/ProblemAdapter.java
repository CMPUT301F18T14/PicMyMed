package com.example.mukha.picmymedcode.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.mukha.picmymedcode.Controller.PicMyMedController;
import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.R;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.PorblemViewHolder>{
    private static final String FILENAME = "file.sav";


    private ArrayList<Problem> problems;
    Context context;

    public static class PorblemViewHolder extends RecyclerView.ViewHolder{
        TextView problemTitleTextView;
        TextView problemMoreTextView;

        public PorblemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.problemTitleTextView = (TextView) itemView.findViewById(R.id.problem_title_text_view);
            this.problemMoreTextView = (TextView) itemView.findViewById(R.id.problem_more_bar);
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
    public void onBindViewHolder(@NonNull final PorblemViewHolder myViewHolder, final int listPosition) {
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


        myViewHolder.problemMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, myViewHolder.problemMoreTextView);
                //inflating menu from xml resource
                popup.inflate(R.menu.problem_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent Intent = new Intent(context,EditProblemActivity.class);
                                Intent.putExtra("key",listPosition);
                                context.startActivity(Intent);
                                //handle menu1 click
                                break;
                            case R.id.delete:
                                //handle menu2 click
                                PicMyMedController.deleteProblem(problems.get(listPosition));
                                notifyDataSetChanged();
                                //saveInFile();
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });


    }


    @Override
    public int getItemCount() {
        return (problems == null) ? 0 : problems.size();
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(problems,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}
