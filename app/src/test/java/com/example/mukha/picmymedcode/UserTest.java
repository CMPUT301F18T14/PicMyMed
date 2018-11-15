package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.User;
import com.example.mukha.picmymedcode.View.TooManyCharactersException;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        User user = null;
        try {
            user = new Patient("Test", "test@t.t","111222test");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertEquals("Test",user.getUsername());

        //testing if username is over 8 chars, aka, too long
        try {
            user = new Patient("123456789","test@t.t","111222test");
            fail("Exception that was supposed to be thrown for the username, wasn't.");
        } catch (TooManyCharactersException e) {
            assertTrue("Expected to get here. Username too long.", true);
        }
    }

    public void testGetUsername() {
        User user = null;
        try {
            user = new CareProvider("test");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertTrue("Wrong username", user.getUsername().equals("test"));
    }

}
