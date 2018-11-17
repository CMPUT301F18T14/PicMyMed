package com.example.mukha.picmymedcode.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mukha.picmymedcode.Model.Record;
import com.example.mukha.picmymedcode.R;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private ArrayList<Record> records;

    public static class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView recordTitleTextView;
        //TextView recordLocationTextView;
        TextView recordDescriptionTextView;
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recordTitleTextView = itemView.findViewById(R.id.record_title_text_view);
            //this.recordLocationTextView = itemView.findViewById(R.id.record_location_text_view);
            this.recordDescriptionTextView = itemView.findViewById(R.id.record_description_text_view);
        }
    }

    public RecordAdapter(ArrayList<Record> recordsdata) {
        this.records = recordsdata;
    }
    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordcard_layout,parent,false);
        RecordViewHolder myViewHolder = new RecordViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder recordViewHolder, int i) {
        TextView recordTitleTextView = recordViewHolder.recordTitleTextView;
        //TextView recordLocationTextView = recordViewHolder.recordLocationTextView;
        TextView recordDescriptionTextView = recordViewHolder.recordDescriptionTextView;
        recordTitleTextView.setText(records.get(i).getTitle());
        //recordLocationTextView.setText(records.get(i).getTitle());
        recordDescriptionTextView.setText(records.get(i).getDescription());

    }


    @Override
    public int getItemCount() {
        return records.size();
    }
}
