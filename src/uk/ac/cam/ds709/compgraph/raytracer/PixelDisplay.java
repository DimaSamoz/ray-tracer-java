package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public class PixelDisplay implements Display {

    private int width;
    private int height;
	private Pixel[][] pixels;
	private Scene scene;


    protected PixelDisplay(int width, int height) {
        this.width = width;
        this.height = height;
		pixels = new Pixel[width][height];
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// Initialise the pixels in the x-y plane
				pixels[x][y] = new Pixel(
						Color.DARK_GRAY,
						new Vector(x - width/2, -(y - height/2), 500));
			}
		}

		scene = setupScene();
		scene.renderScene();

    }

	@Override
    public int getWidth() {
        return this.width;
    }

	@Override
    public int getHeight() {
        return this.height;
    }

	@Override
    public void draw(Graphics g, int width, int height) {
        int worldWidth = getWidth();
        int worldHeight = getHeight();

        double colScale = (double) width / (double) worldWidth;
        double rowScale = (double) height / (double) worldHeight;

        for (int col = 0; col < worldWidth; ++col) {
            for (int row = 0; row < worldHeight; ++row) {
                int colPos = (int) (col * colScale);
                int rowPos = (int) (row * rowScale);
                int nextCol = (int) ((col + 1) * colScale);
                int nextRow = (int) ((row + 1) * rowScale);

                if (g.hitClip(colPos, rowPos, nextCol - colPos, nextRow - rowPos)) {
                    g.setColor(getPixelColor(col, row));
                    g.fillRect(colPos, rowPos, nextCol - colPos, nextRow - rowPos);
                }
            }
        }
    }

	@Override
	public Pixel getPixel(int x, int y) {
		if (y < 0 || y >= getHeight()) return null;
		if (x < 0 || x >= getWidth()) return null;
		return pixels[x][y];
	}
	@Override
	public void setPixel(int x, int y, Pixel pixel) {
		if (y < 0 || y >= getHeight()) return;
		if (x < 0 || x >= getWidth()) return;
		pixels[y][x] = pixel;
	}

	@Override
	public Color getPixelColor(int x, int y) {
		if (y < 0 || y >= getHeight()) return Color.BLACK;
		if (x < 0 || x >= getWidth()) return Color.BLACK;
		return pixels[x][y].getColor();
	}

	@Override
	public void setPixelColor(int x, int y, Color color) {
		if (y < 0 || y >= getHeight()) return;
		if (x < 0 || x >= getWidth()) return;
		pixels[y][x].setColour(color);
	}

	private Scene setupScene() {
		Scene newScene = new Scene(new Vector(0, 0, -500), pixels);

		newScene.addLight(new AmbientLight(1.0f, Color.WHITE));

		newScene.addLight(new DirectionalLight(1.0f, Color.WHITE, new Vector(5, -5, 10)));
//		newScene.addLight(new DirectionalLight(0.8f, Color.WHITE, new Vector(-1, -1, -1)));
//		newScene.addLight(new DirectionalLight(1.0f, Color.WHITE, new Vector(0, 10, 2)));

		Solid floor = new Plane(new Material.Builder(Color.BLUE).reflectance(0.5f).build(),
				new Vector(0, 1, 0), new Vector(0, -50, 0));

		Solid background = new Plane(new Material.Builder(Color.ORANGE).build(),
				new Vector(0, 0, -1), new Vector(0, 0, 200));

		Solid frontwall = new Plane(new Material.Builder(Color.PINK).build(),
				new Vector(0, 0, 1), new Vector(0, 0, -600));
//		frontwall.toggleVisibility();
//		frontwall.toggleReflections();

		Solid leftWall = new Plane(new Material.Builder(Color.CYAN).build(),
				new Vector(1, 0, 0), new Vector(-200, 0, 0));

		Solid rightWall = new Plane(new Material.Builder(Color.LIGHT_GRAY).build(),
				new Vector(-1, 0, 0), new Vector(200, 0, 0));

		Solid ceiling = new Plane(new Material.Builder(Color.MAGENTA).build(),
				new Vector(0, -1, 0), new Vector(0, 200, 0));


		newScene.addSolid(floor);
		newScene.addSolid(background);
		newScene.addSolid(leftWall);
		newScene.addSolid(ceiling);
		newScene.addSolid(frontwall);
		newScene.addSolid(rightWall);
		newScene.addSolid(new Sphere(new Material.Builder(Color.YELLOW).reflectance(1f).build(),
							new Vector(-50, 50, 0), 50));
		newScene.addSolid(new Sphere(new Material.Builder(Color.RED).build(),
							new Vector(100, -10, -10), 40));
		newScene.addSolid(new Sphere(new Material.Builder(Color.GREEN).build(),
							new Vector(-150, 30, -30), 60));

		return newScene;
	}
}

