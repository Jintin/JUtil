package com.jintin.jutil.view;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Checkable RelativeLayout
 *
 * @author Jintin
 * @version 1.0
 */
@Keep
public class JCheckableLayout extends RelativeLayout implements Checkable {

    private boolean isChecked;
    private List<Checkable> checkableViews = new ArrayList<>();

    /**
     * Constructor
     *
     * @param context
     */
    public JCheckableLayout(Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context
     * @param attrs
     */
    public JCheckableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public JCheckableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final int childCount = this.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            findCheckableChildren(this.getChildAt(i));
        }
    }

    /**
     * recursively find Checkable Layout
     *
     * @param v
     */
    private void findCheckableChildren(View v) {
        if (v instanceof Checkable) {
            this.checkableViews.add((Checkable) v);
        }

        if (v instanceof ViewGroup) {
            final ViewGroup vg = (ViewGroup) v;
            final int childCount = vg.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                findCheckableChildren(vg.getChildAt(i));
            }
        }
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        for (Checkable c : checkableViews) {
            c.setChecked(isChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        this.isChecked = !this.isChecked;
        for (Checkable c : checkableViews) {
            c.toggle();
        }
    }
}
