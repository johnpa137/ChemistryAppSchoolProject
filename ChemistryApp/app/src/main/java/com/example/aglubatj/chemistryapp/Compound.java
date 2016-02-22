package com.example.aglubatj.chemistryapp;

import java.util.ArrayList;

/**
 * Created by John on 2/14/2016.
 */
public class Compound {
    private ArrayList<CompoundUnit> formula;
    private double coefficient;

    public Compound(){
        formula = new ArrayList<>();
    }

    public void addCompoundUnit(CompoundUnit compoundUnit){
        formula.add(compoundUnit);
    }

    public ArrayList<CompoundUnit> getFormula() {
        return formula;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void addElement(int atomicNumber){
        formula.add(new CompoundUnit(atomicNumber, 1, 0));
    }

    public void addSubscript(int subscript){
        if(formula.size() > 0 && subscript > 1){
            formula.get(formula.size() - 1).setSubscript(subscript);
        }
    }

    public void addCharge(int charge){
        if(formula.size() > 0 && charge != 0){
            formula.get(formula.size() - 1).setCharge(charge);
        }
    }

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

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String toString(){
        StringBuilder compoundStrBuilder = new StringBuilder();
        for(int i = 0; i < formula.size(); ++i){
            compoundStrBuilder.append(formula.get(i).toString());
        }
        return compoundStrBuilder.toString();
    }

    public int size(){
        return formula.size();
    }

    public void clear(){
        formula.clear();
    }

    public double getMolarMass(){
        double retValue = 0.0;

        for(int i = 0; i < formula.size(); ++i){
            double atomicWeight = formula.get(i).getElement().getAtomicWeight();
            retValue += atomicWeight * formula.get(i).getSubscript();
        }

        return retValue;
    }

    public void changeTo(Compound compound){
        this.clear();
        for(int i = 0; i < compound.size(); ++i){
            this.addCompoundUnit(compound.getFormula().get(i));
        }
    }
}
