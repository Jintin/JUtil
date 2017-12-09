package com.jintin.jutil.utils;

import android.support.annotation.Keep;
import android.util.Log;

import com.jintin.jutil.BuildConfig;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class JLog {

    public static String tag = "jintin";

    public static void setTag(String tag) {
        JLog.tag = tag;
    }

    public static void v(Object msg) {
        JLog.v(tag, msg.toString());
    }

    public static void d(Object msg) {
        JLog.d(tag, msg.toString());
    }

    public static void i(Object msg) {
        JLog.i(tag, msg.toString());
    }

    public static void w(Object msg) {
        JLog.w(tag, msg.toString());
    }

    public static void e(Object msg) {
        JLog.e(tag, msg.toString());
    }

    public static void wtf(Object msg) {
        JLog.wtf(tag, msg.toString());
    }

    public static void v(Object tag, Object msg) {
        if (BuildConfig.DEBUG) {
            Log.v(tag.toString(), msg.toString());
        }
    }

    public static void d(Object tag, Object msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag.toString(), msg.toString());
        }
    }

    public static void i(Object tag, Object msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag.toString(), msg.toString());
        }
    }

    public static void e(Object tag, Object msg) {
        Log.e(tag.toString(), msg.toString());
    }

    public static void w(Object tag, Object msg) {
        Log.w(tag.toString(), msg.toString());
    }

    public static void wtf(Object tag, Object msg) {
        Log.wtf(tag.toString(), msg.toString());
    }

}
