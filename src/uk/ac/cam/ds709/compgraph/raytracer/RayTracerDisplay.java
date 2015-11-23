package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

/**
 * Class representing a display that can show raytraced images
 */
public class RayTracerDisplay extends Display {

	private Scene scene;
	private Pixel[][] pixels;

	protected RayTracerDisplay(int width, int height) {
		super(width, height);
		pixels = new Pixel[width][height];
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// Initialise the pixels in the x-y plane
				pixels[x][y] = new Pixel(
						Color.DARK_GRAY, new Vector3D(x - width / 2, -(y - height / 2), 500));
			}
		}


	}

	@Override
	public Pixel[][] renderPixels() {
		scene = setupScene();
		scene.renderScene();
		return pixels;
	}

	private Scene setupScene() {
		Scene newScene = new Scene(new Vector3D(0, 0, -500), pixels);

		newScene.addLight(new AmbientLight(1.0f, Color.WHITE));

		newScene.addLight(new DirectionalLight(1.0f, Color.WHITE, new Vector3D(5, -5, 10)));
		newScene.addLight(new DirectionalLight(0.8f, Color.WHITE, new Vector3D(-1, -1, -1)));
//		newScene.addLight(new DirectionalLight(1.0f, Color.WHITE, new Vector3D(0, 10, 2)));

		Solid floor = new Plane(new Material.Builder(Color.LIGHT_GRAY).reflectance(0.5f).build(),
				new Vector3D(0, 1, 0), new Vector3D(0, -50, 0));

		Solid background = new Plane(new Material.Builder(Color.ORANGE).build(),
				new Vector3D(0, 0, -1), new Vector3D(0, 0, 200));

		Solid frontwall = new Plane(new Material.Builder(Color.PINK).build(),
				new Vector3D(0, 0, 1), new Vector3D(0, 0, -600));
//		frontwall.toggleVisibility();
//		frontwall.toggleReflections();

		Solid leftWall = new Plane(new Material.Builder(Color.CYAN).build(),
				new Vector3D(1, 0, 0), new Vector3D(-200, 0, 0));

		Solid rightWall = new Plane(new Material.Builder(Color.BLUE).build(),
				new Vector3D(-1, 0, 0), new Vector3D(200, 0, 0));

		Solid ceiling = new Plane(new Material.Builder(Color.MAGENTA).build(),
				new Vector3D(0, -1, 0), new Vector3D(0, 200, 0));


		newScene.addSolid(floor);
		newScene.addSolid(background);
		newScene.addSolid(leftWall);
		newScene.addSolid(ceiling);
		newScene.addSolid(frontwall);
		newScene.addSolid(rightWall);
		newScene.addSolid(new Sphere(new Material.Builder(Color.YELLOW).reflectance(0.2f).build(),
				new Vector3D(-50, 50, 0), 50));
		newScene.addSolid(new Sphere(new Material.Builder(Color.RED).build(),
				new Vector3D(100, -10, -10), 40));
		newScene.addSolid(new Sphere(new Material.Builder(Color.GREEN).build(),
				new Vector3D(-150, 30, -30), 60));

		return newScene;
	}
}
