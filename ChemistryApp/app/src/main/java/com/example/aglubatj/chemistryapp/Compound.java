package com.example.aglubatj.chemistryapp;

import java.util.ArrayList;

/**
 * Basically an array of compounds units with a coefficient to be used in a chemical equation

 * @author JP Aglubat
 * @version 3/6/2016
 */
public class Compound {
    private ArrayList<CompoundUnit> formula;
    private int coefficient = 1;

    /**
     * Constructor.
     */
    public Compound(){
        formula = new ArrayList<>();
    }

    /**
     * adds a compound unit to this compound
     * @param compoundUnit
     */
    public void addCompoundUnit(CompoundUnit compoundUnit){
        formula.add(compoundUnit);
    }

    /**
     * returns this compound array of compound units
     * @return an array of compound units
     */
    public ArrayList<CompoundUnit> getFormula() {
        return formula;
    }

    /**
     * returns this compounds coefficient
     * @return the coefficient
     */
    public int getCoefficient() {
        return coefficient;
    }

    /**
     * adds a compound unit according to the atomic number
     * @param atomicNumber atomic number of element
     */
    public void addElement(int atomicNumber){
        formula.add(new CompoundUnit(atomicNumber, 1, 0));
    }

    /**
     * adds a subscript to the last compound unit of the array
     * @param subscript subscript to be added
     */
    public void addSubscript(int subscript){
        if(formula.size() > 0 && subscript > 1){
            formula.get(formula.size() - 1).setSubscript(subscript);
        }
    }

    /**
     * adds a charge to the last compound unit of the array
     * @param charge charge to be added
     */
    public void addCharge(int charge){
        if(formula.size() > 0 && charge != 0){
            formula.get(formula.size() - 1).setCharge(charge);
        }
    }

    /**
     * removes either the last charge, subscript, element added in that order
     */
    public void backspace(){
        if(formula.size() > 0){
            if(formula.get(formula.size() - 1).getCharge() != 0){
                formula.get(formula.size() - 1).setCharge(0);
            }
            else if(formula.get(formula.size() - 1).getSubscript() > 1){
                formula.get(formula.size() - 1).setSubscript(1);
            }
            else{
                formula.remove(formula.size() - 1);
            }
        }
    }

    /**
     * setter for the coefficient of this compound
     * @param coefficient coefficient to be set
     */
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * returns this compound's chemical formula in string form
     * @return this compound's chemical formula in string form
     */
    public String toString(){
        StringBuilder compoundStrBuilder = new StringBuilder();
        for(int i = 0; i < formula.size(); ++i){
            compoundStrBuilder.append(formula.get(i).toString());
        }
        return compoundStrBuilder.toString();
    }

    /**
     * returns the number of compound units in this compound
     * @return number of compound units
     */
    public int size(){
        return formula.size();
    }

    /**
     * clears the compound unit array list
     */
    public void clear(){
        formula.clear();
    }

    /**
     * returns the molar mass of this compound in grams
     * @return molar mass of this compound in grams
     */
    public double getMolarMass(){
        double retValue = 0.0;

        for(int i = 0; i < formula.size(); ++i){
            double atomicWeight = formula.get(i).getElement().getAtomicWeight();
            retValue += atomicWeight * formula.get(i).getSubscript();
        }

        return retValue;
    }

    /**
     * converts this compound to another compound, essentially the assignment operator
     * @param compound compound to be assigned
     */
    public void changeTo(Compound compound){
        this.clear();
        for(int i = 0; i < compound.size(); ++i){
            this.addCompoundUnit(compound.getFormula().get(i));
        }
    }
}
