package com.example.mukha.picmymedcode.ProblemFile;

import com.example.mukha.picmymedcode.RecordFile.RecordList;

import java.util.Date;

public class Problem {
    private Date startDate;
    private String title;
    private String description;
    private RecordList recordList;

    public Problem(Date startDate, String title, String description) {

        this.startDate = startDate;
        this.title = title;
        this.description = description;
        this.recordList = new RecordList();
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecordList getRecordList() {
        return recordList;
    }
}
