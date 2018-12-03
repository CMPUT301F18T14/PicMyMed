package com.example.picmymedcode.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.picmymedphotohandler.GalleryActivity;
import com.example.android.picmymedphotohandler.GalleryAdapter;
import com.example.android.picmymedphotohandler.GalleryCells;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.BodyLocation;
import com.example.picmymedcode.Model.BodyLocationPhoto;
import com.example.picmymedcode.Model.Geolocation;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Photo;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.R;
import com.example.picmymedmaphandler.View.DrawMapActivity;

import java.util.ArrayList;

public class SearchRecordAdapter extends RecyclerView.Adapter<SearchRecordAdapter.RecordViewHolder> {

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
        TextView recordTimeStampView;
        RecyclerView recordPhotoView;
        ImageView bodyLocationIcon;


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
            bodyLocationIcon.setVisibility(View.INVISIBLE);
            mapIcon.setVisibility(View.INVISIBLE);
            galleryIcon.setVisibility(View.INVISIBLE);
            recordMoreImageView .setVisibility(View.INVISIBLE);



        }
    }

    /**
     * Method handles record data
     *
     * @param recordsdata   ArrayList<Record>
     */
    public SearchRecordAdapter(Context context, ArrayList<Record> recordsdata) {
        this.records = recordsdata;
        this.context = context;
    }

    /**
     * Method handles record data
     *
     * @param recordsdata   ArrayList<Record>
     */
    public SearchRecordAdapter(Context context, ArrayList<Record> recordsdata, int problemIndex) {
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
    public SearchRecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordcard_layout,parent,false);
        SearchRecordAdapter.RecordViewHolder myViewHolder = new SearchRecordAdapter.RecordViewHolder(view);
        return myViewHolder;
    }

    /**
     * Method sets view when specific record is selected
     *
     * @param recordViewHolder  RecordViewHolder
     * @param i                 int
     */
    @Override
    public void onBindViewHolder(@NonNull final SearchRecordAdapter.RecordViewHolder recordViewHolder, final int i) {
        TextView recordTitleTextView = recordViewHolder.recordTitleTextView;
        TextView recordDescriptionTextView = recordViewHolder.recordDescriptionTextView;
        TextView recordTimeTextView = recordViewHolder.recordTimeTextView;
        // I think this is deprecated? Ask Shawna
        TextView recordTimeStampTextView = recordViewHolder.recordTimeStampView;
        TextView recordLocationTextView = recordViewHolder.recordLocationTextView;


        recordTitleTextView.setText(records.get(i).getTitle());
        recordDescriptionTextView.setText(records.get(i).getDescription());
        recordTimeTextView.setText(records.get(i).getTimeStamp().toString());
        Geolocation geolocation = records.get(i).getGeolocation();


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
            @Override
            public void onClick(View v) {
            }
        });

        recordViewHolder.mapIcon.setOnClickListener(new View.OnClickListener() {
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
            @Override
            public void onClick(View v) {
                Log.d("onclicklistener", "clicked");
                Intent viewBodyLocationIntent = new Intent(context, XFixedPhotoActivity.class);
                Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
                BodyLocation bodyLocation = user.getProblemList().get(problemIndex).getRecordList().get(i).getBodyLocation();
                String bodyID = bodyLocation.getPhotoID();
                BodyLocationPhoto bodyLocationPhoto = user.getBodyLocationPhotoByID(bodyID);
                if (bodyLocationPhoto != null) {
                    Log.d("inside if", "not null");
                    viewBodyLocationIntent.putExtra("base64String", bodyLocationPhoto.getBase64EncodedString());
                    viewBodyLocationIntent.putExtra("x", bodyLocation.getxCoordinate());
                    viewBodyLocationIntent.putExtra("y", bodyLocation.getyCoordinate());
                    context.startActivity(viewBodyLocationIntent);
                }
                else {
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




        recordTimeStampTextView.setText(records.get(i).getDate().toString());

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
    public void setFilter(ArrayList<Record> filteredList) {
        records = filteredList;
        notifyDataSetChanged();
    }
}


