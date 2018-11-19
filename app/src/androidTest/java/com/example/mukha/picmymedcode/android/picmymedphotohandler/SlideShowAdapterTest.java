/*
 * SlideShowTest
 *
 * 1.1
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

package com.example.mukha.picmymedcode.android.picmymedphotohandler;

import android.content.Context;
import android.view.View;

import com.example.android.picmymedphotohandler.GalleryCells;
import com.example.android.picmymedphotohandler.SlideShowAdapter;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

/**
 * This class tests two testable methods from SlideShowAdapter using Mock objects.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class SlideShowAdapterTest extends TestCase {

    @Mock
    ArrayList<GalleryCells> galleryCells;
    @Mock
    Context context;
    @Mock
    Object mO;
    @Mock
    View mView;

    //SlideShowAdapter mSlideShowAdapter = Mockito.mock(SlideShowAdapter.class);

    @Test
    public void testGetCount() {

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

    // Checking if the view is from a LinearLayout
    @Test
    public void testIsViewFromObject() {
        SlideShowAdapter slideShowAdapter = new SlideShowAdapter(galleryCells, context);
        assertTrue(slideShowAdapter.isViewFromObject(mView, mO));
    }
}