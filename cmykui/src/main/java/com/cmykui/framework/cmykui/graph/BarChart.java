package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BarChart extends View {


    private Paint paint;
    private int width, height, padding;
    private boolean isInit = false;

    public float[] Data = new float[7];



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
        padding = 20;

        isInit = true;

        Data[0] = 15.5f;
        Data[1] = 50.5f;
        Data[2] = 3.5f;
        Data[3] = 8.5f;
        Data[4] = 25.5f;
        Data[5] = 30.5f;
        Data[6] = 15.5f;

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
    }

    private void drawShapes(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        // Izracun
        float max = -999;
        for(int i = 0; i < Data.length; i++) {
            if(max < Data[i]){
                max = Data[i];
            }
        }

        int razmak = padding;
        int maxWidth = (width-padding*2) / Data.length - (razmak);
        int currentX = padding * 2;
        float broj = ((height - padding) / max);

        for(int i = 0; i < Data.length; i++) {

            float data = 0;


            if(Data[i] == max) {
                data = padding;
            }
            else {
                data = (max - Data[i]) * broj;
            }

            drawShape(canvas, data,max, maxWidth, currentX, paint);
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

        int pomicanje = 20;

        //int broj_linijaX = (int)Math.floor((width - 100) / pomicanje); // 10 je ramak izmedu linija
        int broj_linijaY = (int)Math.floor((height - padding*2) / pomicanje); // 10 je ramak izmedu linija

        // po X
        /*for(int i = 1; i <= broj_linijaX; i++){
            int x = 50 + (pomicanje * i);
            int sY = height - 40; // sjeti se da je dole - 50;
            int eY = height - 60;
            canvas.drawLine(x, sY, x, eY, paint);
        }*/

        // po Y
        for(int i = 1; i <= broj_linijaY; i++){
            int y = (height - padding) - (pomicanje * i);
            int sX = padding;
            int eX = padding + 10;

            canvas.drawLine(sX, y, eX, y, paint);
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
}
