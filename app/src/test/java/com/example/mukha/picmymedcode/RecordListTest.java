package com.example.mukha.picmymedcode;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

/**
 * This class implements TDD for RecordList class
 *
 * @Date: November 1, 2018
 */

public class RecordListTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        RecordList recordList = new RecordList();
        assertTrue(recordList.getRecordList() instanceof ArrayList);
    }

    @Test
    public void testGetRecordList() {
        RecordList recordList = new RecordList();

        assertNotNull(recordList.getRecordList()); // Check if object is != null
        assertTrue(recordList.getRecordList() instanceof ArrayList); // Check the return type
    }

    @Test
    public void testGetRecord() {
        RecordList recordList = new RecordList();
        Record newRecord = new Record("New Record");

        //get correct record
        recordList.addRecord(newRecord);
        assertEquals(newRecord, recordList.getRecord(0)); // Comparing objects

        //test for invalid index
        try {
            recordList.getRecord(2);
            fail("Exception wasn't thrown for the index, was supposed to be.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Expected to get here. Index out of bounds.", true);
        }
    }

    @Test
    public void testDeleteRecord() {
        RecordList recordList = new RecordList();
        Record record = new Record("New Record");
        Record record1 = new Record("New Record1");

        //test deleting from an empty list
        try {
            recordList.deleteRecord(0);
            fail("Exception wasn't thrown for deleting from an empty list, was supposed to be.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Expected to get here. Deleting from an empty list.", true);
        }

        //test deleting an element
        recordList.addRecord(record);
        recordList.deleteRecord(0);
        assertEquals("Record not deleted.",0,recordList.sizeOfRecordList());

        recordList = new RecordList(); //reset no. of elements to zero

        //test correct element is deleted
        recordList.addRecord(record);
        recordList.addRecord(record1);
        assertNotSame(record,record1); //make sure the two elements are different
        assertEquals("Records didn't get added to the list.",
                2,recordList.sizeOfRecordList());
        //delete record
        recordList.deleteRecord(0);
        //make sure the remaining record wasn't the same as the one deleted
        assertNotSame(record,recordList.getRecord(0));
        assertSame(record1,recordList.getRecord(0));

    }

    @Test
    public void testAddRecord() {
        RecordList recordList = new RecordList();
        Record newRecord = new Record("New Record");
        recordList.addRecord(newRecord);

        assertEquals(newRecord, recordList.getRecord(0)); // Comparing objects
    }

    @Test
    public void testHasRecord() {
        RecordList recordList = new RecordList();
        Record record = new Record("New Record");
        Record newRecord = new Record("Another Record");

        recordList.addRecord(record);
        assertTrue(recordList.hasRecord(record));
        assertFalse(recordList.hasRecord(newRecord));
    }

    @Test
    public void testSizeOfRecordList() {
        RecordList recordList = new RecordList();
        Record newRecord = new Record("New Record");
        recordList.addRecord(newRecord);
        recordList.addRecord(newRecord);
        recordList.addRecord(newRecord);

        assertEquals(3, recordList.sizeOfRecordList());
    }
}