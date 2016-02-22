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

public class ConversionActivity extends AppCompatActivity {
    public static final String moleResult = "MoleResult"; // result in moles regardless of whether it was entered in moles or grams
    private double molarMass;
    private EditText edtGramAmount;
    private EditText edtMoleAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtGramAmount = (EditText) findViewById(R.id.edtGramAmount);
        edtMoleAmount = (EditText) findViewById(R.id.edtMoleAmount);

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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

    public void onBackPressed(){
        onClickBtnCancel(null);
    }

    public void onClickBtnAdd(View view){
        Intent intent = new Intent();
        intent.putExtra(moleResult, Double.parseDouble(((EditText)findViewById(R.id.edtMoleAmount)).getText().toString()));
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClickBtnCancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    public double convertGramsToMoles(double amount){
        return amount/molarMass;
    }

    public double convertMolesToGrams(double amount){
        return amount * molarMass;
    }
}
