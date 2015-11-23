package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public abstract class Display {

	private int width;
	private int height;
	private Pixel[][] pixels;


    protected Display(int width, int height) {
        this.width = width;
        this.height = height;
    }

	public abstract Pixel[][] renderPixels();

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void draw(Graphics g, int width, int height) {
		pixels = renderPixels();
        int worldWidth = getWidth();
        int worldHeight = getHeight();

        double colScale = (double) width / (double) worldWidth;
        double rowScale = (double) height / (double) worldHeight;

        for (int col = 0; col < worldWidth; ++col) {
            for (int row = 0; row < worldHeight; ++row) {
                int colPos = (int) (col * colScale);
                int rowPos = (int) (row * rowScale);
                int nextCol = (int) ((col + 1) * colScale);
                int nextRow = (int) ((row + 1) * rowScale);

                if (g.hitClip(colPos, rowPos, nextCol - colPos, nextRow - rowPos)) {
                    g.setColor(getPixelColor(col, row));
                    g.fillRect(colPos, rowPos, nextCol - colPos, nextRow - rowPos);
                }
            }
        }
    }

	public Pixel getPixel(int x, int y) {
		if (y < 0 || y >= getHeight()) return null;
		if (x < 0 || x >= getWidth()) return null;
		return pixels[x][y];
	}

	public void setPixel(int x, int y, Pixel pixel) {
		if (y < 0 || y >= getHeight()) return;
		if (x < 0 || x >= getWidth()) return;
		pixels[y][x] = pixel;
	}

	public Color getPixelColor(int x, int y) {
		if (y < 0 || y >= getHeight()) return Color.BLACK;
		if (x < 0 || x >= getWidth()) return Color.BLACK;
		return pixels[x][y].getColour();
	}

	public void setPixelColor(int x, int y, Color color) {
		if (y < 0 || y >= getHeight()) return;
		if (x < 0 || x >= getWidth()) return;
		pixels[y][x].setColour(color);
	}
}

