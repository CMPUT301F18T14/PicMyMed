package com.example.mukha.picmymedcode.ProblemFile;

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
        return this.problemList.get(index);
    }

    public void deleteProblem(int index) {
        this.problemList.remove(index);
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