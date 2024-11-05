package com.sofe4790u.assignment2;

import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class WhiteboardClient extends JFrame {
    private Whiteboard whiteboard;
    private int thickness = 2;
    private Color currentColor = Color.BLACK;
    private List<Point> currentStrokePoints = new ArrayList<>();

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
                    for (WhiteboardAction action : actions) {
                        g.setColor(new Color(action.colorRGB));
                        ((Graphics2D) g).setStroke(new BasicStroke(action.thickness));
                        List<Point> points = action.points;
                        for (int i = 1; i < points.size(); i++) {
                            Point p1 = points.get(i - 1);
                            Point p2 = points.get(i);
                            g.drawLine(p1.x, p1.y, p2.x, p2.y);
                        }
                    }
                    // Draw the current stroke being drawn
                    if (currentStrokePoints != null && currentStrokePoints.size() > 1) {
                        g.setColor(currentColor);
                        ((Graphics2D) g).setStroke(new BasicStroke(thickness));
                        for (int i = 1; i < currentStrokePoints.size(); i++) {
                            Point p1 = currentStrokePoints.get(i - 1);
                            Point p2 = currentStrokePoints.get(i);
                            g.drawLine(p1.x, p1.y, p2.x, p2.y);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                currentStrokePoints = new ArrayList<>();
                currentStrokePoints.add(e.getPoint());
            }

            public void mouseReleased(MouseEvent e) {
                try {
                    if (currentStrokePoints.size() > 1) {
                        whiteboard.addStroke(currentStrokePoints, currentColor, thickness);
                    }
                    currentStrokePoints.clear();
                    canvas.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentStrokePoints.add(e.getPoint());
                canvas.repaint();
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

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            try {
                whiteboard.undoLastAction();
                canvas.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        String[] colorNames = {"Black", "Purple", "Grey"};
        Color[] colors = {Color.BLACK, new Color(128, 0, 128), Color.GRAY};
        JComboBox<String> colorSelector = new JComboBox<>(colorNames);
        colorSelector.addActionListener(e -> {
            int index = colorSelector.getSelectedIndex();
            currentColor = colors[index];
        });

        JPanel controls = new JPanel();
        controls.add(clearButton);
        controls.add(undoButton);
        controls.add(new JLabel("Color:"));
        controls.add(colorSelector);

        add(canvas, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WhiteboardClient().setVisible(true);
        });
    }
}
