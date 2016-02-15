package com.example.aglubatj.chemistryapp;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class PeriodicTableActivity extends AppCompatActivity {
    // private static ArrayList<ElementView> ElementViews = new ArrayList<>(PeriodicTable.NUMBER_OF_ELEMENTS);
    private int ZoomFactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_period_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ZoomFactor = PeriodicTable.NUMBER_OF_PERIODS + PeriodicTable.NUMBER_OF_SPECIAL_GROUPS;

        FloatingActionButton fabZoomIn = (FloatingActionButton) findViewById(R.id.fabZoomIn);
        fabZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFabZoomIn(view);
            }
        });

        FloatingActionButton fabZoomOut = (FloatingActionButton) findViewById(R.id.fabZoomOut);
        fabZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFabZoomOut(view);
            }
        });

        // ElementView elementView = (ElementView) findViewById(R.id.elvHydrogen);
        // elementView.setTextColor(Color.parseColor("#59b9c4"));

        // for(int i = 0; i < PeriodicTable.NUMBER_OF_ELEMENTS; ++i){
        //     ElementView elementView = new ElementView(this);
        //     elementView.setElementNumber(i + 1);
        //     ElementViews.add(elementView);
        // }

        // Display display = getWindowManager().getDefaultDisplay();
        // Point size = new Point();
        // display.getSize(size);
        // int screenWidth = size.x;
        // int screenHeight = size.y;

        // int cellWidth = Math.round(screenWidth/18.f);
        // int cellHeight = Math.round(screenHeight / 9.f);
        // int symbolSize = Math.round(cellWidth/2.5f);

        // PeriodicTable pTable = PeriodicTable.getPeriodicTable();

        // RelativeLayout layout = (RelativeLayout) findViewById(R.id.layPeriodTable);

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
            //elv.setMinimumHeight(screenHeight/9);
            //elv.setMinimumWidth(screenHeight/9 - screenHeight/30);
        }
    }

    // @Override
    // public void onResume(){
    //    super.onResume();
        // if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        //     setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    // }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onClickElementView(View view){
        ElementView elementView = (ElementView) view;
        Intent intent = new Intent(this, ElementDetailsActivity.class);
        intent.putExtra("atomicNumber", elementView.getElementNumber());
        startActivity(intent);
    }

    public void onClickFabZoomIn(View view){
        if(ZoomFactor != 1){
            ZoomFactor -= 1;
            this.onResume();
        }
    }

    public void onClickFabZoomOut(View view){
        ZoomFactor += 1;
        this.onResume();
    }

    // public void onClickMainMenu(View view){
    //     finish();
    // }


}
