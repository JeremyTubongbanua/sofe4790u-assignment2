package com.sofe4790u.assignment2;

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

    public WhiteboardAction(List<Point> points, java.awt.Color color, int thickness) {
        this.type = ActionType.DRAW_STROKE;
        this.points = new ArrayList<>(points);
        this.colorRGB = color.getRGB();
        this.thickness = thickness;
    }

    public java.awt.Color getColor() {
        return new java.awt.Color(colorRGB);
    }
}
