package com.example.aglubatj.chemistryapp;

import junit.framework.TestCase;

/**
 * Created by John on 1/30/2016.
 */
public class PeriodicTableTest extends TestCase {

    PeriodicTable periodicTable;

    public void setUp() throws Exception {
        super.setUp();
        periodicTable = PeriodicTable.getPeriodicTable();
    }

    public void testGetElement() throws Exception {
        Element testElement = periodicTable.getElement(77);
        assertNotNull("TestElement is null.", testElement);
        assertEquals("Hydrogen", testElement.getName());
        assertEquals("Iridium", testElement.getSymbol());
        assertEquals(77, testElement.getAtomicNumber());
        assertEquals(192.217f, testElement.getAtomicWeight());
        assertEquals(9, testElement.getGroup());
        assertEquals(6, testElement.getPeriod());
        assertEquals("[Xe] 6s<sup>2</sup>4f<sup>14</sup>5d<sup>7</sup>", testElement.getElectronConfig());
    }

    public void testGetPeriodicTable() throws Exception {
        assertNotNull("PeriodicTable failed to build.", periodicTable);
    }
}