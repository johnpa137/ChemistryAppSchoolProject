package com.example.aglubatj.chemistryapp;

import java.util.ArrayList;

/**
 * Created by John on 2/14/2016.
 */
public class Equation {
    private ArrayList<Compound> reactants;
    private ArrayList<Compound> products;

    public Equation(){
        reactants = new ArrayList<>();
        products = new ArrayList<>();
    }

    public void addReactant(Compound compound){
        reactants.add(compound);
    }

    // remove all if int count = 0
    public void removeReactant(Compound compound){
        int index = reactants.indexOf(compound);
        reactants.remove(index);
    }

    public void removeReactant(int index){
        reactants.remove(index);
    }

    public void addProduct(Compound compound){
        products.add(compound);
    }

    public void removeProduct(Compound compound){
        int index = products.indexOf(compound);
        products.remove(index);
    }

    public void removeProduct(int index){
        products.remove(index);
    }

    public void balanceFormula(){
        ArrayList<Integer> elementNumbers;

        for(int i = 0; i < reactants.size(); ++i){
            reactants.get(i).getFormula();
        }
    }

    public String toString(){
        StringBuilder formulaStrBuilder = new StringBuilder();
        for(int i = 0; i < reactants.size(); ++i){
            formulaStrBuilder.append(reactants.get(i).getCoefficient());
            formulaStrBuilder.append(reactants.get(i).toString());
            if(i != reactants.size() - 1){
                formulaStrBuilder.append(" + ");
            }
        }

        formulaStrBuilder.append(" = ");

        for(int i = 0; i < products.size(); ++i){
            formulaStrBuilder.append(products.get(i).getCoefficient());
            formulaStrBuilder.append(products.get(i).toString());
            if(i != products.size() - 1){
                formulaStrBuilder.append(" + ");
            }
        }

        return formulaStrBuilder.toString();
    }

    public int getReactantCount(){
        return reactants.size();
    }

    public int getProductCount(){
        return products.size();
    }

    public Compound getReactant(int index){
        return reactants.get(index);
    }

    public Compound getProduct(int index){
        return products.get(index);
    }
}
