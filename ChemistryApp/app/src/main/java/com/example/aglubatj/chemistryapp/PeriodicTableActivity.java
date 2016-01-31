package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.HashMap;

public class PeriodicTableActivity extends AppCompatActivity {
    private PeriodicTable periodicTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        periodicTable = new PeriodicTable(this);
    }

    public void onClickElementView(View view){
        ElementView elementView = (ElementView) view;
        Intent intent = new Intent(this, ElementDetailsActivity.class);
        intent.putExtra("name", periodicTable.Elements.get(elementView.getElementNumber()).getName());
        intent.putExtra("symbol", periodicTable.Elements.get(elementView.getElementNumber()).getSymbol());
        intent.putExtra("atomicNumber", periodicTable.Elements.get(elementView.getElementNumber()).getAtomicNumber());
        intent.putExtra("atomicWeight", periodicTable.Elements.get(elementView.getElementNumber()).getAtomicWeight());
        intent.putExtra("group", periodicTable.Elements.get(elementView.getElementNumber()).getGroup());
        intent.putExtra("period", periodicTable.Elements.get(elementView.getElementNumber()).getPeriod());
        startActivity(intent);
    }

    public void onClickMainMenu(View view){
        finish();
    }
}
