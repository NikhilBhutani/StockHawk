package com.sam_chordas.android.stockhawk.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.ViewParent;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.TaskParams;
import com.sam_chordas.android.stockhawk.ui.MyStocksActivity;

/**
 * Created by sam_chordas on 10/1/15.
 */
public class StockIntentService extends IntentService {



  public StockIntentService(){
    super(StockIntentService.class.getName());
  }

  public StockIntentService(String name) {
    super(name);
  }

  @Override protected void onHandleIntent(Intent intent) {
    Log.d(StockIntentService.class.getSimpleName(), "Stock Intent Service");
    StockTaskService stockTaskService = new StockTaskService(this);
    Bundle args = new Bundle();
    if (intent.getStringExtra("tag").equals("add")){
      args.putString("symbol", intent.getStringExtra("symbol"));
    }
    // We can call OnRunTask from the intent service to force it to run immediately instead of
    // scheduling a task.

    Handler handler = new Handler(getMainLooper());

   if( stockTaskService.onRunTask(new TaskParams(intent.getStringExtra("tag"), args)) == GcmNetworkManager.RESULT_FAILURE)
   {
       handler.post(new Runnable() {
         @Override
         public void run() {
           Snackbar.make(MyStocksActivity.view, "Ops! Symbol Not Found!", Snackbar.LENGTH_LONG).show();
         }
       });
   }
    else
   {
     handler.post(new Runnable() {
       @Override
       public void run() {
           Snackbar.make(MyStocksActivity.view, "New Symbol Found & Added!", Snackbar.LENGTH_LONG).show();
       }
     });
   }
  }
}
