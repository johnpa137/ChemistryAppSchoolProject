package com.example.aglubatj.chemistryapp;

/**
 * Basically an element number and a subscript value and an ionization charge value

 * @author JP Aglubat
 * @version 3/6/2016
 */
public class CompoundUnit {
    private int elementNumber;
    private int subscript;
    private int charge;

    /**
     * Constructor.
     *
     * @param elementNumber atomic number of element
     * @param subscript subscript for chemical expression
     * @param charge ionic charge of compound
     */
    public CompoundUnit(int elementNumber, int subscript, int charge){
        this.elementNumber = elementNumber;
        this.subscript = subscript;
        this.charge = charge;
    }

    /**
     * getter for compound unit's element
     *
     * @return atomic number of element
     */
    public int getElementNumber() {
        return elementNumber;
    }

    /**
     * setter for compound unit's element
     *
     * @param elementNumber atomic number of element
     */
    public void setElementNumber(int elementNumber) {
        this.elementNumber = elementNumber;
    }

    /**
     * getter for compound unit's subscript
     *
     * @return subscript of the compound unit
     */
    public int getSubscript() {
        return subscript;
    }

    /**
     * setter for compound unit's subscript
     *
     * @param subscript subscript of the compound unit
     */
    public void setSubscript(int subscript) {
        this.subscript = subscript;
    }

    /**
     * getter for compound unit's charge
     *
     * @return charge of the compound unit
     */
    public int getCharge() {
        return charge;
    }

    /**
     * setter for compound unit's charge
     *
     * @param  charge charge of the compound unit
     */
    public void setCharge(int charge) {
        this.charge = charge;
    }

    /**
     * returns this compound's element number's element object
     *
     * @return element object according to element number
     */
    public Element getElement(){
        return PeriodicTable.getPeriodicTable().getElement(elementNumber);
    }

    /**
     * returns this compound's chemical formula as a string
     *
     * @return chemical formula's string form
     */
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
