//reference: https://stackoverflow.com/a/47890524 accessed on 2018-11-01

package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.ProblemList;

import junit.framework.TestCase;

public class PatientTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        Patient patient = null;
        try {
            patient = new Patient("username",
                    "test@email.com", "1111111111");
        } catch (IllegalArgumentException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertEquals("username", patient.getUsername());
        assertEquals("test@email.com", patient.getEmail());
        assertEquals("1111111111", patient.getPhoneNumber());

        //testing if username is over 8chars, aka too long
        try {
            patient = new Patient("123456789","test@t.t","111222test");
            fail("Exception that was supposed to be thrown for the username, wasn't.");
        } catch (IllegalArgumentException e) {
            assertTrue("Expected to get here. Username too long.", true);
        }
    }

    public void testGetEmail() {
        Patient patient = null;
        try {
            patient = new Patient("username",
                    "test@email.com", "1111111111");
        } catch (IllegalArgumentException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertEquals("test@email.com", patient.getEmail());

    }

    public void testSetEmail() {
        Patient patient = null;
        try {
            patient = new Patient("username",
                    "test@email.com", "1111111111");
        } catch (IllegalArgumentException e) {
            fail("Exception shouldn't have been thrown.");
        }

        patient.setEmail("email@email.ca");
        assertEquals("email@email.ca", patient.getEmail());
    }

    public void testGetPhoneNumber() {
        Patient patient = null;
        try {
            patient = new Patient("username",
                    "test@email.com", "1111111111");
        } catch (IllegalArgumentException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertEquals("1111111111", patient.getPhoneNumber());
    }

    public void testSetPhoneNumber() {
        Patient patient = null;
        try {
            patient = new Patient("username",
                    "test@email.com", "1111111111");
        } catch (IllegalArgumentException e) {
            fail("Exception shouldn't have been thrown.");
        }
        patient.setPhoneNumber("1231231234");
        assertEquals("1231231234",patient.getPhoneNumber());
    }

    public void testGetProblemList() {
        Patient patient = null;
        try {
            patient = new Patient("username",
                    "test@email.com", "1111111111");
        } catch (IllegalArgumentException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertTrue(patient.getProblemList() instanceof ProblemList);
    }
}
