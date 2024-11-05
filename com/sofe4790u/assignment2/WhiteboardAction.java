/*
 * Author: Jeremy Mark Tubongbanua
 * 100849092
 */

 package com.sofe4790u.assignment2;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WhiteboardAction implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum ActionType { DRAW_STROKE }

    public ActionType type;
    public List<Point> points;
    public int thickness;
    public int colorRGB;

    /**
     * Create a new WhiteboardAction object representing a stroke.
     * @param points List of points that make up the stroke
     * @param color Color of the stroke
     * @param thickness Thickness of the stroke
     */
    public WhiteboardAction(List<Point> points, Color color, int thickness) {
        this.type = ActionType.DRAW_STROKE;
        this.points = new ArrayList<>(points);
        this.colorRGB = color.getRGB();
        this.thickness = thickness;
    }

    /**
     * Get the color of the stroke.
     * @return Color of the stroke
     */
    public Color getColor() {
        return new Color(colorRGB);
    }
}
