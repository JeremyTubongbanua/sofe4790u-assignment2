package com.sofe4790u.assignment2;

import java.awt.Color;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class WhiteboardServer implements Whiteboard {
    private List<WhiteboardAction> actions = new ArrayList<>();

    public void drawLine(int x1, int y1, int x2, int y2, Color color, int thickness) {
        actions.add(new WhiteboardAction(x1, y1, x2, y2, thickness));
    }

    public void clearCanvas() {
        actions.clear();
    }

    public List<WhiteboardAction> getCurrentCanvas() {
        return actions;
    }

    public static void main(String[] args) {
        try {
            WhiteboardServer server = new WhiteboardServer();
            Whiteboard stub = (Whiteboard) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Whiteboard", stub);
            System.out.println("Whiteboard server ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
