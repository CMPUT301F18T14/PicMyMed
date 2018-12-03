package com.example.picmymedcode.Model;

import java.io.Serializable;
import java.util.Random;

public class BodyLocationPhoto extends Photo implements Serializable {
    private String photoID;
    private String label;

    public BodyLocationPhoto(String filepath) {
        super(filepath);
        setUniquePhotoID();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Method to generate a unique photo id
     */

    private void setUniquePhotoID() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        final int LENGTH_OF_THE_RANDOM_STRING = 18;

        StringBuilder stringBuilder = new StringBuilder();   // Builds the string
        Random random = new Random();               // Generates random numbers

        // Keeps on adding random numbers until the string is filled
        while (stringBuilder.length() < LENGTH_OF_THE_RANDOM_STRING) { // length of the random string
            // Randomly picks a character from CHARACTERS by randomly choosing index
            int index = (int) (random.nextFloat() * CHARACTERS.length());

            // Appending the random character to the String builder
            stringBuilder.append(CHARACTERS.charAt(index));
        }

        // Converting the object to String
        this.photoID = stringBuilder.toString();

    }
    public String getPhotoID() {
        return photoID;
    }

}
