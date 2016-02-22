package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {
    public static Intent periodicTableActivity;
    public static Intent formulaInputActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        periodicTableActivity = new Intent(this, PeriodicTableActivity.class);
        formulaInputActivity = new Intent(this, FormulaInputActivity.class);
    }

    public void onClickPeriodicTable(View view){
        startActivity(periodicTableActivity);
    }

    public void onClickFormulaInput(View view){
        startActivity(formulaInputActivity);
    }
}
