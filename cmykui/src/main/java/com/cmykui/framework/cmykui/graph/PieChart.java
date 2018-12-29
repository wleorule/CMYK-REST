package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


public class PieChart extends View {
    private Paint paint;
    private int width, height, padding, radius;
    private Rect rect = new Rect();

    private boolean isInit = false;


    public float[] Data = new float[7];



    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        height = getHeight();
        width = getWidth();
        padding = 50;
        int min = Math.min(height,width);
        radius = min /2 - padding;
        paint = new Paint();
        isInit = true;


        Data[0] = 20.5f;
        Data[1] = 10.5f;
        Data[2] = 5.5f;
        Data[3] = 10.5f;
        Data[4] = 30.5f;
        Data[5] = 10.5f;
        Data[6] = 10.5f;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            init();
        }
        canvas.drawColor(Color.BLACK);
        drawCircle(canvas);
        drawLines(canvas);

    }

    private void drawLines(Canvas canvas) {
        double angle = 0;

        for(int i = 0; i < Data.length; i++) {
            angle += Math.PI * (share()*Data[i]);
            canvas.drawLine(width/2, height/2, (float)(width / 2 + Math.cos(angle) * radius),(float)(height / 2 + Math.sin(angle) * radius), paint);
        }
    }


    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, height /2, radius , paint);

    }

    private double share(){
        float sum = -360;

        for(int i = 0; i < Data.length; i++) {
            sum =+ Data[i];
        }
        double s = Math.PI / sum;
        return s;
    }
}
