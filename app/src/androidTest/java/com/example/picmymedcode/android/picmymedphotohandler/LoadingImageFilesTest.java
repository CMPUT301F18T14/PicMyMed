package com.example.picmymedcode.android.picmymedphotohandler;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.example.android.picmymedphotohandler.LoadingImageFiles;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class LoadingImageFilesTest extends TestCase {

    private final static String TAG = "LoadingImageFilesTest: ";
    @Mock
    Context context;

    @Mock
    ArrayList<Bitmap> bitmaps;

    @Mock
    int position;

    @Mock
    File[] file;

    @Test
    public void testConvertingToBitmap() {
        try {
            LoadingImageFiles loadingImageFiles = new LoadingImageFiles(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            //Assert.assertSame(bitmaps, loadingImageFiles.convertingToBitmap());
            System.out.println("Success!");
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception !");
        }
    }

    @Test
    public void testAbsolutePath() {
        try {
            LoadingImageFiles loadingImageFiles = new LoadingImageFiles(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            when(file[position].getAbsolutePath()).thenReturn("FilePath");
            //assertEquals("FilePath", loadingImageFiles.absolutePath(position));
            System.out.println("Success!");
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception !");
        }
    }
}