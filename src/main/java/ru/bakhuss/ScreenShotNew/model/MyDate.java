package ru.bakhuss.ScreenShotNew.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyDate extends Date {

    private static Date dateNow = null;

    private static final String dateFormStr = "dd.MM.yyyy-HH:mm:ss.SSS-z";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormStr);
    private static final TimeZone TZ_local = TimeZone.getDefault();
    private static final TimeZone TZ_utc = TimeZone.getTimeZone("UTC");
    private static final int TIMEZONE_OFFSET = TimeZone.getDefault().getOffset(new Date().getTime());


    public static String dateNowUTCString() {
        dateFormat.setTimeZone(TZ_utc);
        String date = dateFormat.format(new Date());
        System.out.println("dateUTC: " + date);
        return date;
    }

    public static String dateNowLocalString() {
        dateFormat.setTimeZone(TZ_local);
        String date = dateFormat.format(new Date());
        System.out.println("dateLocal: " + date);
        return date;
    }

    public static Long dateNowUTClong() {
        return (System.currentTimeMillis() - TIMEZONE_OFFSET);
    }

    public static Long dateNowLocalLong() {
        return System.currentTimeMillis();
    }


    public static int timeZoneOffset() {
        return TIMEZONE_OFFSET;
    }
}
