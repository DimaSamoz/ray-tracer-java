package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

/**
 * A class representing a 2D canvas for drawing lines, curves and polygons
 */
public class Canvas {

	Pixel[][] pixels;

	public Canvas(Pixel[][] pixels) {
		this.pixels = pixels;
	}

	public void addLine(LineSegment line) {

		Vector2D c0 = line.getC0();
		Vector2D c1 = line.getC1();

		int octant = computeOctant(c0, c1);

		Vector2D coord0 = transformCoordinate(c0);
		Vector2D coord1 = transformCoordinate(c1);

		LineSegment shiftLine = new LineSegment(coord0, coord1, line.getColour());
		LineSegment revShiftLine = new LineSegment(coord1, coord0, line.getColour());

		switch (octant) {
			case 1: shiftLine.bresenhamFirst(pixels);
				break;
			case 2: shiftLine.bresenhamSecond(pixels);
				break;
			case 3: shiftLine.bresenhamThird(pixels);
				break;
			case 4: revShiftLine.bresenhamEighth(pixels);
				break;
			case 5: revShiftLine.bresenhamFirst(pixels);
				break;
			case 6: revShiftLine.bresenhamSecond(pixels);
				break;
			case 7: revShiftLine.bresenhamThird(pixels);
				break;
			case 8: shiftLine.bresenhamEighth(pixels);
				break;

		}

	}



	/**
	 * Transforms the coordinates from the input metric to the implementation metric
	 *
	 * In our implementation the origin is the top left corner of the picture,
	 * and y increases downwards - the user assumes an Eucledian coordinate system,
	 * with the origin in the centre and y increasing upwards.
	 * @return the coordinates transformed to be compatible with the implementation
	 */
	private Vector2D transformCoordinate(Vector2D original) {
		int halfWidth = pixels.length / 2;
		int halfHeight = pixels[0].length / 2;
		float x = original.x + halfWidth;
		float y = -original.y + halfHeight;

		return new Vector2D(x, y);
	}

	private int computeOctant(Vector2D c0, Vector2D c1) {
		float x0 = c0.x;
		float y0 = c0.y;
		float x1 = c1.x;
		float y1 = c1.y;

		if (0 <= x1 - x0) { // 1, 2, 7, 8
			if (0 <= y1 - y0) { // 1, 2
				if (Math.abs(y1 - y0) <= Math.abs(x1 - x0)) return 1;
				else return 2;
			} else {			// 7, 8
				if (Math.abs(x1 - x0) <= Math.abs(y1 - y0)) return 7;
				else return 8;
			}
		} else {			// 3, 4, 5, 6
			if (0 <= y1 - y0) { // 3, 4
				if (Math.abs(y1 - y0) <= Math.abs(x1 - x0)) return 4;
				else return 3;
			} else {            // 5, 6
				if (Math.abs(x1 - x0) <= Math.abs(y1 - y0)) return 6;
				else return 5;
			}
		}
	}

	/**
	 * Adds a cubic Bézier curve defined by the four control points
	 * @param curve the Bézier curve to be drawn
	 */
	public void addCurve(BezierCurve curve) {
		Vector2D p0 = curve.p0;
		Vector2D p1 = curve.p1;
		Vector2D p2 = curve.p2;
		Vector2D p3 = curve.p3;

		if (curve.isFlat(0.5f)) {
			addLine(new LineSegment(curve.p0, curve.p3, curve.getColour()));
		} else {
			Vector2D temp = new Vector2D(0, 0);
			BezierCurve leftCurve = new BezierCurve(temp, temp, temp, temp, Color.BLACK);
			BezierCurve rightCurve = new BezierCurve(temp, temp, temp, temp, Color.BLACK);
			BezierCurve[] subdividedCurve = curve.subdivide();
			this.addCurve(subdividedCurve[0]);
			this.addCurve(subdividedCurve[1]);

		}

	}

}
