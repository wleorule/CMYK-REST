package com.cmykui.framework.cmykui.graph;

import android.graphics.Color;

public class DrawParameters {

    private final int defaultBackgroundColor = Color.BLACK;
    private final int defaultLineColor = Color.WHITE;
    private final int defaultFontColor = Color.WHITE;
    private final int defaultFontSize = 40;

    public int backgroundColor = defaultBackgroundColor;
    public int lineColor = defaultLineColor;
    public int fontColor = defaultFontColor;
    public int fontSize = defaultFontSize;

    public DrawParameters(){}

    public DrawParameters(int bColor, int lColor, int fColor, int fSize){
        this.backgroundColor = bColor;
        this.lineColor = lColor;
        this.fontColor = fColor;
        this.fontSize = fSize;
    }
}
