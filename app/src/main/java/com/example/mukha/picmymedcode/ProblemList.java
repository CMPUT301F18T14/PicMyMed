package com.example.mukha.picmymedcode;

import java.util.ArrayList;

public class ProblemList {

    private ArrayList<Problem> problemList;

    public ProblemList() {
        this.problemList = new ArrayList<Problem>();

    }

    public ArrayList<Problem> getProblemList() {
        return this.problemList;
    }

    public Problem getProblem(int index) {
        return this.problemList.get(index);
    }

    public void deleteProblem(int index) {
        this.problemList.remove(index);
    }

    public void addProblem(Problem problem) {
        this.problemList.add(problem);
    }
}
