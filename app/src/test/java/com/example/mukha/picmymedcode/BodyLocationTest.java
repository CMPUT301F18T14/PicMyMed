
package com.example.mukha.picmymedcode;

import com.example.picmymedcode.Model.BodyLocation;

import junit.framework.TestCase;

public class BodyLocationTest extends TestCase {
    public void testAddFaceBodyLocation () {
        BodyLocation bodyLocation = new BodyLocation();

        bodyLocation.addBodyLocation("Face");
        assertTrue(bodyLocation.getLocation("Face"));
    }
    public void testAddRightShoulderBodyLocation() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Right Shoulder");
        assertTrue(bodyLocation.getLocation("Right Shoulder"));
    }

    public void testAddLeftShoulderBodyLocation () {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Left Shoulder");
        assertTrue(bodyLocation.getLocation("Left Shoulder"));
    }

    public void testAddRightArmBodyLocation() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Right Arm");
        assertTrue(bodyLocation.getLocation("Right Arm"));
    }

    public void testAddLeftArmBodyLocation() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Left Arm");
        assertTrue(bodyLocation.getLocation("Left Arm"));
    }

    public void testAddUpperChestBodyLocation() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Upper Chest");
        assertTrue(bodyLocation.getLocation("Upper Chest"));
    }

    public void testAddTorsoLocation() {

        BodyLocation bodyLocation = new BodyLocation();

        bodyLocation.addBodyLocation("Torso");
        assertTrue(bodyLocation.getLocation("Torso"));
    }

    public void testAddRightLeg() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Right Leg");
        assertTrue(bodyLocation.getLocation("Right Leg"));
    }

    public void testAddLeftLeg() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Left Leg");
        assertTrue(bodyLocation.getLocation("Left Leg"));
    }

    public void testRemoveFaceBodyLocation () {
        BodyLocation bodyLocation = new BodyLocation();

        bodyLocation.addBodyLocation("Face");
        bodyLocation.removeBodyLocation("Face");
        assertFalse(bodyLocation.getLocation("Face"));
    }
    public void testRemoveRightShoulderBodyLocation() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Right Shoulder");
        bodyLocation.removeBodyLocation("Right Shoulder");
        assertFalse(bodyLocation.getLocation("Right Shoulder"));
    }

    public void testRemoveLeftShoulderBodyLocation () {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Right Shoulder");
        bodyLocation.removeBodyLocation("Right Shoulder");
        assertFalse(bodyLocation.getLocation("Left Shoulder"));
    }

    public void testRemoveRightArmBodyLocation() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Right Arm");
        bodyLocation.removeBodyLocation("Right Arm");
        assertFalse(bodyLocation.getLocation("Right Arm"));
    }

    public void testRemoveLeftArmBodyLocation() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Left Arm");
        bodyLocation.removeBodyLocation("Left Arm");
        assertFalse(bodyLocation.getLocation("Left Arm"));
    }

    public void testRemoveUpperChestBodyLocation() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Upper Chest");
        bodyLocation.removeBodyLocation("Upper Chest");
        assertFalse(bodyLocation.getLocation("Upper Chest"));
    }

    public void testRemoveTorsoLocation() {

        BodyLocation bodyLocation = new BodyLocation();

        bodyLocation.addBodyLocation("Torso");
        bodyLocation.removeBodyLocation("Torso");
        assertFalse(bodyLocation.getLocation("Torso"));
    }

    public void testRemoveRightLeg() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Right Leg");
        bodyLocation.removeBodyLocation("Right Leg");
        assertFalse(bodyLocation.getLocation("Right Leg"));
    }

    public void testRemoveLeftLeg() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.addBodyLocation("Left Leg");
        bodyLocation.removeBodyLocation("Left Leg");
        assertFalse(bodyLocation.getLocation("Left Leg"));
    }



}
