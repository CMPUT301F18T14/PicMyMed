package com.example.mukha.picmymedcode;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        User user = new Patient("Test", "test@t.t","111222test");
        assertEquals("Test",user.getUsername());
    }

    public void testUsername() {
        User user = new CareProvider("test");
        assertTrue("Wrong username", user.getUsername().equals("test"));
    }

}
