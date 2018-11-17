package com.example.mukha.picmymedcode.Model;

import java.util.ArrayList;

public class RecordList {
    private ArrayList<Record> recordList;

    public RecordList() {
        this.recordList = new ArrayList<Record>();
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    public Record getRecord(int index) {
        if (index < recordList.size()) {
            return this.recordList.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void deleteRecord(int index) {
        if (index < recordList.size()) {
            this.recordList.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void addRecord(Record record) {
        this.recordList.add(record);
    }

    public boolean hasRecord(Record record) {
        return this.recordList.contains(record);
    }

    public int sizeOfRecordList() {
        return this.recordList.size();
    }
}
