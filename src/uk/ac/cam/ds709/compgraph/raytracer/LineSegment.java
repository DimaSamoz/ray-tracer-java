package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public class LineSegment {
	private Vector2D c0;
	private Vector2D c1;
	private Color colour;

	public LineSegment(Vector2D c0, Vector2D c1, Color colour) {
		this.c0 = c0;
		this.c1 = c1;
		this.colour = colour;
	}


	public Vector2D getC0() {
		return c0;
	}

	public Vector2D getC1() {
		return c1;
	}

	public Color getColour() {
		return colour;
	}

	public void bresenhamFirst(Pixel[][] pixels) {
		float x0 = c0.x;
		float y0 = c0.y;
		float x1 = c1.x;
		float y1 = c1.y;
		float m = (y1 - y0) / (x1 - x0);        // slope of line
		int x = Math.round(x0);                    // integer x coordinate
		float yInc = y0 + m * (x - x0);            // increment of y coordinate
		int y = Math.round(yInc);                // integer y coordinate
		float yFrac = yInc - y;                    // fractional part of y coordinate

		while (x <= Math.round(x1)) {
			pixels[x][y].setColour(colour);

			x++;
			yFrac = yFrac + m;
			if (yFrac < -0.5) {
				y--;
				yFrac++;
			}
		}
	}

	public void bresenhamEighth(Pixel[][] pixels) {
		float x0 = c0.x;
		float y0 = c0.y;
		float x1 = c1.x;
		float y1 = c1.y;
		float m = (y1 - y0) / (x1 - x0);    // slope of line
		int x = Math.round(x0);                // integer x coordinate
		float yInt = y0 + m * (x - x0);        // integer part of the y coordinate
		int y = Math.round(yInt);            // integer y coordinate
		float yFrac = yInt - y;                // fractional part of y coordinate

		while (x <= Math.round(x1)) {
			pixels[x][y].setColour(colour);

			x++;
			yFrac = yFrac + m;
			if (yFrac > 0.5) {
				y++;
				yFrac--;
			}
		}

	}

	public void bresenhamSecond(Pixel[][] pixels) {
		float x0 = c0.x;
		float y0 = c0.y;
		float x1 = c1.x;
		float y1 = c1.y;
		float m = -(x1 - x0) / (y1 - y0);        // slope of line
		int y = Math.round(y0);                    // integer x coordinate
		float xInc = x0 + m * (y - y0);            // increment of y coordinate
		int x = Math.round(xInc);                // integer y coordinate
		float xFrac = xInc - x;                    // fractional part of y coordinate

		while (Math.round(y1) <= y) {
			pixels[x][y].setColour(colour);

			y--;
			xFrac = xFrac + m;
			if (xFrac > 0.5) {
				x++;
				xFrac--;
			}
		}
	}

	public void bresenhamThird(Pixel[][] pixels) {
		float x0 = c0.x;
		float y0 = c0.y;
		float x1 = c1.x;
		float y1 = c1.y;
		float m = -(x1 - x0) / (y1 - y0);        // slope of line
		int y = Math.round(y0);                    // integer x coordinate
		float xInc = x0 + m * (y - y0);            // increment of y coordinate
		int x = Math.round(xInc);                // integer y coordinate
		float xFrac = xInc - x;                    // fractional part of y coordinate

		while (Math.round(y1) <= y) {
			pixels[x][y].setColour(colour);

			y--;
			xFrac = xFrac + m;
			if (xFrac <= -0.5) {
				x--;
				xFrac++;
			}
		}
	}
}
