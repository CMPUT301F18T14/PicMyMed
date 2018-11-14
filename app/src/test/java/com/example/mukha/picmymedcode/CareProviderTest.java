// reference: https://stackoverflow.com/questions/12404650/assert-an-object-is-a-specific-type
// accessed on 2018-10-27

package com.example.mukha.picmymedcode;

import junit.framework.TestCase;

public class CareProviderTest extends TestCase {

    public void  testConstructor() {
        //testing that the constructor initialized correctly
        CareProvider careProvider = null;
        try {
            careProvider = new CareProvider("123");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertEquals("123", careProvider.getUsername());

        //testing if username is over 8chars, aka too long
        try {
            careProvider = new CareProvider("123456789");
            fail("Exception that was supposed to be thrown for the username, wasn't.");
        } catch (TooManyCharactersException e) {
            assertTrue("Expected to get here. Username too long.", true);
        }
    }

    public void testUsername() {
        CareProvider careProvider = null;
        try {
            careProvider = new CareProvider("test");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertTrue("Wrong username", careProvider.getUsername().equals("test"));
    }

    public void testPatientList() throws TooManyCharactersException {
        // this test cannot resolve with: User user = new CareProvider("test", "123");
        // why?
        CareProvider user = new CareProvider("test");
        assertTrue("Did not return PatientList", user.getPatientList() instanceof PatientList);
    }
}
