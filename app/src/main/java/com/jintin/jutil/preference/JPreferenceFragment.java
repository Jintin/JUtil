package com.jintin.jutil.preference;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.jintin.jutil.R;
import com.jintin.jutil.view.JDialog;

/**
 * @author Jintin
 * @version 1.0
 */
public class JPreferenceFragment extends PreferenceFragment {
    public static final String EXTRA_LAYOUT = "layout";

    private int layoutId;
    private String facebookId;
    private String facebookUrl;

    public static JPreferenceFragment getInstance(int layoutId) {
        JPreferenceFragment fragment = new JPreferenceFragment();
        fragment.layoutId = layoutId;
        return fragment;
    }

    public static JPreferenceFragment getInstance(int layoutId, String facebookId, String facebookUrl) {
        JPreferenceFragment fragment = new JPreferenceFragment();
        fragment.layoutId = layoutId;
        fragment.facebookUrl = facebookUrl;
        fragment.facebookId = facebookId;
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_LAYOUT, layoutId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            layoutId = savedInstanceState.getInt(EXTRA_LAYOUT);
        }
        addPreferencesFromResource(layoutId);
        setPreference();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(android.R.id.list);
        if (listView != null) {
            listView.setVerticalScrollBarEnabled(false);
            int paddingHorizontal = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
            int paddingVertical = (int) getResources().getDimension(R.dimen.activity_vertical_margin);
            listView.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
            listView.setClipToPadding(false);
        }
    }

    private void setPreference() {
        Preference myPref = findPreference(getString(R.string.key_team));
        if (myPref != null) {
            myPref.setOnPreferenceClickListener(preference -> {
                JDialog.showMessage(getActivity(), R.string.setting_team, R.string.team_detail);
                return true;
            });
        }
        Preference facebookPref = findPreference(getString(R.string.key_facebook));
        if (facebookPref != null) {
            facebookPref.setOnPreferenceClickListener(preference -> {
                Activity activity = getActivity();
                if (activity != null) {
                    try {
                        activity.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + facebookId)));
                    } catch (Exception e) {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + facebookUrl)));
                    }
                }
                return true;
            });
        }
        try {
            String version = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            findPreference(getString(R.string.key_version)).setSummary(version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFacebookId(String id) {
        facebookId = id;
    }

    public void setFacebookUrl(String url) {
        facebookUrl = url;
    }

}
