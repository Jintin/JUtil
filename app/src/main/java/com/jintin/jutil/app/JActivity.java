package com.jintin.jutil.app;

import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public abstract class JActivity extends AppCompatActivity implements IActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isTaskRoot()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        getData();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initLayout();
    }

    @Override
    public void onPause() {
        super.onPause();
        setData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return ActivityDelegate.onOptionsItemSelected(this, item) || super.onOptionsItemSelected(item);
    }
}
