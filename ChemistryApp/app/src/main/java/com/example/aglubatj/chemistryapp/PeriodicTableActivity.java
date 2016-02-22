package com.example.aglubatj.chemistryapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ZoomControls;

public class PeriodicTableActivity extends AppCompatActivity {
    private int ZoomFactor;
    public static Compound compound = new Compound();
    private boolean superscript;
    private boolean subscript;
    private TextView formulaView;
    private int negativeCharge;
    private int currentNumber;
    private int ADD_AMOUNT_REQUEST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_period_table_vertical);
        else
            setContentView(R.layout.activity_period_table_horizontal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ZoomFactor = PeriodicTable.NUMBER_OF_PERIODS + PeriodicTable.NUMBER_OF_SPECIAL_GROUPS;

        ZoomControls zoomControls = (ZoomControls) findViewById(R.id.zoomControls);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickZoomIn(v);
            }
        });

        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickZoomOut(v);
            }
        });

        formulaView = (TextView) findViewById(R.id.lblFormulaView);

        superscript = false;
        subscript = true;
        negativeCharge = 1;
        currentNumber = 0;

        Button btnKeySubScript = (Button) findViewById(R.id.btnKeySubScript);
        btnKeySubScript.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onResume(){
        super.onResume();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        PeriodicTable pTable = PeriodicTable.getPeriodicTable();

        DisplayMetrics metrics = getResources().getDisplayMetrics();

        int screenHeight = metrics.heightPixels;

        for(int i = 0; i < PeriodicTable.NUMBER_OF_ELEMENTS; ++i){
            ElementView elv = (ElementView) findViewById(getResources().getIdentifier("elv" + pTable.getElement(i + 1).getName(), "id", getPackageName()));
            ViewGroup.LayoutParams params = elv.getLayoutParams();
            params.height = (screenHeight - toolbar.getHeight()) / ZoomFactor;
            params.width = (screenHeight - toolbar.getHeight()) / ZoomFactor;
            elv.setLayoutParams(params);
            elv.setSymbolSize((float)(screenHeight - toolbar.getHeight()) / (ZoomFactor * 2));
        }

        updateFormulaView();
    }

    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public void updateFormulaView(){
        if(compound.size() != 0)
            formulaView.setText(Html.fromHtml(compound.toString()));
        else
            formulaView.setText("No Formula");
    }

    public void onClickElementView(View view){
        ElementView elementView = (ElementView) view;
        compound.addElement(elementView.getElementNumber());
        currentNumber = 0;

        updateFormulaView();
    }

    public void onClickZoomIn(View view){
        if(ZoomFactor != 1){
            ZoomFactor -= 1;
            this.onResume();
        }
    }

    public void onClickZoomOut(View view){
        ZoomFactor += 1;
        this.onResume();
    }

    public void onClickKeyPadNumber(View view){
        currentNumber *= 10;
        Button numberButton = (Button) view;

        int numberToAdd = Integer.parseInt(numberButton.getText().toString());

        currentNumber += numberToAdd;

        if(superscript)
            compound.addCharge(currentNumber * negativeCharge);
        else{
            compound.addSubscript(currentNumber);
        }

        updateFormulaView();
    }

    public void onClickKeySuperScript(View view){
        Button btnKeySuperScript = (Button) view;
        Button btnKeySubScript = (Button) findViewById(R.id.btnKeySubScript);
        Button btnKeySign = (Button) findViewById(R.id.btnKeySign);
        currentNumber = 0;

        if(superscript){
            superscript = false;
            subscript = true;
            btnKeySuperScript.getBackground().setColorFilter(null);
            btnKeySubScript.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            btnKeySign.setEnabled(false);
        }
        else{
            superscript = true;
            subscript = false;
            btnKeySuperScript.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            btnKeySubScript.getBackground().setColorFilter(null);
            btnKeySign.setEnabled(true);
        }
    }

    public void onClickKeySubScript(View view){
        Button btnKeySubScript = (Button) view;
        Button btnKeySuperScript = (Button) findViewById(R.id.btnKeySuperScript);
        Button btnKeySign = (Button) findViewById(R.id.btnKeySign);
        currentNumber = 0;

        if(subscript){
            superscript = true;
            subscript = false;
            btnKeySuperScript.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            btnKeySubScript.getBackground().setColorFilter(null);
            btnKeySign.setEnabled(true);
        }
        else{
            superscript = false;
            subscript = true;
            btnKeySuperScript.getBackground().setColorFilter(null);
            btnKeySubScript.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            btnKeySign.setEnabled(false);
        }
    }

    public void onClickKeySign(View view){
        negativeCharge = -negativeCharge;

        if(superscript)
            compound.addCharge(currentNumber * negativeCharge);

        updateFormulaView();
    }

    public void onClickKeyDel(View view){
        currentNumber = 0;
        compound.backspace();

        updateFormulaView();
    }

    public void onClickKeyClr(View view){
        currentNumber = 0;
        compound.clear();

        updateFormulaView();
    }

    public void onClickKeyOK(View view) {
        Intent intent = new Intent(this, ConversionActivity.class);

        PeriodicTable.passObject = compound;

        startActivityForResult(intent, ADD_AMOUNT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == ADD_AMOUNT_REQUEST){
                setResult(RESULT_OK, data);
                finish();
            }
        }
    }
}
