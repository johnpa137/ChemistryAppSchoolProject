package com.example.aglubatj.chemistryapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ElementDetailsActivity extends AppCompatActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // finishes this activity when it goes out of focus
    @Override
    public void onPause(){
        super.onPause();

        finish();
    }
}
