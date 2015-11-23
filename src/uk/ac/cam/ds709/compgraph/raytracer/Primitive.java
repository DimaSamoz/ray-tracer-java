package uk.ac.cam.ds709.compgraph.raytracer;

/**
 * Abstract class representing a primitive solid - one that is defined by a mathematical equation
 */
public abstract class Primitive implements Solid {
	private Material material;

	// Rendering flags - all initialised to true
	private boolean seenByCamera = true;		// The object is visible on the screen
	private boolean seenByShadows = true;		// The object casts shadows
	private boolean seenByReflections = true;	// The object is reflected in other objects

	public Primitive(Material material) {
		this.material = material;
	}

	@Override
	public Material getMaterial() {
		return material;
	}

	@Override
	public boolean isReflective() {
		return material.getReflectanceK() != 0.0f;
	}

	@Override
	public void toggleVisibility() { seenByCamera = !seenByCamera; }

	@Override
	public void toggleShadows() { seenByShadows = !seenByShadows; }

	@Override
	public void toggleReflections() { seenByReflections = !seenByReflections; }

	@Override
	public boolean isSeenByCamera() {
		return seenByCamera;
	}

	@Override
	public boolean isSeenByShadows() {
		return seenByShadows;
	}

	@Override
	public boolean isSeenByReflections() {
		return seenByReflections;
	}
}
