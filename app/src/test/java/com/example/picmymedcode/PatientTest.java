//reference: https://stackoverflow.com/a/47890524 accessed on 2018-11-01

package com.example.picmymedcode;

import com.example.picmymedcode.Model.BodyLocationPhoto;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Photo;

import junit.framework.TestCase;

import java.util.ArrayList;

public class PatientTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        Patient patient = new Patient("username",
                "test@email.com", "1111111111");
        assertEquals("username", patient.getUsername());
        assertEquals("test@email.com", patient.getEmail());
        assertEquals("1111111111", patient.getPhoneNumber());
        assertTrue(patient.getProblemList() instanceof ArrayList);
        assertTrue(patient.getBodyLocationPhotoList() instanceof ArrayList);
    }

    public void testGetProblemList() {
        Patient patient = new Patient("username",
                "test@email.com", "1111111111");
        assertTrue(patient.getProblemList() instanceof ArrayList);
    }

    public void testIsPatient() {
        Patient patient = new Patient("username",
                "test@email.com", "1111111111");
        assertTrue("Is not a patient.", patient.isPatient());
    }

    public void testGetBodyLocationPhotoList() {
        Patient patient = new Patient("username",
                "test@email.com", "1111111111");

        assertTrue(patient.getBodyLocationPhotoList() instanceof ArrayList);
    }

    public void testAddBodyLocationPhoto() {
        BodyLocationPhoto photo = new BodyLocationPhoto("filepath");
        Patient patient = new Patient("username",
                "test@email.com", "1111111111");
        patient.addBodyLocationPhoto(photo);

        assertTrue(patient.getBodyLocationPhotoList().get(0).equals(photo));
    }
}
