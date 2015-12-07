package com.getbase.floatingactionbutton.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.getbase.floatingactionbutton.R;

import java.util.List;

/**
 * Created by xinlyun on 15-12-7.
 */
public class HengDataView extends View {
    private long dataSet = 0;
    private float lastD;
    private int width,w;
    private Paint textPaint;
    private float speed;
    private MyThread myThread;
    public HengDataView(Context context) {
        super(context);
        initPaint();
    }

    public HengDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public HengDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
    private void initPaint(){
        textPaint = new Paint();
        textPaint.setTextSize(30);
        textPaint.setColor(getContext().getResources().getColor(R.color.red));
        textPaint.setAntiAlias(true);
        this.width = 1080;
        this.w = width/6;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastD = event.getX();
                if(myThread != null ) {
                    myThread.stopthis();
                    myThread = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                dataSet = dataSet + (long)(speed=(speed+event.getX()-lastD)/2);

                lastD = event.getX();
                update();
                break;
            case MotionEvent.ACTION_UP:
                myThread = new MyThread(speed,dataSet);
                myThread.start();
                break;
        }
        Log.d("HengDataView", "speed:" + speed);
        return true;
    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dataSet = (long) msg.obj;
            update();
        }
    };

    class MyThread extends Thread {
        private long data;
        private float speed;
        private float smeed;
        private boolean flag = true;
        MyThread(float speed,long data){
            this.data = data;
            this.speed = speed;
            this.smeed = speed/100;
        }

        public void stopthis(){
            flag = false;
        }
        @Override
        public void run() {
            super.run();
            int i = 0;
            while (speed!=0){
                if(flag) {
                    if(i==90)break;
                    if(Math.abs(speed)<3)break;
                    data = data + (int)speed;
                    Message message = myHandler.obtainMessage();
                    message.obj = data;
                    myHandler.sendMessage(message);
                    speed = speed - smeed;
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                else break;
            }
            long d1 = data/w*w;
            long d2 = d1+w;
            long posi ;
            if(d2-data>data-d1){
                posi = d1;
            }else {
                posi = d2;
            }
            speed = (posi-data)/10f;
            for(int k=0;k<10;k++){
                if(!flag)break;
                if(k==9){
                    Message message = myHandler.obtainMessage();
                    message.obj = posi;
                    myHandler.sendMessage(message);
                    break;
                }
                data = (long) (data+speed);
                Message message = myHandler.obtainMessage();
                message.obj = data;
                myHandler.sendMessage(message);
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }



    private void update(){
        if(dataSet<0)dataSet=0;
        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

    }
    private List<Float> data;
    public void setData(List<Float> data){
        this.data = data;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("" + (int) (dataSet / w - 1), width / 2 + dataSet % w + w - 32, 50, textPaint);
        if((int) (dataSet / w - 1)>=0&&(int) (dataSet / w - 1)<data.size())
            canvas.drawRect(width/2+dataSet%w+w  -32,0,width/2+dataSet%w+w  -32+42,300*data.get((int) (dataSet / w - 1)),textPaint);

        canvas.drawText(""+(int)(dataSet/w-2),width/2+dataSet%w+w*2-32,50 ,textPaint);
        if((int) (dataSet / w - 2)>=0&&(int) (dataSet / w - 2)<data.size())
            canvas.drawRect(width/2+dataSet%w+w*2-32,0,width/2+dataSet%w+w*2-32 +42,300*data.get((int) (dataSet / w - 2)),textPaint);

        canvas.drawText(""+(int)(dataSet/w-3),width/2+dataSet%w+w*3-32,50 ,textPaint);
        if((int) (dataSet / w - 3)>=0&&(int) (dataSet / w - 3)<data.size())
            canvas.drawRect(width/2+dataSet%w+w*3-32,0,width/2+dataSet%w+w*3-32 +42,300*data.get((int) (dataSet / w - 3)),textPaint);

        canvas.drawText(""+(int)(dataSet/w-4),width/2+dataSet%w+w*4-32,50 ,textPaint);
        if((int) (dataSet / w - 4)>=0&&(int) (dataSet / w - 4)<data.size())
            canvas.drawRect(width/2+dataSet%w+w*4-32,0,width/2+dataSet%w+w*4-32 +42,300*data.get((int) (dataSet / w - 4)),textPaint);

        canvas.drawText(""+(int)(dataSet/w  ),width/2+dataSet%w    -32,50 ,textPaint);
        if((int) (dataSet / w )>=0&&(int) (dataSet / w )<data.size())
            canvas.drawRect(width/2+dataSet%w-32,0,width/2+dataSet%w-32 +42,300*data.get((int) (dataSet / w )),textPaint);

        canvas.drawText("" + (int) (dataSet / w + 1), width / 2 + dataSet % w - w * 1 - 32, 50, textPaint);
        if((int) (dataSet / w + 1)>=0&&(int) (dataSet / w + 1)<data.size())
            canvas.drawRect(width/2+dataSet%w-w-32,0,width/2+dataSet%w-w-32 +42,300*data.get((int) (dataSet / w + 1)),textPaint);

        canvas.drawText(""+(int)(dataSet/w+2),width/2+dataSet%w-w*2-32,50 ,textPaint);
        if((int) (dataSet / w + 2)>=0&&(int) (dataSet / w + 2)<data.size())
            canvas.drawRect(width/2+dataSet%w-w*2-32,0,width/2+dataSet%w-w*2-32 +42,300*data.get((int) (dataSet / w + 2)),textPaint);

        canvas.drawText(""+(int)(dataSet/w+3),width/2+dataSet%w-w*3-32,50 ,textPaint);
        if((int) (dataSet / w + 3)>=0&&(int) (dataSet / w + 3)<data.size())
            canvas.drawRect(width/2+dataSet%w-w*3-32,0,width/2+dataSet%w-w*3-32 +42,300*data.get((int) (dataSet / w + 3)),textPaint);

        canvas.drawText(""+(int)(dataSet/w+4),width/2+dataSet%w-w*4-32,50 ,textPaint);
        if((int) (dataSet / w + 4)>=0&&(int) (dataSet / w + 4)<data.size())
            canvas.drawRect(width/2+dataSet%w-w*4-32,0,width/2+dataSet%w-w*4-32 +42,300*data.get((int) (dataSet / w + 4)),textPaint);

        canvas.drawText(""+(int)(dataSet/w+5),width/2+dataSet%w-w*5-32,50 ,textPaint);
        if((int) (dataSet / w + 5)>=0&&(int) (dataSet / w + 5)<data.size())
            canvas.drawRect(width/2+dataSet%w-w*5-32,0,width/2+dataSet%w-w*5-32 +42,300*data.get((int) (dataSet / w + 5)),textPaint);
    }
}
