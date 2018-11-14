package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.ProblemFile.Problem;
import com.example.mukha.picmymedcode.ProblemFile.ProblemList;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;

public class ProblemListTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        ProblemList problemList = new ProblemList();
        assertTrue(problemList.getProblemList() instanceof ArrayList);
    }

    public void testGetProblem() {
        ProblemList problemList = new ProblemList();
        Problem problem = null;
        try {
            problem = new Problem(new Date(), "Title", "Description");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        problemList.addProblem(problem);

        //test that it got retrieved the correct problem
        Problem problemTest = problemList.getProblem(0);
        assertEquals(problem,problemTest); //compares references

        //test for invalid index
        try {
            problemList.getProblem(1);
            fail("Exception wasn't thrown for the index, was supposed to be.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Expected to get here. Index out of bounds.", true);
        }
    }

    public void testDeleteProblem() {
        ProblemList problemList = new ProblemList();
        Problem problem1 = null;
        try {
            problem1 = new Problem(new Date(), "Title1", "Description1");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        Problem problem2 = null;
        try {
            problem2 = new Problem(new Date(), "Title2", "Description2");
        } catch (TooManyCharactersException e){
            fail("Exception shouldn't have been thrown.");
        }

        //test deleting from empty list
        try {
            problemList.deleteProblem(1);
            fail("Exception wasn't thrown for deleting from an empty list, was supposed to be.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Expected to get here. Deleting from an empty list.", true);
        }

        //test index deleteProblem method
        problemList.addProblem(problem1);
        problemList.deleteProblem(0);
        assertEquals(0,problemList.sizeOfProblemList());

        //test problem deleteProblem method
        problemList.addProblem(problem1);
        problemList.deleteProblem(problem1);
        assertEquals(0,problemList.sizeOfProblemList());

        //test correct element was deleted with index
        problemList.addProblem(problem1);
        problemList.addProblem(problem2);
        assertTrue("Elements didn't add to list.",
                problemList.sizeOfProblemList() == 2);
        problemList.deleteProblem(0);
        //make sure problem was removed, leaving only one element in the list
        assertTrue(problemList.sizeOfProblemList() == 1);
        //make sure the remaining element is not the same as the one deleted
        assertNotSame(problem1,problemList.getProblem(0));
        problemList.deleteProblem(0); //make list empty again

        //test correct element was deleted with problem
        problemList.addProblem(problem1);
        problemList.addProblem(problem2);
        assertTrue("Elements didn't add to list.",
                problemList.sizeOfProblemList() == 2);
        problemList.deleteProblem(problem1);
        //make sure problem was removed, leaving only one element in the list
        assertTrue(problemList.sizeOfProblemList() == 1);
        //make sure the remaining element is not the same as the one deleted
        assertNotSame(problem1,problemList.getProblem(0));
        assertSame(problem2,problemList.getProblem(0));
        problemList.deleteProblem(0); //make list empty again

    }

    public void testAddProblem() {
        ProblemList problemList = new ProblemList();
        Problem problem = null;
        try {
            problem = new Problem(new Date(), "Title", "Description");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        problemList.addProblem(problem);
        assertEquals(problem,problemList.getProblem(0)); //compares references
        assertSame(problem,problemList.getProblem(0));
    }

    public void testGetProblemList(){
        ProblemList problemList = new ProblemList();
        ProblemList problemList1 = problemList;
        assertTrue(problemList.getProblemList().equals(problemList1.getProblemList()));
    }

    public void testSizeOfProblemList() {
        ProblemList problemList = new ProblemList();
        Problem problem = null;
        try {
            problem = new Problem(new Date(), "Title1", "Description1");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }

        //check 0
        assertEquals("Incorrect size returned.",
                problemList.sizeOfProblemList(), 0);

        //check 1
        problemList.addProblem(problem);
        assertEquals("Incorrect size returned.",
                problemList.sizeOfProblemList(), 1);

        problemList = new ProblemList();//to reset count

        //check size 50
        for (int i = 0; i < 50; i++){
            problemList.addProblem(problem);
        }
        assertEquals("Incorrect size returned.",
                problemList.sizeOfProblemList(), 50);
    }
}
