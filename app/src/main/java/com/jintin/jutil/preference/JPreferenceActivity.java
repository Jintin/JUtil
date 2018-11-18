package com.jintin.jutil.preference;

import android.os.Bundle;
import android.support.annotation.Keep;

import com.jintin.jutil.app.JActivity;


/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public abstract class JPreferenceActivity extends JActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JPreferenceFragment fragment = JPreferenceFragment.getInstance(getPreferenceView(), getFacebookId(), getFacebookUrl());
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

    public abstract int getPreferenceView();

    public abstract String getFacebookId();

    public abstract String getFacebookUrl();

}
