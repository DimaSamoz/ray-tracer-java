package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;
import java.util.List;

/**
 * The class used to calculate the surface shading of a solid.
 */
public class Shading {

	private Material material;
	private Color surfaceColour;
	private float red;
	private float green;
	private float blue;

	// Flags which are set when we add different types of shading to the scene -
	// prevents us from adding two specular or diffuse lightings to the scene
	private boolean ambShadingPresent = false;
	private boolean diffShadingPresent = false;
	private boolean specShadingPresent = false;
	private boolean reflectionsPresent = false;

	// The maximum value is the sum of the maximum ambient intensity, and maximum diffuse and
	// specular intensities for each light in the scene, which are added on the go
	private int maxColourIntensity = 255;


	public Shading(Material material) {
		this.material = material;
		surfaceColour = material.getColour();
		red = 0;
		green = 0;
		blue = 0;
	}

	/**
	 * Add ambient shading to the solid.
	 *
	 * @param ambientLight the ambient light in the scene
	 */
	public void addAmbient(AmbientLight ambientLight) {
		if (ambShadingPresent) return;
		float colourIntensity = ambientLight.getIntensity() * material.getAmbientK();
		red += colourIntensity * surfaceColour.getRed();
		green += colourIntensity * surfaceColour.getGreen();
		blue += colourIntensity * surfaceColour.getBlue();
		ambShadingPresent = true;
	}

	/**
	 * Add diffuse shading to the solid.
	 *
	 * @param dirLights  the directional lights in the scene
	 * @param surfNormal the normal to the surface
	 */
	public void addDiffuse(java.util.List<DirectionalLight> dirLights, Vector3D surfNormal) {
		if (diffShadingPresent) return;
		for (DirectionalLight dirLight :
				dirLights) {
			Vector3D lightNormal = dirLight.getDirection();
			float surfDotLight = surfNormal.dot(lightNormal);
			if (surfDotLight < 0) surfDotLight = 0; // The light doesn't shine on this side of the surface

			float colourIntensity = dirLight.getIntensity() * material.getDiffuseK()
					* surfDotLight;
			red += colourIntensity * surfaceColour.getRed();
			green += colourIntensity * surfaceColour.getGreen();
			blue += colourIntensity * surfaceColour.getBlue();

			maxColourIntensity += 255;
		}
		diffShadingPresent = true;
	}

	/**
	 * Adds shadows to the scene, if it has diffuse lighting. If it doesn't, it first adds diffuse lighting, then
	 * creates the shadows.
	 *
	 * @param dirLights  the list of directional lights used in the scene
	 * @param solids     the list of solids
	 * @param surfPoint  the intersection point
	 * @param surfNormal the normal to the surface
	 */
	public void addShadows(java.util.List<DirectionalLight> dirLights, List<Solid> solids,
						   Vector3D surfPoint, Vector3D surfNormal) {
		if (!diffShadingPresent) addDiffuse(dirLights, surfNormal);
		for (DirectionalLight dirLight : dirLights) {
			Vector3D lightNormal = dirLight.getDirection();

			// Starts the ray a little bit further from the surface – avoids
			// accidental intersections and clears up shadows
			Ray lightRay = new Ray(surfPoint.plus(surfNormal.times(0.1f)), lightNormal);
			boolean occluded = false;
			for (Solid solid :
					solids) {
				if (!solid.isSeenByShadows()) continue;
				if (solid.intersectionPoints(lightRay).size() != 0) {
					occluded = true;
					break;
				}
			}
			if (occluded) {
				float surfDotLight = surfNormal.dot(lightNormal);
				if (surfDotLight < 0) surfDotLight = 0; // The light doesn't shine on this side of the surface

				// Undo the diffuse lighting
				float colourIntensity = dirLight.getIntensity() * material.getDiffuseK()
						* surfDotLight;
				red -= colourIntensity * surfaceColour.getRed();
				green -= colourIntensity * surfaceColour.getGreen();
				blue -= colourIntensity * surfaceColour.getBlue();

			}
		}
	}

	public void addSpecular(List<DirectionalLight> dirLights, Vector3D surfNormal,
							Vector3D viewerNormal, int phongK) {
		if (specShadingPresent) return;

		// The color of the specular highlight depends on the metalness of the material:
		// if metalness = 0, the highlight is pure white, if it's 1, it's the same as the surface colour
		float specularColourRed = material.getMetalnessK() * surfaceColour.getRed()
				+ (1 - material.getMetalnessK()) * Color.WHITE.getRed();
		float specularColourGreen = material.getMetalnessK() * surfaceColour.getGreen()
				+ (1 - material.getMetalnessK()) * Color.WHITE.getGreen();
		float specularColourBlue = material.getMetalnessK() * surfaceColour.getBlue()
				+ (1 - material.getMetalnessK()) * Color.WHITE.getBlue();

		for (DirectionalLight dirLight :
				dirLights) {
			Vector3D lightNormal = dirLight.getDirection().times(-1);
			Vector3D reflectedNormal =
					lightNormal.minus(surfNormal.times(lightNormal.dot(surfNormal)).times(2));

			float reflectDotViewer = reflectedNormal.dot(viewerNormal);
			if (reflectDotViewer < 0) reflectDotViewer = 0; // the viewer wouldn't be able to see specular here


			float colourIntensity = (float) (dirLight.getIntensity() * material.getSpecularK()
					* Math.pow(reflectDotViewer, phongK));
			red += colourIntensity * specularColourRed;
			green += colourIntensity * specularColourGreen;
			blue += colourIntensity * specularColourBlue;


			// Specular usually makes the overall shading darker, so we can increase the maximum
			// intensity by less than 255 - lower value means lighter image, but can cause
			// outofbounds exceptions
			maxColourIntensity += 255;
		}
		specShadingPresent = true;
	}

	public void addReflections(Color reflectedColour, Vector3D viewerNormal, Vector3D surfaceNormal) {

		if (reflectionsPresent) return;

		// Fresnel reflections
		float reflectance = material.getReflectanceK() /** (1 - viewerNormal.dot(surfaceNormal))*/;

		red = reflectance * reflectedColour.getRed()  + (1 - reflectance) * red;
		green = reflectance * reflectedColour.getGreen()  + (1 - reflectance) * green;
		blue = reflectance * reflectedColour.getBlue()  + (1 - reflectance) * blue;

		reflectionsPresent = true;
	}

	public Color getPixelColour() {
		int redChannel = (int) (255 * (red / maxColourIntensity));
		int greenChannel = (int) (255 * (green / maxColourIntensity));
		int blueChannel = (int) (255 * (blue / maxColourIntensity));

		Color pixelColour = new Color(redChannel, greenChannel, blueChannel);


		return pixelColour;
	}
}
