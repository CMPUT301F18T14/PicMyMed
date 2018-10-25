package com.example.mukha.picmymedcode;

import java.util.ArrayList;

public class ProblemList {

    private ArrayList<Problem> problems;

    public ProblemList() {
    }

    public ArrayList<Problem> getProblems() {
        return this.problems;
    }

    public Problem getProblem(int index) {
        return this.problems.get(index);
    }

    public void deleteProblem(int index) {
        this.problems.remove(index);
    }

    public void addProblem(Problem problem) {
        this.problems.add(problem);
    }
}
