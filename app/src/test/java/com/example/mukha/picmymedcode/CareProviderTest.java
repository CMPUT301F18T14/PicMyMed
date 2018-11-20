// reference: https://stackoverflow.com/questions/12404650/assert-an-object-is-a-specific-type
// accessed on 2018-10-27

package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.Model.CareProvider;


import junit.framework.TestCase;

import java.util.ArrayList;

public class CareProviderTest extends TestCase {

    public void  testConstructor() {
        //testing that the constructor initialized correctly
        CareProvider careProvider = new CareProvider("123");
        assertEquals("123", careProvider.getUsername());
    }

    public void testUsername() {
        CareProvider user = new CareProvider("test");
        assertEquals("Wrong username", user.getUsername(), "test");
    }

    public void testPatientList() {
        // this test cannot resolve with: User user = new CareProvider("test", "123");
        // why?
        CareProvider user = new CareProvider("test");
        assertTrue("Did not return ArrayList<Patient>", user.getPatientList() instanceof ArrayList);
    }
}
