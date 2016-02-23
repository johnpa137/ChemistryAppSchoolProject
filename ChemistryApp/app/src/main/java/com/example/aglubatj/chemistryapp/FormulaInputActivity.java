package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
    private static final int ADD_PRODUCT_REQUEST = 2;
    private static final int CHANGE_PRODUCT_REQUEST = 3;
    private static final int CHANGE_REACTANT_REQUEST = 4;
    private static final int CHANGE_REACTANT_AMOUNT_REQUEST = 5;
    private static final int CHANGE_PRODUCT_AMOUNT_REQUEST = 6;
    private ArrayList<TextView> lblReactants = new ArrayList<>();
    private ArrayList<TextView> lblReactantAmounts = new ArrayList<>();
    private ArrayList<Button> btnRemoveReactants = new ArrayList<>();
    private ArrayList<TextView> lblProducts = new ArrayList<>();
    private ArrayList<TextView> lblProductAmounts = new ArrayList<>();
    private ArrayList<Button> btnRemoveProducts = new ArrayList<>();
    private int indexFocused = -1;
    private static final String balanced = "Formula is Balanced";
    private static final String unbalanced = "Formula is Unbalanced";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView lblFormulaTitle = (TextView) findViewById(R.id.lblFormulaTitle);
        if(formula.checkBalance())
            lblFormulaTitle.setText(balanced);
        else
            lblFormulaTitle.setText(unbalanced);
    }

    public void onClickBtnAddReactant(View view){
        Intent intent = new Intent(this, PeriodicTableActivity.class);
        startActivityForResult(intent, ADD_REACTANT_REQUEST);
    }

    public void onClickBtnAddProduct(View view){
        Intent intent = new Intent(this, PeriodicTableActivity.class);
        startActivityForResult(intent, ADD_PRODUCT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == ADD_REACTANT_REQUEST || requestCode == ADD_PRODUCT_REQUEST) {
                int coefficient = data.getIntExtra(ConversionActivity.moleResult, 0);
                addTableRow(coefficient, requestCode);
            }
            else if(requestCode == CHANGE_REACTANT_REQUEST){
                formula.getReactant(indexFocused).changeTo(PeriodicTable.passObject);
                lblReactants.get(indexFocused).setText(Html.fromHtml(PeriodicTable.passObject.toString()));
                int coefficient = data.getIntExtra(ConversionActivity.moleResult, 0);
                formula.getReactant(indexFocused).setCoefficient(coefficient);
                lblReactantAmounts.get(indexFocused).setText(String.valueOf(coefficient));
            }
            else if(requestCode == CHANGE_PRODUCT_REQUEST){
                formula.getProduct(indexFocused).changeTo(PeriodicTable.passObject);
                lblProducts.get(indexFocused).setText(Html.fromHtml(PeriodicTable.passObject.toString()));
                int coefficient = data.getIntExtra(ConversionActivity.moleResult, 0);
                formula.getProduct(indexFocused).setCoefficient(coefficient);
                lblProductAmounts.get(indexFocused).setText(String.valueOf(coefficient));
            }
            else if(requestCode == CHANGE_REACTANT_AMOUNT_REQUEST){
                int coefficient = data.getIntExtra(ConversionActivity.moleResult, 0);
                formula.getReactant(indexFocused).setCoefficient(coefficient);
                lblReactantAmounts.get(indexFocused).setText(String.valueOf(coefficient));
            }
            else if(requestCode == CHANGE_PRODUCT_AMOUNT_REQUEST){
                int coefficient = data.getIntExtra(ConversionActivity.moleResult, 0);
                formula.getProduct(indexFocused).setCoefficient(coefficient);
                lblProductAmounts.get(indexFocused).setText(String.valueOf(coefficient));
            }
        }
        TextView lblFormula = (TextView) findViewById(R.id.lblFormula);
        lblFormula.setText(Html.fromHtml(formula.toString()));
        PeriodicTable.passObject.clear();
        TextView lblFormulaTitle = (TextView) findViewById(R.id.lblFormulaTitle);
        if(formula.checkBalance())
            lblFormulaTitle.setText(balanced);
        else
            lblFormulaTitle.setText(unbalanced);
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
        TableLayout layReactantList = (TableLayout) findViewById(R.id.layReactantList);
        layReactantList.removeViewAt(indexFocused + 1);
        lblReactants.remove(indexFocused);
        lblReactantAmounts.remove(indexFocused);
        btnRemoveReactants.remove(indexFocused);
        TextView lblFormula = (TextView) findViewById(R.id.lblFormula);
        lblFormula.setText(Html.fromHtml(formula.toString()));
        TextView lblFormulaTitle = (TextView) findViewById(R.id.lblFormulaTitle);
        if(formula.checkBalance())
            lblFormulaTitle.setText(balanced);
        else
            lblFormulaTitle.setText(unbalanced);
    }

    public void onClickReactantAmountLabel(View view){
        TextView lblAmount = (TextView) view;
        indexFocused = lblReactantAmounts.indexOf(lblAmount);
        Intent intent = new Intent(this, ConversionActivity.class);
        PeriodicTable.passObject.changeTo(formula.getReactant(indexFocused));
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
        TableLayout layProductList = (TableLayout) findViewById(R.id.layProductList);
        layProductList.removeViewAt(indexFocused + 1);
        lblProducts.remove(indexFocused);
        lblProductAmounts.remove(indexFocused);
        btnRemoveProducts.remove(indexFocused);
        TextView lblFormula = (TextView) findViewById(R.id.lblFormula);
        lblFormula.setText(Html.fromHtml(formula.toString()));
        TextView lblFormulaTitle = (TextView) findViewById(R.id.lblFormulaTitle);
        if(formula.checkBalance())
            lblFormulaTitle.setText(balanced);
        else
            lblFormulaTitle.setText(unbalanced);
    }

    public void onClickProductAmountLabel(View view){
        TextView lblAmount = (TextView) view;
        indexFocused = lblProductAmounts.indexOf(lblAmount);
        PeriodicTable.passObject.changeTo(formula.getProduct(indexFocused));
        Intent intent = new Intent(this, ConversionActivity.class);
        startActivityForResult(intent, CHANGE_PRODUCT_AMOUNT_REQUEST);
    }
    
    private void addTableRow(int coefficient, int requestCode){
        TextView lblAmount = new TextView(this);
        lblAmount.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.f));
        lblAmount.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
        lblAmount.setText(String.valueOf(coefficient));
        Button btnRemove = new Button(this);
        btnRemove.setText("x");
        btnRemove.setTextColor(Color.WHITE);
        btnRemove.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        btnRemove.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.f));
        Compound compound = new Compound();
        compound.changeTo(PeriodicTable.passObject);
        compound.setCoefficient(coefficient);
        TextView lblCompound = new TextView(this);
        lblCompound.setText(Html.fromHtml(PeriodicTable.passObject.toString()));
        lblCompound.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 3.f));
        lblCompound.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
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
            tblRow.addView(lblAmount);
            tblRow.addView(lblCompound);
            tblRow.addView(btnRemove);
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
            tblRow.addView(lblAmount);
            tblRow.addView(lblCompound);
            tblRow.addView(btnRemove);
            TableLayout layProductList = (TableLayout) findViewById(R.id.layProductList);
            layProductList.addView(tblRow);
        }
    }
}
