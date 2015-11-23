package uk.ac.cam.ds709.compgraph.raytracer;

public interface Solid {

	boolean isSurfacePoint(Vector point);

	java.util.List<Vector> intersectionPoints(Ray ray);

	Vector getNormal(Vector surfacePoint);

	Material getMaterial();

	boolean isReflective();

	void toggleVisibility();

	void toggleShadows();

	void toggleReflections();

	boolean isSeenByCamera();

	boolean isSeenByShadows();

	boolean isSeenByReflections();


}
