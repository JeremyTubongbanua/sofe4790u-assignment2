package com.sofe4790u.assignment2;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Whiteboard extends Remote {
    void drawLine(int x1, int y1, int x2, int y2, Color color, int thickness) throws RemoteException;
    void clearCanvas() throws RemoteException;
    List<WhiteboardAction> getCurrentCanvas() throws RemoteException;
}
