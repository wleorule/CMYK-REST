package hr.foi.air.cmykui.graph;

import android.graphics.Color;

/**
 * The type Draw parameters.
 */
public class DrawParameters {

    /**
     * backgroundColor is an atribute over which it is determined background color of a graph.
     */
    public int backgroundColor = Color.BLACK;
    /**
     *
     * lineColor is a atribute over which the color of the axis of the graph is determined.
     */
    public int lineColor = Color.WHITE;
    /**
     * fontColor is a atribute over which the color of the font on the graph is determined.
     */
    public int fontColor = Color.WHITE;
    /**
     * fontSize is a atribute over which the font size on the graph is determined
     */
    public int fontSize = 40;

    /**
     * Instantiates a new Draw parameters.
     */
    public DrawParameters(){}

    /**
     * Instantiates a new Draw parameters.
     *
     * @param bColor the b color
     * @param lColor the l color
     * @param fColor the f color
     * @param fSize  the f size
     */
    public DrawParameters(int bColor, int lColor, int fColor, int fSize){
        this.backgroundColor = bColor;
        this.lineColor = lColor;
        this.fontColor = fColor;
        this.fontSize = fSize;
    }

    /**
     * Sets background color.
     *
     * @param backgroundColor the background color
     */
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Sets font color.
     *
     * @param fontColor the font color
     */
    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

    /**
     * Sets line color.
     *
     * @param lineColor the line color
     */
    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Sets font size.
     *
     * @param fontSize the font size
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
