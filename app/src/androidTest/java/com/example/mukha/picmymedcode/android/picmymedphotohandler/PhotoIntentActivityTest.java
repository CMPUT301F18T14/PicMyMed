package com.example.mukha.picmymedcode.android.picmymedphotohandler;

import android.graphics.Bitmap;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;

public class PhotoIntentActivityTest extends TestCase {

    @Mock
    Bitmap bitmap;
    @Mock
    String imageFilePath;
    @Mock
    int width;
    @Mock
    int height;
    @Test
    public void testDecodeImageFromFiles() {
//        PhotoIntentActivity photoIntentActivity = new PhotoIntentActivity();
//        Bitmap bm = photoIntentActivity.decodeImageFromFiles(imageFilePath, width, height);
//        assertSame(bitmap, bm);
    }

    @Test
    public void testConvertBitmapToJPEG() {
    }

    @Test
    public void testWritingByteArrayToFile() {
    }
}