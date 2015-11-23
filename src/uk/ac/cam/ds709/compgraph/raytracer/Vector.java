package uk.ac.cam.ds709.compgraph.raytracer;

/**
 * A class representing a vector in a 3D Euclidean space
 */
public class Vector {

	// No real need to make these private, since they can take any value
	public float x, y, z;

	// Constructors
	public Vector() {
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(Vector v) {
		this.x = v.x;
		this.y = v.y;
		this.x = v.z;
	}

	// Methods

	/**
	 * Calculates the length of the vector
	 * @return the length of this
	 */
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Normalises the vector
	 * @return vector with the same direction as this, but length 1
	 */
	public Vector normalise() {
		float l = length();
		if (l != 0 && l != 1) return this.times(1/l);
		else return this;
	}

	/**
	 * Vector addition
	 * @param v the vector to be added to this
	 * @return the sum of this and v
	 */
	public Vector plus(Vector v) {
		return new Vector(x + v.x, y + v.y, z + v.z);
	}

	/**
	 * Vector subtraction
	 * @param v the vector to be subtracted from this
	 * @return the difference of this and v
	 */
	public Vector minus(Vector v) {
		return new Vector(x - v.x, y - v.y, z - v.z);

	}

	/**
	 * Scalar multiplication
	 * @param l the scalar that multiplies the vector
	 * @return the scalar product of this and l
	 */
	public Vector times(float l) {
		return new Vector(x * l, y * l, z * l);

	}

	/**
	 * Dot product
	 * @param v the vector to be dotted with this
	 * @return the dot product of this and v
	 */
	public float dot(Vector v) {
		return x * v.x + y * v.y + z * v.z;
	}

	/**
	 * Cross product
	 * @param v the vector to be crossed with this
	 * @return the cross product of this and v
	 */
	public Vector cross(Vector v) {
		return new Vector(y * v.z, z * v.x, x * v.y);
	}

}
