package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public class BezierCurve {
	// Control points
	public Vector2D p0, p1, p2, p3;
	private Color colour;

	public BezierCurve(Vector2D p0, Vector2D p1, Vector2D p2, Vector2D p3, Color colour) {
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.colour = colour;
	}

	public Color getColour() {
		return colour;
	}

	/**
	 * Checks if the distance of the control point and line between the endpoints
	 * is smaller than the tolerance
	 * @param tolerance the minimum distance over which the curve cannot be considered flat
	 * @return true if the curve can be well approximated by a straight line, false otherwise
	 */
	public boolean isFlat(float tolerance) {

		float s0 = new Vector2D(p0, p3).dot(new Vector2D(p0, p1)) /
				(new Vector2D(p0, p3).length() * new Vector2D(p0, p3).length());
		float s1 = new Vector2D(p0, p3).dot(new Vector2D(p0, p2)) /
					(new Vector2D(p0, p3).length() * new Vector2D(p0, p3).length());

		Vector2D ps0 = p0.times(1 - s0).plus(p3.times(s0));
		Vector2D ps1 = p0.times(1 - s1).plus(p3.times(s1));

		return ps0.minus(p1).length() < tolerance && ps1.minus(p2).length() < tolerance;
	}

	/**
	 * Splits the Bezier curve in half, creating two new curves with control points that
	 * can be computed from the interpolation points
	 * @return an array containing the left and right Bezier curve
	 */
	public BezierCurve[] subdivide() {
		BezierCurve[] halves = new BezierCurve[2];
		halves[0] = new BezierCurve(
				p0,
				p0.times(0.5f).plus(p1.times(0.5f)),
				p0.times(0.25f).plus(p1.times(0.5f)).plus(p2.times(0.25f)),
				p0.times(0.125f).plus(p1.times(0.375f)).plus(p2.times(0.375f)).plus(p3.times(0.125f)),
				colour);
		halves[1] = new BezierCurve(
				p0.times(0.125f).plus(p1.times(0.375f)).plus(p2.times(0.375f)).plus(p3.times(0.125f)),
				p1.times(0.25f).plus(p2.times(0.5f)).plus(p3.times(0.25f)),
				p2.times(0.5f).plus(p3.times(0.5f)),
				p3,
				colour);

		return halves;
	}
}
