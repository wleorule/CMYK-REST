package hr.foi.air.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * The type Line chart.
 */
public class LineChart extends AxisBaseChart {

    /**
     * Instantiates a new Line chart.
     *
     * @param context the context
     */
    public LineChart(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Line chart.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Line chart.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public LineChart(Context context,  AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            this.init();
        }

        canvas.drawColor(this.parameters.backgroundColor);

        this.drawOsi(canvas);
        drawLine(canvas);
        this.drawLinesY(canvas);
        drawLinesX(canvas);
        drawLabel(canvas);
        this.drawNumerals(canvas);

    }

    private void drawLinesX(Canvas canvas) {
        paint.reset();
        paint.setColor(this.parameters.lineColor);
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

    private void drawLabel(Canvas canvas) {
        paint.reset();
        paint.setColor(this.parameters.fontColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(this.parameters.fontSize);
        paint.setAntiAlias(true);

        float x = padding;
        float y = height - padding + (this.parameters.fontSize);
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
        paint.setStrokeCap(Paint.Cap.ROUND);
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
}
