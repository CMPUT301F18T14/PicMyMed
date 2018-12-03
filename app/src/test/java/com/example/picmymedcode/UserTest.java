package com.example.picmymedcode;

import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.User;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testUsername() {
        User user = new CareProvider("testPatient", "test@gmail.com", "1231231111");
        assertTrue("Wrong username", user.getUsername().equals("test"));
    }

}
