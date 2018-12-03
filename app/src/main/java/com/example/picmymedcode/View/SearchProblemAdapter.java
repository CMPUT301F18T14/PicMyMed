package com.example.picmymedcode.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class SearchProblemAdapter extends RecyclerView.Adapter<SearchProblemAdapter.ProblemViewHolder>{
    private static final String FILENAME = "file.sav";


    private ArrayList<Problem> problems;
    Context context;

    /**
     * Method extends ViewHolder
     */
    public static class ProblemViewHolder extends RecyclerView.ViewHolder{
        TextView problemTitleTextView;
        TextView problemDateTextView;
        TextView numberofRecordTextView;
        TextView descriptionTextView;
        ImageView problemMoreImageView;

        /**
         * Method handles how problems are viewed
         *
         * @param itemView View
         */
        public ProblemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.problemTitleTextView = itemView.findViewById(R.id.problem_title_text_view);
            this.problemDateTextView = itemView.findViewById(R.id.problem_date_text_view);
            this.numberofRecordTextView = itemView.findViewById(R.id.problem_record_text_view);
            this.descriptionTextView = itemView.findViewById(R.id.problem_description_text_view);
            this.problemMoreImageView = itemView.findViewById(R.id.problem_more_bar);


            problemMoreImageView .setVisibility(View.INVISIBLE);
        }
    }

    /**
     *
     * @param context
     * @param problemsdata
     */
    public SearchProblemAdapter(Context context, ArrayList<Problem> problemsdata){
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
    public SearchProblemAdapter.ProblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.problemcard_layout,parent,false);
        SearchProblemAdapter.ProblemViewHolder myViewHolder = new SearchProblemAdapter.ProblemViewHolder(view);
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

    public void onBindViewHolder(@NonNull final SearchProblemAdapter.ProblemViewHolder myViewHolder, final int listPosition) {
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

    /**
     * Method saved data to file. No longer implemented, data now saved to database
     */
    public void setFilter(ArrayList<Problem> filteredList) {
        problems = filteredList;
        notifyDataSetChanged();
    }




}

