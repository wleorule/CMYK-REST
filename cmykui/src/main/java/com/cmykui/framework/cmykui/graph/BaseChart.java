package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.cmykui.framework.cmykui.base.DataSource;

import java.util.ArrayList;
import java.util.List;

public class BaseChart extends View {

    public Paint paint;
    public int width, height, padding;
    public boolean isInit = false;
    public Rect rect = new Rect();

    public DrawParameters parameters = new DrawParameters();

    public List<DataSource> DataSource = new ArrayList<DataSource>();

    public BaseChart(Context context) {
        super(context);
    }

    public BaseChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseChart(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

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
