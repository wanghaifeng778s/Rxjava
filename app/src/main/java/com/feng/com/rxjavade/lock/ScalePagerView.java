package com.feng.com.rxjavade.lock;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ScalePagerView extends ViewPager {


    public ScalePagerView(Context context) {
        this(context, null);
    }

    public ScalePagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setPageTransformer(true, new PageTransformer() {
            private static final float MIN_SCALE = 0.82f;
            private static final float MIN_ALPHA = 0.8f;

            @Override
            public void transformPage(View page, float position) {
                if (position < -1 || position > 1) {
                    page.setAlpha(MIN_ALPHA);
                    page.setScaleX(MIN_SCALE);
                    page.setScaleY(MIN_SCALE);
                } else if (position <= 1) { // [-1,1]
                    position = Math.abs(position);
                    float scaleX = MIN_SCALE + (1 - position) * (1 - MIN_SCALE);
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                    page.setAlpha(MIN_ALPHA + (1 - position) * (1 - MIN_ALPHA));
                }
            }
        });
    }
}
