package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 * Activity class for Conversion screen.
 *
 * @author JP Aglubat
 * @version 3/6/2016
 */
public class ConversionActivity extends AppCompatActivity {
    public static final String moleResult = "MoleResult"; // result in moles regardless of whether it was entered in moles or grams
    private double molarMass;
    private EditText edtGramAmount;
    private EditText edtMoleAmount;
    private static final String moleInformation = "1 mole = 6.022 x 10<sup>23</sup> molecules";

    /**
     * Android onCreate method.
     *
     * @param savedInstanceState the class state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtGramAmount = (EditText) findViewById(R.id.edtGramAmount);
        edtMoleAmount = (EditText) findViewById(R.id.edtMoleAmount);
        TextView lblMoleInformation = (TextView) findViewById(R.id.lblMoleInfo);
        lblMoleInformation.setText(Html.fromHtml(moleInformation));

        molarMass = PeriodicTable.passObject.getMolarMass();

        edtGramAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(edtGramAmount.isFocused())
                    edtMoleAmount.setText(String.valueOf(convertGramsToMoles(Double.parseDouble(edtGramAmount.getText().toString()))));
            }
        });

        edtMoleAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (edtMoleAmount.isFocused())
                    edtGramAmount.setText(String.valueOf(convertMolesToGrams(Double.parseDouble(edtMoleAmount.getText().toString()))));
            }
        });

        TextView lblMolarMassValue = (TextView) findViewById(R.id.lblMolarMassValue);
        lblMolarMassValue.setText(String.valueOf(molarMass));
        TextView lblCompoundView = (TextView) findViewById(R.id.lblCompoundView);
        lblCompoundView.setText(Html.fromHtml(PeriodicTable.passObject.toString()));
    }

    /**
     * Android onBackPressed method.
     */
    public void onBackPressed(){
        onClickBtnCancel(null);
    }

    /**
     * Listener for when the user touches the add button
     *
     * @param view the view that called this function
     */
    public void onClickBtnAdd(View view){
        Intent intent = new Intent();
        if(Integer.parseInt(((EditText) findViewById(R.id.edtMoleAmount)).getText().toString()) > 0){
            intent.putExtra(moleResult, Integer.parseInt(((EditText) findViewById(R.id.edtMoleAmount)).getText().toString()));
            setResult(RESULT_OK, intent);
        }
        else
            setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * Listener for when the user touches the cancel button
     *
     * @param view the view that called this function
     */
    public void onClickBtnCancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * Method that converts grams to moles
     *
     * @param amount number of grams
     * @return amount in moles
     */
    public int convertGramsToMoles(double amount){
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        return Integer.parseInt(df.format(amount / molarMass));
    }

    /**
     * Method that converts moles to grams
     *
     * @param amount number of grams
     * @return amount in grams
     */
    public double convertMolesToGrams(double amount){
        return amount * molarMass;
    }
}
