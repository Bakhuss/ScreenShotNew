package ru.bakhuss.ScreenShotNew;

import java.sql.Time;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ClassMainn {
    public static void main(String[] args) {
        String dateForm = "dd.MM.yyyy-HH:mm:ss.SSS-z";

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateForm);
        TimeZone utcTz = TimeZone.getTimeZone("UTC");
        System.out.println("offset: " + TimeZone.getDefault().getOffset(new Date().getTime()) );
        dateFormat.setTimeZone(utcTz);
        System.out.println( dateFormat.format(date) );
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(sdf.format(dt));


    }
}
