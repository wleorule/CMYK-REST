package hr.foi.air.cmykui.graph;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import hr.foi.air.cmykui.base.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Base chart.
 */
public abstract class BaseChart extends View {

    /**
     *
     * Paint is an atribute which determines the parameters of things that are currently being drawn.
     */
    public Paint paint;
    /**
     * Width is a background width of a graph.
     */
    public int width, /**
     * height is a background height of a graph .
     */
    height, /**
     * The Padding.
     */
    padding;
    /**
     * isInit checks if the metho init() has been called.
     */
    public boolean isInit = false;
    /**
     * rect is used for drawing.
     */
    public Rect rect = new Rect();

    /**
     * parameters is used for certain parameters of DrawParameters class.
     */
    public DrawParameters parameters = new DrawParameters();

    /**
     * DataSource is a list that stores the data for drawing..
     */
    public List<DataSource> DataSource = new ArrayList<DataSource>();

    /**
     * Instantiates a new Base chart.
     *
     * @param context the context
     */
    public BaseChart(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base chart.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Base chart.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseChart(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * init method serves for initialization of all values ​​that are needed for drawing a graph.
     */
    public void init() {
        paint = new Paint();

        width = getWidth();
        height = getHeight();
        padding = 50;

        isInit = true;

        DataSource temp = new DataSource("prvi", 15.5f, Color.RED);
        this.DataSource.add(temp);

        temp = new DataSource("drugi", 20.5f, Color.BLUE);
        this.DataSource.add(temp);

        temp = new DataSource("treci", 3.5f);
        this.DataSource.add(temp);

        temp = new DataSource("cetvrti", 10.0f);
        this.DataSource.add(temp);

        temp = new DataSource("peti", 16f);
        this.DataSource.add(temp);
    }

}
