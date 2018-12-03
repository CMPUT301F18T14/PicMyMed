package com.example.picmymedcode;

import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.User;

import junit.framework.TestCase;

import org.junit.Test;

public class UserTest extends TestCase {

    @Test
    public void testUsername() {
        CareProvider careProvider = new CareProvider("123456789",
                "email@email.ca","1231231234");
        assertTrue("Wrong username", careProvider.getUsername().equals("12356789"));

        //testing if username is under 8 chars, aka, too short
        try {
            careProvider = new CareProvider("123456","test@t.t","1112221234");
            fail("Exception that was supposed to be thrown for the username, wasn't.");
        } catch (IllegalArgumentException e) {
            assertTrue("Expected to get here. Username too long.", true);
        }
    }

    public void testGetEmail() {
        Patient patient = new Patient("username", "test@email.com", "1111111111");
        assertEquals("test@email.com", patient.getEmail());

    }

    public void testSetEmail() {
        Patient patient = new Patient("username", "test@email.com", "1111111111");
        patient.setEmail("email@email.ca");
        assertEquals("email@email.ca", patient.getEmail());
    }

    public void testGetPhoneNumber() {
        Patient patient = new Patient("username", "test@email.com", "1111111111");
        assertEquals("1111111111", patient.getPhoneNumber());
    }

    public void testSetPhoneNumber() {
        Patient patient = new Patient("username", "test@email.com", "1111111111");
        patient.setPhoneNumber("1231231234");
        assertEquals("1231231234",patient.getPhoneNumber());
    }
}
