package com.example.mukha.picmymedcode;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class implements TDD for PatientList class
 *
 * @Date: November 1, 2018
 */

public class PatientListTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        PatientList patientList = new PatientList();
        assertTrue(patientList.getPatientList() instanceof ArrayList);

    }

    @Test
    public void testGetPatientList() {
        PatientList patientList = new PatientList();

        assertNotNull(patientList.getPatientList()); // Check if object is != null
        assertTrue(patientList.getPatientList() instanceof ArrayList); // Check if the return type
    }

    @Test
    public void testGetPatient() {
        PatientList patientList = new PatientList();
        Patient patient = null;
        try {
            patient = new Patient("Daenerys",
                    "motherofdragons@got.ca", "+9-999-999-9999");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }

        //test that the correct patient was retrieved
        patientList.addPatient(patient);
        assertEquals(patient, patientList.getPatient(0)); // Comparing objects

        //test for invalid index
        try {
            patientList.getPatient(1);
            fail("Exception wasn't thrown for the index, was supposed to be.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Expected to get here. Index out of bounds.", true);
        }
    }

    @Test
    public void testDeletePatient() {
        PatientList patientList = new PatientList();
        Patient patient = null;
        try {
            patient = new Patient("Daenerys",
                    "motherofdragons@got.ca", "+9-999-999-9999");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }

        //delete from an empty list
        try{
            patientList.deletePatient(0);
            fail("Exception wasn't thrown for deleting from an empty list, was supposed to be.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Expected to get here. Deleting from an empty list.", true);
        }

        //test that elements are getting deleted
        patientList.addPatient(patient);
        assertEquals("Patient wasn't added", 1, patientList.sizeOfPatientList());
        patientList.deletePatient(0);
        assertEquals(0,patientList.sizeOfPatientList());

        patientList = new PatientList(); //reset the number of elements to zero

        //test that the correct element was deleted
        Patient patient2 = null;
        try {
            patient2 = new Patient("Patient",
                    "motherofdragons@got.ca", "+9-999-999-9999");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        patientList.addPatient(patient);
        patientList.addPatient(patient2);
        assertNotSame(patient,patient2);//make sure the two elements are different
        assertEquals("Patients didn't add to the list.",
                2, patientList.sizeOfPatientList());
        //delete patient
        patientList.deletePatient(0);
        //make sure the patient was removed, leaving only one element in the list
        assertEquals("Patient wasn't deleted",1,
                patientList.sizeOfPatientList());
        //make sure the remaining patient wasn't the same as the one deleted
        assertNotSame(patient,patientList.getPatient(0));
        assertSame(patient2,patientList.getPatient(0));

    }

    @Test
    public void testAddPatient() {
        PatientList patientList = new PatientList();
        Patient patient = null;
        try {
            patient = new Patient("Daenerys",
                    "motherofdragons@got.ca", "+9-999-999-9999");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        patientList.addPatient(patient);
        assertEquals(patient, patientList.getPatient(0)); // Comparing objects
    }

    @Test
    public void testHasPatient() {
        PatientList patientList = new PatientList();
        Patient patient = null;
        try {
            patient = new Patient("Daenerys",
                    "motherofdragons@got.ca", "+9-999-999-9999");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        patientList.addPatient(patient);
        assertTrue(patientList.hasPatient(patient)); // Comparing booleans

        //test if the patient isn't in the list
        Patient newPatient = null;
        try {
            newPatient = new Patient("Daenerys",
                    "motherofdragons@got.ca", "+9-999-999-9999");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertFalse(patientList.hasPatient(newPatient));

    }

    @Test
    public void testSizeOfPatientList() {
        PatientList patientList = new PatientList();
        Patient newPatient = null;
        try {
            newPatient = new Patient("Daenerys",
                    "motherofdragons@got.ca", "+9-999-999-9999");
        } catch (TooManyCharactersException e) {
            fail("Exception shouldn't have been thrown.");
        }
        patientList.addPatient(newPatient);
        patientList.addPatient(newPatient);
        patientList.addPatient(newPatient);

        assertEquals(3, patientList.sizeOfPatientList()); // Comparing integers
    }
}