package application2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.proj.Features;
import com.proj.ThinImage;

import application.ANN;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestingLetter {
	Scene scene;
	ImageView im;
	Button b;
	HBox hBox_outter;
	ScrollPane scrollPane;
	HBox hbox;

	public TestingLetter() throws IOException {
		AnchorPane ap = new AnchorPane();
		ap.setMaxHeight(AnchorPane.USE_PREF_SIZE);
		ap.setMaxWidth(AnchorPane.USE_PREF_SIZE);
		ap.setMinHeight(AnchorPane.USE_PREF_SIZE);
		ap.setMinWidth(AnchorPane.USE_PREF_SIZE);
		ap.setPrefHeight(400.0);
		ap.setPrefWidth(600.0);
		DecimalFormat dbf = new DecimalFormat("##.0%");
		ArrayList<Integer> in = new ArrayList<>();
		in.add(1);
		ANN ann1 = new ANN(in, 1, 1, 1, 1);
		ann1.loadNetwork(new File("ANN-diag100.txt"));

		ANN ann2 = new ANN(in, 1, 1, 1, 1);
		ann2.loadNetwork(new File("ANN-diag200++.txt"));
		ANN ann3 = new ANN(in, 1, 1, 1, 1);
		ann3.loadNetwork(new File("ANN - zon.txt"));
		ANN ann4 = new ANN(in, 1, 1, 1, 1);
		ann4.loadNetwork(new File("ANN-zon200.txt"));
		ANN ann5 = new ANN(in, 1, 1, 1, 1);
		ann5.loadNetwork(new File("ANN - hist.txt"));

		ImageView im1 = new ImageView(new Image(getClass().getResourceAsStream("draw.png")));
		im1.setFitHeight(25);
		im1.setFitWidth(30);
		ImageView im12 = new ImageView(new Image(getClass().getResourceAsStream("draw1.png")));
		im12.setFitHeight(25);
		im12.setFitWidth(30);
		b = new Button("Draw", im1);
		String button = ".hover {-fx-graphic: url(\"draw1.png\");} ";
		b.setStyle(button);
		b.setLayoutX(0);
		b.setLayoutY(350);
		b.setPrefHeight(50);
		b.setPrefWidth(300);

		ImageView im2 = new ImageView(new Image(getClass().getResourceAsStream("image.png")));
		im2.setFitHeight(25);
		;
		im2.setFitWidth(30);
		ImageView im22 = new ImageView(new Image(getClass().getResourceAsStream("image1.png")));
		im22.setFitHeight(25);
		;
		im22.setFitWidth(30);
		Button b2 = new Button("Import", im2);
		b2.setLayoutX(300);
		b2.setLayoutY(350);
		b2.setPrefHeight(50);
		b2.setPrefWidth(300);

		b.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				b.setGraphic(im12);
			}
		});

		b.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				b.setGraphic(im1);
			}
		});

		b2.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				b2.setGraphic(im22);
			}
		});

		b2.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				b2.setGraphic(im2);
			}
		});

		im = new ImageView();
		im.setLayoutX(50);
		im.setLayoutY(50);
		im.setFitHeight(230);
		im.setFitWidth(230);

		ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(1000), im);

		scaleTransition1.setFromX(0.2);
		scaleTransition1.setFromY(0.2);
		scaleTransition1.setToX(1);
		scaleTransition1.setToY(1);
		scaleTransition1.setCycleCount(1);
		scaleTransition1.setAutoReverse(true);
		HBox hBox_inner = new HBox();

		// Outter border
		HBox hBox_outter = new HBox();
		hBox_outter.setVisible(false);
		String style_outter = "-fx-border-color: CORNFLOWERBLUE;" + "-fx-border-width: 2;";
		hBox_outter.setStyle(style_outter);

		hBox_inner.getChildren().add(im);
		hBox_outter.getChildren().add(hBox_inner);
		hBox_outter.setLayoutX(50);
		hBox_outter.setLayoutY(30);
		hBox_outter.setPrefHeight(230);
		hBox_outter.setPrefWidth(230);

		Text t = new Text();
		FileChooser fileChooser = new FileChooser();
		b2.setOnAction(e -> {
			reset(hbox);
			File file = fileChooser.showOpenDialog(scene.getWindow());
			if (file != null) {
				Image image = new Image(file.toURI().toString());
				im.setImage(image);
				hBox_outter.setVisible(true);
				scrollPane.setVisible(true);

				scaleTransition1.play();
				// ScaleTransition scaleTransition = new
				// ScaleTransition(Duration.millis(3),im);
				// scaleTransition.setToX(2f);
				// scaleTransition.setToY(2f);
				// scaleTransition.setCycleCount(2);
				// scaleTransition.setAutoReverse(true);
				// scaleTransition.play();

				try {
					double[] zone = Features.getZoning(file);
					double[][] diag = Features.getDiagonalFeature(file.toString()); // resized
																					// to
																					// 90
																					// by
																					// 60

					ArrayList<ArrayList<Integer>> hist = Features.ProjectionHistogram(file.toString()); // resized
																										// 32
																										// by
																										// 32

					ArrayList<Double> in1 = new ArrayList<>();
					ArrayList<Double> in2 = new ArrayList<>();
					ArrayList<Double> in3 = new ArrayList<>();

					for (double d : zone)
						in1.add(d);
					for (double[] dd : diag) {
						for (double d : dd) {
							in2.add(d);
						}
					}
					for (ArrayList<Integer> dd : hist) {
						for (int d : dd) {
							in3.add(d * 1.0);
						}
					}

					ArrayList<Double> out1 = ann1.getOutput(in2); // DIAG
					ArrayList<Double> out2 = ann2.getOutput(in2);
					ArrayList<Double> out3 = ann3.getOutput(in1);
					ArrayList<Double> out4 = ann4.getOutput(in1);
					ArrayList<Double> out5 = ann5.getOutput(in3);
					String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

					String[] s1 = getResult(out1);
					String[] s2 = getResult(out2);
					String[] s3 = getResult(out3);
					String[] s4 = getResult(out4);
					String[] s5 = getResult(out5);

					String k = "";
					k += "\n\nDiagonal1: ";
					for (String i : s1)
						k += i + " : " + 100 / s1.length + "%  ";
					if (s1.length == 0)
						k += "??";
					k += "\nDiagonal2: ";
					for (String i : s2)
						k += i + " : " + 100 / s2.length + "%  ";
					if (s2.length == 0)
						k += "??";
					k += "\nZoning1: ";
					for (String i : s3)
						k += i + " : " + 100 / s3.length + "%  ";
					if (s3.length == 0)
						k += "??";
					k += "\nZoning2: ";
					for (String i : s4)
						k += i + " : " + 100 / s4.length + "%  ";
					if (s4.length == 0)
						k += "??";
					k += "\nHistogram: ";
					for (String i : s5)
						k += i + " : " + 100 / s5.length + "%  ";
					if (s5.length == 0)
						k += "??";

					double[] r = getFinalResult(out1, out2, out3, out4, out5);
					k += "\n \n Final Result: \n";

					getBlue(r);

					double s = 0;
					for (double tr : r)
						System.out.println(tr);
					int[] tt = getRank(r);
					for (double tr : r)
						System.out.println(tr);
					for (int l : tt) {
						s += r[l];
					}
					for (int l : tt) {
						if (r[l] != 0) {
							k += "\t" + str.charAt(l) + " : " + dbf.format(r[l] / s) + "\n";
						}
					}
					/*
					 * System.out.println(s); if((int)((1-s)*100)!=0) {
					 * k+="\t? : "+dbf.format(1-s); }
					 */
					t.setText(k);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		b.setOnAction(e -> {
			// File file = fileChooser.showOpenDialog(stage);
			// if (file != null) {
			reset(hbox);
			Stage sta = new Stage();
			Paint p = new Paint(sta);
			Scene ps = p.getScene();

			sta.setScene(ps);
			sta.initOwner(scene.getWindow());
			sta.initModality(Modality.APPLICATION_MODAL);
			sta.showAndWait();
			File file = new File("Img.png");
			if (file.exists()) {
				try {
					BufferedImage bif = ImageIO.read(file);
					ThinImage.cropPicture(bif, "Img.png");

					Image image = new Image(file.toURI().toString());
					im.setImage(image);
					hBox_outter.setVisible(true);
					scrollPane.setVisible(true);

					scaleTransition1.play();
					double[] zone = Features.getZoning(file);
					double[][] diag = Features.getDiagonalFeature(file.toString()); // resized
																					// to
																					// 90
																					// by
																					// 60
					ArrayList<ArrayList<Integer>> hist = Features.ProjectionHistogram(file.toString()); // resized
																										// 32
																										// by
																										// 32

					ArrayList<Double> in1 = new ArrayList<>();
					ArrayList<Double> in2 = new ArrayList<>();
					ArrayList<Double> in3 = new ArrayList<>();

					for (double d : zone)
						in1.add(d);
					for (double[] dd : diag) {
						for (double d : dd) {
							in2.add(d);
						}
					}
					for (ArrayList<Integer> dd : hist) {
						for (int d : dd) {
							in3.add(d * 1.0);
						}
					}

					ArrayList<Double> out1 = ann1.getOutput(in2); // DIAG
					ArrayList<Double> out2 = ann2.getOutput(in2);
					ArrayList<Double> out3 = ann3.getOutput(in1);
					ArrayList<Double> out4 = ann4.getOutput(in1);
					ArrayList<Double> out5 = ann5.getOutput(in3);
					String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

					String[] s1 = getResult(out1);
					String[] s2 = getResult(out2);
					String[] s3 = getResult(out3);
					String[] s4 = getResult(out4);
					String[] s5 = getResult(out5);

					String k = "";
					k += "\n\nDiagonal1: ";
					for (String i : s1)
						k += i + " : " + 100 / s1.length + "%  ";
					if (s1.length == 0)
						k += "??";
					k += "\nDiagonal2: ";
					for (String i : s2)
						k += i + " : " + 100 / s2.length + "%  ";
					if (s2.length == 0)
						k += "??";
					k += "\nZoning1: ";
					for (String i : s3)
						k += i + " : " + 100 / s3.length + "%  ";
					if (s3.length == 0)
						k += "??";
					k += "\nZoning2: ";
					for (String i : s4)
						k += i + " : " + 100 / s4.length + "%  ";
					if (s4.length == 0)
						k += "??";
					k += "\nHistogram: ";
					for (String i : s5)
						k += i + " : " + 100 / s5.length + "%  ";
					if (s5.length == 0)
						k += "??";

					double[] r = getFinalResult(out1, out2, out3, out4, out5);
					
					getBlue(r);//for GUI
					
					
					k += "\n \n Final Result: \n";
					double s = 0;
					for (double tr : r)
						System.out.println(tr);
					int[] tt = getRank(r);
					for (double tr : r)
						System.out.println(tr);
					for (int l : tt) {
						s += r[l];
					}
					for (int l : tt) {
						if (r[l] != 0) {
							k += "\t" + str.charAt(l) + " : " + dbf.format(r[l] / s) + "\n";
						}
					}
					/*
					 * System.out.println(s); if((int)((1-s)*100)!=0) {
					 * k+="\t? : "+dbf.format(1-s); }
					 */
					t.setText(k);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// file.delete();
			}
			// }
		});

		scrollPane = new ScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setLayoutX(350);
		scrollPane.setLayoutY(30.0);
		scrollPane.setPrefHeight(230);
		scrollPane.setPrefWidth(230.0);
		scrollPane.setContent(t);
		scrollPane.setStyle(style_outter);

		hbox = new HBox(6);
		String s = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
		String[] arr = s.split(" ");
		for (int i = 0; i < arr.length; i++) {
			Text t1 = new Text(arr[i]);
			t1.setStyle(" -fx-stroke: white;");
			t1.setScaleX(1.4);
			t1.setScaleY(1.4);

			hbox.getChildren().add(t1);
		}
		hbox.setLayoutX(115);
		hbox.setLayoutY(300);

		ap.getChildren().add(hbox);
		ap.getChildren().add(hBox_outter);
		ap.getChildren().add(b);
		ap.getChildren().add(b2);
		ap.getChildren().add(scrollPane);
		scene = new Scene(ap);

	}

	public static int[] getRank(double[] a) {
		double[] c = new double[a.length];
		int f = 0;
		for (double t : a) {
			c[f] = t;
			f++;
		}
		int[] k = new int[a.length];
		int s = 0;
		for (int i = 0; i < a.length; i++) {
			int j = getMax(c);
			k[i] = j;
			c[j] = Integer.MIN_VALUE;
		}

		return k;
	}

	public static int getMax(double[] a) {
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > a[max])
				max = i;
		}
		return max;
	}

	public String[] getResult(ArrayList<Double> out) {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		ArrayList<String> letters = new ArrayList<>();
		for (int i = 0; i < out.size(); i++) {
			if (out.get(i) == 1) {
				letters.add(s.charAt(i) + "");
			}
		}
		int i = 0;
		String[] toR = new String[letters.size()];
		for (String ss : letters) {
			toR[i] = ss;
			i++;
		}
		return toR;
	}

	public Scene getScene() {
		return scene;
	}

	public static double[] getFinalResult(ArrayList<Double> a1, ArrayList<Double> a2, ArrayList<Double> a3,
			ArrayList<Double> a4, ArrayList<Double> a5) {
		double[] w1 = getWeights(a1);
		double[] w2 = getWeights(a2);
		double[] w3 = getWeights(a3);
		double[] w4 = getWeights(a4);
		double[] w5 = getWeights(a5);
		double[] agg = new double[w1.length];

		for (int i = 0; i < agg.length; i++) {
			agg[i] = (w1[i] + w2[i] + w3[i] + w4[i] + w5[i]) / 5;
		}

		return agg;

	}

	public static double[] getWeights(ArrayList<Double> out) {
		// out contains 1 where there is a letter
		int cnt = 0;
		for (double i : out)
			cnt += i;
		// counted the number of 1s
		double[] weights = new double[out.size()];
		if (cnt != 0)
			for (int i = 0; i < weights.length; i++)
				weights[i] = out.get(i) / cnt;

		return weights;
	}

	public void reset(HBox h) {
		int i = 0;
		for (Node t : h.getChildren()) {
			h.getChildren().get(i++).setStyle("-fx-stroke:WHITE;");
		}
	}

	public void getBlue(double[] arr) {
		double max = 0;
		for (double r : arr)
			if (r > max)
				max = r;
		System.out.println("MAXIMUM: " + max);
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == max){
				hbox.getChildren().get(i).setStyle("-fx-stroke:CORNFLOWERBLUE;");
				
			}
	}

}