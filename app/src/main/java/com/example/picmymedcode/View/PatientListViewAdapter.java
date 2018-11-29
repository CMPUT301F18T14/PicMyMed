package com.example.picmymedcode.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.picmymedcode.R;

import java.util.ArrayList;
import java.util.Locale;

public class PatientListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    ArrayList<String> patientNameData;

    public PatientListViewAdapter(Context context){
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.patientNameData = new ArrayList<String>();
        this.patientNameData.addAll(CareProvierAddPatientActivity.patientName);
        //collection of data
    }
    @Override
    public int getCount() {
        return CareProvierAddPatientActivity.patientName.size();
    }

    @Override
    public String getItem(int position) {
        return CareProvierAddPatientActivity.patientName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //holder for patientname textview (add things here)
    public class ViewHolder {
        TextView patientName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_item, null);
            //Find the textview in the listview layout
            holder.patientName = convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //input the result into textview
        holder.patientName.setText(CareProvierAddPatientActivity.patientName.get(position));
        return convertView;
    }

    //Filter class
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        CareProvierAddPatientActivity.patientName.clear();
        if (charText.length() == 0) {
            CareProvierAddPatientActivity.patientName.addAll(patientNameData); //method from above code will add the whole arraylist and thus full listview is shown in this case.
        } else {
            for (String wp : patientNameData) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    CareProvierAddPatientActivity.patientName.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}