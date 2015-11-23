package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public interface Display {

	void setPixel(int x, int y, Pixel pixel);

	void setPixelColor(int x, int y, Color color);

	Pixel getPixel(int x, int y);

	Color getPixelColor(int x, int y);
	
	int getWidth();

	int getHeight();

	void draw(Graphics g, int width, int height);
	
}
