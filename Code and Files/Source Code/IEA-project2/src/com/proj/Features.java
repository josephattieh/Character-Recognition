package com.proj;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Features {
	static int i=0;
	public static void main(String[] args) throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			double[] z =Features.getZoning(new File("100.jpg"));
		System.out.println(z.length);
	}

		/*PrintWriter pw1 = new PrintWriter(new BufferedWriter(new FileWriter(new File("Chain.txt"))));
		PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(new File("Zone.txt"))));
		PrintWriter pw3= new PrintWriter(new BufferedWriter(new FileWriter(new File("Diag.txt"))));
		PrintWriter pw4 = new PrintWriter(new BufferedWriter(new FileWriter(new File("Hist.txt"))));
		PrintWriter pw5 = new PrintWriter(new BufferedWriter(new FileWriter(new File("Skeleton.txt"))));
		PrintWriter pw6= new PrintWriter(new BufferedWriter(new FileWriter(new File("Border.txt"))));



		File f = new File("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\dataset(A-F)");
		  for(File t: f.listFiles()) {
    	BufferedImage b = ThinImage.cropPicture(ImageIO.read(new File(t.toString())),t.toString());

    }

		for(File t: f.listFiles()) {

			char c  =t.getName().charAt(0);
			String s ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String out="";
			int[] g = new int [26];
			for(int i=0; i<26;i++)
				g[i]=0;
			g[s.indexOf(c)]=1;
			for(int ss: g)
				out+=ss+",";
			out = out.substring(0, out.length()-1);

			int[] chain =getChainCode(t.toString());
			double[] zone =getZoning(t);
			double [][] diag = getDiagonalFeature(t.toString()); // resized to 90 by 60
			double sym1 = SymX(t.toString());
			double sym2 = SymY(t.toString());
			ArrayList<ArrayList<Integer>> hist = ProjectionHistogram(t.toString()); // resized 32 by 32
			BufferedImage i = ThinImage.getSkeleton(ThinImage.BufferedImage2Mat(ThinImage.addWhite(ThinImage.resize(ImageIO.read(t), 100, 100))));
			//resized to 102 by 102

			BufferedImage j = ThinImage.getContour(ThinImage.BufferedImage2Mat(ThinImage.addWhite(ThinImage.resize(ImageIO.read(t), 100, 100))));

			System.out.println("Chain "+chain.length);
			System.out.println("Zone "+(zone.length));
			System.out.println("Diag "+(diag.length*diag[0].length));
			System.out.println("Skeleton "+(i.getWidth()*i.getHeight()));
			int cnt =0;
			for(ArrayList<Integer> arr:hist)
				for(int d: arr)
					cnt++;
			System.out.println("Histogram "+cnt);

			pw1.print("(");
			for(int d=0; d<chain.length-1;d++)
				pw1.print(chain[d]+",");
			pw1.println(chain[chain.length-1]+"),("+out+");");

			pw2.print("(");
			for(int d=0; d<zone.length-1;d++)
				pw2.print(zone[d]+",");
			pw2.println(zone[zone.length-1]+"),("+out+");");


			pw4.print("(");
			for(int k=0;k<hist.size();k++)
				for(int o =0; o<hist.get(k).size();o++)
					if(k!=hist.size()-1 || o!=hist.size()-1)
						pw4.print(hist.get(k).get(o)+",");
			pw4.println(hist.get(hist.size()-1).get(hist.get(hist.size()-1).size()-1)+"),("+out+");");

			pw3.print("(");
			for(int k=0; k<diag.length;k++)
				for(int o=0; o<diag[k].length;o++)
					if(k!=diag.length-1 || o!=diag[0].length-1)
						pw3.print(diag[k][o]+",");
			pw3.println(diag[diag.length-1][diag[0].length-1]+"),("+out+");");

			pw5.print("(");
			for(int k =0; k<i.getWidth();k++)
				for(int l =0; l<i.getHeight();l++)
					if(k!=i.getWidth()-1 || l!=i.getHeight()-1)
						pw5.print(i.getRGB(k, l)+",");
			pw5.println(i.getRGB(i.getWidth()-1, i.getHeight()-1)+"),("+out+");");


			pw6.print("(");
			for(int k =0; k<j.getWidth();k++)
				for(int l =0; l<j.getHeight();l++)
					if(k!=j.getWidth()-1 || l!=j.getHeight()-1)
						pw6.print(j.getRGB(k, l)+",");
			pw6.println(j.getRGB(j.getWidth()-1, j.getHeight()-1)+"),("+out+");");

		}



		pw1.close();
		pw2.close();
		pw3.close();
		pw4.close();
		pw5.close();
		pw6.close();


	}
*/

	/*
	 * 
	 * Mat im = ThinImage.BufferedImage2Mat(ThinImage.cropPicture(ImageIO.read(new File("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\dataset(A-F)\\dataset1.png")),"Hi"));

    for(int i=0; i< im.rows();i+=120) {
    	for(int j=0; j<im.cols();j+=120)
    	{
    		if(i+100<im.rows() && j+200<im.cols())
    		{    Mat outputImage = new Mat(im, new Rect( j, i,100, 100) );
    	    Imgcodecs.imwrite("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\dataset(A-F)\\"+i+"_"+j+".png", outputImage);
    		}
    	}
    }

	 */
	

	public static ArrayList<ArrayList<Integer>> ProjectionHistogram(String s) throws IOException {
		  System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

		BufferedImage bf = ThinImage.cropPicture(ImageIO.read(new File(s)), "k");
		bf = ThinImage.addWhite(bf);
		Mat src = ThinImage.BufferedImage2Mat(bf);
		src = ThinImage.BufferedImage2Mat(ThinImage.getSkeleton(src));

		Size size = new Size(32, 32);// the dst image size,e.g.100x100
		Mat dst = new Mat();
		Imgproc.resize(src, dst, size);// resize image
		int[][] matrix = ThinImage.getBinaryImage(dst);

		ArrayList<Integer> h = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			int count = 0;
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1)
					count++;
			}
			h.add(count);
		}
		ArrayList<Integer> v = new ArrayList<>();

		for (int i = 0; i < matrix[0].length; i++) {
			int count = 0;
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[j][i] == 1)
					count++;
			}
			v.add(count);
		}
		ArrayList<Integer> d = new ArrayList<>();
		for (int r = 0; r < matrix.length; r++) {
			int count = 0;
			for (int row = r, col = 0; row >= 0 && col < matrix[0].length; row--, col++) {
				if (matrix[row][col] == 1)
					count++;
			}
			d.add(count);
		}

		for (int c = 1; c < matrix[0].length; c++) {
			int count = 0;
			for (int row = matrix.length - 1, col = c; row >= 0 && col < matrix[0].length; row--, col++) {
				if (matrix[row][col] == 1)
					count++;
			}
			d.add(count);
		}

		ArrayList<ArrayList<Integer>> toreturn = new ArrayList<>();
		toreturn.add(h);
		toreturn.add(v);
		toreturn.add(d);
		return toreturn;
	}

	public static double[][] getDiagonalFeature(String s) throws IOException {
		// crop image and get thinned
		  System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

		BufferedImage bf = ThinImage.cropPicture(ImageIO.read(new File(s)), "k");
		bf = ThinImage.addWhite(bf);
		Mat src = ThinImage.BufferedImage2Mat(bf);
		
		src = ThinImage.BufferedImage2Mat(ThinImage.getSkeleton(src));

		Size size = new Size(60, 90);// the dst image size,e.g.100x100
		Mat dst = new Mat();
		Imgproc.resize(src, dst, size);// resize image
		BufferedImage bif = ThinImage.Mat2BufferedImage(dst);
		ArrayList<Integer> r = new ArrayList<>();
		ArrayList<Integer> c = new ArrayList<>();
		for (int i = 0; i < 6; i++)
			c.add(i * 10);
		for (int i = 0; i < 9; i++)
			r.add(i * 10);

		double[][] zones = new double[6][9];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 9; j++) {
				zones[i][j] = getDiag(getAreaof10(bif, c.get(i), r.get(j)));

			}
		}
		double[][] toReturn = new double[7][10];
		double[] row = averageRow(zones);
		double[] col = averageCol(zones);
		for (int i = 0; i < toReturn.length; i++) {
			for (int j = 0; j < toReturn[0].length; j++) {
				if (i == toReturn.length - 1 && j == toReturn[0].length - 1) {

				} else if (i == toReturn.length - 1) {
					toReturn[i][j] = col[j];
				} else if (j == toReturn[0].length - 1) {
					toReturn[i][j] = row[i];
				} else {
					toReturn[i][j] = zones[i][j];

				}
			}
		}
		return toReturn;

	}

	public static double[][] getDiagonalFeature(Mat src) throws IOException {
		Size size = new Size(60, 90);// the dst image size,e.g.100x100

		Mat dst = new Mat();
		Imgproc.resize(src, dst, size);// resize image
		BufferedImage bif = ThinImage.Mat2BufferedImage(dst);
		ArrayList<Integer> r = new ArrayList<>();
		ArrayList<Integer> c = new ArrayList<>();
		for (int i = 0; i < 6; i++)
			c.add(i * 10);
		for (int i = 0; i < 9; i++)
			r.add(i * 10);

		double[][] zones = new double[6][9];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 9; j++) {
				zones[i][j] = getDiag(getAreaof10(bif, c.get(i), r.get(j)));

			}
		}
		double[][] toReturn = new double[7][10];
		double[] row = averageRow(zones);
		double[] col = averageCol(zones);
		for (int i = 0; i < toReturn.length; i++) {
			for (int j = 0; j < toReturn[0].length; j++) {
				if (i == toReturn.length - 1 && j == toReturn[0].length - 1) {

				} else if (i == toReturn.length - 1) {
					toReturn[i][j] = col[j];
				} else if (j == toReturn[0].length - 1) {
					toReturn[i][j] = row[i];
				} else {
					toReturn[i][j] = zones[i][j];

				}
			}
		}
		return toReturn;

	}

	public static double[] averageRow(double[][] a2) {

		double average[] = new double[a2.length];
		for (int row = 0; row < a2.length; row++) {
			int rowTotal = 0;
			for (int column = 0; column < a2[row].length; column++) {
				rowTotal += a2[row][column];
			}

			average[row] = rowTotal / a2[row].length;
		}
		return average;

	}

	public static double[] averageCol(double[][] a2) {

		double average[] = new double[a2[0].length];
		for (int col = 0; col < a2[0].length; col++) {
			int rowTotal = 0;
			for (int row = 0; row < a2.length; row++) {
				rowTotal += a2[row][col];
			}

			average[col] = rowTotal / a2.length;
		}
		return average;

	}

	public static BufferedImage getAreaof10(BufferedImage bif, int startX, int startY) throws IOException {
		BufferedImage b = new BufferedImage(10, 10, bif.getType());
		for (int i = startX; i < startX + 10; i++) {
			for (int j = startY; j < startY + 10; j++) {
				b.setRGB(i - startX, j - startY, bif.getRGB(i, j));
				;
			}
		}
		return b;
	}

	public static double getDiag(BufferedImage bif) {
		double[] vals = new double[19];
		int rowCount = 9;
		int columnCount = 6;
		int t = 0;
		double s = 0;
		for (int r = 0; r < rowCount; r++) {
			for (int row = r, col = 0; row >= 0 && col < columnCount; row--, col++) {
				vals[t] += bif.getRGB(row, col);
				s++;
			}
			vals[t] /= s;
			t++;
			s = 0;
		}

		for (int c = 1; c < columnCount; c++) {
			for (int row = rowCount - 1, col = c; row >= 0 && col < columnCount; row--, col++) {
				vals[t] += bif.getRGB(row, col);
				s++;
			}
			vals[t] /= s;
			t++;
			s = 0;
		}
		double avg = 0;
		for (int i = 0; i < vals.length; i++)
			avg += vals[i];

		return avg / vals.length;
	}

	public static double SymY(String s) throws IOException {
		BufferedImage bf = ThinImage.cropPicture(ImageIO.read(new File(s)), "k");
		Mat src = ThinImage.BufferedImage2Mat(bf);
		src = ThinImage.BufferedImage2Mat(ThinImage.getSkeleton(src));

		int[][] matrix = ThinImage.getBinaryImage(src);

		int[][] inverse = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				inverse[i][matrix[0].length - j - 1] = matrix[i][j];
			}
		}
		double sum = 0;
		int cnt = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (inverse[i][j] == matrix[i][j])
					cnt++;
				sum++;

			}
		}
		sum = cnt / sum;

		return sum;
	}

	public static double SymX(String s) throws IOException {
		BufferedImage bf = ThinImage.cropPicture(ImageIO.read(new File(s)), "k");
		Mat src = ThinImage.BufferedImage2Mat(bf);
		src = ThinImage.BufferedImage2Mat(ThinImage.getSkeleton(src));
		Imgcodecs.imwrite("TRY.png", src);
		int[][] matrix = ThinImage.getBinaryImage(src);

		int[][] inverse = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				inverse[matrix.length - i - 1][j] = matrix[i][j];
			}
		}
		double sum = 0;
		int cnt = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (inverse[i][j] == matrix[i][j])
					cnt++;
				sum++;

			}
		}
		sum = cnt / sum;

		return sum;
	}

	private static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	public static double[] getZoning(File f) throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		int scaledWidth = 32;
		int scaledHeight = 32;

		BufferedImage inputImage = ImageIO.read(f);
		BufferedImage cropped = ThinImage.cropPicture(inputImage, "ss.png");
		cropped = ThinImage.addWhite(cropped);
		cropped = ThinImage.getSkeleton(ThinImage.BufferedImage2Mat(cropped));
		ImageIO.write(cropped, "PNG", new File("cropped.png"));

		BufferedImage outputImage = resize(cropped,scaledWidth,scaledHeight);


		ImageIO.write(outputImage, "png", new File("resized.png"));

		// double[][] mat = getMatrix(outputImage);
		int[][] mat = ThinImage.getBinaryImage(ThinImage.BufferedImage2Mat(outputImage));
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++)
				System.out.print((int) mat[i][j] + " ");
			System.out.println();
		}

		double[] zoned = zoning(mat, 8); // 4x4 matrix = 16 zones
		for (int i = 0; i < zoned.length; i++)
			if (i == zoned.length - 1)
				System.out.println(zoned[i]);
			else
				System.out.print(zoned[i] + ", ");

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

	public static double[] zoning(int[][] mat, int noOfZones) {
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
		double diff1 = (up - down) / (h * w);
		double diff2 = (left - right) / (h * w);

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
		return array;}
}
