package com.example.aglubatj.chemistryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickPeriodicTable(View view){
        Intent intent = new Intent(this, PeriodicTableActivity.class);
        startActivity(intent);
    }

    public void onClickUnimplemented(View view){
        Toast.makeText(this.getApplicationContext(), "This feature has not been implemented yet.", Toast.LENGTH_LONG).show();
    }

}
