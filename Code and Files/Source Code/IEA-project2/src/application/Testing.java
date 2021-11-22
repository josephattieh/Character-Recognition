package application;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.proj.Features;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class Testing {
	private Desktop desktop = Desktop.getDesktop();
	protected final ScrollPane scrollPane;
	protected final AnchorPane anchorPane;

	protected final ScrollPane scrollPane0;
	protected AnchorPane anchorPane0;
	protected final Button button;
	protected final Button button0;

	protected final Label label;
	protected final Label label0;
	protected final Label label1;
	
	

	File f;
	Scene scene;

	public Testing(ANN ann, int feature) {
		label = new Label();
		label0 = new Label();
		label1 = new Label();

		scrollPane = new ScrollPane();
		anchorPane = new AnchorPane();
		scrollPane0 = new ScrollPane();
		anchorPane0 = new AnchorPane();
		button = new Button();
		button0 = new Button();
		AnchorPane ap = new AnchorPane();
		

		label.setLayoutX(56.0);
		label.setLayoutY(67.0);
		label.setText("Testing");
		label.setFont(new Font(20.0));

		scrollPane.setLayoutX(56.0);
		scrollPane.setLayoutY(140.0);
		scrollPane.setPrefHeight(200.0);
		scrollPane.setPrefWidth(200.0);

		
		scrollPane.setContent(anchorPane);

		label0.setLayoutX(56.0);
		label0.setLayoutY(119.0);
		label0.setText("Enter the inputs:");

		label1.setLayoutX(350.0);
		label1.setLayoutY(120.0);
		label1.setText("Outputs:");

		scrollPane0.setLayoutX(346.0);
		scrollPane0.setLayoutY(140.0);
		scrollPane0.setPrefHeight(200.0);
		scrollPane0.setPrefWidth(200.0);
		ArrayList<TextField> list = new ArrayList<>();
		for (int j = 0; j < ann.input; j++) {
			Text tx = new Text();
			tx.setText("Input#" + (j + 1));
			tx.setLayoutX(10);
			tx.setLayoutY(30 * j + 10);
			TextField t = new TextField();
			t.setPrefHeight(20.0);
			t.setPrefWidth(70.0);
			t.setLayoutX(60);
			t.setLayoutY(30 * j);

			anchorPane.getChildren().add(t);
			anchorPane.getChildren().add(tx);
			list.add(t);
		}

		scrollPane0.setContent(anchorPane0);

		button0.setLayoutX(56.0);
		button0.setLayoutY(360.0);
		button0.setMnemonicParsing(false);
		button0.setText("Upload testing Picture");

		FileChooser fileChooser = new FileChooser();
		Text t0 = new Text();
		t0.setLayoutX(200);
		t0.setLayoutY(125);
		button0.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				anchorPane0.getChildren().clear();

				File file = fileChooser.showOpenDialog(scene.getWindow());
				if (file != null) {
					try {
						f = file;
						t0.setText(f.getName());
						double[] zone =Features.getZoning(f);
						double [][] diag = Features.getDiagonalFeature(f.toString()); // resized to 90 by 60
						ArrayList<ArrayList<Integer>> hist = Features.ProjectionHistogram(f.toString()); // resized 32 by 32

						int cnt =0;
						for(ArrayList<Integer> arr:hist)
							for(int d: arr)
								cnt++;
						System.out.println("Histogram "+cnt);
						String zones ="", hists="", diags="";
						if(feature==0) {
							for(int d=0; d<zone.length-1;d++)
								zones+=zone[d]+",";
							zones+=zone[zone.length-1];

						}else if(feature==2) {
							for(int k=0;k<hist.size();k++)
								for(int o =0; o<hist.get(k).size();o++)
									if(k!=hist.size()-1 || o!=hist.size()-1)
										hists+=hist.get(k).get(o)+",";
							hists+=hist.get(hist.size()-1).get(hist.get(hist.size()-1).size()-1);
						}else if(feature==1){
							for(int k=0; k<diag.length;k++)
								for(int o=0; o<diag[k].length;o++)
									if(k!=diag.length-1 || o!=diag[0].length-1)
										diags+=diag[k][o]+",";
							diags+=diag[diag.length-1][diag[0].length-1];

						
					}
						ArrayList<Double>inp = new ArrayList<>();
						if(feature==0) {
							inp = getArray(zones);
						}else if(feature==1) {
							inp = getArray(diags);

						}else if(feature==2) {
							inp = getArray(hists);

						}
						System.out.println("HEY: "+ inp);
						ArrayList<Double>outp =ann.getOutput(inp);
						System.out.println("OUTP: "+outp);

						if (outp != null) {
							/*for (int k = 0; k < outp.size(); k++) {
								Text tx = new Text();
								tx.setText("Output#" + (k + 1));
								tx.setLayoutX(10);
								tx.setLayoutY(30 * k + 10);
								Text t = new Text();
								t.setText(outp.get(k) + "");
								t.setLayoutX(80);
								t.setLayoutY(30 * k + 10);

								//anchorPane0.getChildren().add(t);
								anchorPane0.getChildren().add(tx);
							}*/
							String [] s = getResult(outp);
							String tt ="";
							double p = 100/s.length;
							for(String ss: s)
								tt+=ss+" : "+ p+"%\n";
							Text t = new Text();
							t.setText(tt);
							t.setLayoutX(80);
							t.setLayoutY(40);
							anchorPane0.getChildren().add(t);

						}
				
						

					} catch (Exception ee) {
					}
				}

			}
		});

		button.setLayoutX(282.0);
		button.setLayoutY(215.0);
		button.setMnemonicParsing(false);
		button.setText("Test");

		button.setOnAction(e -> {

			anchorPane0.getChildren().clear();
			ArrayList<Double> al = new ArrayList<>();

			try {
				for (int i = 0; i < list.size(); i++)
					al.add(Double.parseDouble(list.get(i).getText()));
			} catch (Exception ss) {

			}
			ArrayList<Double> out = ann.getOutput(al);
			if (out != null) {
				for (int k = 0; k < out.size(); k++) {
					Text tx = new Text();
					tx.setText("Output#" + (k + 1));
					tx.setLayoutX(10);
					tx.setLayoutY(30 * k + 10);
					Text t = new Text();
					t.setText(out.get(k) + "");
					t.setLayoutX(80);
					t.setLayoutY(30 * k + 10);

					anchorPane0.getChildren().add(t);
					anchorPane0.getChildren().add(tx);
				}
			}
		});
		ap.getChildren().add(label);
		ap.getChildren().add(scrollPane);
		ap.getChildren().add(label0);
		ap.getChildren().add(label1);
		ap.getChildren().add(scrollPane0);
		ap.getChildren().add(button);
		ap.getChildren().add(button0);

		scene = new Scene(ap);

	}

	public Scene getScene() {
		return scene;
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
	public static ArrayList<Double> getArray(String f) throws FileNotFoundException {
		Scanner scan = new Scanner(f);

		ArrayList<Double> a = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		while (scan.hasNextLine()) {
			sb.append(scan.nextLine().replace(" ", ""));
		}

		String sr = sb.toString().replace(" ", "");
		System.out.println("STRING:  " );
		String[] inputs = sr.split(Pattern.quote(","));
		
		for (int i = 0; i < inputs.length; i++)
			a.add(Double.parseDouble(inputs[i]));

		return a;
	}

	private void openFile(File file) {
		try {
			desktop.open(file);
		} catch (IOException ex) {

		}
	}
}