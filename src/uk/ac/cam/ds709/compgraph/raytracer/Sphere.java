package uk.ac.cam.ds709.compgraph.raytracer;

import java.util.ArrayList;
import java.util.List;

public class Sphere extends Primitive {
	// Fields
	private Vector3D centre;
	private float radius;

	public Sphere(Material material, Vector3D centre, float radius) {
		super(material);
		this.centre = centre;
		this.radius = radius;
	}



	@Override
	public boolean isSurfacePoint(Vector3D point) {
		return point.minus(centre).length() == radius;
	}

	@Override
	public java.util.List<Vector3D> intersectionPoints(Ray ray) {
		List<Vector3D> intersectionPoints = new ArrayList<>();
		// a = D.D
		float a = ray.direction().dot(ray.direction());
		// b = 2D.(O-C)
		float b = 2 * ray.direction().dot(ray.origin().minus(centre));
		// c = (O-C).(O-C) - r^2
		float c = ray.origin().minus(centre).dot(ray.origin().minus(centre)) - radius * radius;

		float det = b * b - 4 * a * c;

		if (0 < det) {
			// Positive determinant, two intersection points
			float s1 = (float) ((-b + Math.sqrt(det)) / (2 * a));
			float s2 = (float) ((-b - Math.sqrt(det)) / (2 * a));

			if (s1 > 0) intersectionPoints.add(ray.origin().plus(ray.direction().times(s1)));

			if (s2 > 0) intersectionPoints.add(ray.origin().plus(ray.direction().times(s2)));
			return intersectionPoints;

		} else if (det == 0) {
			// Determinant is 0, the ray is tangent to the sphere
			float s = ((-b) / (2 * a));
			if (s > 0) intersectionPoints.add(ray.origin().plus(ray.direction().times(s)));
			return intersectionPoints;

		} else {
			// Determinant is negative, no intersection points
			return intersectionPoints;
		}

	}

	@Override
	public Vector3D getNormal(Vector3D surfacePoint) {
		return surfacePoint.minus(centre).normalise();
	}

}
