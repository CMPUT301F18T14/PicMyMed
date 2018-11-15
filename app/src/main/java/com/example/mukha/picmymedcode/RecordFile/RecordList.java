package com.example.mukha.picmymedcode.RecordFile;

import com.example.mukha.picmymedcode.RecordFile.Record;

import java.util.ArrayList;

public class RecordList {
    public ArrayList<Record> recordList;

    public RecordList() {
        this.recordList = new ArrayList<Record>();
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    public Record getRecord(int index) {
        return this.recordList.get(index);
    }

    public void deleteRecord(int index) {
        this.recordList.remove(index);
    }

    public void addRecord(Record record) {
        this.recordList.add(record);
    }

    public boolean hasRecord(Record record) { return this.recordList.contains(record); }

    public int sizeOfRecordList() {
        return this.recordList.size();
    }
}
