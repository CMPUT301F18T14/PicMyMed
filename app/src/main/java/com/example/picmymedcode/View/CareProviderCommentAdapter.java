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

public class CareProviderCommentAdapter extends RecyclerView.Adapter<CareProviderCommentAdapter.ProblemViewHolder>{
    private static final String FILENAME = "file.sav";


    private ArrayList<String> problemsdata;
        Context context;

/**
 * Method extends ViewHolder
 */
public static class ProblemViewHolder extends RecyclerView.ViewHolder{
    TextView commentTextView;



    /**
     * Method handles how problems are viewed
     *
     * @param itemView View
     */
    public ProblemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.commentTextView = (TextView) itemView.findViewById(R.id.careprovider_comment_text_view);
    }
}

    /**
     *
     * @param context
     * @param problemsdata
     */
    public CareProviderCommentAdapter(Context context, ArrayList<String> problemsdata){
        this.problemsdata = problemsdata;
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
    public CareProviderCommentAdapter.ProblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentcard_layout,parent,false);
        CareProviderCommentAdapter.ProblemViewHolder myViewHolder = new CareProviderCommentAdapter.ProblemViewHolder(view);
        return myViewHolder;
    }

    /**
     * Method gets element from the dataset at a certain position and replaces contents of the view
     * with that element
     *
     * @param myViewHolder  PorblemViewHolder
     * @param listPosition  int
     */
    @Override
    public void onBindViewHolder(@NonNull final CareProviderCommentAdapter.ProblemViewHolder myViewHolder, final int listPosition) {
        //set title
        TextView commentTextView = myViewHolder.commentTextView;
        commentTextView.setText(problemsdata.get(listPosition));

    }

    /**
     * Method gets number of problems
     *
     * @return  int
     */
    @Override
    public int getItemCount() {
        return (problemsdata == null) ? 0 : problemsdata.size();
    }

    /**
     * Method saved data to file. No longer implemented, data now saved to database
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(problemsdata,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
