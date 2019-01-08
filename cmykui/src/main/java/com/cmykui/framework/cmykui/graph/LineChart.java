package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class LineChart extends BaseChart {

    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            this.init();
        }

        canvas.drawColor(Color.BLACK);

        drawOsi(canvas);
        drawLine(canvas);
        drawLinesY(canvas);
        drawLinesX(canvas);
        drawLabel(canvas);
        drawNumerals(canvas);

    }

    private void drawLinesX(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        float x = padding;
        float sY = height - padding;
        float eY = height - padding - 10;
        int maxWidth = (width-padding*2) / DataSource.size() - (padding);
        for (int i = 0; i < DataSource.size(); i++){

            if(i == 0){
                x = padding;
            }
            else{
                x += padding + maxWidth;
            }

            canvas.drawLine(x, sY, x, eY, paint);
        }
    }

    private void drawNumerals(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(fontSize);
        paint.setAntiAlias(true);

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

    private void drawLabel(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(fontSize);
        paint.setAntiAlias(true);

        float x = padding;
        float y = height - (fontSize / 2);
        int maxWidth = (width-padding*2) / DataSource.size() - (padding);
        for (int i = 0; i < DataSource.size(); i++){
            paint.getTextBounds(DataSource.get(i).Name,0,DataSource.get(i).Name.length(),rect);

            canvas.drawText(DataSource.get(i).Name,x,y,paint);

            x += padding + maxWidth;
        }
    }

    private void drawLine(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);

        float startX = padding;
        float startY = height - padding;
        int maxWidth = (width-padding*2) / DataSource.size() - (padding);
        int currentX = padding;
        float currentY = 0;
        float broj = ((height - padding) / maxVisina());

        for(int i = 0; i < DataSource.size(); i++) {

            if(i == 0){
            startY = (maxVisina() - (float)DataSource.get(i).Value) * broj;
            }

            if(DataSource.get(i).Value == maxVisina()) {
                currentY = padding;
            }
            else {
                currentY = (maxVisina() - (float)DataSource.get(i).Value) * broj;
            }

            canvas.drawLine(startX, startY, currentX , currentY, paint);
            startX = currentX;
            startY = currentY;
            currentX += padding + maxWidth;
        }
    }

    private void drawLinesY(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        int broj_linijaY = (int)Math.floor(maxVisina());

        int pomicanje = Math.round((height - padding) / broj_linijaY);

        for(int i = 1; i < broj_linijaY; i++){
            int y = (height - padding) - (pomicanje * i);
            int sX = padding;
            int eX = padding + 10;

            canvas.drawLine(sX, y, eX, y, paint);
        }

    }

    private void drawOsi(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        float startX = padding;
        float startY = height - padding;
        float endX = width - padding;
        float endY = padding;

        canvas.drawLine(startX, startY, endX, startY, paint);
        canvas.drawLine(startX, startY, startX, endY, paint);

    }

    private float maxVisina(){
        float max = -999;

        for(int i = 0; i < DataSource.size(); i++) {
            if(max < DataSource.get(i).Value){
                max = (float)DataSource.get(i).Value;
            }
        }
        return max;
    }

}
