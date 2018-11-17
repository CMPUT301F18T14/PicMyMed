package com.example.mukha.picmymedcode;

import android.location.Location;

import com.example.mukha.picmymedcode.Model.BodyLocation;
import com.example.mukha.picmymedcode.Model.PhotoList;
import com.example.mukha.picmymedcode.Model.Record;

import junit.framework.TestCase;

import java.util.Date;

public class RecordTest extends TestCase {

    public void testGetTitle (){
        Record record = new Record ("Record");
        assertEquals("Record", record.getTitle());
    }

    public void testSetTitle (){
        Record record = new Record ("Record");
        record.setTitle("Record2");
        assertEquals("Record2", record.getTitle());
    }

    public void testGetComment (){
        Record record = new Record ("Record");
        assertEquals("", record.getComment());
    }

    public void testSetComment (){
        Record record = new Record ("Comment");
        record.setComment("Comment2");
        assertEquals("Comment2", record.getComment());
    }

    public void testGetGeolocation (){
        Record record = new Record ("Record");
        assertFalse("Wrong Location", record.getGeolocation().equals(""));
    }


    public void testSetGeolocation (){
        Location location = new Location("google");
        Record record = new Record ("Record");
        record.setLocation(location);
        assertFalse("Wrong Location", record.getGeolocation().equals(""));

        ///assertTrue(record.getGeolocation() instanceof );
    }

    public void testSetPhotoList(){
        PhotoList photoList = new PhotoList();
        Record record = new Record ("Record");
        record.setPhotoList(photoList);
        assertTrue(record.getPhotoList() instanceof PhotoList);

    }

    public void testGetPhotoList(){
        Record record = new Record ("Record");
        assertTrue(record.getPhotoList() instanceof PhotoList);
    }

    public void testBodyLocation(){
        Record record = new Record ("Record");
        assertTrue(record.getBodyLocation() instanceof BodyLocation);
    }

    public void testGetTimeStamp(){
        Date date = new Date();
        Record record = new Record ("Record");
        assertFalse("Wrong TimeStamp",record.getTimeStamp().equals(""));
    }



}
