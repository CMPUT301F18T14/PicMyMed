package com.example.mukha.picmymedcode;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testUsername() {
        User user = new CareProvider("test");
        assertTrue("Wrong username", user.getUsername().equals("test"));
    }

//    public void testPassword() {
//        User user = new CareProvider("test");
//        assertTrue("Wrong password", user.getPassword().equals("123"));
//
//        String newPassword = "123456";
//        user.setPassword(newPassword);
//        assertTrue("Password did not change", user.getPassword().equals(newPassword));
//    }
}
