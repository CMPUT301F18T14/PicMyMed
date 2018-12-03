package com.example.picmymedcode.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CareProviderProblemAdapter extends RecyclerView.Adapter<CareProviderProblemAdapter.PorblemViewHolder>{
    private static final String FILENAME = "file.sav";


    private ArrayList<Problem> problems;
    Context context;

    /**
     * Method extends ViewHolder
     */
    public static class PorblemViewHolder extends RecyclerView.ViewHolder{
        TextView problemTitleTextView;
        TextView problemDateTextView;
        TextView numberofRecordTextView;
        TextView descriptionTextView;


        /**
         * Method handles how problems are viewed
         *
         * @param itemView View
         */
        public PorblemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.problemTitleTextView = itemView.findViewById(R.id.cp_problem_title_text_view);
            this.problemDateTextView = itemView.findViewById(R.id.cp_problem_date_text_view);
            this.numberofRecordTextView = itemView.findViewById(R.id.cp_problem_record_text_view);
            this.descriptionTextView = itemView.findViewById(R.id.cp_problem_description_text_view);
        }
    }

    /**
     * Method initializes class
     *
     * @param context       Context
     * @param problemsdata  ArrayList<Problem></Problem>
     */
    public CareProviderProblemAdapter(Context context, ArrayList<Problem> problemsdata){
        this.problems = problemsdata;
        this.context = context; //collection of data
    }

    /**
     * Method checks if the existing view is being reused, otherwise it inflates the view
     *
     * @param parent    ViewGroup
     * @param viewType  viewType
     * @return          myViewHolder
     */
    @NonNull
    @Override
    public CareProviderProblemAdapter.PorblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.careproviderproblemcard_layout,parent,false);
        CareProviderProblemAdapter.PorblemViewHolder myViewHolder = new CareProviderProblemAdapter.PorblemViewHolder(view);
        return myViewHolder;
    }

    /**
     * Method gets element from the dataset at a certain position and replaces contents of the view
     * with that element
     *
     * @param myViewHolder  ProblemViewHolder
     * @param listPosition  int
     */
    @Override
    public void onBindViewHolder(@NonNull final CareProviderProblemAdapter.PorblemViewHolder myViewHolder, final int listPosition) {
        //set title
        TextView problemTitleTextView = myViewHolder.problemTitleTextView;
        problemTitleTextView.setText(problems.get(listPosition).getTitle().toUpperCase());
        //set date
        TextView problemDateTextView = myViewHolder.problemDateTextView;
        problemDateTextView.setText(problems.get(listPosition).getStartDate());
        //set number of record
        TextView numberofRecordTextView = myViewHolder.numberofRecordTextView;
        numberofRecordTextView.setText("Number of Records : "+problems.get(listPosition).getRecordList().size());
        //set description
        TextView DescriptionTextView = myViewHolder.descriptionTextView;
        DescriptionTextView.setText(problems.get(listPosition).getDescription());

        myViewHolder.problemTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            //onClick to go to next activity
            public void onClick(View v) {
                Intent Intent = new Intent(context,CareProviderRecordActivity.class);
                Intent.putExtra("key",listPosition);
                context.startActivity(Intent);
            }
        });
    problems.get(listPosition).getRecordList();

        //myViewHolder.problemMoreTextView.setOnClickListener(new View.OnClickListener() {
        /**
         * Method handles user clicking on an item in the view
         *
         * @param view  View
         */
           /* @Override
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
        }); */


    }

    /**
     * Method gets number of problems
     *
     * @return  int
     */
    @Override
    public int getItemCount() {
        return (problems == null) ? 0 : problems.size();
    }


}
