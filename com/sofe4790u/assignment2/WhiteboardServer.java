package com.sofe4790u.assignment2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class WhiteboardServer {
    public static void main(String[] args) {
        try {
            WhiteboardImpl server = new WhiteboardImpl();
            Whiteboard stub = (Whiteboard) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Whiteboard", stub);
            System.out.println("Whiteboard server ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
