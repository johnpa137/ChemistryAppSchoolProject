package com.example.aglubatj.chemistryapp;

import junit.framework.TestCase;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by John on 2/22/2016.
 */
public class CompoundTest extends TestCase {

    public void testAddCompoundUnit() throws Exception {
        Compound testCompound = new Compound();
        testCompound.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound.addCompoundUnit(new CompoundUnit(8, 1, 0));

        assertEquals(testCompound.toString(), "H<sub>2</sub>O");
    }

    public void testAddElement() throws Exception {
        Compound testCompound = new Compound();
        testCompound.addElement(1);

        assertEquals(testCompound.toString(), "H");
    }

    public void testAddSubscript() throws Exception {
        Compound testCompound = new Compound();
        testCompound.addElement(1);
        testCompound.addSubscript(2);

        assertEquals(testCompound.toString(), "H<sub>2</sub>");
    }

    public void testAddCharge() throws Exception {
        Compound testCompound = new Compound();
        testCompound.addElement(1);
        testCompound.addCharge(1);

        assertEquals(testCompound.toString(), "H<sup>+</sup>");
    }

    public void testBackspace() throws Exception {
        Compound testCompound = new Compound();
        testCompound.addElement(1);
        testCompound.addCharge(1);
        testCompound.backspace();

        assertEquals(testCompound.toString(), "H");
    }

    public void testSize() throws Exception {
        Compound testCompound = new Compound();
        testCompound.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound.addCompoundUnit(new CompoundUnit(8, 1, 0));

        assertEquals(testCompound.size(), 2);
    }

    public void testClear() throws Exception {
        Compound testCompound = new Compound();
        testCompound.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound.addCompoundUnit(new CompoundUnit(8, 1, 0));
        testCompound.clear();

        assertEquals(testCompound.size(), 0);
    }

    public void testGetMolarMass() throws Exception {
        Compound testCompound = new Compound();
        testCompound.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound.addCompoundUnit(new CompoundUnit(8, 1, 0));

        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        assertEquals(df.format(testCompound.getMolarMass()), "18.0152");
    }

    public void testChangeTo() throws Exception {
        Compound testCompound1 = new Compound();
        testCompound1.addCompoundUnit(new CompoundUnit(1, 2, 0));
        testCompound1.addCompoundUnit(new CompoundUnit(8, 1, 0));

        Compound testCompound2 = new Compound();
        testCompound2.addCompoundUnit(new CompoundUnit(11, 1, 0));
        testCompound2.addCompoundUnit(new CompoundUnit(17, 1, 0));

        testCompound1.changeTo(testCompound2);

        assertEquals(testCompound1.toString(), "NaCl");
    }
}