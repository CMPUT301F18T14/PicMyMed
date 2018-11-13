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
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.addPatient(newPatient);

        assertEquals(newPatient, patientList.getPatient(0)); // Comparing objects
    }

    @Test
    public void testDeletePatient() {
        PatientList patientList = new PatientList();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.addPatient(newPatient);
        patientList.deletePatient(0);

        assertEquals(0,patientList.sizeOfPatientList()); // Comparing integers
    }

    @Test
    public void testAddPatient() {
        PatientList patientList = new PatientList();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.addPatient(newPatient);

        assertEquals(newPatient, patientList.getPatient(0)); // Comparing objects
    }

    @Test
    public void testHasPatient() {
        PatientList patientList = new PatientList();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.addPatient(newPatient);

        assertTrue(patientList.hasPatient(newPatient)); // Comparing booleans
    }

    @Test
    public void testSizeOfPatientList() {
        PatientList patientList = new PatientList();
        Patient newPatient = new Patient("Daenerys Targaryen", "motherofdragons@got.ca", "+9-999-999-9999");
        patientList.addPatient(newPatient);
        patientList.addPatient(newPatient);
        patientList.addPatient(newPatient);

        assertEquals(3, patientList.sizeOfPatientList()); // Comparing integers
    }
}