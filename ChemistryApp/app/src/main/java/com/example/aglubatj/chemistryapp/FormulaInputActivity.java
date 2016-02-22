package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class FormulaInputActivity extends AppCompatActivity {
    public static Equation formula = new Equation();
    private int ADD_REACTANT_REQUEST = 1;
    private int ADD_PRODUCT_REQUEST = 2;
    private int CHANGE_PRODUCT_REQUEST = 3;
    private int CHANGE_REACTANT_REQUEST = 4;
    private int CHANGE_REACTANT_AMOUNT_REQUEST = 5;
    private int CHANGE_PRODUCT_AMOUNT_REQUEST = 6;
    private ArrayList<TextView> lblReactants = new ArrayList<>();
    private ArrayList<TextView> lblReactantAmounts = new ArrayList<>();
    private ArrayList<Button> btnRemoveReactants = new ArrayList<>();
    private ArrayList<TextView> lblProducts = new ArrayList<>();
    private ArrayList<TextView> lblProductAmounts = new ArrayList<>();
    private ArrayList<Button> btnRemoveProducts = new ArrayList<>();
    private int indexFocused = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickBtnAddReactant(View view){
        startActivityForResult(MainMenuActivity.periodicTableActivity, ADD_REACTANT_REQUEST);
    }

    public void onClickBtnAddProduct(View view){
        startActivityForResult(MainMenuActivity.periodicTableActivity, ADD_PRODUCT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == ADD_REACTANT_REQUEST || requestCode == ADD_PRODUCT_REQUEST) {
                double coefficient = data.getDoubleExtra(ConversionActivity.moleResult, 0.0);
                addTableRow(coefficient, requestCode);
            }
            else if(requestCode == CHANGE_REACTANT_REQUEST){
                formula.getReactant(indexFocused).changeTo(PeriodicTable.passObject);
                lblReactants.get(indexFocused).setText(Html.fromHtml(PeriodicTable.passObject.toString()));
                double coefficient = data.getDoubleExtra(ConversionActivity.moleResult, 0.0);
                formula.getReactant(indexFocused).setCoefficient(coefficient);
                lblReactantAmounts.get(indexFocused).setText(String.valueOf(coefficient));
            }
            else if(requestCode == CHANGE_PRODUCT_REQUEST){
                formula.getProduct(indexFocused).changeTo(PeriodicTable.passObject);
                lblProducts.get(indexFocused).setText(Html.fromHtml(PeriodicTable.passObject.toString()));
                double coefficient = data.getDoubleExtra(ConversionActivity.moleResult, 0.0);
                formula.getProduct(indexFocused).setCoefficient(coefficient);
                lblProductAmounts.get(indexFocused).setText(String.valueOf(coefficient));
            }
            else if(requestCode == CHANGE_REACTANT_AMOUNT_REQUEST){
                double coefficient = data.getDoubleExtra(ConversionActivity.moleResult, 0.0);
                formula.getReactant(indexFocused).setCoefficient(coefficient);
                lblReactantAmounts.get(indexFocused).setText(String.valueOf(coefficient));
            }
            else if(requestCode == CHANGE_PRODUCT_AMOUNT_REQUEST){
                double coefficient = data.getDoubleExtra(ConversionActivity.moleResult, 0.0);
                formula.getProduct(indexFocused).setCoefficient(coefficient);
                lblProductAmounts.get(indexFocused).setText(String.valueOf(coefficient));
            }
        }
    }

    public void onClickReactantCompoundView(View view){
        TextView lblCompound = (TextView) view;
        indexFocused = lblReactants.indexOf(lblCompound);
        Intent intent = new Intent(this, PeriodicTableActivity.class);
        startActivityForResult(intent, CHANGE_REACTANT_REQUEST);
    }

    public void onClickReactantRemoveButton(View view){
        Button btnRemove = (Button) view;
        indexFocused = btnRemoveReactants.indexOf(btnRemove);
        formula.removeReactant(indexFocused);
        lblReactants.remove(indexFocused);
        lblReactantAmounts.remove(indexFocused);
        btnRemoveReactants.remove(indexFocused);
    }

    public void onClickReactantAmountLabel(View view){
        TextView lblAmount = (TextView) view;
        indexFocused = lblReactantAmounts.indexOf(lblAmount);
        Intent intent = new Intent(this, ConversionActivity.class);
        startActivityForResult(intent, CHANGE_REACTANT_AMOUNT_REQUEST);
    }
    public void onClickProductCompoundView(View view){
        TextView lblCompound = (TextView) view;
        indexFocused = lblProducts.indexOf(lblCompound);
        Intent intent = new Intent(this, PeriodicTableActivity.class);
        startActivityForResult(intent, CHANGE_PRODUCT_REQUEST);
    }

    public void onClickProductRemoveButton(View view){
        Button btnRemove = (Button) view;
        indexFocused = btnRemoveProducts.indexOf(btnRemove);
        formula.removeProduct(indexFocused);
        lblProducts.remove(indexFocused);
        lblProductAmounts.remove(indexFocused);
        btnRemoveProducts.remove(indexFocused);
    }

    public void onClickProductAmountLabel(View view){
        TextView lblAmount = (TextView) view;
        indexFocused = lblProductAmounts.indexOf(lblAmount);
        Intent intent = new Intent(this, ConversionActivity.class);
        startActivityForResult(intent, CHANGE_PRODUCT_AMOUNT_REQUEST);
    }
    
    private void addTableRow(double coefficient, int requestCode){
        TextView lblAmount = new TextView(this);
        lblAmount.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.f));
        Button btnRemove = new Button(this);
        btnRemove.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.f));
        Compound compound = PeriodicTable.passObject;
        compound.setCoefficient(coefficient);
        TextView lblCompound = new TextView(this);
        lblCompound.setText(Html.fromHtml(PeriodicTable.passObject.toString()));
        lblCompound.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 3.f));
        TableRow tblRow = new TableRow(this);
        tblRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        if(requestCode == ADD_REACTANT_REQUEST){
            formula.addReactant(compound);
            lblCompound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReactantCompoundView(v);
                }
            });
            lblReactants.add(lblCompound);
            lblAmount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReactantAmountLabel(v);
                }
            });
            lblReactantAmounts.add(lblAmount);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReactantRemoveButton(v);
                }
            });
            btnRemoveReactants.add(btnRemove);
            tblRow.addView(lblCompound);
            TableLayout layReactantList = (TableLayout) findViewById(R.id.layReactantList);
            layReactantList.addView(tblRow);
        } 
        else if(requestCode == ADD_PRODUCT_REQUEST){
            formula.addProduct(compound);
            lblCompound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickProductCompoundView(v);
                }
            });
            lblProducts.add(lblCompound);
            lblAmount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickProductAmountLabel(v);
                }
            });
            lblProductAmounts.add(lblAmount);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickProductRemoveButton(v);
                }
            });
            btnRemoveProducts.add(btnRemove);
            tblRow.addView(lblCompound);
            TableLayout layProductList = (TableLayout) findViewById(R.id.layProductList);
            layProductList.addView(tblRow);
        }
    }
}
