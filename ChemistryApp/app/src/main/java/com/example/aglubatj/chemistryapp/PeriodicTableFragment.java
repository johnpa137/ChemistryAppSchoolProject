package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment class for the periodic table
 *
 * @author JP Aglubat
 * @version 3/6/2016
 */
public class PeriodicTableFragment extends Fragment {
    public int cellSize;
    public int cellSymbolSize;

    /**
     * Constructor.
     */
    public PeriodicTableFragment() {
        cellSize = 1;
        cellSymbolSize = 1;
    }

    /**
     * onCreateView for fragment classes
     *
     * @param inflater layout inflater
     * @param container layout container
     * @param savedInstanceState bundle savedinstancestate
     * @return inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_periodic_table, container, false);
    }

    /**
     * onActivity created for fragment classes, sets listeners for element views
     *
     * @param savedInstanceState bundle saved state
     */
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        PeriodicTable pTable = PeriodicTable.getPeriodicTable();
        for(int i = 0; i < PeriodicTable.NUMBER_OF_ELEMENTS; ++i){
            ElementView elv = (ElementView) getActivity().findViewById(getResources().getIdentifier("elv" + pTable.getElement(i + 1).getName(), "id", getActivity().getPackageName()));
            elv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickElementView(v);
                }
            });
        }
    }

    /**
     * Android onResume method, resizes element views to fit zoom
     */
    @Override
    public void onResume(){
        super.onResume();
        updateCellDimensions();
    }

    /**
     * Method to resize element views
     */
    private void updateCellDimensions(){
        PeriodicTable pTable = PeriodicTable.getPeriodicTable();
        for(int i = 0; i < PeriodicTable.NUMBER_OF_ELEMENTS; ++i){
            ElementView elv = (ElementView) getActivity().findViewById(getResources().getIdentifier("elv" + pTable.getElement(i + 1).getName(), "id", getActivity().getPackageName()));
            ViewGroup.LayoutParams params = elv.getLayoutParams();
            params.height = cellSize;
            params.width = cellSize;
            elv.setLayoutParams(params);
            elv.setSymbolSize((float) cellSymbolSize);
        }
    }

    /**
     * Method to set dimensions of element views from outside the class, used by parent activity
     *
     * @param size size of the cell
     * @param symbolSize size of the symbol on the cell
     */
    public void setCellDimensions(int size, int symbolSize){
        cellSize = size;
        cellSymbolSize = symbolSize;
        updateCellDimensions();
    }

    /**
     * Listener for the element views
     * @param view the view that was pressed
     */
    public void onClickElementView(View view){
        PeriodicTableActivity pTAct = (PeriodicTableActivity) getActivity();
        if(pTAct.chkElementDetails.isChecked()){
            ElementView elementView = (ElementView) view;
            Intent intent = new Intent(getActivity(), ElementDetailsActivity.class);
            intent.putExtra("atomicNumber", elementView.getElementNumber());
            startActivity(intent);
        }
        else{
            ElementView elementView = (ElementView) view;
            PeriodicTableActivity.compound.addElement(elementView.getElementNumber());
            pTAct.currentNumber = 0;

            pTAct.updateFormulaView();
        }
    }
}
