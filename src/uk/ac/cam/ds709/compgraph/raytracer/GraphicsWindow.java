package uk.ac.cam.ds709.compgraph.raytracer;

import javax.swing.*;
import java.awt.*;

public class GraphicsWindow extends JFrame {

    // Fields
    private DisplayPanel gamePanel;
    private Display display;

    // Constructor
    public GraphicsWindow() {
        super("GraphicsWindow");
        setSize(1200, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());
        JComponent gamePanel = createDisplayPanel();
        add(gamePanel, BorderLayout.CENTER);
    }

    private JComponent createDisplayPanel() {
        JPanel holder = new JPanel();
        DisplayPanel result = new DisplayPanel();
        gamePanel = result;
        holder.add(result);
        return new JScrollPane(holder);
    }

    private void resetDisplay() {
        display = new PixelDisplay(1120,640);
        gamePanel.display(display);
        repaint();
    }

    public static void main(String[] args) {
        GraphicsWindow gui = new GraphicsWindow();
        gui.resetDisplay();
        gui.setVisible(true);

    }

}