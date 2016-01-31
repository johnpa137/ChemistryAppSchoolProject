package com.example.aglubatj.chemistryapp;

import android.content.Context;
import java.util.HashMap;

/**
 * Created by John on 1/23/2016.
 */
public class PeriodicTable {
    private Context mContext;
    public HashMap<Integer, Element> Elements;
    private final int NUMBER_OF_ELEMENTS = 118;

    public PeriodicTable(Context context){
        mContext = context;
        generateElements();
    }

    public void generateElements(){
        String elementNames[] = mContext.getResources().getStringArray(R.array.elementNamesArray);
        String elementSymbols[] = mContext.getResources().getStringArray(R.array.elementSymbolsArr);
        int elementNumbers[] = mContext.getResources().getIntArray(R.array.elementNumberArray);
        String elementWeights[] = mContext.getResources().getStringArray(R.array.elementWeightArray);
        int elementGroups[] = mContext.getResources().getIntArray(R.array.elementGroupArray);
        int elementPeriods[] = mContext.getResources().getIntArray(R.array.elementPeriodArray);
        // elementViews = new ArrayList<ElementView>();
        Elements = new HashMap<>(NUMBER_OF_ELEMENTS);

        for(int i = 0; i < NUMBER_OF_ELEMENTS; ++i){
            Element element = new Element(elementNames[i], elementSymbols[i], elementNumbers[i], Float.parseFloat(elementWeights[i]), elementGroups[i], elementPeriods[i]);
            Elements.put(element.getAtomicNumber(), element);
        }
    }
}
