package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Scene {
	private Vector viewpoint;
	AmbientLight ambientLight;
	private List<DirectionalLight> lights;
	private List<Solid> solids;
	private Pixel[][] pixels;

	public Scene(Vector viewpoint, Pixel[][] pixels) {
		this.viewpoint = viewpoint;
		ambientLight = new AmbientLight(0, Color.WHITE);
		lights = new LinkedList<>();
		solids = new LinkedList<>();
		this.pixels = pixels;
	}

	public void addLight(Light light) {
		if (light instanceof AmbientLight) {
			ambientLight = (AmbientLight) light;
		} else {
			lights.add((DirectionalLight) light);
		}
	}

	public void addSolid(Solid solid) {
		solids.add(solid);
	}

	public void renderScene() {
		int width = pixels.length;
		int height = pixels[0].length;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Ray ray = new Ray(viewpoint, pixels[i][j].getLocation().minus(viewpoint));
				Color pixelColour = rayTrace(ray, 4);
				if (pixelColour != null) {
					pixels[i][j].setColour(pixelColour);
				}
			}
		}
	}


	private Color rayTrace(Ray ray, int reflectionDepth) {
		float minDistance = Float.MAX_VALUE;
		Solid closestSolid = null;
		Vector closestPoint = null;
		Vector surfaceNormal = null;

		for (Solid solid : solids) {
			if (!solid.isSeenByCamera()) continue;
			// Add the intersection points of the ray and the solids to a list
			List<Vector> intersections = solid.intersectionPoints(ray);
			if (intersections.size() == 0) continue; // No intersections
			else if (intersections.size() == 1) {
				// One intersection
				float distance = intersections.get(0).minus(viewpoint).length();
				if (distance < minDistance) {
					minDistance = distance;
					closestSolid = solid;
					closestPoint = intersections.get(0);
					surfaceNormal = solid.getNormal(closestPoint);
				}
			} else {
				// Two intersections
				for (Vector intersection : intersections) {
					float distance = intersection.minus(viewpoint).length();
					if (distance < minDistance) {
						minDistance = distance;
						closestSolid = solid;
						closestPoint = intersection;
						surfaceNormal = solid.getNormal(closestPoint);
					}
				}
			}
		}
		if (closestSolid != null) {
			Shading shading = new Shading(closestSolid.getMaterial());
			shading.addAmbient(ambientLight);
			shading.addDiffuse(lights, surfaceNormal);
			shading.addShadows(lights, solids, closestPoint, surfaceNormal);
//			shading.addSpecular(lights, surfaceNormal, ray.direction().times(-1), 20);

			if (closestSolid.isReflective() && reflectionDepth != 0) {
				Vector viewerNormal = ray.direction().times(-1);
				Vector reflectedNormal =
						surfaceNormal.times(surfaceNormal.dot(viewerNormal)).times(2).minus(viewerNormal);

				Ray reflectedRay = new Ray(closestPoint.plus(surfaceNormal.times(0.1f)), reflectedNormal);

				Color reflectionColour = rayTrace(reflectedRay, reflectionDepth - 1);

				if (reflectionColour != null) {
					shading.addReflections(reflectionColour, viewerNormal, surfaceNormal);
				}
			}

			return shading.getPixelColour();
		} else return null;
	}

}
