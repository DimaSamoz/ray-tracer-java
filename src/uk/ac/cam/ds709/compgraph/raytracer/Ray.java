package uk.ac.cam.ds709.compgraph.raytracer;

public class Ray {

	private Vector origin;
	private Vector direction;

	public Ray(Vector origin, Vector direction) {
		this.origin = origin;
		this.direction = direction;
	}

	public Vector direction() {
		return direction.normalise();
	}

	public Vector origin() {
		return origin;
	}
}
