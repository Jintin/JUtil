package com.jintin.jutil.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.support.annotation.Keep;
import android.view.MenuItem;

import com.jintin.jutil.utils.JPrefs;
import com.jintin.jutil.view.JDialog;

/**
 * Common Activity methods
 *
 * @author Jintin
 * @version 1.0
 */
@Keep
public class ActivityDelegate {

    public static boolean onOptionsItemSelected(IActivity act, MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                act.finish();
                return true;
        }

        return false;
    }

    public static void shareByText(IActivity act) {
        String title = getApplicationName(act);
        String url = "https://play.google.com/store/apps/details?id=" + act.getApplicationContext().getPackageName();
        String text = "Download at Google Play! " + url;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        act.startActivity(Intent.createChooser(intent, title));
    }

    public static void rateApp(IActivity act, String name) {
        String url = "https://play.google.com/store/apps/details?id=" + name;
        act.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public static String getApplicationName(IActivity act) {
        ApplicationInfo applicationInfo = act.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : act.getString(stringId);
    }

    public static void rateRequest(final IActivity act, String msg) {
        int rate = JPrefs.getRate((Context) act);
        if (rate >= 0) {
            rate++;
            if (rate >= 5) {
                JDialog.showDialog((Context) act, msg, (dialog, which) -> {
                    rateApp(act);
                    JPrefs.setRate((Context) act, -1);
                });
                rate = 0;
            }
            JPrefs.setRate((Context) act, rate);
        }
    }

    public static void rateApp(IActivity act) {
        rateApp(act, act.getApplicationContext().getPackageName());
    }

}
