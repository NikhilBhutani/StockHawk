package com.sam_chordas.android.stockhawk.ui.Graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sam_chordas.android.stockhawk.Constants;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.service.StockIntentService;

/**
 * Created by Nikhil Bhutani on 8/30/2016.
 */
public class GraphActivity extends AppCompatActivity {

    private Intent serviceIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_line_graph);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra(Constants.SYMBOL_NAME_KEY));


        serviceIntent = new Intent(this, StockIntentService.class);

        serviceIntent.putExtra("tag", "historical_data");
        serviceIntent.putExtra(Constants.SYMBOL_NAME_KEY, getIntent().getStringExtra(Constants.SYMBOL_NAME_KEY));




    }
}
