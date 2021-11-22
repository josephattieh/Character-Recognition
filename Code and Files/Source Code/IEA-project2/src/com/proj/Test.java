package com.proj;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

public class Test {

	public static void main(String [] args) throws Exception {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		BufferedImage bif = ThinImage.Mat2BufferedImage(Imgcodecs.imread("A_B.png"));
		ArrayList<BufferedImage> im  = new ArrayList<>();

		
		

	}
	public static double[] getList(double[][] f){
		double [] al = new double[f.length*f[0].length];
		int k=0;
		for(int i=0; i<f.length;i++)
			for(int j=0; j<f[0].length;j++)
				{al[k]=f[i][j];
				k++;
				}
				
		return al;
	}
}
