package uk.ac.cam.ds709.compgraph.raytracer;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    private int zoom = 1; //Number of pixels used to represent a cell
    private int width = 1; //Width of game board in pixels
    private int height = 1;//Height of game board in pixels
    private Display current = null;

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

	@Override
    protected void paintComponent(Graphics g) {
        if (current == null) return;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        current.draw(g, width, height);

    }

    private void computeSize() {
        if (current == null) return;
        int newWidth = current.getWidth() * zoom;
        int newHeight = current.getHeight() * zoom;
        if (newWidth != width || newHeight != height) {
            width = newWidth;
            height = newHeight;
            revalidate(); //trigger the DisplayPanel to re-layout its components
        }
    }

    public void display(Display w) {
        current = w;
        computeSize();
        repaint();
    }
}
