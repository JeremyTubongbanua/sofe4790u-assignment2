/*
 * Author: Jeremy Mark Tubongbanua
 * 100849092
 */

package com.sofe4790u.assignment2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * WhiteboardServer is the server-side implementation of the Whiteboard RMI interface.
 * 
 * This server exposes port 1099 and binds the Whiteboard RMI interface to the RMI registry.
 * 
 * The server is ready to accept incoming RMI requests from clients.
 * Compatible with multiple Whiteboard Clients
 */
public class WhiteboardServer {
    public static void main(String[] args) {
        try {
            // Create a new WhiteboardImpl object
            WhiteboardImpl server = new WhiteboardImpl();
            Whiteboard stub = (Whiteboard) UnicastRemoteObject.exportObject(server, 0); // create stub
            Registry registry = LocateRegistry.createRegistry(1099); //  start rmi registry on 1099
            registry.bind("Whiteboard", stub); // bind the stub to the registry
            System.out.println("Whiteboard server ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
