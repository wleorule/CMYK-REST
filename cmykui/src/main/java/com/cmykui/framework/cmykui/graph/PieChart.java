package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

public class PieChart extends BaseChart {

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            this.init();
        }
        canvas.drawColor(Color.BLACK);
        drawPie(canvas);
        drawLegend(canvas);

    }

    private void drawLegend(Canvas canvas) {
        paint.reset();
        paint.setTextSize(fontSize);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.WHITE);

        float x = padding * 2;
        float y = (height - (height- width));
        int pomicanje = fontSize*2;
        String temp;
        for (int i = 0; i <= DataSource.size(); i++){
            temp = String.valueOf(DataSource.get(i).Name);
            paint.getTextBounds(temp,0,temp.length(),rect);
            y += pomicanje;
            canvas.drawText(temp,x,y,paint);
            if((i+1) % 3 == 0){
                x += padding * 3;
                y = (height - (height- width));
            }
        }
    }

    private void drawPie(Canvas canvas) {
        paint.reset();
        float angle = 0;

        RectF oval = new RectF();
        oval.top = padding;
        oval.bottom = width - padding;
        oval.left = padding;
        oval.right = width - padding;
        paint.setStyle(Paint.Style.FILL);

        for(int i = 0; i < DataSource.size(); i++) {

            paint.setColor(DataSource.get(i).Color);
            float sweep = (float)(360 * (DataSource.get(i).Value) / sum());
            canvas.drawArc(oval, angle, sweep, true,paint);
            angle += sweep;

        }
    }

    private double sum(){
        float sum = 0;

        for(int i = 0; i < DataSource.size(); i++) {
            sum += DataSource.get(i).Value;
        }
        return sum;
    }
}
