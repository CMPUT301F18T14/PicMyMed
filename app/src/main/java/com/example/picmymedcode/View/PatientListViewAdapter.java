/*
 * PatientListViewAdapter
 *
 * 1.2
 *
 * November 16, 2018
 *
 * Copyright (C) 2018 CMPUT301F18T14. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.picmymedcode.View;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.picmymedcode.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * MainActivity extends AppCompatActivity to handle the main activity of the application
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class PatientListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    ArrayList<String> patientNameData;

    /**
     * Instantiates the class
     *
     * @param context   Context
     */
    public PatientListViewAdapter(Context context){
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.patientNameData = new ArrayList<String>();
        this.patientNameData.addAll(CareProviderAddPatientActivity.patientName);
        //collection of data
    }

    /**
     * gets the count of patients
     *
     * @return  int
     */
    @Override
    public int getCount() {
        return CareProviderAddPatientActivity.patientName.size();
    }

    /**
     * Gets the patient
     *
     * @param    position
     * @return   int
     */
    @Override
    public String getItem(int position) {
        return CareProviderAddPatientActivity.patientName.get(position);
    }

    /**
     * Gets position
     *
     * @param position  int
     * @return          position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    //holder for patientname textview (add things here)
    public class ViewHolder {
        TextView patientName;
    }

    /**
     * Gets the view
     *
     * @param position      int
     * @param convertView   View
     * @param parent        ViewGroup
     * @return              convertView
     */
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
        holder.patientName.setText(CareProviderAddPatientActivity.patientName.get(position));
        holder.patientName.setTextColor(Color.parseColor("#16BBE5"));
        holder.patientName.setTextSize(20);
        return convertView;
    }

    /**
     * Filter class to filter patients
     *
     * @param charText  String
     */
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        CareProviderAddPatientActivity.patientName.clear();
        if (charText.length() == 0) {
            CareProviderAddPatientActivity.patientName.addAll(patientNameData); //method from above code will add the whole arraylist and thus full listview is shown in this case.
        } else {
            for (String wp : patientNameData) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    CareProviderAddPatientActivity.patientName.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}