/*
 * RecordListActivity
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

import java.util.ArrayList;

/**
 * RecordList allows for the adding and deletion of records
 *
 * @author  Apu, Debra, Eenna, Ian, Shawna, Umer
 * @version 1.1, 16/11/18
 * @since   1.1
 */

public class RecordList {
    private ArrayList<Record> recordList;

    /**
     * Initializes the recordList as an ArrayList of Records
     */
    public RecordList() {
        this.recordList = new ArrayList<Record>();
    }

    /**
     * Returns the ArrayList object
     * @return recordList
     */
    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    /**
     * Returns the record object at a specified index
     * @param index
     * @return Record
     */
    public Record getRecord(int index) {
        if (index < recordList.size()) {
            return this.recordList.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Deletes the record object from the list at the
     * specified index
     * @param index
     */
    public void deleteRecord(int index) {
        if (index < recordList.size()) {
            this.recordList.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Adds a record object to the ArrayList
     * @param record
     */
    public void addRecord(Record record) {
        this.recordList.add(record);
    }

    /**
     * Returns true or false depending on if the specified
     * record is in the ArrayList
     * @param record
     * @return true of false
     */
    public boolean hasRecord(Record record) {
        return this.recordList.contains(record);
    }

    /**
     * Returns the size of the ArrayList
     * @return int
     */
    public int sizeOfRecordList() {
        return this.recordList.size();
    }
}
