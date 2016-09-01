package com.sam_chordas.android.stockhawk.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.TaskParams;
import com.sam_chordas.android.stockhawk.Utility.Constants;
import com.sam_chordas.android.stockhawk.ui.MyStocksActivity;

/**
 * Created by sam_chordas on 10/1/15.
 */
public class StockIntentService extends IntentService {


    public StockIntentService() {
        super(StockIntentService.class.getName());
    }

    public StockIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(StockIntentService.class.getSimpleName(), "Stock Intent Service");
        StockTaskService stockTaskService = new StockTaskService(this);
        Bundle args = new Bundle();
        if (intent.getStringExtra("tag").equals("add")) {
            args.putString("symbol", intent.getStringExtra("symbol"));

            Handler handler = new Handler(getMainLooper());

            if (stockTaskService.onRunTask(new TaskParams(intent.getStringExtra("tag"), args)) == GcmNetworkManager.RESULT_FAILURE) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(MyStocksActivity.view, "Ops! Symbol Not Found!", Snackbar.LENGTH_LONG).show();
                    }
                });
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(MyStocksActivity.view, "New Symbol Found & Added!", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        }

        if(intent.getStringExtra("tag").equals("init")){
            stockTaskService.onRunTask(new TaskParams(intent.getStringExtra("tag"), args));
        }

        if (intent.getStringExtra("tag").equals("historical_data")) {

            Log.i("Here Im ", "Starting the graph service");
            args.putString("name", intent.getStringExtra("symbol_name"));
            args.putString("startdate", intent.getStringExtra(Constants.START_DATE_KEY));
            args.putString("currentdate", intent.getStringExtra(Constants.CURRENT_DATE_KEY));

            stockTaskService.onRunTask(new TaskParams(intent.getStringExtra("tag"), args));

        }
    }
}
