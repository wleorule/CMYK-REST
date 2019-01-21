package com.cmykui.framework.cmykui.graph;

import android.graphics.Color;

public class DrawParameters {

    public int backgroundColor = Color.BLACK;
    public int lineColor = Color.WHITE;
    public int fontColor = Color.WHITE;
    public int fontSize = 40;

    public DrawParameters(){}

    public DrawParameters(int bColor, int lColor, int fColor, int fSize){
        this.backgroundColor = bColor;
        this.lineColor = lColor;
        this.fontColor = fColor;
        this.fontSize = fSize;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
