package com.example.cmput301f18t14.PicMyMed;

import com.example.cmput301f18t14.PicMyMed.Model.CareProvider;
import com.example.cmput301f18t14.PicMyMed.Model.User;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testUsername() {
        User user = new CareProvider("test");
        assertTrue("Wrong username", user.getUsername().equals("test"));
    }

}
