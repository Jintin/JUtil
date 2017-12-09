package com.jintin.jutil.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Keep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class JPrefs {
    @Keep
    public interface IJSONObject {
        JSONObject toJSON();
    }

    public enum Type {
        String, Boolean, Integer, Float, Long, HashSet
    }

    public static int getRate(Context context) {
        return getInt(context, "Rate", 0);
    }

    public static void setRate(Context context, int rate) {
        writeValue(context, "Rate", rate);
    }

    protected static void writeValue(Context context, int resId, Object value) {
        writeValue(context, context.getString(resId), value);
    }

    protected static void writeValue(Context context, String tag, Object value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        switch (Type.valueOf(value.getClass().getSimpleName())) {
            case String:
                editor.putString(tag, (String) value);
                break;
            case Boolean:
                editor.putBoolean(tag, (Boolean) value);
                break;
            case Integer:
                editor.putInt(tag, (Integer) value);
                break;
            case Float:
                editor.putFloat(tag, (Float) value);
                break;
            case Long:
                editor.putLong(tag, (Long) value);
                break;
            case HashSet:
                editor.putStringSet(tag, (Set<String>) value);
                break;
            default:
                throw new RuntimeException("not support type");
        }

        editor.apply();
    }

    protected static void writeJSONString(Context context, String tag, List<String> list) {
        if (list == null)
            return;
        JSONArray array = new JSONArray();
        for (String object : list) {
            array.put(object);
        }
        writeValue(context, tag, array.toString());
    }

    protected static void writeJSONList(Context context, String tag, Collection<? extends IJSONObject> list) {
        if (list == null)
            return;
        JSONArray array = new JSONArray();
        for (IJSONObject object : list) {
            array.put(object.toJSON());
        }
        writeValue(context, tag, array.toString());
    }

    protected static String getString(Context context, String tag) {
        return getString(context, tag, null);
    }

    protected static String getString(Context context, int resId, String defValue) {
        return getString(context, context.getString(resId), defValue);
    }

    protected static String getString(Context context, String tag, String defValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(tag, defValue);
    }

    protected static Set<String> getStringSet(Context context, int resId, Set<String> defValues) {
        return getStringSet(context, context.getString(resId), defValues);
    }

    protected static Set<String> getStringSet(Context context, String tag, Set<String> defValues) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getStringSet(tag, defValues);
    }

    protected static Boolean getBoolean(Context context, int resId, Boolean defValue) {
        return getBoolean(context, context.getString(resId), defValue);
    }

    protected static Boolean getBoolean(Context context, String tag, Boolean defValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getBoolean(tag, defValue);
    }

    protected static int getInt(Context context, int resId, int defValue) {
        return getInt(context, context.getString(resId), defValue);
    }

    protected static int getInt(Context context, String tag, int defValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt(tag, defValue);
    }

    protected static float getFloat(Context context, int resId, int defValue) {
        return getFloat(context, context.getString(resId), defValue);
    }

    protected static float getFloat(Context context, String tag, int defValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getFloat(tag, defValue);
    }

    protected static long getLong(Context context, int resId, int defValue) {
        return getLong(context, context.getString(resId), defValue);
    }

    protected static long getLong(Context context, String tag, int defValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getLong(tag, defValue);
    }

    protected static JSONArray getJSONArray(Context context, String tag) {
        String res = getString(context, tag, "");
        try {
            return new JSONArray(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    protected static JSONObject getJSONObject(Context context, String tag, int defValue) {
        String res = getString(context, tag, "");
        try {
            return new JSONObject(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    protected static void delete(Context context, String tag) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        if (settings.contains(tag))
            editor.remove(tag);
        editor.apply();
    }
}
