/*
 * GalleryAdapter
 *
 * 1.2
 *
 * November 16, 2018
 *
 * Copyright 2018 CMPUT301F18T14. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.picmymedphotohandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.picmymedcode.R;
import com.example.picmymedcode.View.BodyLocationPhotoManagerActivity;
import com.example.picmymedcode.View.SelectBodyLocationActivity;
import com.example.picmymedcode.View.XFixedPhotoActivity;
import com.example.picmymedcode.View.XOnBodyLocationActivity;

import java.util.ArrayList;

/**
 * This class is an adapter for GalleryActivity to show the images using RecyclerView.
 * This class inflates the gallery_cell.xml file to correctly position the images loaded
 * from the internal disk space.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<GalleryCells> galleryList;
    private Context context;
    private int problemIndex;
    private int recordIndex;
    private static final int REQUEST_FOR_XACTIVITY_DRAW = 111;


    /**
     * Constructor of the class. It initializes all the member variables.
     *
     * @param galleryList   An ArrayList of GalleryCells
     * @param context       The activity context of the class that will use/call this adapter class.
     */
    public GalleryAdapter(ArrayList<GalleryCells> galleryList, Context context) {
        this.galleryList = galleryList;
        this.context = context;
    }

    /**
     * Constructor of the class. It initializes all the member variables.
     *
     * @param galleryList   An ArrayList of GalleryCells
     * @param context       The activity context of the class that will use/call this adapter class.
     */
    public GalleryAdapter(ArrayList<GalleryCells> galleryList, Context context, int problemIndex, int recordIndex) {
        this.galleryList = galleryList;
        this.context = context;
        this.problemIndex = problemIndex;
        this.recordIndex = recordIndex;
    }

    /**
     * This method inflates the gallery_cell.xml file.
     *
     * @param viewGroup     The respective Layout of the xml file
     * @param i             A position index
     * @return              A ViewHolder object
     */
    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        // The inflated Layout is stored in a view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_cell, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * This method draws the data on the respective ViewHolder object.
     *
     * @param viewHolder    The ViewHolder object
     * @param i             A position index that identifies at which position the cursor is on.
     */
    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder viewHolder, final int i) {
        // Setting the image title into the viewHolder
        viewHolder.title.setText(galleryList.get(i).getTitle());

        // Scaling down the image to CENTER_CROP to show cropped images
        viewHolder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Setting the Bitmap into the viewHolder
        viewHolder.imageView.setImageBitmap(galleryList.get(i).getBitmap());

        if (context instanceof GalleryActivity) {       // Checking which Activity the context is an instance of
            Log.d("GalleryAdapter: ", "Used by GalleryActivity");
            // Listener for selecting the image
            viewHolder.imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    // Upon click Adapter will send an intent to a different activity
                    Intent intentPhotoEnlarge = new Intent(view.getContext(), PhotoEnlargementActivity.class);

                    intentPhotoEnlarge.putExtra("intentSender",2);

                    intentPhotoEnlarge.putExtra("problemIndex", problemIndex);

                    intentPhotoEnlarge.putExtra("recordIndex", recordIndex);

                    intentPhotoEnlarge.putExtra("photoIndex", i);

                    //intentPhotoEnlarge.putExtra("filePath", galleryList.get(i).getFilepath());
                    intentPhotoEnlarge.putExtra("base64String", galleryList.get(i).getBase64());

                    //intentPhotoEnlarge.putExtra("index", i);
                    //intentPhotoEnlarge.putExtra("photoLabel", galleryList.get(i).getTitle());
                    view.getContext().startActivity(intentPhotoEnlarge);

//                    /*-------------------------------------------------------------------------------*/
//                    Intent xActivity = new Intent(view.getContext(), XOnBodyLocationActivity.class);
//                    xActivity.putExtra("base64String", galleryList.get(i).getBase64());
//                    view.getContext().startActivity(xActivity);
//                    /*--------------------------------------------------------------------------------*/
//                    Intent xFixedPhotoActivity = new Intent(view.getContext(), XFixedPhotoActivity.class);
//                    xFixedPhotoActivity.putExtra("base64String", galleryList.get(i).getBase64());
//                    view.getContext().startActivity(xFixedPhotoActivity);
                }
            });
        }

        if (context instanceof BodyLocationPhotoManagerActivity) {       // Checking which Activity the context is an instance of
            Log.d("GalleryAdapter: ", "Used by GalleryActivity");
            // Listener for selecting the image
            viewHolder.imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    // Upon click Adapter will send an intent to a different activity
                    Intent intentPhotoEnlarge = new Intent(view.getContext(), PhotoEnlargementActivity.class);

                    intentPhotoEnlarge.putExtra("intentSender",1);

                    intentPhotoEnlarge.putExtra("photoIndex", i);

                    //intentPhotoEnlarge.putExtra("filePath", galleryList.get(i).getFilepath());
                    if (  galleryList.get(i).getBase64()!= null) {
                        Log.i("DEBUG right", galleryList.get(i).getBase64());
                        intentPhotoEnlarge.putExtra("base64String", galleryList.get(i).getBase64());
                        intentPhotoEnlarge.putExtra("title", galleryList.get(i).getTitle());
                    }else {
                            Log.i("DEBUG wrong", galleryList.get(i).getBase64());
                        }

                    view.getContext().startActivity(intentPhotoEnlarge);
                }
            });
        }

        if (context instanceof SelectBodyLocationActivity) {
            Log.d("GalleryAdapter: ", "Used by SelectBodyLocationActivity");
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent xIntent = new Intent(view.getContext(), XFixedPhotoActivity.class);
                    Intent xActivity = new Intent(view.getContext(), XOnBodyLocationActivity.class);
                    xActivity.putExtra("base64String", galleryList.get(i).getBase64());
                    xActivity.putExtra("photoIndex", i);
                    ((Activity) context).startActivityForResult(xActivity, REQUEST_FOR_XACTIVITY_DRAW);
                    //float xCoordinate = data.getIntExtra
                    //context.startActivityForResult(xActivity, REQUEST_FOR_XACTIVITY_DRAW);
                    //float xCoordinate = getIntent().getIntExtra("x", 0);
                }
            });
        }

    }



    /**
     * This method returns the size of the ArrayList of type GallerCells
     * which in turns represents the number of data being presented by this adapter.
     * It lets the RecyclerView to know how much item to display.
     *
     * @return      An integer of number for the size of GallerCells typed ArrayList
     */
    @Override
    public int getItemCount() {
        if ( galleryList != null) {
            return galleryList.size();
        }
        return 0;
    }

    /**
     * This nested class will have the fields that will be shown in the recycler view.
     * It creates the overall block that will be used in the RecyclerView.
     *
     * @author  Md Touhidul (Apu) Islam
     * @version 1.1, 16/11/18
     * @since   1.1
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView imageView;

        /**
         * Constructor of the class. It initializes all the member variables.
         *
         * @param view  The view that will be used.
         */
        public ViewHolder(View view){
            super (view);
            title = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.img);
        }
    }
}

