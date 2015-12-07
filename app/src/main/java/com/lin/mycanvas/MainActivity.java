package com.lin.mycanvas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.lin.mycanvas.desigin.IdeaCanvas;


public class MainActivity extends Activity {
    IdeaCanvas ideaCanvas;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ideaCanvas = new IdeaCanvas(this);
//        setContentView(ideaCanvas);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        initview();
    }
    private void initview(){
        ideaCanvas = (IdeaCanvas) findViewById(R.id.myIdeaCanvas);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ideaCanvas.setDrawOrmove(false);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ideaCanvas.setDrawOrmove(true);
            }
        });

    }

}
