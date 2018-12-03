// reference: https://stackoverflow.com/questions/12404650/assert-an-object-is-a-specific-type
// accessed on 2018-10-27

package com.example.picmymedcode;

import com.example.picmymedcode.Model.CareProvider;


import junit.framework.TestCase;

import java.util.ArrayList;

public class CareProviderTest extends TestCase {

    public void  testConstructor() {
        //testing that the constructor initialized correctly
        CareProvider careProvider = new CareProvider("123456789",
                "email@email.ca","1231231234");
        assertEquals("12356789", careProvider.getUsername());
        assertEquals("email@email.ca",careProvider.getEmail());
        assertEquals("1231231234", careProvider.getPhoneNumber());
        assertTrue("Did not return ArrayList<Patient>",
                careProvider.getPatientList() instanceof ArrayList);

    }

    public void testGetPatientList() {
        CareProvider careProvider = new CareProvider("12356789",
                "email@email.ca","1231231234");
        assertTrue("Did not return ArrayList<Patient>",
                careProvider.getPatientList() instanceof ArrayList);
    }

    public void testIsPatient() {
        CareProvider careProvider = new CareProvider("12356789",
                "email@email.ca","1231231234");
        assertFalse("Is a patient.",careProvider.isPatient()==false);
    }
}
