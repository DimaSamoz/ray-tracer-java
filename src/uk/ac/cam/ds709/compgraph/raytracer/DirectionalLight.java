package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public class DirectionalLight extends Light {

	private Vector3D direction;

	public DirectionalLight(float intensity, Color color, Vector3D direction) {
		super(intensity, color);
		this.direction = direction;
	}


	public Vector3D getDirection() {
		return direction.times(-1).normalise();
	}
}
