package com.lin.mycanvas.desigin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by root on 15-9-15.
 */
public class IdeaCanvas extends View {
    Context context ;
    public IdeaCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }
    public IdeaCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    public IdeaCanvas(Context context) {
        super(context);
        this.context = context;
    }





}
