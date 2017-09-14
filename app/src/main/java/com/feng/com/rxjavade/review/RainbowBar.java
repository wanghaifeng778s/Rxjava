package com.feng.com.rxjavade.review;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.utils.DensityUtil;

/**
 * Created by WHF.Javas on 2017/8/25.
 */

public class RainbowBar extends View {

    //progress bar color
    int barColor = Color.parseColor("#1E88E5");
    //every bar segment width
    int hSpace = DensityUtil.dpToPx(80, getResources());
    //every bar segment height
    int vSpace = DensityUtil.dpToPx(4, getResources());
    //space among bars
    int space = DensityUtil.dpToPx(10, getResources());
    float startX = 0;
    float delta = 10f;
    Paint mPaint;


    public RainbowBar(Context context) {
        super(context);
    }

    public RainbowBar(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public RainbowBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //read custom attrs
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.rainbowbar, 0, 0);
        hSpace = t.getDimensionPixelSize(R.styleable.rainbowbar_rainbowbar_hspace, hSpace);
        vSpace = t.getDimensionPixelOffset(R.styleable.rainbowbar_rainbowbar_vspace, vSpace);
        barColor = t.getColor(R.styleable.rainbowbar_rainbowbar_color, barColor);
        t.recycle();   // we should always recycle after used
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(barColor);
        mPaint.setStrokeWidth(vSpace);
    }

    int index = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float sw = this.getMeasuredWidth();
        if (startX >= sw + (hSpace + space) - (sw % (hSpace + space))) {
            startX = 0;
        } else {
            startX += delta;
        }
        float start = startX;
        // draw latter parse
        while (start < sw) {
            canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
            start += (hSpace + space);
        }

        start = startX - space - hSpace;

        // draw front parse
        while (start >= -hSpace) {
            canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
            start -= (hSpace + space);
        }
        if (index >= 700000) {
            index = 0;
        }
        invalidate();
    }
}
