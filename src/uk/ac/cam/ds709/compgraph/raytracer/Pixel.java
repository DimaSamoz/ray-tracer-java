package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

/**
 * Representation of the screen pixels
 */
public class Pixel {
	private Color colour;
	private Vector3D location;

	/**
	 * Pixel constructor for the raytracer screen pixels
	 * @param colour the colour of the pixel
	 * @param location the location of the pixel in the 3D coordinate system
	 */
	public Pixel(Color colour, Vector3D location) {
		this.colour = colour;
		this.location = location;
	}

	/**
	 * Pixel constructor for the raster screen pixels
	 * @param colour the colour of the pixel
	 */
	public Pixel(Color colour) {
		this.colour = colour;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color color) {
		this.colour = color;
	}

	public Vector3D getLocation() {
		return location;
	}
}
