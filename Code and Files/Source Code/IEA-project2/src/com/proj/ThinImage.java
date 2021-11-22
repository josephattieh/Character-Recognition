package com.proj;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import Skeleton.OtsuBinarize;
import Skeleton.ZongSyn;

public class ThinImage {

	public static void main(String [] args) throws IOException {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	String s ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    	BufferedImage im = ImageIO.read(new File("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\Data\\2.jpg"));
        int h = im.getHeight()/26;
        int w = im.getWidth()/16;
        for(int i=0; i< h*26;i+=h) {
        	for(int j=0; j<w*16;j+=w)
        	{
        		if(i+h<im.getHeight() && j+w<im.getWidth())
        		{    Mat outputImage = new Mat(BufferedImage2Mat(im), new Rect( j,i, w,h) );
        	    Imgcodecs.imwrite("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\Data\\Set2\\"+s.charAt(i/h)+"_"+j+".png", outputImage);
        		}
        	}
        }

    	
	}
	public static BufferedImage addWhite(BufferedImage dest) {
	BufferedImage dest2 = new BufferedImage(dest.getWidth()+2, dest.getHeight()+2, dest.getType());
		
		for(int i=0; i<dest2.getWidth();i++) {
			for(int j=0; j<dest2.getHeight();j++) {
				if(i ==0 || j==0 || i== (dest2.getWidth()-1) || j== (dest2.getHeight()-1)) {
					
					dest2.setRGB(i, j, Color.WHITE.getRGB());
					
				} else
					dest2.setRGB(i, j, dest.getRGB(i-1, j-1));
			}
		}
		return dest2;
	}
	public static BufferedImage resize(BufferedImage bf ,int width, int height ) throws IOException {
		Mat src = ThinImage.BufferedImage2Mat(bf);
		Size size = new Size(width,height);//the dst image size,e.g.100x100
		Mat dst= new Mat();
		Imgproc.resize(src,dst,size);//resize image
		return Mat2BufferedImage(dst);
	}
	public static int[][] getBinaryImage(Mat src) throws IOException{
		 BufferedImage binarizedImage = OtsuBinarize.binarize(Mat2BufferedImage(src));
         WritableRaster binarizedData = binarizedImage.getRaster();
         int[][] matrix = new int[binarizedData.getWidth()][binarizedData.getHeight()];
         for (int y = 0; y < binarizedData.getHeight(); y++) {
             for (int x = 0; x < binarizedData.getWidth(); x++) {
                 int[] a = new int[3];
                 a = binarizedData.getPixel(x, y, a);
                 //255 - white, 0 - black
                 if (a[0] == 255) {
                     matrix[x][y] = 0;
                 } else {
                     matrix[x][y] = 1;
                 }
             }
         }
         int[][] matrx = new int[binarizedData.getHeight()][binarizedData.getWidth()];

         for(int i=0; i<matrix.length;i++) {
        	 for(int j=0; j<matrix[i].length;j++) {
        		 matrx[j][i]= matrix[matrix.length-i-1][j];
        	 }
         }
         
        return matrx;
         

	}
	public static Mat BufferedImage2Mat(BufferedImage image) throws IOException {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    ImageIO.write(image, "jpg", byteArrayOutputStream);
	    byteArrayOutputStream.flush();
	    return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
	}
	public static BufferedImage Mat2BufferedImage(Mat matrix)throws IOException {
	    MatOfByte mob=new MatOfByte();
	    Imgcodecs.imencode(".jpg", matrix, mob);
	    return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
	}
	
	public static BufferedImage getContour(String s) throws IOException {
	    
		Mat bm = Imgcodecs.imread(s);
		Imgproc.blur(bm,bm,new Size(3,3));
		Imgproc.cvtColor(bm,bm,Imgproc.COLOR_BGR2GRAY);
		Imgproc.Canny( bm, bm, 10, 100, 3 );
		Core.bitwise_not( bm, bm );
	 Imgcodecs.imwrite("77.jpg", bm);
	 return Mat2BufferedImage(bm);

	}
public static BufferedImage getContour(Mat bm) throws IOException {
	    	Imgproc.blur(bm,bm,new Size(3,3));
		Imgproc.cvtColor(bm,bm,Imgproc.COLOR_BGR2GRAY);
		Imgproc.Canny( bm, bm, 10, 100, 3 );
		Core.bitwise_not( bm, bm );
	 return Mat2BufferedImage(bm);

	}
	public static BufferedImage getSkeleton(String s) throws IOException {
		
            BufferedImage binarizedImage = OtsuBinarize.binarize(Mat2BufferedImage(Imgcodecs.imread(s)));
            WritableRaster binarizedData = binarizedImage.getRaster();
            int[][] matrix = new int[binarizedData.getHeight()][binarizedData.getWidth()];
            for (int y = 0; y < binarizedData.getHeight(); y++) {
                for (int x = 0; x < binarizedData.getWidth(); x++) {
                    int[] a = new int[3];
                    a = binarizedData.getPixel(x, y, a);
                    //255 - white, 0 - black
                    if (a[0] == 255) {
                        matrix[y][x] = 0;
                    } else {
                        matrix[y][x] = 1;
                    }
                }
            }
            binarizedData = ZongSyn.skeletonization(matrix, binarizedData);
            ImageIcon i =new ImageIcon(binarizedImage);
            Image image = i.getImage();
            BufferedImage buffered = (BufferedImage) image;
            
            
       	 Imgcodecs.imwrite("99.jpg", BufferedImage2Mat(buffered));
return buffered;
            	
	}
	public static BufferedImage getSkeleton(Mat  m) throws IOException {
		
        BufferedImage binarizedImage = OtsuBinarize.binarize(Mat2BufferedImage(m));
        WritableRaster binarizedData = binarizedImage.getRaster();
        int[][] matrix = new int[binarizedData.getHeight()][binarizedData.getWidth()];
        for (int y = 0; y < binarizedData.getHeight(); y++) {
            for (int x = 0; x < binarizedData.getWidth(); x++) {
                int[] a = new int[3];
                a = binarizedData.getPixel(x, y, a);
                //255 - white, 0 - black
                if (a[0] == 255) {
                    matrix[y][x] = 0;
                } else {
                    matrix[y][x] = 1;
                }
            }
        }
        binarizedData = ZongSyn.skeletonization(matrix, binarizedData);
        ImageIcon i =new ImageIcon(binarizedImage);
        Image image = i.getImage();
        BufferedImage buffered = (BufferedImage) image;
        
        
   	 Imgcodecs.imwrite("99.jpg", BufferedImage2Mat(buffered));
return buffered;
        	
}
	public static BufferedImage getSkeleton2(Mat d) throws IOException {
		  BufferedImage binarizedImage = OtsuBinarize.binarize(Mat2BufferedImage(d));
        WritableRaster binarizedData = binarizedImage.getRaster();
        int[][] matrix = new int[binarizedData.getHeight()][binarizedData.getWidth()];
        for (int y = 0; y < binarizedData.getHeight(); y++) {
            for (int x = 0; x < binarizedData.getWidth(); x++) {
                int[] a = new int[3];
                a = binarizedData.getPixel(x, y, a);
                //255 - white, 0 - black
                if (a[0] == 255) {
                    matrix[y][x] = 0;
                } else {
                    matrix[y][x] = 1;
                }
            }
        }
        binarizedData = ZongSyn.corruptSkeletonization(matrix, binarizedData);
        ImageIcon i =new ImageIcon(binarizedImage);
        Image image = i.getImage();
        BufferedImage buffered = (BufferedImage) image;
   	 Imgcodecs.imwrite("100.jpg", BufferedImage2Mat(buffered));
   	 return buffered;
   	 
	}
	public static BufferedImage getSkeleton2(String s) throws IOException {
		  BufferedImage binarizedImage = OtsuBinarize.binarize(Mat2BufferedImage(Imgcodecs.imread(s)));
          WritableRaster binarizedData = binarizedImage.getRaster();
          int[][] matrix = new int[binarizedData.getHeight()][binarizedData.getWidth()];
          for (int y = 0; y < binarizedData.getHeight(); y++) {
              for (int x = 0; x < binarizedData.getWidth(); x++) {
                  int[] a = new int[3];
                  a = binarizedData.getPixel(x, y, a);
                  //255 - white, 0 - black
                  if (a[0] == 255) {
                      matrix[y][x] = 0;
                  } else {
                      matrix[y][x] = 1;
                  }
              }
          }
          binarizedData = ZongSyn.corruptSkeletonization(matrix, binarizedData);
          ImageIcon i =new ImageIcon(binarizedImage);
          Image image = i.getImage();
          BufferedImage buffered = (BufferedImage) image;
     	 Imgcodecs.imwrite("100.jpg", BufferedImage2Mat(buffered));
     	 return buffered;
     	 
	}
	
public static BufferedImage cropPicture(BufferedImage io, String s) throws IOException {
		
		int xStart = 0, yStart = 0, xEnd = io.getWidth()-1, yEnd = io.getHeight()-1;
		int width = io.getWidth();
		int height = io.getHeight();
		boolean t = true;
		for(int i=0; i< height &&t;i++)
			for(int j=0; j<width&&t;j++)
				if(io.getRGB(j, i)==Color.BLACK.getRGB()) {
					yStart =i;
					t=false;
					break;
				}
		t=true;
			for(int j=0; j<width&&t;j++)
				for(int i=0; i< height &&t;i++)
					if(io.getRGB(j, i)==Color.BLACK.getRGB()) {
					xStart =j;
					t=false;
					break;
				}

			t=true;
			for(int i=height-1; i>=0 &&t;i--)
				for(int j=0; j<width&&t;j++)
					if(io.getRGB(j, i)==Color.BLACK.getRGB()) {
						yEnd =i;
						t=false;
						break;
					}
			t=true;
				for(int j=width-1; j>=0&&t;j--)
					for(int i=0; i<height&&t;i++)
						if(io.getRGB(j, i)==Color.BLACK.getRGB()) {
						xEnd =j;
						t=false;
						break;
					}
			
				
		File save = new File(s );
		
		BufferedImage dest = io.getSubimage(xStart, yStart, (xEnd - xStart+1), (yEnd - yStart+1));
		BufferedImage dest2 = new BufferedImage(dest.getWidth()+2, dest.getHeight()+2, dest.getType());
		
		for(int i=0; i<dest2.getWidth();i++) {
			for(int j=0; j<dest2.getHeight();j++) {
				if(i ==0 || j==0 || i== (dest2.getWidth()-1) || j== (dest2.getHeight()-1)) {
					
					dest2.setRGB(i, j, Color.WHITE.getRGB());
					
				} else
					dest2.setRGB(i, j, dest.getRGB(i-1, j-1));
			}
		}
		ImageIO.write(dest2, "PNG", save);

		return dest2;
	}
	
}
