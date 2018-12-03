package com.example.picmymedcode;

import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.User;

import junit.framework.TestCase;

import org.junit.Test;

public class UserTest extends TestCase {

    @Test
    public void testUsername() {
        User user = new CareProvider("test", "apu@a.com", "1234567898");
        assertTrue("Wrong username", user.getUsername().equals("test"));
    }

}
