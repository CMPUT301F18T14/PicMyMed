package com.example.cmput301f18t14.picmymed;

import com.example.cmput301f18t14.picmymed.Model.Patient;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

/**
 * This class implements TDD for Patient (patientList) class
 *
 * @Date: November 1, 2018
 */

public class PatientListTest extends TestCase {

    public void testConstructor() {

    }

    @Test
    public void testGetPatientList() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();

        assertNotNull(patientList); // Check if object is != null
        assertTrue(patientList instanceof ArrayList); // Check if the return type
    }

    @Test
    public void testGetPatient() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.add(newPatient);

        assertEquals(newPatient, patientList.get(0)); // Comparing objects
    }

    @Test
    public void testDeletePatient() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.add(newPatient);
        patientList.remove(0);

        assertEquals(0,patientList.size()); // Comparing integers
    }

    @Test
    public void testAddPatient() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.add(newPatient);

        assertEquals(newPatient, patientList.get(0)); // Comparing objects
    }

    @Test
    public void testHasPatient() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.add(newPatient);

        assertTrue(patientList.contains(newPatient)); // Comparing booleans
    }

    @Test
    public void testSizeOfPatientList() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.add(newPatient);
        patientList.add(newPatient);
        patientList.add(newPatient);

        assertEquals(3, patientList.size()); // Comparing integers
    }
}