package com.example.picmymedcode.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class CareProviderAdapter extends RecyclerView.Adapter<com.example.picmymedcode.View.CareProviderAdapter.CareProviderViewHolder> {

    private static final String FILENAME = "file.sav";


    private ArrayList<String> patientnameData;
    Context context;

    /**
     * Method extends ViewHolder
     */
    public static class CareProviderViewHolder extends RecyclerView.ViewHolder{
        TextView patientNameTextView;
        ImageView patientDeleteImageView;

        /**
         * Method handles how problems are viewed
         *
         * @param itemView View
         */
        public CareProviderViewHolder (@NonNull View itemView) {
            super(itemView);
            this.patientNameTextView = (TextView) itemView.findViewById(R.id.patientlist_name_text_view);
            this.patientDeleteImageView = (ImageView) itemView.findViewById(R.id.patient_delete_image_view);
        }
    }

    /**
     *
     * @param context
     * @param patientnameData
     */
    public CareProviderAdapter (Context context, ArrayList<String> patientnameData){
        this.patientnameData = patientnameData;
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
    public CareProviderAdapter.CareProviderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patientnamecard_layout,parent,false);
        CareProviderAdapter.CareProviderViewHolder myViewHolder = new CareProviderAdapter.CareProviderViewHolder(view);
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
    public void onBindViewHolder(@NonNull final CareProviderAdapter.CareProviderViewHolder myViewHolder, final int listPosition) {
        TextView patientNameTextView = myViewHolder.patientNameTextView;
        patientNameTextView.setText(patientnameData.get(listPosition));


        myViewHolder.patientNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            //onClick to go to next activity
            public void onClick(View v) {
                Intent Intent = new Intent(context,CareProviderProblemActivity.class);
                Intent.putExtra("name",patientnameData.get(listPosition));
                context.startActivity(Intent);
            }
        });
        //delete patient
        ImageView patientDeleteImageView = myViewHolder.patientDeleteImageView;
        myViewHolder.patientDeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            //onClick to go to next activity
            public void onClick(View v) {
                patientnameData.remove(listPosition);
                PicMyMedController.updateUser(PicMyMedApplication.getLoggedInUser());
                notifyDataSetChanged();
            }
        });


    }

    /**
     * Method gets number of problems
     *
     * @return  int
     */
    @Override
    public int getItemCount() {
        return (patientnameData == null) ? 0 : patientnameData.size();
    }

    /**
     * Method saved data to file. No longer implemented, data now saved to database
     */
    /*private void saveInFile() {
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
    } */
}