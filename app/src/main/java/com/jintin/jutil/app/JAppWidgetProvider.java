package com.jintin.jutil.app;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.support.annotation.Keep;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public abstract class JAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int id : appWidgetIds) {
            onUpdate(context, appWidgetManager, id);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        for (int id : appWidgetIds) {
            onDeleted(context, id);
        }
    }

    public abstract void onUpdate(Context context, AppWidgetManager appWidgetManager, int id);

    public abstract void onDeleted(Context context, int id);

    /**
     * Returns number of cells needed for given size of the widget.
     *
     * @param size Widget size in dp.
     * @return Size in number of cells.
     */
    protected int getCellsForSize(int size) {
        int n = 2;
        while (70 * n - 30 < size) {
            ++n;
        }
        return n - 1;
    }

}
