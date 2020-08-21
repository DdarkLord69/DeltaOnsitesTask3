package com.example.stopwatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ClockView extends View {

    public boolean isRunning = false;

    private Paint hand = new Paint();
    private Paint dial = new Paint();

    private float screenWidth;
    private float screenHeight;

    private final float pi = (float) Math.PI;

    private float mainDialcx;
    private float mainDialcy;
    private float mainRadius;
    private float smallDialcx;
    private float smallDialcy;
    private float smallRadius;
    private float secondHandlength;
    private float minHandlength;

    public int i = -900;
    public int s = -15;

    public ClockView(Context context) {
        super(context);

        init(null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        mainDialcx = screenWidth/2;
        mainDialcy = screenHeight*5/12;
        mainRadius = screenHeight/4;
        smallDialcx = screenWidth/2;
        smallDialcy = screenHeight*15/48;
        smallRadius = screenHeight/15;
        secondHandlength = screenHeight/6;
        minHandlength = screenHeight/20;

        dial.setColor(Color.BLACK);
        dial.setStyle(Paint.Style.STROKE);
        dial.setStrokeWidth(10f);
        hand.setColor(Color.BLACK);
        hand.setStyle(Paint.Style.STROKE);
        hand.setStrokeWidth(10f);
        hand.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(screenWidth/2, screenHeight*5/12, screenHeight/4, dial);
        canvas.drawCircle(screenWidth/2, screenHeight*15/48, screenHeight/15, dial);
        for(int j = 0 ; j < 12 ; j++) {
            canvas.drawLine((float) (mainDialcx + mainRadius*9/10*Math.cos(pi*j/6)) ,
                    (float) (mainDialcy + mainRadius*9/10*Math.sin(pi*j/6)),
                    (float) (mainDialcx + mainRadius*Math.cos(pi*j/6)),
                    (float) (mainDialcy + mainRadius*Math.sin(pi*j/6)), hand);
            canvas.drawLine((float) (smallDialcx + smallRadius*9/10*Math.cos(pi*j/6)) ,
                    (float) (smallDialcy + smallRadius*9/10*Math.sin(pi*j/6)),
                    (float) (smallDialcx + smallRadius*Math.cos(pi*j/6)),
                    (float) (smallDialcy + smallRadius*Math.sin(pi*j/6)), hand);
        }

        drawMinLineAng(i, canvas);
        drawSecLineAng(s, canvas);

        if(isRunning) {
            i++;
            s++;
            postInvalidateDelayed(1000);
        }
    }

    private void drawSecLineAng(int i, Canvas canvas) {
        canvas.drawLine(mainDialcx, mainDialcy, (float) (mainDialcx + secondHandlength*Math.cos(pi*i/30)),
                (float) (mainDialcy + secondHandlength*Math.sin(pi*i/30)), hand);
    }

    private void drawMinLineAng(int i, Canvas canvas) {
        canvas.drawLine(smallDialcx, smallDialcy, (float) (smallDialcx + minHandlength*Math.cos(pi*i/1800)),
                (float) (smallDialcy + minHandlength*Math.sin(pi*i/1800)), hand);
    }
}
