package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


import com.cmykui.framework.cmykui.base.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarChart extends View {


    private Paint paint;
    private int width, height, padding;
    private boolean isInit = false;
    private Rect rect = new Rect();
    private int fontSize;

    //public float[] Data = new float[7];
    //public String[] label = new String[7];
    public List<DataSource> DataSource = new ArrayList<DataSource>();

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


        DataSource temp = new DataSource("prvi", 15.5f);
        this.DataSource.add(temp);

        temp = new DataSource("drugi", 20.5f);
        this.DataSource.add(temp);

        temp = new DataSource("treci", 3.5f);
        this.DataSource.add(temp);

        //Data[0] = 15.5f;
        //Data[1] = 20.5f;
        //Data[2] = 3.5f;
        //Data[3] = 8.5f;
        //Data[4] = 25.5f;
        //Data[5] = 8.5f;
        //Data[6] = 25.5f;

        //label [0] = "prvi";
        //label [1] = "drugi";
        //label [2] = "treci";
        //label [3] = "cetvrti";
        //label [4] = "peti";
        //label [5] = "sesti";
        //label [6] = "sedmi";


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


        int maxWidth = (width-padding*2) / DataSource.size() - (padding);
        int currentX = padding * 2;
        double broj = ((height - padding) / maxVisina());

        for(int i = 0; i < DataSource.size(); i++) {


            paint.setColor(randomColor());

            double data = 0;

            if(DataSource.get(i).Value == maxVisina()) {
                data = padding;
            }
            else {
                data = (maxVisina() - DataSource.get(i).Value) * broj;
            }

            drawShape(canvas, data,maxVisina(), maxWidth, currentX, paint);
            currentX += padding + maxWidth;
        }
    }

    private void drawShape(Canvas canvas, double data, double max, double maxWidth, double currentX, Paint paint) {
        canvas.drawRect((float)currentX, (float) data, (float)(currentX + maxWidth),(height - padding), paint);
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
        int maxWidth = (width-padding*2) / DataSource.size() - (padding);
        for (int i = 0; i < DataSource.size(); i++){
            paint.getTextBounds(DataSource.get(i).Name,0,DataSource.get(i).Name.length(),rect);

            canvas.drawText(DataSource.get(i).Name,x,y,paint);

            x += padding + maxWidth;
        }
    }

    private int randomColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }

    private double maxVisina(){
        // Izracun
        double max = -999;

        for(int i = 0; i < DataSource.size(); i++) {
            if(max < DataSource.get(i).Value){
                max = DataSource.get(i).Value;
            }
        }
        return max;
    }
}
