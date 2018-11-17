//// reference: https://stackoverflow.com/questions/12404650/assert-an-object-is-a-specific-type
//// accessed on 2018-10-27
//
//package com.example.mukha.picmymedcode;
//
//import junit.framework.TestCase;
//
//public class CareProviderTest extends TestCase {
//
//    public void testUsername() {
//        User user = new CareProvider("test", "123");
//        assertTrue("Wrong username", user.getUsername().equals("test"));
//    }
//
//    public void testPassword() {
//        User user = new CareProvider("test", "123");
//        assertTrue("Wrong password", user.getPassword().equals("123"));
//
//        String newPassword = "123456";
//        user.setPassword(newPassword);
//        assertTrue("Password did not change", user.getPassword().equals(newPassword));
//    }
//
//    public void testPatientList() {
//        // this test cannot resolve with: User user = new CareProvider("test", "123");
//        // why?
//        CareProvider user = new CareProvider("test", "123");
//        assertTrue("Did not return PatientList", user.getPatientList() instanceof PatientList);
//    }
//}
