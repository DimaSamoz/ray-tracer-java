package uk.ac.cam.ds709.compgraph.raytracer;

/**
 * Class representing a 2D coordinate used on the 2D canvas
 */
public class Vector2D {
	public final float x;
	public final float y;

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Vector3D from a 2D Cartesian coordinate point
	 *
	 * @param p point
	 */
	public Vector2D(Vector2D p) {
		this.x = p.x;
		this.y = p.y;
	}

	/**
	 * Vector3D from point 1 to point 2
	 *
	 * @param p0 point 1
	 * @param p1 point 2
	 */
	public Vector2D(Vector2D p0, Vector2D p1) {
		Vector2D v0 = new Vector2D(p0);
		Vector2D v1 = new Vector2D(p1);
		Vector2D p0Top1 = v1.minus(v0);
		this.x = p0Top1.x;
		this.y = p0Top1.y;
	}

	/**
	 * Calculates the length of the vector
	 *
	 * @return the length of this
	 */
	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	/**
	 * Normalises the vector
	 *
	 * @return vector with the same direction as this, but length 1
	 */
	public Vector2D normalise() {
		float l = length();
		if (l != 0 && l != 1) return this.times(1 / l);
		else return this;
	}

	/**
	 * Vector addition
	 *
	 * @param v the vector to be added to this
	 * @return the sum of this and v
	 */
	public Vector2D plus(Vector2D v) {
		return new Vector2D(x + v.x, y + v.y);
	}

	/**
	 * Vector subtraction
	 *
	 * @param v the vector to be subtracted from this
	 * @return the difference of this and v
	 */
	public Vector2D minus(Vector2D v) {
		return new Vector2D(x - v.x, y - v.y);

	}

	/**
	 * Scalar multiplication
	 *
	 * @param l the scalar that multiplies the vector
	 * @return the scalar product of this and l
	 */
	public Vector2D times(float l) {
		return new Vector2D(x * l, y * l);

	}

	/**
	 * Dot product
	 *
	 * @param v the vector to be dotted with this
	 * @return the dot product of this and v
	 */
	public float dot(Vector2D v) {
		return x * v.x + y * v.y;
	}

	@Override
	public String toString() {
		return "Vector2D{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
