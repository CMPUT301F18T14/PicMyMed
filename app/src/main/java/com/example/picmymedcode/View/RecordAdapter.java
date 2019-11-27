/*
 * RecordAdapter
 *
 * 1.2
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
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.picmymedphotohandler.GalleryActivity;
import com.example.android.picmymedphotohandler.GalleryAdapter;
import com.example.android.picmymedphotohandler.GalleryCells;
import com.example.android.picmymedphotohandler.SlideShowAdapter;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.BodyLocation;
import com.example.picmymedcode.Model.BodyLocationPhoto;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Geolocation;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Photo;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedmaphandler.View.DrawMapActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>
 * to create an activity for the user to
 * view and manage problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private ArrayList<Record> records;
    private int problemIndex;
    Context context;
    //Nested Photo recyclerView
    GalleryAdapter galleryAdapter;
    private ArrayList<GalleryCells> galleryCells;
    RecyclerView.LayoutManager layoutManager;

    /**
     * Method extends RecyclerView.Holder to manage how records are displayed
     */
    public static class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView recordTitleTextView;
        TextView recordLocationTextView;
        TextView recordDescriptionTextView;
        TextView recordTimeTextView;
        ImageView recordMoreImageView;
        ImageView galleryIcon;
        ImageView mapIcon;
        ImageView bodyLocationIcon;
        TextView recordTimeStampView;
        RecyclerView recordPhotoView;


        /**
         * Method takes itemView and assigns it the record title and description
         *
         * @param itemView  View
         */
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recordTitleTextView = itemView.findViewById(R.id.record_title_text_view);
            this.recordLocationTextView = itemView.findViewById(R.id.record_location_text_view);
            this.recordDescriptionTextView = itemView.findViewById(R.id.record_description_text_view);
            this.recordTimeTextView = itemView.findViewById(R.id.record_time_text_view);
            this.recordTimeStampView = itemView.findViewById(R.id.record_time_text_view);
            this.recordPhotoView = itemView.findViewById(R.id.recyclerView_in_recordCard);
            this.galleryIcon = itemView.findViewById(R.id.record_gallery);
            this.mapIcon = itemView.findViewById(R.id.mapIcon);
            this.bodyLocationIcon = itemView.findViewById(R.id.bodyLocationIcon);

            this.recordMoreImageView = (ImageView) itemView.findViewById(R.id.record_more_bar);
            if (!PicMyMedApplication.getLoggedInUser().isPatient()){
                recordMoreImageView .setVisibility(View.INVISIBLE);
            }

        }
    }

    /**
     * Method handles record data
     *
     * @param recordsdata   ArrayList<Record>
     */
    public RecordAdapter(Context context, ArrayList<Record> recordsdata) {
        this.records = recordsdata;
        this.context = context;
    }

    /**
     * Method handles record data
     *
     * @param recordsdata   ArrayList<Record>
     */
    public RecordAdapter(Context context, ArrayList<Record> recordsdata, int problemIndex) {
        this.records = recordsdata;
        this.context = context;
        this.problemIndex = problemIndex;
    }

    /**
     * Method creates record view
     *
     * @param parent    ViewGroup
     * @param viewType  int
     * @return          myViewHolder
     */
    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordcard_layout,parent,false);
        RecordViewHolder myViewHolder = new RecordViewHolder(view);
        return myViewHolder;
    }

    /**
     * Method sets view when specific record is selected
     *
     * @param recordViewHolder  RecordViewHolder
     * @param i                 int
     */
    @Override
    public void onBindViewHolder(@NonNull final RecordViewHolder recordViewHolder,final int i) {
        TextView recordTitleTextView = recordViewHolder.recordTitleTextView;
        TextView recordDescriptionTextView = recordViewHolder.recordDescriptionTextView;
        TextView recordTimeTextView = recordViewHolder.recordTimeTextView;
        // I think this is deprecated? Ask Shawna
        //TextView recordTimeStampTextView = recordViewHolder.recordTimeStampView;
        TextView recordLocationTextView = recordViewHolder.recordLocationTextView;

        if(records.get(i).getTitle().equals("")){
            recordTitleTextView.setVisibility(View.GONE);
        } else {
            recordTitleTextView.setText(records.get(i).getTitle());
        }
        if(records.get(i).getDescription().equals("")){
            recordDescriptionTextView.setVisibility(View.GONE);
        } else {
            recordDescriptionTextView.setText(records.get(i).getDescription());
        }

        recordTimeTextView.setText(records.get(i).getDate());

        Geolocation geolocation = records.get(i).getGeolocation();
        if (geolocation != null) {
            recordLocationTextView.setText(geolocation.getLocationName());
        } else {
            recordViewHolder.mapIcon.setVisibility(View.GONE);
        }
        //todo
        if (!PicMyMedApplication.getLoggedInUser().isPatient()){
            Patient patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
            records =patient.getProblemList().get(CareProviderRecordActivity.problemPosition).getRecordList();
            BodyLocation bodyLocation = patient.getProblemList().get(problemIndex).getRecordList().get(i).getBodyLocation();
            records.get(i).getBodyLocation();
            System.out.println("BODY LOCATION "+bodyLocation);
            if (bodyLocation!=null){
                System.out.println(records.get(i).getBodyLocation());
                Log.d("getBodyLocation", "not null ");
                recordViewHolder.bodyLocationIcon.setVisibility(View.VISIBLE);
            } else {
                Log.d("getBodyLocation", "null ");
                recordViewHolder.bodyLocationIcon.setVisibility(View.GONE);
            }
        }else{
            Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
            BodyLocation bodyLocation = user.getProblemList().get(problemIndex).getRecordList().get(i).getBodyLocation();
            records.get(i).getBodyLocation();
            System.out.println("BODY LOCATION "+bodyLocation);
            if (bodyLocation!=null){
                System.out.println(records.get(i).getBodyLocation());
                Log.d("getBodyLocation", "not null ");
                recordViewHolder.bodyLocationIcon.setVisibility(View.VISIBLE);
            } else {
                Log.d("getBodyLocation", "null ");
                recordViewHolder.bodyLocationIcon.setVisibility(View.GONE);
            }
        }




        RecyclerView recordPhotoSlider = recordViewHolder.recordPhotoView;
        // Initialize the layout format and span
        layoutManager = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, true);
        // Set the layout in the recycler view
        recordPhotoSlider.setLayoutManager(layoutManager);
        galleryCells = preparedDataFromRecord(records.get(i));
        galleryAdapter = new GalleryAdapter(galleryCells, context);
        recordPhotoSlider.setAdapter(galleryAdapter);


        if (records.get(i).getPhotoList().size()==0) {
            recordViewHolder.galleryIcon.setVisibility(View.GONE);
            recordPhotoSlider.setVisibility(View.GONE);
        }

        recordViewHolder.galleryIcon.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles user selecting gallery icon
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent galleryActivityIntent = new Intent(context, GalleryActivity.class);
                galleryActivityIntent.putExtra("problemIndex", problemIndex);
                galleryActivityIntent.putExtra("recordIndex", i);
                galleryActivityIntent.putExtra("intentSender", 1);
                context.startActivity(galleryActivityIntent);
            }
        });

        recordViewHolder.recordLocationTextView.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles user selecting record location text
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
            }
        });

        recordViewHolder.mapIcon.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles user selecting the map icon
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent drawMapActivityIntent = new Intent(context, DrawMapActivity.class);
                drawMapActivityIntent.putExtra("problemIndex", problemIndex);
                drawMapActivityIntent.putExtra("recordIndex", i);
                drawMapActivityIntent.putExtra("callingActivity", "SingleRecordActivity");
                context.startActivity(drawMapActivityIntent);
            }
        });

        recordViewHolder.bodyLocationIcon.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles user selecting body location icon
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Log.d("onclicklistener", "clicked");
                Intent viewBodyLocationIntent = new Intent(context, XFixedPhotoActivity.class);
                Patient user = (Patient) PicMyMedApplication.getLoggedInUser();

                BodyLocation bodyLocation = user.getProblemList().get(problemIndex).getRecordList().get(i).getBodyLocation();
                String bodyID = bodyLocation.getPhotoID();
                BodyLocationPhoto bodyLocationPhoto = user.getBodyLocationPhotoByID(bodyID);
                if (bodyLocationPhoto != null) {
                    recordViewHolder.bodyLocationIcon.setVisibility(View.VISIBLE);
                    Log.d("inside if", "not null");
                    viewBodyLocationIntent.putExtra("base64String", bodyLocationPhoto.getBase64EncodedString());
                    viewBodyLocationIntent.putExtra("x", bodyLocation.getxCoordinate());
                    viewBodyLocationIntent.putExtra("y", bodyLocation.getyCoordinate());
                    context.startActivity(viewBodyLocationIntent);
                }
                else {
                    recordViewHolder.bodyLocationIcon.setVisibility(View.GONE);
                    Log.d("onclicklistener", "did not work");
                }
            }
        });



//        recordViewHolder.recordTitleTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            //onClick to go to next activity
//            public void onClick(View v) {
//                Intent Intent = new Intent(context,ViewRecordActivity.class);
//                Intent.putExtra("key", i);
//                context.startActivity(Intent);
//            }
//        });

        recordViewHolder.recordMoreImageView.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles user clicking on the image icon
             *
             * @param view  View
             */
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, recordViewHolder.recordMoreImageView);
                //inflating menu from xml resource
                popup.inflate(R.menu.problem_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    /**
                     * Handles the user clicking on the menu
                     *
                     * @param item  MenuItem
                     * @return      boolean
                     */
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
                        ArrayList<Problem> problemArrayList = user.getProblemList();
                        final Problem problem = problemArrayList.get(RecordActivity.position);

                        switch (item.getItemId()) {
                            case R.id.edit:
                                //TODO edit
                                Intent Intent = new Intent(context,EditRecordActivity.class);
                                Intent.putExtra("problem index", RecordActivity.position);
                                Intent.putExtra("record index", i);
                                context.startActivity(Intent);
                                break;
                            case R.id.delete:
                                //handle menu2 click
                                //TODO delete
                                //Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
                                //ArrayList<Problem> problemArrayList;
                                //problemArrayList = user.getProblemList();
                                //problemArrayList.get(RecordActivity.position).getRecordList().remove(i);
                                //PicMyMedController.updatePatient(user);
                                //notifyDataSetChanged();
                                //saveInFile();

                                AlertDialog.Builder authorizationDialog = new AlertDialog.Builder(context);
                                authorizationDialog.setTitle("Delete")
                                        .setCancelable(false)
                                        .setMessage("Are you sure you want to delete?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (PicMyMedApplication.isNetworkAvailable(context)) {
                                                    PicMyMedController.deleteRecord(problem, records.get(i), context);
                                                    notifyDataSetChanged();
                                                } else {
                                                    Toast.makeText(context, "You must be online to delete a record" , Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(context, "Keep enjoying!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                authorizationDialog.show();

                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

        //recordTimeStampTextView.setText(records.get(i).getDate());

    }

    /**
     * Method gets the number of records
     *
     * @return  int
     */
    @Override
    public int getItemCount() {
        return (records == null) ? 0 : records.size();
    }

    /**
     * This method performs operation on the data
     * to make it viewable under the defined adapter setting.
     *
     * @return      ArrayList of GalleryCells containing modified data for adapter compatibility
     */
    private ArrayList<GalleryCells> preparedDataFromRecord(Record record) {
        return preparedData(record.getPhotoList());
    }

    /**
     * This method performs operation on the data
     * to make it viewable under the defined adapter setting.
     *
     * @return      ArrayList of GalleryCells containing modified data for adapter compatibility
     */
    private ArrayList<GalleryCells> preparedData(ArrayList<Photo> photos) {
        ArrayList<GalleryCells> galleryCellsArrayList = new ArrayList<>();
        byte[] decodedString;
        Bitmap decodedByte;

        for (Photo photo : photos) {
            GalleryCells galleryCells = new GalleryCells();
            decodedString = Base64.decode(photo.getBase64EncodedString(), Base64.DEFAULT);
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            galleryCells.setBitmap(decodedByte);
            galleryCellsArrayList.add(galleryCells);
        }

        return galleryCellsArrayList;
    }
}
