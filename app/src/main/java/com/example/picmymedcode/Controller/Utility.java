package com.example.picmymedcode.Controller;

import android.content.Context;
import android.widget.Toast;

public class Utility {

    /**
     * Method shows toast message if user could create account or if user name exists
     *
     * @param message String
     */
    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
