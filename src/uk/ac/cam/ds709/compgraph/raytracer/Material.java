package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

/**
 * Class representing the material of the solid: its surface colour and the ambient, diffuse and
 * specular coefficients.
 */
public class Material {

	private final Color colour;
	private final float kAmbient;
	private final float kDiffuse;
	private final float kSpecular;
	private final float kMetalness;
	private final float kReflectance;

	/**
	 * Using the Builder pattern to specify an arbitrary number of fields and default the rest
	 */
	public static class Builder {
		// Required parameter
		private final Color colour;

		// Optional parameters initialised to default values
		private float kAmbient = 1.0f;
		private float kDiffuse = 1.0f;
		private float kSpecular = 1.0f;
		private float kMetalness = 0.0f;
		private float kReflectance = 0.0f;

		public Builder(Color colour) {
			this.colour = colour;
		}

		public Builder ambient(float val) {
			kAmbient = val;
			return this;
		}

		public Builder diffuse(float val) {
			kDiffuse = val;
			return this;
		}

		public Builder specular(float val) {
			kSpecular = val;
			return this;
		}

		public Builder metalness(float val) {
			kMetalness = val;
			return this;
		}

		public Builder reflectance(float val) {
			kReflectance = val;
			return this;
		}

		public Material build() {
			return new Material(this);
		}
	}

	/**
	 * Material constructor via the Builder pattern
	 * @param builder the Builder object used to build the material
	 */
	private Material(Builder builder) {
		colour = builder.colour;
		kAmbient = builder.kAmbient;
		kDiffuse = builder.kDiffuse;
		kSpecular = builder.kSpecular;
		kMetalness = builder.kMetalness;
		kReflectance = builder.kReflectance;
	}


	public Color getColour() {
		return colour;
	}

	public float getAmbientK() {
		return kAmbient;
	}

	public float getDiffuseK() {
		return kDiffuse;
	}

	public float getSpecularK() {
		return kSpecular;
	}

	public float getMetalnessK() {
		return kMetalness;
	}

	public float getReflectanceK() {
		return kReflectance;
	}
}
