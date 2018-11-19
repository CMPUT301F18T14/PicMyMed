/*
 * ProblemList
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

import com.example.mukha.picmymedcode.Model.Problem;
import java.util.ArrayList;

/**
 * ProblemList class creates a problemList object for a patient
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */

public class ProblemList {

    public ArrayList<Problem> problemList;

    /**
     * Constructor creates problemList variables
     */
    public ProblemList() {
        this.problemList = new ArrayList<Problem>();
    }

    /**
     * Method gets ProblemList
     *
     * @return ArrayList of problems
     */
    public ArrayList<Problem> getProblemList() {
        return this.problemList;
    }

    /**
     * Method gets a particular problem
     *
     * @param index int
     * @return      String of a problem
     */
    public Problem getProblem(int index) {
        if (index < problemList.size()) {
            return this.problemList.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Method deletes problem from the list
     *
     * @param index int
     */
    public void deleteProblem(int index) {
        if (index < problemList.size()) {
            this.problemList.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Method deletes problem from the problem list
     *
     * @param problem   String of a problem
     */
    public void deleteProblem(Problem problem) {
        this.problemList.remove(problem);
    }

    /**
     * Method adds problem to the problem list
     *
     * @param problem   String of a problem
     */
    public void addProblem(Problem problem) {
        this.problemList.add(problem);
    }

    /**
     * Method returns the size of the problem list
     *
     * @return  int
     */
    public int sizeOfProblemList() {
        return this.problemList.size();
    }
}