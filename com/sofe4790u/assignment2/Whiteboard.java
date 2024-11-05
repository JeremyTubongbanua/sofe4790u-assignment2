/*
 * Author: Jeremy Mark Tubongbanua
 * 100849092
 */

package com.sofe4790u.assignment2;

import java.awt.Color;
import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Whiteboard is the RMI interface for the Whiteboard application.
 */
public interface Whiteboard extends Remote {

    /**
     * Add a stroke to the whiteboard.
     * @param points List of points that make up the stroke
     * @param color Color of the stroke
     * @param thickness Thickness of the stroke
     * @throws RemoteException if an RMI error occurs
     */
    void addStroke(List<Point> points, Color color, int thickness) throws RemoteException;

    /**
     * Clear the whiteboard canvas.
     * @throws RemoteException if an RMI error occurs
     */
    void clearCanvas() throws RemoteException;

    /**
     * Get the current canvas of the whiteboard.
     * @return List of WhiteboardAction objects representing the current canvas
     * @throws RemoteException if an RMI error occurs
     */
    List<WhiteboardAction> getCurrentCanvas() throws RemoteException;

    /**
     * Undo the last action on the whiteboard.
     * @throws RemoteException if an RMI error occurs
     */
    void undoLastAction() throws RemoteException;

    /**
     * Get the number of actions on the whiteboard.
     * @return Number of actions on the whiteboard
     * @throws RemoteException if an RMI error occurs
     */
    int getActionsCount() throws RemoteException;
}
