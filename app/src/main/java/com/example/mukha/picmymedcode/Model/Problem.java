
package com.example.mukha.picmymedcode.Model;

import java.util.Date;

public class Problem {
    private Date startDate;
    private String title;
    private String description;
    private String id;
    private RecordList recordList;
    private static final Integer MAX_PROBLEM_TITLE_LENGTH = 30;
    private static final Integer MAX_PROBLEM_DESCRIPTION_LENGTH = 300;

    public Problem(Date startDate, String title, String description) throws IllegalArgumentException {

        this.startDate = startDate;
        this.setTitle(title);
        this.setDescription(description);

        this.id = null;

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

    public void setTitle(String title) throws IllegalArgumentException {

        if (title.length() <= MAX_PROBLEM_TITLE_LENGTH) {
            this.title = title;
        } else {
            throw new IllegalArgumentException(String.format("Problem title should not exceed %s characters!", MAX_PROBLEM_TITLE_LENGTH));
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IllegalArgumentException {

        if (description.length() <= MAX_PROBLEM_DESCRIPTION_LENGTH) {
            this.description = description;
        } else {
            throw new IllegalArgumentException(String.format("Problem description should not exceed %s characters!", MAX_PROBLEM_DESCRIPTION_LENGTH));
        }
    }

    public RecordList getRecordList() {
        return recordList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
