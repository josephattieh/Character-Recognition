package com.proj;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;


public class Zoning {
	public static void main(String[] args) throws Exception {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		int scaledWidth = 32;
		int scaledHeight = 32;

		BufferedImage inputImage = ImageIO.read(new File("A.png"));
		BufferedImage cropped=ThinImage.cropPicture(inputImage,"ss");
		cropped = ThinImage.getSkeleton2(ThinImage.BufferedImage2Mat(cropped));

		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, cropped.getType());
		Graphics2D g = outputImage.createGraphics();
		g.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);

		ImageIO.write(outputImage, "png", new File("resized.png"));
		int [][] ma = ThinImage.getBinaryImage(ThinImage.BufferedImage2Mat(outputImage));
		double [] [] mat = new double[ma.length][ma[0].length];
		for(int i =0; i<ma.length;i++)
			for(int j=0; j<ma[i].length;j++)
				mat[i][j] = ma[i][j];
		
		
		// for(int i=0; i<mat.length;i++){
		// for(int j=0; j<mat[0].length;j++)
		// System.out.print((int)mat[i][j]+" ");
		// System.out.println();
		// }

		double[] zoned = zoning(mat, 4); // 4x4 matrix = 16 zones
		for (int i = 0; i < zoned.length; i++)
			if (i == zoned.length - 1)
				System.out.println(zoned[i]);
			else
				System.out.print(zoned[i] + ", ");

	}
	public static double[] getFeature(String s) throws IOException{
		int scaledWidth = 32;
		int scaledHeight = 32;

		BufferedImage inputImage = ImageIO.read(new File(s));
		BufferedImage cropped=ThinImage.cropPicture(inputImage,"ss.png");
		cropped = ThinImage.getSkeleton2(ThinImage.BufferedImage2Mat(cropped));

		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, cropped.getType());
		Graphics2D g = outputImage.createGraphics();
		g.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);

		int [][] ma = ThinImage.getBinaryImage(ThinImage.BufferedImage2Mat(outputImage));
		double [] [] mat = new double[ma.length][ma[0].length];
		for(int i =0; i<ma.length;i++)
			for(int j=0; j<ma[i].length;j++)
				mat[i][j] = ma[i][j];
		

		double[] zoned = zoning(mat, 8); // 4x4 matrix = 16 zones
		return zoned;
	}
	public static double[][] getMatrix(BufferedImage image) {
		// 1 for on pixels(black) and 0 for off pixels(white)
		double[][] mat = new double[image.getHeight()][image.getWidth()];
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[0].length; j++)
				if (image.getRGB(j, i) == Color.BLACK.getRGB())
					mat[i][j] = 1;
				else
					mat[i][j] = 0;
		return mat;

	}

	public static double[] zoning(double[][] mat, int noOfZones) {
		int h = mat.length;
		int w = mat[0].length;
		int zoneSizeH = h / noOfZones;
		int zoneSizeW = w / noOfZones;

		int denom = zoneSizeH * zoneSizeW;
		double up, down, left, right;
		up = down = left = right = 0;
		double[][] zonedMatrix = new double[noOfZones][noOfZones];
		double[] cons = new double[noOfZones * noOfZones / 2];

		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				if (mat[i][j] == 1) {
					zonedMatrix[i / zoneSizeH][j / zoneSizeW]++; // density

					if (i < h / 2)
						up++;
					else
						down++;
					if (j < w / 2)
						left++;
					else
						right++;

				}

		for (int i = 0; i < zonedMatrix.length; i++)
			for (int j = 0; j < zonedMatrix[0].length; j++) {
				zonedMatrix[i][j] = zonedMatrix[i][j] / denom;

			}
		double[] density = linearize(zonedMatrix);
		int counter = 0;
		for (int i = 0; i < density.length; i++)
			if (i % 2 == 0)
				cons[counter++] = density[i] + density[i + 1];
		double diff1 = (up - down);
		double diff2 = (left - right);

		double[] vector = new double[2 + density.length + cons.length];
		counter = 0;
		vector[counter++] = diff1;
		vector[counter++] = diff2;
		for (int i = 0; i < density.length; i++)
			vector[counter++] = density[i];
		for (int i = 0; i < cons.length; i++)
			vector[counter++] = cons[i];
		return vector;
	}

	public static double[] linearize(double[][] matrix) {
		int width = matrix[0].length;
		int heigth = matrix.length;
		int count = 0;
		double[] array = new double[width * heigth];
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				array[count++] = matrix[i][j];
			}
		}
		return array;

	}


	
	
}
