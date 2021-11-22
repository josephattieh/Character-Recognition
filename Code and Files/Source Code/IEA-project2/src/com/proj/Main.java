package com.proj;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import application.ANN;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

	public void start(Stage stage) throws FileNotFoundException  {
		AnchorPane root = new AnchorPane();
		//	s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		ArrayList<Integer> in = new ArrayList<>();
		in.add(1);
		ANN ann1 = new ANN(in,1,1,1,1 );
		ann1.loadNetwork(new File("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\ANN-diag200++.txt"));

		ANN ann2 = new ANN(in,1,1,1,1 );
		ann2.loadNetwork(new File("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\ANN - zon.txt"));
		ANN ann3 = new ANN(in,1,1,1,1 );
		ann3.loadNetwork(new File("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\ANN - hist.txt"));
		stage.setTitle("ANN");

		Button b = new Button("Draw");
		b.setLayoutX(230);
		b.setLayoutY(300);
		Button b2 = new Button("Upload");
		b2.setLayoutX(300);
		b2.setLayoutY(300);
		ImageView im = new ImageView();
		root.getChildren().add(im);
		im.setLayoutX(100);
		im.setLayoutY(50);
		im.setFitHeight(100);
		root.getChildren().add(b);
		root.getChildren().add(b2);
		Scene s = new Scene(root);
		Text t = new Text();
		root.getChildren().add(t);
		FileChooser fileChooser = new FileChooser();
		b2.setOnAction(e->{
			File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
            	Image image = new Image(file.toURI().toString());
                im.setImage(image);
                try {
					double[] zone =Features.getZoning(file);
					double [][] diag = Features.getDiagonalFeature(file.toString()); // resized to 90 by 60
					ArrayList<ArrayList<Integer>> hist = Features.ProjectionHistogram(file.toString()); // resized 32 by 32

					 ArrayList<Double> in1= new ArrayList<>();
					 ArrayList<Double> in2= new ArrayList<>();
					 ArrayList<Double> in3= new ArrayList<>();

					 for(double d : zone)
						 in1.add(d);
					 for(double[] dd : diag) {
						 for(double d: dd) {
							 in2.add(d);
						 }
					 }
					 for(	ArrayList<Integer> dd : hist) {
						 for(int d: dd) {
							 in3.add(d*1.0);
						 }
					 }
					 
					 ArrayList<Double> out1 = ann1.getOutput(in2);
					 ArrayList<Double> out2= ann2.getOutput(in1);
					ArrayList<Double> out3= ann3.getOutput(in3);
					String str ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
					
					String[] s1 =getResult(out1);
					String[] s2 =getResult(out2);
					String[] s3 =getResult(out3);
              
                String k ="";
                k+="\n\nFirst: \n";
                for(String i: s1)
                	k+=i+ " : "+ 100/s1.length+"%\n";
                if(s1.length==0)
                	k+="Not recognizeable\n";
                k+="Second: \n";
                for(String i: s2)
                	k+=i+ " : "+ 100/s2.length+"%\n";
                if(s2.length==0)
                	k+="Not recognizeable\n";
                k+="Third: \n";
                for(String i: s3)
                	k+=i+ " : "+ 100/s3.length+"%\n";
                if(s3.length==0)
                	k+="Not recognizeable\n";
					t.setText(k);
                } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
                
            }
		});
		
		
		b.setOnAction(e->{
			//File file = fileChooser.showOpenDialog(stage);
			// if (file != null) {
			Stage sta = new Stage();
			Paint p = new Paint(sta);
			Scene ps = p.getScene();

			sta.setScene(ps);
			sta.initOwner(stage);
			sta.initModality(Modality.APPLICATION_MODAL); 
			sta.showAndWait();
			File file = new File("Img.png");
			if(file.exists()) {
			try {
				BufferedImage bif = ImageIO.read(file);
				bif = ThinImage.cropPicture(bif, "Img.png");
		
			Image image = new Image(file.toURI().toString());
			im.setImage(image);
			
			
				
				double[] zone =Features.getZoning(file);
				double [][] diag = Features.getDiagonalFeature(file.toString()); // resized to 90 by 60
				ArrayList<ArrayList<Integer>> hist = Features.ProjectionHistogram(file.toString()); // resized 32 by 32

				ArrayList<Double> in1= new ArrayList<>();
				ArrayList<Double> in2= new ArrayList<>();
				ArrayList<Double> in3= new ArrayList<>();

				for(double d : zone)
					in1.add(d);
				for(double[] dd : diag) {
					for(double d: dd) {
						in2.add(d);
					}
				}
				for(	ArrayList<Integer> dd : hist) {
					for(int d: dd) {
						in3.add(d*1.0);
					}
				}

				ArrayList<Double> out1 = ann1.getOutput(in2);
				ArrayList<Double> out2= ann2.getOutput(in1);
				ArrayList<Double> out3= ann3.getOutput(in3);
				String str ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

				String[] s1 =getResult(out1);
				String[] s2 =getResult(out2);
				String[] s3 =getResult(out3);

				String k ="";
				k+="\n\nFirst: \n";
				for(String i: s1)
					k+=i+ " : "+ 100/s1.length+"%\n";
				if(s1.length==0)
					k+="Not recognizeable\n";
				k+="Second: \n";
				for(String i: s2)
					k+=i+ " : "+ 100/s2.length+"%\n";
				if(s2.length==0)
					k+="Not recognizeable\n";
				k+="Third: \n";
				for(String i: s3)
					k+=i+ " : "+ 100/s3.length+"%\n";
				if(s3.length==0)
					k+="Not recognizeable\n";
				t.setText(k);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			file.delete();
			}
			//    }
		});

		stage.setScene(s);
		stage.show();	
		stage.setResizable(false);

	}


	public String[] getResult(ArrayList<Double> out) {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		ArrayList<String> letters = new ArrayList<>();
		for(int i=0; i<out.size();i++) {
			if(out.get(i)==1) {
				letters.add(s.charAt(i)+"");
			}
		}
		int i=0;
		String [] toR = new String[letters.size()];
		for(String ss: letters) {
			toR[i]=ss;
			i++;
		}
		return toR;
	}


	public static void main(String[] args) {
		launch(args);
	}


	/*  public static void main(String[] args) throws IOException {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    	String s ="OPQRSTUVWXYZ";

    	String p ="C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\d\\";
    	File f = new File(p);
    	for(int j=0; j<f.listFiles().length;j++) {
    		File t = f.listFiles()[j];
        	BufferedImage bif = ImageIO.read(t);
        	int w = bif.getWidth()/21;

    	for(int i=0; i< bif.getWidth();i+=w) {
    			if(i+w>=bif.getWidth()) {

    			}else {
    			BufferedImage im = Crop(bif,i, 0,w, bif.getHeight()-1);


    			Imgcodecs.imwrite("C:\\Users\\Joseph Attieh\\Desktop\\Dataset\\dd\\"+s.charAt(j)+"_"+i+".jpg", ThinImage.BufferedImage2Mat(im));
    			}
    		}
    	}




    }

	private static BufferedImage Crop(BufferedImage bif, int i, int j, int w, int h) {
	BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for(int s=i; s<i+w;s++) {
			for(int t=j; t<j+h;t++) {

					im.setRGB(s%w, t%h,bif.getRGB(s,t));

			}
		}
		return im;
	}*/

}