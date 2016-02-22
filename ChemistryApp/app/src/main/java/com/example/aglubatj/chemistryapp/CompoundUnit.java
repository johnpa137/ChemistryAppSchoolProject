package com.example.aglubatj.chemistryapp;

/**
 * Basically an element number and a subscript value and an ionization charge value
 * Created by John on 2/16/2016.
 */
public class CompoundUnit {
    private int elementNumber;
    private int subscript;
    private int charge;

    public CompoundUnit(int elementNumber, int subscript, int charge){
        this.elementNumber = elementNumber;
        this.subscript = subscript;
        this.charge = charge;
    }

    public int getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(int elementNumber) {
        this.elementNumber = elementNumber;
    }

    public int getSubscript() {
        return subscript;
    }

    public void setSubscript(int subscript) {
        this.subscript = subscript;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public Element getElement(){
        return PeriodicTable.getPeriodicTable().getElement(elementNumber);
    }

    public String toString(){
        StringBuilder formulaStrBuilder = new StringBuilder();
        formulaStrBuilder.append(PeriodicTable.getPeriodicTable().getElement(elementNumber).getSymbol());
        if(subscript > 1){
            formulaStrBuilder.append("<sub>");
            formulaStrBuilder.append(subscript);
            formulaStrBuilder.append("</sub>");
        }
        if(charge != 0){
            formulaStrBuilder.append("<sup>");
            if(charge > 0) {
                if(charge > 1)
                    formulaStrBuilder.append(charge);
                formulaStrBuilder.append("+");
            }
            else {
                if(charge < -1)
                    formulaStrBuilder.append(-charge);
                formulaStrBuilder.append("-");
            }
            formulaStrBuilder.append("</sup>");
        }
        return formulaStrBuilder.toString();
    }
}
