package com.chinese_character_lion;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.3f;

    public void transformPage(View view, float position) {

        if (position <= 0.1F && position >= -0.1F) {
            view.setAlpha(1.0F);
        } else {
            float scaleFactor = Math.max(MIN_SCALE, 1.0F - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }
    }
}
