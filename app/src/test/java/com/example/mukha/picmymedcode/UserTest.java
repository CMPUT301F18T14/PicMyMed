package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.User;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testUsername() {
        User user = new CareProvider("test");
        assertTrue("Wrong username", user.getUsername().equals("test"));
    }

}
