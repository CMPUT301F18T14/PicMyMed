package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.Model.RecordList;
import com.example.mukha.picmymedcode.Model.Problem;

import junit.framework.TestCase;

import java.util.Date;

public class ProblemTest extends TestCase {

        public void testConstructor(){
            //testing that the constructor initialized correctly
            Date date = new Date();
            Problem testProblem = null;
            try {
                testProblem = new Problem(date, "testProblem", "test description");
            } catch (IllegalArgumentException e) {
                fail("Exception shouldn't have been thrown.");
            }
            assertEquals("testProblem", testProblem.getTitle());
            assertEquals("test description", testProblem.getDescription());
            assertEquals(date,testProblem.getStartDate());

            //testing if title is over 30chars, aka, too long
            try {
                testProblem = new Problem(date, "ckMGtw9SkuEOtOG1Z1fvo7qQhhpNlz0",
                        "test description");
                fail("Exception wasn't thrown for the title, was supposed to be.");
            } catch (IllegalArgumentException e) {
                assertTrue("Expected to get here. Title too long.", true);
            }

            //testing if description is over 300 chars, aka, too long
            try {
                testProblem = new Problem(date,"testProblem","9uvDAE0rWBRb7FhvMtD4I0" +
                        "ES69rO7YKkDXpLWyx1CI4ALQ6GhZEvXGkLZnMAFF9FfupDCXk" +
                        "MBrTReSSj7TXMEXhFwZcobxOyFTUkvoKYHIYQoJfOlqvaE3guRx8n2" +
                        "73kyzCxh0qvWOwyeFMPiSgGXdg5GcUjwOcZ0eim1mIHckBty8CITdqmL" +
                        "Hb76iC6EYidTMEflLubBQ3WuyLoCOSftcwSRGNEZf4IHfOm4ubPPSs2VrcZ" +
                        "BBU6TPWYLWmTdxfBCfskT49tRreA8EAoMgiOMxGPwDPYhqygcjWJIuHCCOZoY");
                fail("Exception wasn't thrown for the description, was supposed to be.");
            } catch (IllegalArgumentException e) {
                assertTrue("Expected to get here. Description too long.", true);
            }
        }

        public void testGetTitle() {
            Problem testProblem = null;
            try {
                testProblem = new Problem(new Date(), "testProblem", "test description");
            } catch (IllegalArgumentException e) {
                fail("Exception shouldn't have been thrown.");
            }
            assertEquals("testProblem", testProblem.getTitle());

        }

        public void testSetTitle() {
            Problem testProblem = null;
            try {
                testProblem = new Problem(new Date(), "testProblem",
                        "test description");
                testProblem.setTitle("new testProblem");
            } catch (IllegalArgumentException e) {
                fail("Exception shouldn't have been thrown.");
            }
            assertEquals("new testProblem", testProblem.getTitle());

            //testing if title is over max title chars, 30chars
            try {
                testProblem.setTitle("ckMGtw9SuEOtOG1Z1kfvo7qQhhpNlz0");
                fail("Exception wasn't thrown for the title, was supposed to be.");
            } catch (IllegalArgumentException e) {
                assertTrue("Expected to get here. Title too long.", true);
            }

        }

        public void testGetDescription() {
            Problem testProblem = null;
            try {
                testProblem = new Problem(new Date(), "testProblem", "test description");
            } catch (IllegalArgumentException e) {
                fail("Exception shouldn't have been thrown.");
            }
            assertEquals("test description", testProblem.getDescription());
        }

        public void testSetDescription() {
            Problem testProblem = null;
            try {
                testProblem = new Problem(new Date(), "testProblem", "test description");
                testProblem.setDescription("new test description");
            } catch (IllegalArgumentException e) {
                fail("Exception shouldn't have been thrown.");
            }
            assertEquals("new test description", testProblem.getDescription());

            //description is 300 chars, aka, too long
            try {
                testProblem.setDescription("9uvDAE0rWBRb7FhvMtD4I0" +
                        "ES69rO7YKkDXpLWyx1CI4ALQ6GhZEvXGkLZnMAFF9FfupDCXk" +
                        "MBrTReSSj7TXMEXhFwZcobxOyFTUvoKYHIYQoJfOlqvaE3guRx8n2" +
                        "73kyzCxh0qvWOwyeFMPiSgGXkdg5GcUjwOcZ0eim1mIHckBty8CITdqmL" +
                        "Hb76iC6EYidTMEflLubBQ3WuyLoCOSftcwSRGNEZf4IHfOm4ubPPSs2VrcZ" +
                        "BBU6TPWYLWmTdxfBCfskT49tRreA8EAoMgiOMxGPwDPYhqygcjWJIuHCCOZoY");
                fail("Exception wasn't thrown for the description, was supposed to be.");
            } catch (IllegalArgumentException e) {
                assertTrue("Expected to get here. Description too long.", true);
            }
        }

        public void testGetRecordList() {
            Problem testProblem = null;
            try {
                testProblem = new Problem(new Date(), "testProblem", "test description");
            } catch (IllegalArgumentException e) {
                fail("Exception shouldn't have been thrown.");
            }
            assertTrue(testProblem.getRecordList() instanceof RecordList);
        }

        public void testGetDate() {
            Date date = new Date();
            Problem testProblem = null;
            try {
                testProblem = new Problem(date, "testProblem", "test description");
            } catch (IllegalArgumentException e) {
                fail("Exception shouldn't have been thrown.");
            }
            assertEquals(testProblem.getStartDate(), date);
         }

        public void testSetDate() {
            Date date = new Date();
            Problem testProblem = null;
            try {
                testProblem = new Problem(new Date(), "testProblem", "test description");
            } catch (IllegalArgumentException e) {
                fail("Exception shouldn't have been thrown.");
            }
            testProblem.setStartDate(date);
            assertEquals(testProblem.getStartDate(), date);
    }
    }


