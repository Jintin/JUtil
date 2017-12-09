package com.jintin.jutil.utils;

import android.support.annotation.Keep;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class JTime extends GregorianCalendar {

    public JTime() {
        super();
    }

    public String format(String format) {
        Date date = super.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        return dateFormat.format(date);
    }

    public void set(JTime time) {
        setTimeInMillis(time.getTimeInMillis());
    }

    public void set(long time) {
        setTimeInMillis(time);
    }

    public String getDate() {
        return format("MM/dd/yyyy");
    }

    public String getAll() {
        return format("MM/dd/yyyy HH:mm:ss");
    }

    public int month() {
        return get(Calendar.MONTH);
    }

    public int monthDay() {
        return get(Calendar.DAY_OF_MONTH);
    }

    public int year() {
        return get(Calendar.YEAR);
    }
}
