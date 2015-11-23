package uk.ac.cam.ds709.compgraph.raytracer;

public interface Solid {

	boolean isSurfacePoint(Vector3D point);

	java.util.List<Vector3D> intersectionPoints(Ray ray);

	Vector3D getNormal(Vector3D surfacePoint);

	Material getMaterial();

	boolean isReflective();

	void toggleVisibility();

	void toggleShadows();

	void toggleReflections();

	boolean isSeenByCamera();

	boolean isSeenByShadows();

	boolean isSeenByReflections();


}
