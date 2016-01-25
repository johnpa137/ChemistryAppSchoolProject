package com.example.aglubatj.chemistryapp;

import android.content.Intent;
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

    private Element element;
    private String name;
    private String symbol;
    private int atomicNumber;
    private float atomicWeight;
    private int group;
    private int period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementdetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // testElement = Element.getTestElement();

        Intent intent = getIntent();
        intent.getExtras();
        name = intent.getStringExtra("name");
        symbol = intent.getStringExtra("symbol");
        atomicNumber = intent.getIntExtra("atomicNumber", -1);
        atomicWeight = intent.getFloatExtra("atomicWeight", -1);
        group = intent.getIntExtra("group", -1);
        period = intent.getIntExtra("period", -1);
        element = new Element(name, symbol, atomicNumber, atomicWeight, group, period);

        TextView lblNameValue = (TextView) findViewById(R.id.lblNameValue);
        lblNameValue.setText(element.getName());
        TextView lblSymbolValue = (TextView) findViewById(R.id.lblSymbolValue);
        lblSymbolValue.setText(element.getSymbol());
        TextView lblAtomicNumberValue = (TextView) findViewById(R.id.lblAtomicNumberValue);
        lblAtomicNumberValue.setText(String.valueOf(element.getAtomicNumber()));
        TextView lblAtomicWeightValue = (TextView) findViewById(R.id.lblAtomicWeightValue);
        lblAtomicWeightValue.setText(String.valueOf(element.getAtomicWeight()));
        TextView lblGroupValue = (TextView) findViewById(R.id.lblGroupValue);
        lblGroupValue.setText(String.valueOf(element.getGroup()));
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

    public void onBtnAddtoFormulaClick(View view){
        Spinner spnOptions = (Spinner) findViewById(R.id.spnOptions);
        String option = String.valueOf(spnOptions.getSelectedItem());
        EditText edtAmount = (EditText) findViewById(R.id.edtAmount);
        String amount = String.valueOf(edtAmount.getText());
        TextView lblNameValue = (TextView) findViewById(R.id.lblNameValue);
        String name = String.valueOf(lblNameValue.getText());

        if(option.equals(getString(R.string.spnOptionsMolesStr))){
            Toast.makeText(this.getApplicationContext(), "You have selected to add " + amount + " moles of " + name, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this.getApplicationContext(), "You have selected to add " + amount + " grams of " + name, Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
