package com.jintin.jutil.utils;

import android.support.annotation.Keep;
import android.widget.TextView;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class JNumber {

    public static <T extends Number> T getValue(TextView textView, T defaultValue) {
        return getValue(textView.getText(), defaultValue);
    }

    public static <T extends Number> T getValue(CharSequence ch, T defaultValue) {
        return getValue(ch.toString(), defaultValue);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T getValue(String string, T defaultValue) {
        if (string != null) {
            string = string.replace(",", "");
        }
        try {
            if (defaultValue instanceof Integer) {
                return (T) Integer.valueOf(string);
            } else if (defaultValue instanceof Double) {
                return (T) Double.valueOf(string);
            } else if (defaultValue instanceof Long) {
                return (T) Long.valueOf(string);
            } else {
                throw new RuntimeException();
            }
        } catch (Exception ignore) {

        }
        return defaultValue;
    }
}
