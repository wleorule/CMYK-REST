package hr.foi.air.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.graphics.RectF;
import android.util.AttributeSet;


/**
 * The type Pie chart.
 */
public class PieChart extends BaseChart {

    /**
     * Instantiates a new Pie chart.
     *
     * @param context the context
     */
    public PieChart(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Pie chart.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Pie chart.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public PieChart(Context context,  AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            this.init();
        }
        canvas.drawColor(this.parameters.backgroundColor);
        drawPie(canvas);
        drawLegendColor(canvas);
        drawLegendText(canvas);
    }

    private void drawLegendColor(Canvas canvas) {
        paint.reset();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        float x = padding * 2;
        float y = (height - (height- width));
        float size = this.parameters.fontSize;
        int pomicanje = this.parameters.fontSize * 2;
        int duljinaTeksta = 0;

        for(int i = 0; i < DataSource.size(); i++) {

            paint.setColor(DataSource.get(i).Color);
            if(duljinaTeksta <   DataSource.get(i).Name.length()){
                duljinaTeksta = DataSource.get(i).Name.length();
            }

            y += pomicanje;
            canvas.drawRect(x,y-size,x+size,y,paint);
            if((i+1) % 3 == 0){
                x += padding + (duljinaTeksta * parameters.fontSize);
                y = (height - (height- width));
                duljinaTeksta= 0;
            }
        }
    }

    private void drawLegendText(Canvas canvas) {
        paint.reset();
        paint.setTextSize(this.parameters.fontSize);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);
        paint.setColor(this.parameters.fontColor);

        float x = (padding * 2) + (2 * this.parameters.fontSize);
        float y = (height - (height - width));
        int pomicanje = this.parameters.fontSize * 2;
        int duljinaTeksta = 0;
        String temp;
        for (int i = 0; i < DataSource.size(); i++){
            temp = String.valueOf(DataSource.get(i).Name);
            paint.getTextBounds(temp,0,temp.length(),rect);
            if(duljinaTeksta <   DataSource.get(i).Name.length()) {
                duljinaTeksta = DataSource.get(i).Name.length();
            }
            y += pomicanje;
            canvas.drawText(temp,x,y,paint);
            if((i+1) % 3 == 0){
                x += padding + (duljinaTeksta * parameters.fontSize);
                y = (height - (height- width));
                duljinaTeksta = 0;
            }
        }
    }

    private void drawPie(Canvas canvas) {
        paint.reset();
        paint.setAntiAlias(true);
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