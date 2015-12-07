package com.lin.mycanvas.desigin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 */
public class ColorsFinder extends View {
    Paint paint ;
    public ColorsFinder(Context context) {
        super(context);
        paint = new Paint();

    }

    public ColorsFinder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorsFinder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }
}
