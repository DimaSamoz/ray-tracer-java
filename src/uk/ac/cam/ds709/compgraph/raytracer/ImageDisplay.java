package uk.ac.cam.ds709.compgraph.raytracer;

import java.awt.*;

public class ImageDisplay extends Display {

	private ProcessedImage image;
	private Pixel[][] pixels;

	protected ImageDisplay(int width, int height) {
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

		image = setupImage();
		image.displayImage();
		return pixels;
	}

	private ProcessedImage setupImage() {
		ProcessedImage newImage = new ProcessedImage(pixels);

//		newImage.fastBlur(1);

		newImage.detectHorizontals();
//		newImage.detectVerticals();

		return newImage;
	}



}
