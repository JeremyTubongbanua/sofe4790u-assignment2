/*
 * Author: Jeremy Mark Tubongbanua
 * 100849092
 */

package com.sofe4790u.assignment2;

import java.awt.Color;
import java.awt.Point;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * WhiteboardImpl is the server-side implementation of the Whiteboard RMI interface.
 */
public class WhiteboardImpl implements Whiteboard {
    private List<WhiteboardAction> actions = new ArrayList<>();

    public synchronized void addStroke(List<Point> points, Color color, int thickness) throws RemoteException {
        actions.add(new WhiteboardAction(points, color, thickness));
    }

    public synchronized void clearCanvas() throws RemoteException {
        actions.clear();
    }

    public synchronized List<WhiteboardAction> getCurrentCanvas() throws RemoteException {
        return new ArrayList<>(actions);
    }

    public synchronized void undoLastAction() throws RemoteException {
        if (!actions.isEmpty()) {
            actions.remove(actions.size() - 1);
        }
    }

    public synchronized int getActionsCount() throws RemoteException {
        return actions.size();
    }
}
