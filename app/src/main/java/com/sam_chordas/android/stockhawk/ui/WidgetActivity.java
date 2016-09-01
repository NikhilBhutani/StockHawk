package com.sam_chordas.android.stockhawk.ui;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.service.WidgetService;

/**
 * Created by Nikhil Bhutani on 9/2/2016.
 */
public class WidgetActivity extends AppWidgetProvider {

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.stock_widget);
        // Instruct the widget manager to update the widget
        setRemoteAdapterV11(context, views);

        Intent launchMain = new Intent(context, MyStocksActivity.class);
//        Intent launchDetail = new Intent(context, StockDetailActivity.class);
        PendingIntent pendingMainIntent = PendingIntent.getActivity(context, 0, launchMain, 0);
//        PendingIntent pendingDetailIntent = TaskStackBuilder.create(context)
//                .addNextIntentWithParentStack(launchDetail)
//                .getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_toolbar, pendingMainIntent);
//        views.setOnClickPendingIntent(R.id.widget_listView,pendingDetailIntent);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_listView);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @SuppressWarnings("deprecation")
    private static void setRemoteAdapterV11(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(0, R.id.widget_listView,
                new Intent(context, WidgetService.class));
    }

}
