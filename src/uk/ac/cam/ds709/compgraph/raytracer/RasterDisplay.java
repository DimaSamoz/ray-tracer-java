package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

/**
 * Class representing a display that can show 2D raster images
 */
public class RasterDisplay extends Display {

	private Canvas canvas;
	private Pixel[][] pixels;

	protected RasterDisplay(int width, int height) {
		super(width, height);
		pixels = new Pixel[width][height];
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// Initialise the pixels in the x-y plane
				pixels[x][y] = new Pixel(Color.WHITE);
			}
		}
	}

	@Override
	public Pixel[][] renderPixels() {

		canvas = setupCanvas();
		return pixels;

	}

	public Canvas setupCanvas() {
		Canvas newCanvas = new Canvas(pixels);

//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(200, 0), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(200, 100), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(100, 200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(0, 200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(-100, 200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(-200, 100), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(-200, 0), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(-200, -100), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(-100, -200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(0, -200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(100, -200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 0), new Vector2D(200, -100), Color.BLACK));
//
//		newCanvas.addLine(new LineSegment(new Vector2D(200, 0), new Vector2D(200, 100), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(200, 100), new Vector2D(100, 200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(100, 200), new Vector2D(0, 200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, 200), new Vector2D(-100, 200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(-100, 200), new Vector2D(-200, 100), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(-200, 100), new Vector2D(-200, 0), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(-200, 0), new Vector2D(-200, -100), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(-200, -100), new Vector2D(-100, -200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(-100, -200), new Vector2D(0, -200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(0, -200), new Vector2D(100, -200), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(100, -200), new Vector2D(200, -100), Color.BLACK));
//		newCanvas.addLine(new LineSegment(new Vector2D(200, -100), new Vector2D(200, 0), Color.BLACK));



		newCanvas.addCurve(new BezierCurve(new Vector2D(0, 20), new Vector2D(100, 200), new Vector2D(300, -50), new Vector2D(500, 20), Color.BLUE));
		newCanvas.addCurve(new BezierCurve(new Vector2D(18, 101), new Vector2D(181, -60), new Vector2D(163, 195), new Vector2D(-20, 0), Color.RED));
		newCanvas.addCurve(new BezierCurve(new Vector2D(-50, 20), new Vector2D(0, 10), new Vector2D(300, -50), new Vector2D(100, 200), Color.GREEN));
		newCanvas.addCurve(new BezierCurve(new Vector2D(-167, 216), new Vector2D(-166, -125), new Vector2D(-53, 438), new Vector2D(-50, -62), Color.PINK));
		newCanvas.addCurve(new BezierCurve(new Vector2D(-470, 174), new Vector2D(68, -185), new Vector2D(-60, -305), new Vector2D(-412, -270), Color.ORANGE));

		return newCanvas;
	}


}
