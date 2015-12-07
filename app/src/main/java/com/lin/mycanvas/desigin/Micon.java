package com.lin.mycanvas.desigin;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by root on 15-9-15.
 */
public class Micon {
    Paint paint ;
//    = new Paint();
    Ipoint originPoint,currPoint;
    float top,left,right,bottom;
    public Micon(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);

//                   paint.setColor(999999);
    }
    public Micon(Paint paint){
        this.paint = paint;
    }
    public void onDraw(Canvas canvas){
        Ipoint drawPoint = originPoint;
        while(drawPoint!=null){
//            canvas.drawPoint(drawPoint.getX(),drawPoint.getY(),paint);
            try {
                canvas.drawLine(drawPoint.getX(), drawPoint.getY(), drawPoint.nextIpoint.getX(), drawPoint.nextIpoint.getY(), paint);
            }catch (Exception e){

            }
            drawPoint = drawPoint.nextIpoint;
        }
    }
    public void draw(MotionEvent motionEvent){
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                Ipoint ipoint = new Ipoint(motionEvent);
                originPoint=currPoint = ipoint;
                left=right =motionEvent.getX();
                top=bottom = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                right = motionEvent.getX()>right?motionEvent.getX():right;
                left = motionEvent.getX()<left?motionEvent.getX():left;
                top = motionEvent.getY()<top?motionEvent.getY():top;
                bottom = motionEvent.getY()>bottom?motionEvent.getY():bottom;
                Ipoint ipoint1 = new Ipoint(motionEvent);
                currPoint.setNextIpoint(ipoint1);
                currPoint = ipoint1;
                break;
            case MotionEvent.ACTION_UP:
                Ipoint ipoint2 = new Ipoint(motionEvent);
                currPoint.setNextIpoint(ipoint2);
                ipoint2.setNextIpoint(null);
                break;
        }
    }

    public boolean isTouch(MotionEvent event){
        if(event.getX()>left&&event.getX()<right){
            if(event.getY()>top&&event.getY()<bottom)
                return true;
        }
        Log.d("Micon","notouch");
        return false;
    }

    public void moveadd(float addx,float addy){
        Ipoint ipoint = originPoint;
        while(ipoint!=null){
            ipoint.move(addx,addy);
            ipoint = ipoint.nextIpoint;
        }
        top=top+addy;
        bottom = bottom +addy;
        left = left+addx;
        right = right+addx;
    }

    class Ipoint{
        public Ipoint(MotionEvent motionEvent){
            this.x = motionEvent.getX();
            this.y = motionEvent.getY();
        }
        private float x=0;
        private float y=0;

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
        public void move(float addx,float addy){
            this.x = this.x +addx;
            this.y = this.y +addy;
        }
        Ipoint nextIpoint = null;
        public void setNextIpoint(Ipoint nextIpoint) {
            this.nextIpoint = nextIpoint;
        }
    }
}
