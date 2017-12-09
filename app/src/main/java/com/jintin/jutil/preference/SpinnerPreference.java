package com.jintin.jutil.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.support.annotation.Keep;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jintin.jutil.R;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class SpinnerPreference extends Preference {

    private String[] entries;
    private int defaultValue;
    private Spinner spinner;

    public SpinnerPreference(Context context) {
        super(context);
        init(null, 0);
    }

    public SpinnerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SpinnerPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        setWidgetLayoutResource(R.layout.spinner_preference);
        TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.spinner_preference, defStyle, 0);

        int str = a.getResourceId(R.styleable.spinner_preference_android_entries, 0);
        entries = this.getContext().getResources().getStringArray(str);
        defaultValue = a.getInt(R.styleable.spinner_preference_android_defaultValue, 0);
        a.recycle();
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        View view = super.onCreateView(parent);

        spinner = view.findViewById(android.R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.preference_text, entries);
        spinner.setAdapter(adapter);
        spinner.setSelection(getPersistedInt(defaultValue));
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                persistInt(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }

    @Override
    protected void onClick() {
        super.onClick();
        spinner.performClick();
    }
}
