package com.example.mukha.picmymedcode.ProblemFile;

import com.example.mukha.picmymedcode.RecordList;

import java.util.Date;

public class Problem {
    private Date startDate;
    private String title;
    private String description;
    private RecordList recordList;
    private static final Integer MAX_PROBLEM_TITLE_LENGTH = 30;
    private static final Integer MAX_PROBLEM_DESCRIPTION_LENGTH = 300;

    public Problem(Date startDate, String title, String description) throws TooManyCharactersException {

        this.startDate = startDate;

        if (title.length() <= MAX_PROBLEM_TITLE_LENGTH) {
            this.title = title;
        } else {
            throw new TooManyCharactersException();
        }

        if (description.length() <= MAX_PROBLEM_DESCRIPTION_LENGTH) {
            this.description = description;
        } else {
            throw new TooManyCharactersException();
        }

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

    public void setTitle(String title) throws TooManyCharactersException {
        if (title.length() <= MAX_PROBLEM_TITLE_LENGTH) {
            this.title = title;
        } else {
            throw new TooManyCharactersException();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws TooManyCharactersException {
        if (description.length() <= MAX_PROBLEM_DESCRIPTION_LENGTH) {
            this.description = description;
        } else {
            throw new TooManyCharactersException();
        }
    }

    public RecordList getRecordList() {
        return recordList;
    }
}
