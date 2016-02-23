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

    public void removeReactant(int index){
        reactants.remove(index);
    }

    public void addProduct(Compound compound){
        products.add(compound);
    }

    public void removeProduct(int index){
        products.remove(index);
    }

    public String toString(){
        StringBuilder formulaStrBuilder = new StringBuilder();
        for(int i = 0; i < reactants.size(); ++i){
            if(reactants.get(i).getCoefficient() > 0){
                if(reactants.get(i).getCoefficient() > 1)
                    formulaStrBuilder.append(reactants.get(i).getCoefficient());
                formulaStrBuilder.append(reactants.get(i).toString());
                if(i != reactants.size() - 1){
                    formulaStrBuilder.append(" + ");
                }
            }
        }

        if(reactants.size() > 0 && products.size() > 0)
            formulaStrBuilder.append(" = ");

        for(int i = 0; i < products.size(); ++i){
            if(products.get(i).getCoefficient() > 0){
                if(products.get(i).getCoefficient() > 1)
                    formulaStrBuilder.append(products.get(i).getCoefficient());
                formulaStrBuilder.append(products.get(i).toString());
                if(i != products.size() - 1){
                    formulaStrBuilder.append(" + ");
                }
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

    public boolean checkBalance(){
        ArrayList<Integer> reactantAtomicNumbers = new ArrayList<>();
        ArrayList<Integer> reactantCounts = new ArrayList<>();
        ArrayList<Integer> productCounts = new ArrayList<>();

        for(int i = 0; i < reactants.size(); ++i){
            int coefficient = reactants.get(i).getCoefficient();
            for(int j = 0; j < reactants.get(i).getFormula().size(); ++j){
                if(reactantAtomicNumbers.indexOf(reactants.get(i).getFormula().get(j).getElementNumber()) == -1){
                    reactantAtomicNumbers.add(reactants.get(i).getFormula().get(j).getElementNumber());
                    reactantCounts.add(reactants.get(i).getFormula().get(j).getSubscript() * coefficient);
                }
                else{
                    reactantCounts.set(reactantAtomicNumbers.indexOf(reactants.get(i).getFormula().get(j).getElementNumber()),
                            reactantCounts.get(reactantAtomicNumbers.indexOf(reactants.get(i).getFormula().get(j).getElementNumber()))
                                    + (reactants.get(i).getFormula().get(j).getSubscript() * coefficient));
                }
            }
        }

        for(int i = 0; i < reactantAtomicNumbers.size(); ++i){
            productCounts.add(0);
        }

        for(int i = 0; i < products.size(); ++i){
            int coefficient = products.get(i).getCoefficient();
            for(int j = 0; j < products.get(i).getFormula().size(); ++j){
                if(reactantAtomicNumbers.indexOf(products.get(i).getFormula().get(j).getElementNumber()) == -1){
                    return false;
                }
                else{
                    productCounts.set(reactantAtomicNumbers.indexOf(products.get(i).getFormula().get(j).getElementNumber()),
                            productCounts.get(reactantAtomicNumbers.indexOf(products.get(i).getFormula().get(j).getElementNumber()))
                                    + (products.get(i).getFormula().get(j).getSubscript() * coefficient));
                }
            }
        }

        for(int i = 0; i < reactantAtomicNumbers.size(); ++i){
            if(!reactantCounts.get(i).equals(productCounts.get(i)))
                return false;
        }

        return true;
    }
}
