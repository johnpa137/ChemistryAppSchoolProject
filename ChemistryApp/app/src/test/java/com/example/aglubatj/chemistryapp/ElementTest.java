package com.example.aglubatj.chemistryapp;

import junit.framework.TestCase;

/**
 * Created by John on 2/16/2016.
 */
public class ElementTest extends TestCase {

    Element testElement;

    public void setUp() throws Exception {
        super.setUp();
        testElement = Element.getTestElement();
        assertNotNull(testElement);
    }

    public void testGetName() throws Exception {
        assertEquals("Iridium", testElement.getName());
    }

    public void testGetSymbol() throws Exception {
        assertEquals("Ir", testElement.getSymbol());
    }

    public void testGetAtomicNumber() throws Exception {
        assertEquals(77, testElement.getAtomicNumber());
    }

    public void testGetAtomicWeight() throws Exception {
        assertEquals(192.217f, testElement.getAtomicWeight());
    }

    public void testGetGroup() throws Exception {
        assertEquals(9, testElement.getGroup());

    }

    public void testGetPeriod() throws Exception {
        assertEquals(6, testElement.getPeriod());
    }

    public void testGetElectronConfig() throws Exception {
        assertEquals("[Xe] 6s<sup>2</sup>4f<sup>14</sup>5d<sup>7</sup>", testElement.getElectronConfig());
    }
}