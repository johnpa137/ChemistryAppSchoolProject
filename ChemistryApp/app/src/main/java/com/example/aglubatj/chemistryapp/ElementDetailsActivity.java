package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

/**
 * Activity class for Element Details screen.
 *
 * @author JP Aglubat
 * @version 3/6/2016
 */
public class ElementDetailsActivity extends AppCompatActivity {

    /**
     * Android onCreate method.
     *
     * @param savedInstanceState the class state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementdetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PeriodicTable periodicTable = PeriodicTable.getPeriodicTable();

        Intent intent = getIntent();
        intent.getExtras();
        int atomicNumber = intent.getIntExtra("atomicNumber", -1);
        String name = periodicTable.getElement(atomicNumber).getName();
        String symbol = periodicTable.getElement(atomicNumber).getSymbol();
        float atomicWeight = periodicTable.getElement(atomicNumber).getAtomicWeight();
        int group = periodicTable.getElement(atomicNumber).getGroup();
        int period = periodicTable.getElement(atomicNumber).getPeriod();
        Element element = new Element(name, symbol, atomicNumber, atomicWeight, group, period);

        TextView lblNameValue = (TextView) findViewById(R.id.lblNameValue);
        lblNameValue.setText(element.getName());
        TextView lblSymbolValue = (TextView) findViewById(R.id.lblSymbolValue);
        lblSymbolValue.setText(element.getSymbol());
        TextView lblAtomicNumberValue = (TextView) findViewById(R.id.lblAtomicNumberValue);
        lblAtomicNumberValue.setText(String.valueOf(element.getAtomicNumber()));
        TextView lblAtomicWeightValue = (TextView) findViewById(R.id.lblAtomicWeightValue);
        lblAtomicWeightValue.setText(String.valueOf(element.getAtomicWeight()));
        TextView lblGroupValue = (TextView) findViewById(R.id.lblGroupValue);
        if(element.getGroup() != 0)
            lblGroupValue.setText(String.valueOf(element.getGroup()));
        else
            lblGroupValue.setText("N/A");
        TextView lblPeriodValue = (TextView) findViewById(R.id.lblPeriodValue);
        lblPeriodValue.setText(String.valueOf(element.getPeriod()));
        TextView lblElectronConfigValue = (TextView) findViewById(R.id.lblElectronConfigValue);
        lblElectronConfigValue.setText(Html.fromHtml(element.getElectronConfig()));
    }

    /**
     * Android onPause method. Terminates activity when out of focus.
     */
    @Override
    public void onPause(){
        super.onPause();

        finish();
    }
}
