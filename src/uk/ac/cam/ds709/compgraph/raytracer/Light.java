package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public abstract class Light {
	private float intensity;
	private Color color;

	public Light(float intensity, Color color) {
		this.intensity = intensity;
		this.color = color;
	}

	public float getIntensity() {
		return intensity;
	}

	public Color getColor() {
		return color;
	}
}
