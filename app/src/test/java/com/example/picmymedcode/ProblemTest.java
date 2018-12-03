package com.example.picmymedcode;

import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;

public class ProblemTest extends TestCase {

    public void testConstructor() {
        Date date = new Date();
        Problem testProblem = new Problem("testUsername", date.toString(),
                "testProblem", "test description");

        assertEquals("Wrong Username", "testUsername", testProblem.getUsername());
        assertEquals("Wrong Date", date.toString(), testProblem.getStartDate());
        assertEquals("Wrong Title", "testProblem", testProblem.getTitle());
        assertEquals("Wrong Description", "test description",
                testProblem.getDescription());
        assertTrue("Did not return ArrayList<Record>",
                testProblem.getRecordList() instanceof ArrayList);
        assertTrue("Did not return ArrayList<String>",
                testProblem.getCommentList() instanceof ArrayList);
    }

    public void testGetTitle() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");
        assertEquals("testProblem", testProblem.getTitle());
    }

    public void testSetTitle() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");

        testProblem.setTitle("new testProblem");
        assertEquals("new testProblem", testProblem.getTitle());
    }

    public void testGetDescription() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");

        assertEquals("test description", testProblem.getDescription());
    }

    public void testSetDescription() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");

        testProblem.setDescription("new test description");
        assertEquals("new test description", testProblem.getDescription());
    }

    public void testGetRecordList() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");

        assertTrue(testProblem.getRecordList() instanceof ArrayList);
    }

    public void testGetStartDate() {
        Date date = new Date();
        Problem testProblem = new Problem("user", date.toString(),
                "testProblem", "test description");

        assertEquals(testProblem.getStartDate(), date.toString());
    }

    public void testSetStartDate() {
        Date date = new Date();
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");

        testProblem.setStartDate(date.toString());
        assertEquals(testProblem.getStartDate(), date.toString());
    }

    public void testGetProblemID() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");

        assertNull(testProblem.getProblemID());
    }

    public void testSetProblemID() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");

        testProblem.setProblemID("testID");
        assertEquals("testID",testProblem.getProblemID());
    }

    public void testSetUsername() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");
        testProblem.setUsername("NewUser");

        assertEquals("NewUser", testProblem.getUsername());
    }

    public void testGetUsername() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");

        assertEquals("user",testProblem.getUsername());
    }

    public void testAddCommentList() {
        Problem testProblem = new Problem("user", new Date().toString(),
                "testProblem", "test description");
        testProblem.addCommentList("new comment");

        assertEquals("new comment", testProblem.getCommentList().get(0));
    }

}
