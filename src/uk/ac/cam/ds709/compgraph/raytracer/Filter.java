package uk.ac.cam.ds709.compgraph.raytracer;

public class Filter {

	private int radius;
	private int[][] values;


	public Filter(int radius) {
		this.radius = radius;
	}

	public Filter(int[][] values) {
		this.values = values;
		this.radius = (values.length - 1)/2;
	}

	private int getSum() {
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[0].length; j++) {
				sum += values[i][j];
			}
		}
		return sum == 0 ? 1 : sum;
	}

	public float getValue(int i,  int j) {
		return (float) values[i][j] / (float) getSum();
	}

	public int getRadius() {
		return radius;
	}
}
