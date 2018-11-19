/*
 * Problem
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
package com.example.mukha.picmymedcode.Model;

import java.util.Date;

/**
 * Problem class creates a problem object for the patient containing a date, title, description, id,
 * and list of records
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class Problem {
    private Date startDate;
    private String title;
    private String description;
    private String id;
    private RecordList recordList;
    private static final Integer MAX_PROBLEM_TITLE_LENGTH = 30;
    private static final Integer MAX_PROBLEM_DESCRIPTION_LENGTH = 300;

    /**
     * Constructor initializes problem class variables
     *
     * @param startDate                 Date
     * @param title                     String
     * @param description               String
     * @throws IllegalArgumentException throws exception when title or description is too long
     */
    public Problem(Date startDate, String title, String description) throws IllegalArgumentException {

        this.startDate = startDate;
        this.setTitle(title);
        this.setDescription(description);

        this.id = null;

        this.recordList = new RecordList();
    }

    /**
     * Method gets the problem's start date
     *
     * @return  Date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Method sets the start date for a problem
     *
     * @param startDate Date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Method gets the title of a problem
     *
     * @return  String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method sets the title of a problem
     *
     * @param title                     String
     * @throws IllegalArgumentException throws exception when problem title is too long
     */
    public void setTitle(String title) throws IllegalArgumentException {

        if (title.length() <= MAX_PROBLEM_TITLE_LENGTH) {
            this.title = title;
        } else {
            throw new IllegalArgumentException(String.format("Problem title should not exceed %s characters!", MAX_PROBLEM_TITLE_LENGTH));
        }

    }

    /**
     * Method gets problem description
     *
     * @return  String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method sets the problem description
     * @param description               String
     * @throws IllegalArgumentException throws exception when problem description is too long
     */
    public void setDescription(String description) throws IllegalArgumentException {

        if (description.length() <= MAX_PROBLEM_DESCRIPTION_LENGTH) {
            this.description = description;
        } else {
            throw new IllegalArgumentException(String.format("Problem description should not exceed %s characters!", MAX_PROBLEM_DESCRIPTION_LENGTH));
        }
    }

    /**
     * Method gets a list of records
     *
     * @return  recordList
     */
    public RecordList getRecordList() {
        return recordList;
    }

    /**
     * Method gets problem ID
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Method sets ID for problem
     *
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }
}