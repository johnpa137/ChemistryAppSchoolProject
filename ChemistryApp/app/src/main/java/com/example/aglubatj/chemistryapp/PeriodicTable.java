package com.example.aglubatj.chemistryapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class contains an arrayList and variables that allow access to the elements
 * of the periodic table
 *
 * @author JP Aglubat
 * @version 3/6/2016
 */
public class PeriodicTable {
    private static PeriodicTable pTable;
    private static ArrayList<Element> Elements;
    public static final int NUMBER_OF_ELEMENTS = 118;
    public static final int NUMBER_OF_PERIODS = 7;
    public static final int NUMBER_OF_SPECIAL_GROUPS = 2; // lanthanoid and actinoid series
    public static final int NUMBER_OF_GROUPS = 18;
    private static final int MAX_NUMBER_OF_ELEMENTS_IN_A_GROUP = 7;
    private static final int MAX_NUMBER_OF_ELEMENTS_IN_A_PERIOD = 18;
    public static Compound passObject;
    public static PeriodicTableHelper helper;

    /**
     * Constructor.
     */
    private PeriodicTable() {
        new GenerateElementsTask().execute();
    }

    /**
     * Method that accesses the database and fills the element arrayList
     */
    private void generateElements(){
        Elements = new ArrayList<>(NUMBER_OF_ELEMENTS);
        helper.getElementArray(Elements);
    }

    /**
     * Method that returns an element object based on the atomic number passed
     *
     * @param atomicNumber atomic number of the element requested
     * @return the element object of the atomic number requested
     */
    public Element getElement(int atomicNumber){
        return Elements.get(atomicNumber - 1);
    }

    /**
     * Method that returns static periodic table object of this class
     *
     * @return the singleton periodic table object
     */
    public static PeriodicTable getPeriodicTable(){
        if(pTable == null)
            pTable = new PeriodicTable();
        return pTable;
    }

    /**
     * Method that returns an arraylist of elements based on the group passed
     *
     * @param group group number of the elemental group requested
     * @return an array list of element objects of the group
     */
    public ArrayList<Element> getElementGroup(int group){
        ArrayList<Element> ElementGroup = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS_IN_A_GROUP);
        for(int i = 0; i < NUMBER_OF_ELEMENTS; ++i){
            if(Elements.get(i).getGroup() == group)
                ElementGroup.add(Elements.get(i));
        }
        ElementGroup.trimToSize();
        return ElementGroup;
    }

    /**
     * Method that returns an arraylist of elements based on the period passed
     *
     * @param period period number of the elemental group requested
     * @return an array list of element objects of the period
     */
    public ArrayList<Element> getElementPeriod(int period){
        ArrayList<Element> ElementPeriod = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS_IN_A_PERIOD);
        for(int i = 0; i < NUMBER_OF_ELEMENTS; ++i){
            if(Elements.get(i).getPeriod() == period)
                ElementPeriod.add(Elements.get(i));
        }
        ElementPeriod.trimToSize();
        return ElementPeriod;
    }

    /**
     * Method that returns an arraylist of atomic numbers based on the group passed
     *
     * @param group group number of the elemental group requested
     * @return an array list of element atomic numbers of the group
     */
    public ArrayList<Integer> getElementGroupIntList(int group){
        ArrayList<Integer> ElementGroup = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS_IN_A_GROUP);
        for(int i = 0; i < NUMBER_OF_ELEMENTS; ++i){
            if(Elements.get(i).getGroup() == group)
                ElementGroup.add(Elements.get(i).getAtomicNumber());
        }
        ElementGroup.trimToSize();
        return ElementGroup;
    }

    /**
     * Method that returns an arraylist of atomic numbers based on the period passed
     *
     * @param period period number of the elemental group requested
     * @return an array list of element atomic numbers of the period
     */
    public ArrayList<Integer> getElementPeriodIntList(int period){
        ArrayList<Integer> ElementPeriod = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS_IN_A_PERIOD);
        for(int i = 0; i < NUMBER_OF_ELEMENTS; ++i){
            if(Elements.get(i).getPeriod() == period)
                ElementPeriod.add(Elements.get(i).getAtomicNumber());
        }
        ElementPeriod.trimToSize();
        return ElementPeriod;
    }

    /**
     * Private inner class to set up a thread to populate all of the data
     * array lists from the database.
     */
    private class GenerateElementsTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            pTable.generateElements();
            return (pTable != null);
        }
    }
}
