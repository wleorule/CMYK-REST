package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;


public class PieChart extends View {
    private Paint paint;
    private int width, height, padding;
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
        drawPie(canvas);

    }

    private void drawPie(Canvas canvas) {
        float angle = 0;

        RectF oval = new RectF();
        oval.top = padding;
        oval.bottom = width - padding;
        oval.left = padding;
        oval.right = width - padding;
        paint.setStyle(Paint.Style.FILL);

        for(int i = 0; i < Data.length; i++) {

            paint.setColor(randomColor());
            float sweep = (float)(360 * (Data[i]) / sum());
            canvas.drawArc(oval, angle, sweep, true,paint);
            angle += sweep;

        }
    }

    private int randomColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }


    private double sum(){
        float sum = 0;

        for(int i = 0; i < Data.length; i++) {
            sum += Data[i];
        }
        return sum;
    }
}
