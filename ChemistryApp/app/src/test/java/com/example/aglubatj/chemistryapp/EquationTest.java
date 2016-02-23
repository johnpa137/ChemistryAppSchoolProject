package com.example.aglubatj.chemistryapp;

import junit.framework.TestCase;

/**
 * Created by John on 2/22/2016.
 */
public class EquationTest extends TestCase {

    public void testAddReactant() throws Exception {
        Equation testEquation = new Equation();
        Compound testCompound = new Compound();
        testCompound.addElement(1);
        testEquation.addReactant(testCompound);
        assertEquals(testEquation.toString(), "H");
    }

    public void testRemoveReactant() throws Exception {
        Equation testEquation = new Equation();
        Compound testCompound = new Compound();
        testCompound.addElement(1);
        testEquation.addReactant(testCompound);
        testEquation.removeReactant(0);
        assertEquals(testEquation.toString(), "");
    }

    public void testAddProduct() throws Exception {
        Equation testEquation = new Equation();
        Compound testCompound1 = new Compound();
        Compound testCompound2 = new Compound();
        testCompound1.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound2.addElement(1);
        testCompound2.setCoefficient(2);
        testEquation.addReactant(testCompound1);
        testEquation.addProduct(testCompound2);

        assertEquals(testEquation.toString(), "H<sub>2</sub> = 2H");
    }

    public void testRemoveProduct() throws Exception {
        Equation testEquation = new Equation();
        Compound testCompound1 = new Compound();
        Compound testCompound2 = new Compound();
        testCompound1.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound2.addElement(1);
        testCompound2.setCoefficient(2);
        testEquation.addReactant(testCompound1);
        testEquation.addProduct(testCompound2);

        assertEquals(testEquation.toString(), "H<sub>2</sub> = 2H");

        testEquation.removeProduct(0);

        assertEquals(testEquation.toString(), "H<sub>2</sub>");
    }

    public void testGetReactantCount() throws Exception {
        Equation testEquation = new Equation();
        Compound testCompound1 = new Compound();
        Compound testCompound2 = new Compound();
        testCompound1.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound2.addElement(1);
        testCompound2.setCoefficient(2);
        testEquation.addReactant(testCompound1);
        testEquation.addReactant(testCompound2);

        assertEquals(testEquation.getReactantCount(), 2);
    }

    public void testGetProductCount() throws Exception {
        Equation testEquation = new Equation();
        Compound testCompound1 = new Compound();
        Compound testCompound2 = new Compound();
        testCompound1.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound2.addElement(1);
        testCompound2.setCoefficient(2);
        testEquation.addReactant(testCompound1);
        testEquation.addReactant(testCompound2);
        testEquation.addProduct(testCompound1);
        testEquation.addProduct(testCompound2);

        assertEquals(testEquation.getProductCount(), 2);
    }

    public void testGetReactant() throws Exception {
        Equation testEquation = new Equation();
        Compound testCompound1 = new Compound();
        Compound testCompound2 = new Compound();
        testCompound1.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound2.addElement(1);
        testCompound2.setCoefficient(2);
        testEquation.addReactant(testCompound1);
        testEquation.addReactant(testCompound2);
        testEquation.addProduct(testCompound1);
        testEquation.addProduct(testCompound2);

        assertEquals(testEquation.getReactant(0).toString(), "H<sub>2</sub>");
    }

    public void testGetProduct() throws Exception {
        Equation testEquation = new Equation();
        Compound testCompound1 = new Compound();
        Compound testCompound2 = new Compound();
        testCompound1.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound2.addElement(1);
        testCompound2.setCoefficient(2);
        testEquation.addReactant(testCompound1);
        testEquation.addReactant(testCompound2);
        testEquation.addProduct(testCompound1);
        testEquation.addProduct(testCompound2);

        assertEquals(testEquation.getProduct(1).toString(), "H");
    }
}