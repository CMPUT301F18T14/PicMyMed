package com.example.picmymedcode.android.picmymedphotohandler;


import android.view.ViewGroup;

import com.example.android.picmymedphotohandler.GalleryAdapter;
import com.example.android.picmymedphotohandler.GalleryCells;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

public class GalleryAdapterTest extends TestCase {

    @Mock
    ArrayList<GalleryCells> galleryCells;
    @Mock
    ViewGroup mViewGroup;
    @Mock
    int mPosition;
    @Mock
    GalleryAdapter.ViewHolder mViewHolder;

    GalleryAdapter mGalleryAdapter = Mockito.mock(GalleryAdapter.class);

    @Test
    public void testOnCreateViewHolder() throws NullPointerException{
        assertEquals(mViewHolder, mGalleryAdapter.onCreateViewHolder(mViewGroup, mPosition));
    }

    // Activity Test
    @Test
    public void testOnBindViewHolder() {
        mGalleryAdapter.onBindViewHolder(mViewHolder, mPosition);
    }

    @Test
    public void testGetItemCount() {
        galleryCells = new ArrayList<>();

        // Empty ArrayList of GalleryCells check
        assertEquals(0, galleryCells.size());

        GalleryCells cells1 = new GalleryCells();
        GalleryCells cells2 = new GalleryCells();
        GalleryCells cells3 = new GalleryCells();
        galleryCells.add(cells1);
        galleryCells.add(cells2);
        galleryCells.add(cells3);

        // Checking Size of ArrayList of GalleryCells
        assertEquals(3, galleryCells.size());

        galleryCells = null;
        // Checking if no ArrayList of GalleryCells exists
        assertNull(galleryCells);
    }
}