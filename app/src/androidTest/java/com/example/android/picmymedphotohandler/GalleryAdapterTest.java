package com.example.android.picmymedphotohandler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mukha.picmymedcode.R;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GalleryAdapterTest extends TestCase {

    @Mock
    Context mContext;
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