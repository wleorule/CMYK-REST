package hr.foi.air.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * The type Bar chart.
 */
public class BarChart extends AxisBaseChart {

    /**
     * Instantiates a new Bar chart.
     *
     * @param context the context
     */
    public BarChart(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Bar chart.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Bar chart.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BarChart(Context context,  AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }
    /**
     * onDraw draws one column.
     *
     * @param canvas the canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            this.init();
        }

        canvas.drawColor(Color.BLACK);

        drawShapes(canvas);
        this.drawOsi(canvas);
        this.drawLinesY(canvas);
        drawLabel(canvas);
        this.drawNumerals(canvas);
    }

    /**
     * drawShape draws one column.
     *
     * @param canvas the canvas
     */
    private void drawShapes(Canvas canvas) {
        paint.reset();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        int maxWidth = (width-padding*2) / DataSource.size() - (padding);
        int currentX = padding * 2;
        double broj = ((height - padding) / maxVisina());

        for(int i = 0; i < DataSource.size(); i++) {

            paint.setColor(DataSource.get(i).Color);

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

    /**
     * drawShapes draws all columns in graph with help of drawShape method.     *
     *
     * @param canvas   the canvas
     * @param data     the data
     * @param max      the max
     * @param maxWidth the max width
     * @param currentX the current x
     * @param paint    the paint
     */
    private void drawShape(Canvas canvas, double data, double max, double maxWidth, double currentX, Paint paint) {
        canvas.drawRect((float)currentX, (float) data, (float)(currentX + maxWidth),(height - padding), paint);
    }

    /**
     * drawLabel prints labels below the columns on the graph.
     *
     * @param canvas the canvas
     */
    private void drawLabel(Canvas canvas) {
        paint.reset();
        paint.setColor(this.parameters.fontColor);
        paint.setTextSize(this.parameters.fontSize);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);

        float x = padding * 2;
        float y = height - padding + (this.parameters.fontSize);
        int maxWidth = (width-padding*2) / DataSource.size() - (padding);
        for (int i = 0; i < DataSource.size(); i++){
            paint.getTextBounds(DataSource.get(i).Name,0,DataSource.get(i).Name.length(),rect);

            canvas.drawText(DataSource.get(i).Name,x,y,paint);

            x += padding + maxWidth;
        }
    }
}
