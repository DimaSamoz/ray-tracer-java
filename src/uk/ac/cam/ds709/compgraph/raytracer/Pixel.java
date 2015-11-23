package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

/**
 * Representation of the screen pixels
 */
public class Pixel {
	private Color color;
	private Vector location;

	public Pixel(Color color, Vector location) {
		this.color = color;
		this.location = location;
	}

	public Color getColor() {
		return color;
	}

	public void setColour(Color color) {
		this.color = color;
	}

	public Vector getLocation() {
		return location;
	}
}
