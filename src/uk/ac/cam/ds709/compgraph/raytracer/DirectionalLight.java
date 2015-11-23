package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public class DirectionalLight extends Light {

	private Vector direction;

	public DirectionalLight(float intensity, Color color, Vector direction) {
		super(intensity, color);
		this.direction = direction;
	}


	public Vector getDirection() {
		return direction.times(-1).normalise();
	}
}
