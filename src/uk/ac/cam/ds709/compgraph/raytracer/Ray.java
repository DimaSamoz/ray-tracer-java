package uk.ac.cam.ds709.compgraph.raytracer;

public class Ray {

	private Vector3D origin;
	private Vector3D direction;

	public Ray(Vector3D origin, Vector3D direction) {
		this.origin = origin;
		this.direction = direction;
	}

	public Vector3D direction() {
		return direction.normalise();
	}

	public Vector3D origin() {
		return origin;
	}
}
