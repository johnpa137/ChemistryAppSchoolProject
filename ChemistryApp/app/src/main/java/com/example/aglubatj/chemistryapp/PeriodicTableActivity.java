package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class PeriodicTableActivity extends AppCompatActivity {
    private static ArrayList<ElementView> ElementViews = new ArrayList<>(PeriodicTable.NUMBER_OF_ELEMENTS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ElementView elementView = (ElementView) findViewById(R.id.view);
        elementView.setTextColor(0x8959c4);

        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // for(int i = 0; i < PeriodicTable.NUMBER_OF_ELEMENTS; ++i){
        //     ElementView elementView = new ElementView(this);
        //     elementView.setElementNumber(i + 1);
        //     ElementViews.add(elementView);
        // }
//
        // Display display = getWindowManager().getDefaultDisplay();
        // Point size = new Point();
        // display.getSize(size);
        // int screenWidth = size.x;
        // int screenHeight = size.y;
//
        // int cellWidth = Math.round(screenWidth/18.f);
        // int cellHeight = Math.round(screenHeight / 9.f);
        // int symbolSize = Math.round(cellWidth/2.5f);
//
        // PeriodicTable pTable = PeriodicTable.getPeriodicTable();
//
        // RelativeLayout layout = (RelativeLayout) findViewById(R.id.layPeriodTable);
//
        // for(int i = 0; i < PeriodicTable.NUMBER_OF_GROUPS; ++i){
        //     RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
        //     ArrayList<Element> ElementGroup = pTable.getElementGroup(i + 1);
        //     for(int j = 0; j < ElementGroup.size(); ++j){
        //         if(ElementGroup.get(j).getAtomicNumber() == 1){
        //             params.addRule(RelativeLayout.ALIGN_PARENT_TOP, -1);
        //             params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, -1);
        //             ElementViews.get(0).setSymbolSize(symbolSize);
        //             ElementViews.get(0).setLayoutParams(params);
        //             layout.addView(ElementViews.get(0));
        //             break;
        //         }
        //     }
        // }
    }

    public void onClickElementView(View view){
        ElementView elementView = (ElementView) view;
        Intent intent = new Intent(this, ElementDetailsActivity.class);
        intent.putExtra("atomicNumber", elementView.getElementNumber());
        startActivity(intent);
    }

    public void onClickMainMenu(View view){
        finish();
    }
}
