package com.cmykui.framework.cmykui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MatijaTest extends View {

    private Paint paint;
    private int width, height;
    private boolean isInit = false;

    public MatijaTest(Context context) {
        super(context);
    }

    public MatijaTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MatijaTest(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint();

        width = getWidth();
        height = getHeight();

        isInit = true;
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

        // prvi
        canvas.drawRect(70, 500,200,(height - 70), paint);
        // drugi
        paint.setColor(Color.YELLOW);
        canvas.drawRect(210, 800,340,(height - 70), paint);
        // treci
        paint.setColor(Color.GREEN);
        canvas.drawRect(350, 300,480,(height - 70), paint);
    }

    private void drawLines(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        int pomicanje = 20;

        int broj_linijaX = (int)Math.floor((width - 100) / pomicanje); // 10 je ramak izmedu linija
        int broj_linijaY = (int)Math.floor((height - 100) / pomicanje); // 10 je ramak izmedu linija

        // po X
        for(int i = 1; i <= broj_linijaX; i++){
            int x = 50 + (pomicanje * i);
            int sY = height - 40; // sjeti se da je dole - 50;
            int eY = height - 60;

            canvas.drawLine(x, sY, x, eY, paint);
        }

        // po Y
        for(int i = 1; i <= broj_linijaY; i++){
            int y = (height - 50) - (pomicanje * i);
            int sX = 40; // sjeti se da je dole - 50;
            int eX = 60;

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
        float startX = 50; // neka krene malo u lijevo
        float startY = height - 50; //neka krene malo manje od kraja
        float endX = width - 50;
        float endY = 50;

        canvas.drawLine(startX, startY, endX, startY, paint);
        canvas.drawLine(startX, startY, startX, endY, paint);

    }
}
