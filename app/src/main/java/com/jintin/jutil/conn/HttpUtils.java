package com.jintin.jutil.conn;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Keep;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class HttpUtils {
    public static boolean checkInternet(Context context) {
        if (context == null)
            return true;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager == null) {
            return false;
        }
        NetworkInfo info = connManager.getActiveNetworkInfo();
        return !(info == null || !info.isAvailable() || !info.isConnectedOrConnecting());
    }

}
