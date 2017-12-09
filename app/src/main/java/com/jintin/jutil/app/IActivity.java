package com.jintin.jutil.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Keep;
import android.view.MenuItem;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public interface IActivity {

    // inherit
    Context getApplicationContext();

    // inherit
    ApplicationInfo getApplicationInfo();

    // inherit
    String getString(int id);

    // inherit
    boolean onOptionsItemSelected(MenuItem item);

    // inherit
    void startActivity(Intent intent);

    // inherit
    void finish();

    default void getData() {

    }

    default void setData() {

    }

    default void initLayout() {

    }
}
