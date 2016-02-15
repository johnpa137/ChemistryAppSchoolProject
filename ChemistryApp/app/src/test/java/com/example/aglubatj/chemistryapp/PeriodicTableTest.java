package com.example.aglubatj.chemistryapp;

import junit.framework.TestCase;

import java.util.ArrayList;

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
        assertEquals("Iridium", testElement.getName());
        assertEquals("Ir", testElement.getSymbol());
        assertEquals(77, testElement.getAtomicNumber());
        assertEquals(192.217f, testElement.getAtomicWeight());
        assertEquals(9, testElement.getGroup());
        assertEquals(6, testElement.getPeriod());
        assertEquals("[Xe] 6s<sup>2</sup>4f<sup>14</sup>5d<sup>7</sup>", testElement.getElectronConfig());
    }

    public void testGetElementGroup() throws Exception {
        ArrayList<Element> testElementGroup = periodicTable.getElementGroup(4);
        assertNotNull("TestElementGroup is null", testElementGroup);
        assertEquals(testElementGroup.size(), 4);
        assertEquals(testElementGroup.get(0).getAtomicNumber(), 22);
        assertEquals(testElementGroup.get(1).getAtomicNumber(), 40);
        assertEquals(testElementGroup.get(2).getAtomicNumber(), 72);
        assertEquals(testElementGroup.get(3).getAtomicNumber(), 104);
    }

    public void testGetElementPeriod() throws Exception {
        ArrayList<Element> testElementPeriod = periodicTable.getElementPeriod(1);
        assertNotNull("TestElementGroup is null", testElementPeriod);
        assertEquals(testElementPeriod.size(), 2);
        assertEquals(testElementPeriod.get(0).getAtomicNumber(), 1);
        assertEquals(testElementPeriod.get(1).getAtomicNumber(), 2);
    }

    public void testGetElementGroupIntList() throws Exception {
        ArrayList<Integer> testElementGroup = periodicTable.getElementGroupIntList(4);
        assertNotNull("TestElementGroup is null", testElementGroup);
        assertEquals(testElementGroup.size(), 4);
        assertEquals(testElementGroup.get(0).intValue(), 22);
        assertEquals(testElementGroup.get(1).intValue(), 40);
        assertEquals(testElementGroup.get(2).intValue(), 72);
        assertEquals(testElementGroup.get(3).intValue(), 104);
    }

    public void testGetElementPeriodIntList() throws Exception {
        ArrayList<Integer> testElementPeriod = periodicTable.getElementPeriodIntList(1);
        assertNotNull("TestElementGroup is null", testElementPeriod);
        assertEquals(testElementPeriod.size(), 2);
        assertEquals(testElementPeriod.get(0).intValue(), 1);
        assertEquals(testElementPeriod.get(1).intValue(), 2);
    }

    public void testGetPeriodicTable() throws Exception {
        assertNotNull("PeriodicTable failed to build.", periodicTable);
    }
}