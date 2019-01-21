package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class AxisBaseChart extends BaseChart {

    public AxisBaseChart(Context context) { super(context); }

    public AxisBaseChart(Context context, AttributeSet attrs) { super(context, attrs); }

    public AxisBaseChart(Context context,  AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    public void drawNumerals(Canvas canvas) {
        paint.reset();
        paint.setColor(this.parameters.fontColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(this.parameters.fontSize);
        paint.setAntiAlias(true);

        float x = padding / 2;
        float y;
        int br = (int)Math.floor(maxVisina());
        int pomicanje = Math.round((height - padding) / br);
        String temp;
        for (int i = 1; i < br; i++){
            temp = String.valueOf(i);
            paint.getTextBounds(temp,0,temp.length(),rect);
            y = (height - padding) - (pomicanje * i) + (this.parameters.fontSize / 2);
            if(i%5==0){
                canvas.drawText(temp,x,y,paint);
            }
        }
    }

    public void drawOsi(Canvas canvas) {
        paint.reset();
        paint.setColor(this.parameters.lineColor);
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

    public void drawLinesY(Canvas canvas) {
        paint.reset();
        paint.setColor(this.parameters.lineColor);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        int broj_linijaY = (int)Math.floor(this.maxVisina());
        int pomicanje = Math.round((height - padding) / broj_linijaY);

        for(int i = 1; i < broj_linijaY; i++){
            int y = (height - padding) - (pomicanje * i);
            int sX = padding;
            int eX = padding + 10;

            canvas.drawLine(sX, y, eX, y, paint);
        }
    }

    public float maxVisina(){
        float max = -999;

        for(int i = 0; i < DataSource.size(); i++) {
            if(max < DataSource.get(i).Value){
                max = (float)DataSource.get(i).Value;
            }
        }
        return max;
    }
}
