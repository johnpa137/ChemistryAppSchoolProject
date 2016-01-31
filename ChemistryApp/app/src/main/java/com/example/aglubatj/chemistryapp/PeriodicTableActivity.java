package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class PeriodicTableActivity extends AppCompatActivity {
    private PeriodicTable periodicTable;
    private ArrayList<ElementView> ElementViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        periodicTable = PeriodicTable.getPeriodicTable();
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
