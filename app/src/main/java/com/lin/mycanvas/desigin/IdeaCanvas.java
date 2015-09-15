package com.lin.mycanvas.desigin;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by root on 15-9-15.
 */
public class IdeaCanvas extends View {
    Context context ;
    ArrayList<Micon> micons ;
    Micon currMicon;
    private float posix,posiy;
    boolean drawOrmove=true;
    private Micon mover=null;
    public IdeaCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        micons = new ArrayList<>();
        this.context = context;
    }
    public IdeaCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        micons = new ArrayList<>();
        this.context = context;
    }
    public IdeaCanvas(Context context) {
        super(context);
        micons = new ArrayList<>();
        this.context = context;
    }
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        for(Micon micon:micons){
            micon.onDraw(canvas);
        }
    }

    public void setDrawOrmove(boolean drawOrmove) {
        this.drawOrmove = drawOrmove;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(drawOrmove){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    currMicon = new Micon();
                    micons.add(currMicon);
                    currMicon.draw(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    currMicon.draw(event);
                    break;
                case MotionEvent.ACTION_UP:
                    currMicon.draw(event);
//                micons.add(currMicon);
                    break;
            }}
        else {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    for(Micon m:micons){
                        if (m.isTouch(event)){
                            mover = m;
                            posix = event.getX();
                            posiy = event.getY();
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(mover!=null){
                        mover.moveadd(event.getX()-posix,event.getY()-posiy);
                        posix = event.getX();
                        posiy = event.getY();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mover = null;
                    break;
            }
        }
        invalidate();
        return true;
    }




}
