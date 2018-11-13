package com.example.mukha.picmymedcode;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testUsername() {
        User user = new CareProvider("test");
        assertTrue("Wrong username", user.getUsername().equals("test"));
    }

}
