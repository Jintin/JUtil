package com.jintin.jutil.preference;

import android.os.Bundle;
import android.support.annotation.Keep;

import com.jintin.jutil.R;
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
        setContentView(R.layout.activity_preference);
        JPreferenceFragment fragment = JPreferenceFragment.getInstance(getPreferenceView(), getFacebookId(), getFacebookUrl());
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    public abstract int getPreferenceView();

    public abstract String getFacebookId();

    public abstract String getFacebookUrl();

}
