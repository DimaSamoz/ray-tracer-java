package uk.ac.cam.ds709.compgraph.raytracer;

import java.util.ArrayList;

public class Plane extends Primitive {

	private Vector3D normal;
	private Vector3D fixPoint;

	public Plane(Material material, Vector3D normal, Vector3D fixPoint) {
		super(material);
		this.normal = normal;
		this.fixPoint = fixPoint;
		toggleShadows();
	}

	@Override
	public boolean isSurfacePoint(Vector3D point) {
		return point.minus(fixPoint).dot(normal) == 0;
	}

	@Override
	public java.util.List<Vector3D> intersectionPoints(Ray ray) {
		java.util.List<Vector3D> intersectionPoints = new ArrayList<>();
		float NdotD = normal.dot(ray.direction());
		if (NdotD == 0) return intersectionPoints;

		float s = (normal.dot(fixPoint) - normal.dot(ray.origin())) / NdotD;

		if (s > 0) intersectionPoints.add(ray.origin().plus(ray.direction().times(s)));
		return intersectionPoints;
	}

	@Override
	public Vector3D getNormal(Vector3D surfacePoint) {
		return normal.normalise();
	}
}
