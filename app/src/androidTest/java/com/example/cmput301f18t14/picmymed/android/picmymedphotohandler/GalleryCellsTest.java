package com.example.cmput301f18t14.picmymed.android.picmymedphotohandler;

import android.graphics.Bitmap;

import com.example.android.picmymedphotohandler.GalleryCells;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;

public class GalleryCellsTest extends TestCase {
    @Mock
    Bitmap bitmap;

    @Test
    public void testGetTitle() {
        GalleryCells cells = new GalleryCells();
        cells.setTitle("1");
        assertEquals("1", cells.getTitle());
    }

    @Test
    public void testGetBitmap() {
        GalleryCells cells = new GalleryCells();
        cells.setBitmap(bitmap);
        // Testing if a bitmap object is returned
        assertTrue(bitmap == cells.getBitmap());
    }

    @Test
    public void testGetFilepath() {
        GalleryCells cells = new GalleryCells();
        cells.setFilepath("/dir/photos");
        assertEquals("/dir/photos", cells.getFilepath());
    }

    @Test
    public void testSetTitle() {
        GalleryCells cells = new GalleryCells();
        cells.setTitle("1");
        assertEquals("1", cells.getTitle());
    }

    @Test
    public void testSetBitmap() {
        GalleryCells cells = new GalleryCells();
        cells.setBitmap(bitmap);
        // Testing if a bitmap object is returned
        assertTrue(bitmap == cells.getBitmap());
    }

    @Test
    public void testSetFilepath() {
        GalleryCells cells = new GalleryCells();
        cells.setFilepath("/dir/photos");
        assertEquals("/dir/photos", cells.getFilepath());
    }
}