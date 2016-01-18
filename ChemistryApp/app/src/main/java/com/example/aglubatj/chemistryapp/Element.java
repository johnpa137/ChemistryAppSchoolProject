package com.example.aglubatj.chemistryapp;

import java.awt.font.TextAttribute;
import java.text.AttributedString;

/**
 * Created by John on 1/17/2016.
 */
public class Element {
    private String name;
    private int atomicNumber;
    private double atomicWeight;
    private int group;
    private int period;
    private int valElecCnt;
    // private AttributedString electronConfig;
    private String electronConfig;
    public Element(){

    }

    public Element(String name, int atomicNumber, double atomicWeight, int group, int period, int valElecCnt, String electronConfig) {
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicWeight = atomicWeight;
        this.group = group;
        this.period = period;
        this.valElecCnt = valElecCnt;
        this.electronConfig = electronConfig;
    }

    public static Element getTestElement(){
        String name = "Hydrogen";
        int atomicNumber = 1;
        double atomicWeight = 1.008;
        int group = 1;
        int period = 1;
        int valElecCnt = 1;
        // AttributedString electronConfig = new AttributedString("1s1 ");
        // electronConfig.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 3, 4);
        String electronConfig = "1s<sup>1</sup>";

        Element retVal = new Element(name, atomicNumber, atomicWeight, group, period, valElecCnt, electronConfig);

        return retVal;
    }

    public String getName() {
        return name;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public double getAtomicWeight() {
        return atomicWeight;
    }

    public int getGroup() {
        return group;
    }

    public int getPeriod() {
        return period;
    }

    public int getValElecCnt() {
        return valElecCnt;
    }

    public String getElectronConfig() {
        return electronConfig;
    }
}
