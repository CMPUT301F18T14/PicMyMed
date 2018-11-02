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

    @Test
    public void testGetRecordList() {
        RecordList recordList = new RecordList();

        assertNotNull(recordList.getRecordList()); // Check if object is != null
        assertTrue(recordList.getRecordList() instanceof ArrayList); // Check if the return type
    }

    @Test
    public void testGetRecord() {
        RecordList recordList = new RecordList();
        Record newRecord = new Record("New Record");
        recordList.addRecord(newRecord);

        assertEquals(newRecord, recordList.getRecord(0)); // Comparing objects
    }

    @Test
    public void testDeleteRecord() {
        RecordList recordList = new RecordList();
        Record newRecord = new Record("New Record");
        recordList.addRecord(newRecord);
        recordList.deleteRecord(0);

        assertEquals(0,recordList.sizeOfRecordList()); // Comparing integers
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
        Record newRecord = new Record("New Record");
        recordList.addRecord(newRecord);

        assertTrue(recordList.hasRecord(newRecord)); // Comparing booleans
    }

    @Test
    public void testSizeOfRecordList() {
        RecordList recordList = new RecordList();
        Record newRecord = new Record("New Record");
        recordList.addRecord(newRecord);
        recordList.addRecord(newRecord);
        recordList.addRecord(newRecord);

        assertEquals(3, recordList.sizeOfRecordList()); // Comparing integers
    }
}