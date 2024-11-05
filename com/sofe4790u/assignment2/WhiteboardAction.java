package com.sofe4790u.assignment2;

import java.io.Serializable;

public class WhiteboardAction implements Serializable {
    private static final long serialVersionUID = 1L;
    public enum ActionType { DRAW_LINE }

    public ActionType type;
    public int x1, y1, x2, y2, thickness;

    public WhiteboardAction(int x1, int y1, int x2, int y2, int thickness) {
        this.type = ActionType.DRAW_LINE;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.thickness = thickness;
    }
}
