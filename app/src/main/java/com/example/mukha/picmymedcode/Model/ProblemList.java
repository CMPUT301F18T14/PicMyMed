package com.example.mukha.picmymedcode.Model;

import com.example.mukha.picmymedcode.Model.Problem;

import java.util.ArrayList;

public class ProblemList {

    public ArrayList<Problem> problemList;

    public ProblemList() {
        this.problemList = new ArrayList<Problem>();
    }

    public ArrayList<Problem> getProblemList() {
        return this.problemList;
    }

    public Problem getProblem(int index) {
        if (index < problemList.size()) {
            return this.problemList.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void deleteProblem(int index) {
        if (index < problemList.size()) {
            this.problemList.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void deleteProblem(Problem problem) {
        this.problemList.remove(problem);
    }

    public void addProblem(Problem problem) {
        this.problemList.add(problem);
    }

    public int sizeOfProblemList() {
        return this.problemList.size();
    }
}