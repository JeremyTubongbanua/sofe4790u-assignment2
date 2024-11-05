package com.sofe4790u.assignment2;

import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.*;

public class WhiteboardClient extends JFrame {
    private Whiteboard whiteboard;
    private int thickness = 2;
    private int lastX, lastY;

    public WhiteboardClient() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            whiteboard = (Whiteboard) registry.lookup("Whiteboard");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        setTitle("Distributed Whiteboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel canvas = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    List<WhiteboardAction> actions = whiteboard.getCurrentCanvas();
                    g.setColor(Color.BLACK);
                    for (WhiteboardAction action : actions) {
                        ((Graphics2D) g).setStroke(new BasicStroke(action.thickness));
                        g.drawLine(action.x1, action.y1, action.x2, action.y2);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                try {
                    whiteboard.drawLine(lastX, lastY, e.getX(), e.getY(), Color.BLACK, thickness);
                    lastX = e.getX();
                    lastY = e.getY();
                    canvas.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    canvas.repaint();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            try {
                whiteboard.clearCanvas();
                canvas.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JPanel controls = new JPanel();
        controls.add(clearButton);

        add(canvas, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WhiteboardClient().setVisible(true);
        });
    }
}
