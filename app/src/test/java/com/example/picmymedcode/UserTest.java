package com.example.picmymedcode;

import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.User;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testUsername() {
        CareProvider careProvider = new CareProvider("12356789",
                "email@email.ca","1231231234");
        assertTrue("Wrong username", careProvider.getUsername().equals("12356789"));
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
