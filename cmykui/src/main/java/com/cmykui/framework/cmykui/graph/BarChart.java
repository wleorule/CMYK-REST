package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


import java.util.Random;

public class BarChart extends View {


    private Paint paint;
    private int width, height, padding;
    private boolean isInit = false;
    private Rect rect = new Rect();
    private int fontSize;

    public float[] Data = new float[7];
    public String[] label = new String[7];

    public BarChart(Context context) {
        super(context);
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarChart(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    private void init() {
        paint = new Paint();

        width = getWidth();
        height = getHeight();
        padding = 30;

        isInit = true;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics());


        Data[0] = 15.5f;
        Data[1] = 20.5f;
        Data[2] = 3.5f;
        Data[3] = 8.5f;
        Data[4] = 25.5f;
        Data[5] = 8.5f;
        Data[6] = 25.5f;

        label [0] = "prvi";
        label [1] = "drugi";
        label [2] = "treci";
        label [3] = "cetvrti";
        label [4] = "peti";
        label [5] = "sesti";
        label [6] = "sedmi";


    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            init();
        }

        canvas.drawColor(Color.BLACK);

        drawOsi(canvas);
        drawLines(canvas);
        drawShapes(canvas);
        drawLabel(canvas);
        drawNumerals(canvas);
    }

    private void drawShapes(Canvas canvas) {
        paint.reset();

        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);


        int maxWidth = (width-padding*2) / Data.length - (padding);
        int currentX = padding * 2;
        float broj = ((height - padding) / maxVisina());

        for(int i = 0; i < Data.length; i++) {


            paint.setColor(randomColor());

            float data = 0;

            if(Data[i] == maxVisina()) {
                data = padding;
            }
            else {
                data = (maxVisina() - Data[i]) * broj;
            }

            drawShape(canvas, data,maxVisina(), maxWidth, currentX, paint);
            currentX += padding + maxWidth;
        }
    }

    private void drawShape(Canvas canvas, float data, float max, int maxWidth, int currentX, Paint paint) {
        canvas.drawRect(currentX, data, (currentX + maxWidth),(height - padding), paint);
    }

    private void drawLines(Canvas canvas) {

        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        int broj_linijaY = (int)Math.floor(maxVisina());

        int pomicanje = Math.round((height - padding) / broj_linijaY);

        // po Y
        for(int i = 1; i < broj_linijaY; i++){
            int y = (height - padding) - (pomicanje * i);
            int sX = padding;
            int eX = padding + 10;

            canvas.drawLine(sX, y, eX, y, paint);
        }

    }


    private void drawNumerals(Canvas canvas) {
        paint.setTextSize(fontSize);
        paint.setTextAlign(Paint.Align.CENTER);

        float x = padding / 2;
        float y;
        int br = (int)Math.floor(maxVisina());
        int pomicanje = Math.round((height - padding) / br);
        String temp;
        for (int i = 1; i < br; i++){
            temp = String.valueOf(i);
            paint.getTextBounds(temp,0,temp.length(),rect);
            y = (height - padding) - (pomicanje * i) + (fontSize / 2);
            if(i%5==0){
                canvas.drawText(temp,x,y,paint);
            }

        }
    }

    private void drawOsi(Canvas canvas) {

        //Postavljam sve za boju u te drekove
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        // Racunam gdje kako sta
        float startX = padding; // neka krene malo u lijevo
        float startY = height - padding; //neka krene malo manje od kraja
        float endX = width - padding;
        float endY = padding;

        canvas.drawLine(startX, startY, endX, startY, paint);
        canvas.drawLine(startX, startY, startX, endY, paint);

    }

    private void drawLabel(Canvas canvas) {
        paint.setTextSize(fontSize);
        paint.setColor(Color.WHITE);

        float x = padding * 2;
        float y = height - 5;
        int maxWidth = (width-padding*2) / Data.length - (padding);
        for (int i = 0; i < Data.length; i++){
            paint.getTextBounds(label[i],0,label[i].length(),rect);

            canvas.drawText(label[i],x,y,paint);

            x += padding + maxWidth;
        }
    }

    private int randomColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }

    private float maxVisina(){
        // Izracun
        float max = -999;

        for(int i = 0; i < Data.length; i++) {
            if(max < Data[i]){
                max = Data[i];
            }
        }
        return max;
    }
}
