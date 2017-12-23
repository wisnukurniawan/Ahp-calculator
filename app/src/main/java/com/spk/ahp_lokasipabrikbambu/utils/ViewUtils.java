package com.spk.ahp_lokasipabrikbambu.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;

/**
 * Created by wisnu on 12/23/17.
 */

public class ViewUtils {

    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color) {
        Drawable normalDrawable = item.getIcon();
        Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
        DrawableCompat.setTint(wrapDrawable, context.getResources().getColor(color));

        item.setIcon(wrapDrawable);
    }

}
