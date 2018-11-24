package com.example.cmput301f18t14.picmymed;

import com.example.cmput301f18t14.picmymed.Model.Problem;

import junit.framework.TestCase;

import java.util.Date;

public class ProblemListTest extends TestCase {

    public void testGetProblem(){
        ProblemList problemList = new ProblemList();
        Problem problem = new Problem(new Date(), "Title", "Description");
        problemList.addProblem(problem);
        Problem problemTest = problemList.getProblem(0);
        assertEquals(problem,problemTest); //compares references
    }

    public void testDeleteProblem(){
        ProblemList problemList = new ProblemList();
        Problem problem = new Problem(new Date(), "Title", "Description");

        //test index deleteProblem method
        problemList.addProblem(problem);
        problemList.deleteProblem(0);
        assertEquals(0,problemList.sizeOfProblemList());

        //test problem deleteProblem method
        problemList.addProblem(problem);
        problemList.deleteProblem(problem);
        assertEquals(0,problemList.sizeOfProblemList());
    }

    public void testAddProblem(){
        ProblemList problemList = new ProblemList();
        Problem problem = new Problem(new Date(), "Title", "Description");
        problemList.addProblem(problem);
        assertEquals(problem,problemList.getProblem(0)); //compares references
    }

    public void testGetProblemList(){
        ProblemList problemList = new ProblemList();
        ProblemList problemList1 = problemList;
        assertTrue(problemList.getProblemList().equals(problemList1.getProblemList()));
    }
}
