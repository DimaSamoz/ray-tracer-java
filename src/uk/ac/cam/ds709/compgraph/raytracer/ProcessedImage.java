package uk.ac.cam.ds709.compgraph.raytracer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProcessedImage {

	private Pixel[][] pixels;
	private BufferedImage image;

	public ProcessedImage(Pixel[][] pixels) {
		this.pixels = pixels;

		try {
			image = ImageIO.read(new File("image.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayImage() {
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				try {
					pixels[i][j] = new Pixel(new Color(image.getRGB(i, j)));
				} catch (Exception e) {
					continue;
				}
			}
		}
	}

	public void fastBlur(int radius) {
		BufferedImage blurred = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		int[][] filterValues = new int[radius * 2 + 1][radius * 2 + 1];
		for (int i = 0; i < filterValues.length; i++) {
			for (int j = 0; j < filterValues[0].length; j++) {
				filterValues[i][j] = 1;
			}
		}

		Filter filter = new Filter(filterValues);

		image = applyBlurFilter(filter);
	}

	public void detectHorizontals() {

		int[][] filterValues = { {1, 1, 1}, {0, 0, 0}, {-1, -1, 0} };

		Filter filter = new Filter(filterValues);

		image = applyDetectionFilter(filter);
	}

	public void detectVerticals() {

		int[][] filterValues = {{1, 0, -1}, {1, 0, -1}, {1, 0, -1}};

		Filter filter = new Filter(filterValues);

		image = applyDetectionFilter(filter);
	}


	private BufferedImage applyDetectionFilter(Filter filter) {
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		int radius = filter.getRadius();
		// Loop through all pixels in image
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {

				float aggRed = 0;
				float aggGreen = 0;
				float aggBlue = 0;

				// Loop through the pixels under the filter
				for (int filtI = i - radius; filtI <= i + radius; filtI++) {
					for (int filtJ = j - radius; filtJ <= j + radius; filtJ++) {
						aggRed += getRed(image, filtI, filtJ)
								* filter.getValue(filtI - (i - radius), filtJ - (j - radius));
						aggGreen += getGreen(image, filtI, filtJ)
								* filter.getValue(filtI - (i - radius), filtJ - (j - radius));
						aggBlue += getBlue(image, filtI, filtJ)
								* filter.getValue(filtI - (i - radius), filtJ - (j - radius));

					}
				}

				int filterSize = 1;

//				int avgRed = aggRed < 0 ? 0 : aggRed == 0 ? 0 : 1;
//				int avgGreen = (int) (aggGreen / filterSize);
//				int avgBlue = (int) (aggBlue / filterSize);

				int avgRed = aggRed < -20 ? 0 : aggRed < 20 ? 127 : 255;
				int avgGreen = aggGreen < -20 ? 0 : aggGreen < 20 ? 127 : 255;
				int avgBlue = aggBlue < -20 ? 0 : aggBlue < 20 ? 127 : 255;


				int avgColour = new Color(avgRed, avgGreen, avgBlue).getRGB();


				result.setRGB(i, j, avgColour);

			}
		}
		return result;
	}

	private BufferedImage applyBlurFilter(Filter filter) {
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		int radius = filter.getRadius();
		// Loop through all pixels in image
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {

				float aggRed = 0;
				float aggGreen = 0;
				float aggBlue = 0;

				// Loop through the pixels under the filter
				for (int filtI = i - radius; filtI <= i + radius; filtI++) {
					for (int filtJ = j - radius; filtJ <= j + radius; filtJ++) {
						aggRed += getRed(image, filtI, filtJ)
								* filter.getValue(filtI - (i - radius), filtJ - (j - radius));
						aggGreen += getGreen(image, filtI, filtJ)
								* filter.getValue(filtI - (i - radius), filtJ - (j - radius));
						aggBlue += getBlue(image, filtI, filtJ)
								* filter.getValue(filtI - (i - radius), filtJ - (j - radius));

					}
				}

				int filterSize = 1;

				int avgRed = (int) (aggRed / filterSize);
				int avgGreen = (int) (aggGreen / filterSize);
				int avgBlue = (int) (aggBlue / filterSize);



				int avgColour = new Color(avgRed, avgGreen, avgBlue).getRGB();


				result.setRGB(i, j, avgColour);

			}
		}
		return result;
	}

	private int getRed(BufferedImage img, int i, int j) {
		int red = 0;
		try {
			red = (new Color(img.getRGB(i, j))).getRed();
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		return red;
	}

	private int getGreen(BufferedImage img, int i, int j) {
		int green = 0;
		try {
			green = (new Color(img.getRGB(i, j))).getGreen();
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		return green;

	}

	private int getBlue(BufferedImage img, int i, int j) {
		int blue = 0;
		try {
			blue = (new Color(img.getRGB(i, j))).getBlue();
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		return blue;
	}
}
