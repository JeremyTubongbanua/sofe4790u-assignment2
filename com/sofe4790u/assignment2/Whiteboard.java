package com.sofe4790u.assignment2;

import java.awt.Color;
import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Whiteboard extends Remote {
    void addStroke(List<Point> points, Color color, int thickness) throws RemoteException;
    void clearCanvas() throws RemoteException;
    List<WhiteboardAction> getCurrentCanvas() throws RemoteException;
    void undoLastAction() throws RemoteException;
    int getActionsCount() throws RemoteException;
}
