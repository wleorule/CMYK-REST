package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.cmykui.framework.cmykui.base.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PieChart extends View {
    private Paint paint;
    private int width, height, padding;

    private boolean isInit = false;

    public List<DataSource> DataSource = new ArrayList<DataSource>();

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

        DataSource temp = new DataSource("prvi", 15.5f, Color.RED);
        this.DataSource.add(temp);

        temp = new DataSource("drugi", 20.5f, Color.BLUE);
        this.DataSource.add(temp);

        temp = new DataSource("treci", 3.5f);
        this.DataSource.add(temp);
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
