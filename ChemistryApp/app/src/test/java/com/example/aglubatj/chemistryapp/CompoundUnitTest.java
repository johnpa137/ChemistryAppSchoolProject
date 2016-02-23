package com.example.aglubatj.chemistryapp;

import junit.framework.TestCase;

/**
 * Created by John on 2/22/2016.
 */
public class CompoundUnitTest extends TestCase {

    public void testGetElementNumber() throws Exception {
        CompoundUnit testCompoundUnit = new CompoundUnit(1, 2, 0);

        assertEquals(testCompoundUnit.getElementNumber(), 1);
    }

    public void testSetElementNumber() throws Exception {
        CompoundUnit testCompoundUnit = new CompoundUnit(1, 2, 0);
        testCompoundUnit.setElementNumber(8);

        assertEquals(testCompoundUnit.getElementNumber(), 8);
    }

    public void testGetSubscript() throws Exception {
        CompoundUnit testCompoundUnit = new CompoundUnit(1, 2, 0);
        assertEquals(testCompoundUnit.getSubscript(), 2);
    }

    public void testSetSubscript() throws Exception {
        CompoundUnit testCompoundUnit = new CompoundUnit(1, 2, 0);
        testCompoundUnit.setSubscript(3);

        assertEquals(testCompoundUnit.getSubscript(), 3);
    }

    public void testGetCharge() throws Exception {
        CompoundUnit testCompoundUnit = new CompoundUnit(1, 2, -3);

        assertEquals(testCompoundUnit.getCharge(), -3);
    }

    public void testSetCharge() throws Exception {
        CompoundUnit testCompoundUnit = new CompoundUnit(1, 2, 0);
        testCompoundUnit.setCharge(2);

        assertEquals(testCompoundUnit.getCharge(), 2);
    }

    public void testGetElement() throws Exception {
        CompoundUnit testCompoundUnit = new CompoundUnit(1, 2, 0);

        assertEquals(testCompoundUnit.getElement(), PeriodicTable.getPeriodicTable().getElement(1));
    }

    public void testToString() throws Exception {
        CompoundUnit testCompoundUnit = new CompoundUnit(1, 2, 1);

        assertEquals(testCompoundUnit.toString(), "H<sub>2</sub><sup>+</sup>");
    }
}