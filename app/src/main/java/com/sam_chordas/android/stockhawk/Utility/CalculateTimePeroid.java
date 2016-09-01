package com.sam_chordas.android.stockhawk.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nikhil Bhutani on 9/1/2016.
 */
public class CalculateTimePeroid {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getFormattedDate(long dateInMillis) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(dateInMillis);
    }


    public static String getOneWeekBackDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        return getFormattedDate(calendar.getTimeInMillis());

    }


    public static String getOneMonthBackDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return getFormattedDate(calendar.getTimeInMillis());

    }


    public static String getThreeMonthBackDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        return getFormattedDate(calendar.getTimeInMillis());

    }


    public static String getSixMonthBackDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        return getFormattedDate(calendar.getTimeInMillis());

    }


    public static String getOneYearBackDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return getFormattedDate(calendar.getTimeInMillis());

    }

}
