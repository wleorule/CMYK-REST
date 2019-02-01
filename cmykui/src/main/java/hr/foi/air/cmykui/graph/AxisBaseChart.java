package hr.foi.air.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * The type Axis base chart.
 */
public abstract class AxisBaseChart extends BaseChart {

    /**
     * Instantiates a new Axis base chart.
     *
     * @param context the context
     */
    public AxisBaseChart(Context context) { super(context); }

    /**
     * Instantiates a new Axis base chart.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public AxisBaseChart(Context context, AttributeSet attrs) { super(context, attrs); }

    /**
     * Instantiates a new Axis base chart.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public AxisBaseChart(Context context,  AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    /**
     * drawNumerals method prints numeric values on Y axis.
     *
     * @param canvas the canvas
     */
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

    /**
     * drawOsi method draws the X and Y axis on canvas.
     *
     * @param canvas the canvas
     */
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

    /**
     * drawLinesY method draws lines along numbers on Y axis.
     *
     * @param canvas the canvas
     */
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

    /**
     * maxVisina method returns the highest value in the list.
     *
     * @return the float
     */
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
