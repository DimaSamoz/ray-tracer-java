/*
package uk.ac.cam.ds709.compgraph.raytracer;

*/
/**
 * Class representing a matrix.
 *//*

public class Matrix {

	// Height and width of the matrix
	int height;
	int width;

	float[][] matrix;

	*/
/**
	 * Initialise an identity matrix of the given height and width
	 * @param height the height of the matrix
	 * @param width the width of the matrix
	 *//*

	public Matrix(int height, int width) {
		this.height = height;
		this.width = width;
		matrix = new float[height][width];

		for (int rowInd = 0, matrixLength = matrix.length; rowInd < matrixLength; rowInd++) {
			for (int colInd = 0, rowLength = matrix[0].length; colInd < rowLength; colInd++) {
				if (rowInd == colInd) matrix[rowInd][colInd] = 1;
				else matrix[rowInd][colInd] = 0;
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getIJ(int i, int j) throws MatrixException {
		if (i <= 0 || height - 1 <= i) throw new MatrixException("Matrix height is only " + height);
		if (j <= 0 || width - 1 <= j) throw new MatrixException("Matrix width is only " + width);
		return matrix[i][j];
	}

	public void setIJ(int i,  int j, float value) throws MatrixException {
		if (i <= 0 || height - 1 <= i) throw new MatrixException("Matrix height is only " + height);
		if (j <= 0 || width - 1 <= j) throw new MatrixException("Matrix width is only " + width);
		matrix[i][j] = value;
	}

	*/
/**
	 * Multiplies the matrix by a scalar
	 * @param l the scalar multiplier
	 * @return the matrix multiplied by l
	 *//*

	public Matrix times(float l) {
		Matrix newMatrix = new Matrix(height, width);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				try {
					float newValue = this.getIJ(i, j) * l;
					newMatrix.setIJ(i, j, newValue);

				} catch (MatrixException e) {
					// Controlled environment, nothing should happen
				}
			}
		}
		return newMatrix;
	}

	*/
/**
	 * Cross product of two matrices.
	 * @param mult The second term of the multiplication
	 * @return the product of this and mult
	 * @throws MatrixException
	 *//*

	public Matrix cross(Matrix mult) throws MatrixException {
		if (this.height != mult.getWidth())
			throw new MatrixException("A.height is not equal to B.width");

		Matrix product = new Matrix(this.getHeight(), mult.getWidth());

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				float sum = 0;
				for (int k = 0; k < width; k++) {
					sum += this.getIJ(i, k) * mult.getIJ(k, j);
				}
				product.setIJ(i, j, sum);
			}
		}

		return product;
	}


	public Matrix transpose() {
		Matrix transposed = new Matrix(width, height);


	}
}
*/
