package com.example.cmput301f18t14.PicMyMed;

import com.example.cmput301f18t14.PicMyMed.Model.Problem;

import junit.framework.TestCase;

import java.util.Date;

public class ProblemTest extends TestCase {

        public void testGetTitle() {
            Problem testProblem = new Problem(new Date(), "testProblem", "test description");
            assertEquals("testProblem", testProblem.getTitle());

        }

        public void testSetTitle() {
            Problem testProblem = new Problem(new Date(), "testProblem", "test description");
            testProblem.setTitle("new testProblem");
            assertEquals("new testProblem", testProblem.getTitle());

        }

        public void testGetDescription() {
            Problem testProblem = new Problem(new Date(), "testProblem", "test description");
            assertEquals("test description", testProblem.getDescription());
        }

        public void testSetDescription() {
            Problem testProblem = new Problem(new Date(), "testProblem", "test description");
            testProblem.setDescription("new test description");
            assertEquals("new test description", testProblem.getDescription());
        }

        public void testGetRecordList() {
            Problem testProblem = new Problem(new Date(), "testProblem", "test description");
            assertTrue(testProblem.getRecordList() instanceof RecordList);
        }

        public void testGetDate() {
            Date date = new Date();
            Problem testProblem = new Problem(date, "testProblem", "test description");
            assertEquals(testProblem.getStartDate(), date);
         }

        public void testSetDate() {
            Date date = new Date();
            Problem testProblem = new Problem(new Date(), "testProblem", "test description");
            testProblem.setStartDate(date);
            assertEquals(testProblem.getStartDate(), date);
    }
    }


