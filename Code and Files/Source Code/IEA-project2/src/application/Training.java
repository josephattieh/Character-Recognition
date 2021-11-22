package application;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.proj.Features;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public  class Training {
	private Desktop desktop = Desktop.getDesktop();

	protected final Button button;
	double a ;
	String[][] staffArray ;


	protected final Label label;

	ArrayList<Stage> al ;


	ArrayList<String> a1,a2,a3;
	Button button0, button1, button2, button3,button6;
	Label text4, text5, text6,text7,text0,text1,text2,text3;
	Spinner<Integer> spinner, spinner0, spinner1;
	File f;
	List<File> list;
	Scene scene;
	public Training(ANN ann,Stage stage, int feature) throws FileNotFoundException {
		button0 = new Button();
		button6 = new Button();
		button1 = new Button();
		button2 = new Button();
		button3 = new Button();
		text0 = new Label();
		text1 = new Label();
		text2 = new Label();
		text3= new Label();
		text4 = new Label();
		text5= new Label();
		text6 = new Label();
		text7 = new Label();

		text4.setText("");
		text5.setText("");
		text6.setText("");
		text7.setText("");

		text0.setLayoutX(325.0);
		text0.setLayoutY(85.0);
		text0.setText("Training data format:");

		text1.setLayoutX(335.0);
		text1.setLayoutY(113.0);
		text1.setText("( in1, in2, ... , ...,inm) , (out1, out2,.. , outn);");

		text2.setLayoutX(335.0);
		text2.setLayoutY(139.0);

		text2.setText("( in'1, in'2, ... , in'm) , (out'1, out'2,.. , out'n);");
		AnchorPane anchorpane= new AnchorPane();
		button = new Button();

		spinner = new Spinner<Integer>();
		spinner0 = new Spinner<Integer>();
		spinner1 = new Spinner<Integer>();
		label= new Label();

		SpinnerValueFactory factory0 =  new IntegerSpinnerValueFactory(1, 1000, 1);
		spinner0.setValueFactory(factory0);
		spinner0.setEditable(true);
		TextFormatter formatter0 = new TextFormatter(factory0.getConverter(), factory0.getValue());
		spinner0.getEditor().setTextFormatter(formatter0);
		factory0.valueProperty().bindBidirectional(formatter0.valueProperty());

		SpinnerValueFactory factory1 =  new IntegerSpinnerValueFactory(1, 10000, 100);
		spinner1.setValueFactory(factory1);
		spinner1.setEditable(true);
		TextFormatter formatter1 = new TextFormatter(factory1.getConverter(), factory1.getValue());
		spinner1.getEditor().setTextFormatter(formatter1);
		factory1.valueProperty().bindBidirectional(formatter1.valueProperty());


		SpinnerValueFactory factory =  new IntegerSpinnerValueFactory(0, 100, 70);
		spinner.setValueFactory(factory);
		spinner.setEditable(true);
		TextFormatter formatter = new TextFormatter(factory.getConverter(), factory.getValue());
		spinner.getEditor().setTextFormatter(formatter);
		factory.valueProperty().bindBidirectional(formatter.valueProperty());
		al = new ArrayList<>();
		anchorpane.setPrefHeight(400.0);
		anchorpane.setPrefWidth(600.0);

		label.setLayoutX(56.0);
		label.setLayoutY(67.0);
		label.setText("Training");
		label.setFont(new Font(20.0));

		button.setLayoutX(56.0);
		button.setLayoutY(109.0);
		button.setMnemonicParsing(false);
		button.setText("Upload Images");
		
		button6.setLayoutX(180.0);
		button6.setLayoutY(109.0);
		button6.setMnemonicParsing(false);
		button6.setText("Upload text File");
		TableView<String[]> table = new TableView<>();

		FileChooser fileChooser = new FileChooser();
		FileChooser fileChooser2 = new FileChooser();

		Text t = new Text();
		t.setLayoutX(200);
		t.setLayoutY(125);

		button.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						/*		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
							fileChooser.getExtensionFilters().add(extFilter);*/
						 list = fileChooser2.showOpenMultipleDialog(stage);
						String zones="", diags="", hists="";

						File file1, file2;
						try {
							if (list!=null) {
								for(File f : list) {
									System.out.println("/////////////");
									System.out.println(f.toString());

										t.setText(f.getName());
										//////////////////////////////
										char c  =f.getName().charAt(0);
										String s ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
										String out="";
										int[] g = new int [26];
										for(int i=0; i<26;i++)
											g[i]=0;
										g[s.indexOf(c)]=1;
										for(int ss: g)
											out+=ss+",";
										out = out.substring(0, out.length()-1);
										System.out.println(out);

										
										double[] zone;
										ArrayList<ArrayList<Integer>> hist;
										int cnt =0;
										double [][] diag;


										if(feature==0) {
											zone =Features.getZoning(f);
											zones+="(";
											for(int d=0; d<zone.length-1;d++)
												zones+=zone[d]+",";
											zones+=zone[zone.length-1]+"),("+out+");";

										}else if(feature==2) {
											hist = Features.ProjectionHistogram(f.toString()); // resized 32 by 32

											hists+="(";
											for(int k=0;k<hist.size();k++)
												for(int o =0; o<hist.get(k).size();o++)
													if(k!=hist.size()-1 || o!=hist.size()-1)
														hists+=hist.get(k).get(o)+",";
											hists+=hist.get(hist.size()-1).get(hist.get(hist.size()-1).size()-1)+"),("+out+");";
										}else if(feature==1){
											 diag = Features.getDiagonalFeature(f.toString()); // resized to 90 by 60

											diags+="(";
											for(int k=0; k<diag.length;k++)
												for(int o=0; o<diag[k].length;o++)
													if(k!=diag.length-1 || o!=diag[0].length-1)
														diags+=diag[k][o]+",";
											diags+=diag[diag.length-1][diag[0].length-1]+"),("+out+");";

										
									}}
								
								//////////////////////////////
									if(feature==0) {
										
										staffArray= getArray(zones);
										
									}else if(feature==1) {
										staffArray= getArray(diags);
										


									}else if(feature==2) {
										staffArray= getArray(hists);
									

									}
								a2= new ArrayList<>();
								a3= new ArrayList<>();

								for(int i=0; i<staffArray.length;i++) {


									a2.add(staffArray[i][1]);
									a3.add(staffArray[i][2]);

								}
							

								ObservableList<String[]> data = FXCollections.observableArrayList();
								data.addAll(Arrays.asList(staffArray));

								table.getColumns().clear();
								data.remove(0);//remove titles from data
								for (int i = 0; i < staffArray[0].length; i++) {
									TableColumn tc = new TableColumn(staffArray[0][i]);
									final int colNo = i;
									tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
										@Override
										public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
											return new SimpleStringProperty((p.getValue()[colNo]));
										}
									});
									tc.setPrefWidth(90);
									table.getColumns().add(tc);
								}

								table.setItems(data);
							}
						}catch(Exception ee) {}




					}
				});


		 button6.setOnAction(
	                new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(final ActionEvent e) {
	                    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	                    	fileChooser.getExtensionFilters().add(extFilter);
	                        File file = fileChooser.showOpenDialog(scene.getWindow());
	                        if (file != null) {
	                            try{f = file;
	                            t.setText(f.getName());
	                             staffArray = getArray(file);
	                            a2= new ArrayList<>();
	                            a3= new ArrayList<>();

	                            for(int i=0; i<staffArray.length;i++) {
	                            	
	                            		a2.add(staffArray[i][1]);
	                            		a3.add(staffArray[i][2]);
	                            	
	                            }
	                            
	                            ObservableList<String[]> data = FXCollections.observableArrayList();
	                            data.addAll(Arrays.asList(staffArray));
	                            data.remove(0);//remove titles from data

	                            for (int i = 0; i < staffArray[0].length; i++) {
	                                TableColumn tc = new TableColumn(staffArray[0][i]);
	                                final int colNo = i;
	                                tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
	                                    @Override
	                                    public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
	                                        return new SimpleStringProperty((p.getValue()[colNo]));
	                                    }
	                                });
	                                tc.setPrefWidth(90);
	                                table.getColumns().add(tc);
	                            }
	                            table.setItems(data);
	                            }catch(Exception ee) {}
	                            }
	                        
	                        }
	                });
	        
	        


		table.setLayoutX(56.0);
		table.setLayoutY(169.0);
		table.setPrefHeight(200.0);
		table.setPrefWidth(235.0);

		StackPane root = new StackPane();


		button0.setLayoutX(363.0);
		button0.setLayoutY(185.0);
		button0.setMnemonicParsing(false);
		button0.setPrefHeight(25.0);
		button0.setPrefWidth(176.0);
		button0.setText("Train All Data");

		button1.setLayoutX(363.0);
		button1.setLayoutY(233.0);
		button1.setMnemonicParsing(false);
		button1.setPrefHeight(25.0);
		button1.setPrefWidth(97.0);
		button1.setText("Seperate V");


		button1.setOnAction(e->{
			try {
					a= ann.SeperateValidation(staffArray, spinner.getValue());

					if(a!=-1)
						text4.setText(""+a);

				
			}catch(Exception ee) {}
		});
		button2.setOnAction(e->{
			try{   	
				{
					a= ann.KFoldValidation(staffArray, spinner0.getValue());
					System.out.println(spinner0.getValue());

					if(a!=-1)
						text5.setText(""+a);
				}
			}catch(Exception ee)
			{

			}
		});
		button3.setOnAction(e->{
			try{   	
				a= ann.MonteCarlo(staffArray, spinner1.getValue());
				System.out.println(a);
				if(a!=-1)
					text6.setText(""+a);

			
			}catch(Exception ee)
			{

			}
		});
		button2.setLayoutX(363.0);
		button2.setLayoutY(280.0);
		button2.setMnemonicParsing(false);
		button2.setPrefHeight(25.0);
		button2.setPrefWidth(97.0);
		button2.setText("K-Fold");

		button3.setLayoutX(363.0);
		button3.setLayoutY(328.0);
		button3.setMnemonicParsing(false);
		button3.setPrefHeight(25.0);
		button3.setPrefWidth(97.0);
		button3.setText("MonteCarlo");

		text3.setLayoutX(546.0);
		text3.setLayoutY(190.0);

		text3.setText("");

		text4.setLayoutX(546.0);
		text4.setLayoutY(240.0);

		text4.setText(" ");

		text5.setLayoutX(546.0);
		text5.setLayoutY(283.0);

		text5.setText(" ");

		text6.setLayoutX(546.0);
		text6.setLayoutY(330.0);

		text6.setText(" ");
		spinner.setLayoutX(476.0);
		spinner.setLayoutY(233.0);
		spinner.setPrefHeight(25.0);
		spinner.setPrefWidth(69.0);

		spinner0.setLayoutX(476.0);
		spinner0.setLayoutY(280.0);
		spinner0.setPrefHeight(25.0);
		spinner0.setPrefWidth(69.0);

		spinner1.setLayoutX(476.0);
		spinner1.setLayoutY(328.0);
		spinner1.setPrefHeight(25.0);
		spinner1.setPrefWidth(69.0);

		button0.pressedProperty().addListener(e->{
			if(list!=null && a2!=null) {

				for(int i=1;i<a2.size();i++) {
					String in = a2.get(i);
					String out = a3.get(i);

					ArrayList<Double> inp = new ArrayList<>();
					ArrayList<Double> outp = new ArrayList<>();
					String [] arr1 = in.split(",");
					String [] arr2 = out.split(",");

					for(int j=0; j<arr1.length;j++)
						inp.add(Double.parseDouble(arr1[j]));

					for(int j=0; j<arr2.length;j++)
						outp.add(Double.parseDouble(arr2[j]));
					//if(inp.size()!=ann.input || outp.size()!=ann.layers.get(ann.layers.size()-1).size()) {

					try {
						int time=-1;


						time =ann.train(inp, outp);
						System.out.println("Data#"+i+" has been trained in "+time+"times.");
						if(time ==-1) {
							Stage dialog = new Stage();

							dialog.initModality(Modality.APPLICATION_MODAL);
							dialog.initOwner(stage);
							VBox dialogVbox = new VBox(20);
							dialogVbox.getChildren().add(
									new Label("The training data set entered \n is not compatible with the network!"));
							Scene dialogScene = new Scene(dialogVbox, 225, 100);
							dialog.setScene(dialogScene);

							dialogScene.getStylesheets()
							.add(getClass().getResource("application1.css").toExternalForm());
							dialog.show();
							break;
						}else
							stage.close();
					} catch (Exception e1) {

						// TODO Auto-generated catch block
						Stage dialog = new Stage();

						dialog.initModality(Modality.APPLICATION_MODAL);
						dialog.initOwner(stage);
						VBox dialogVbox = new VBox(20);
						dialogVbox.getChildren().add(new Text("Training Data Set is not compatible with network!"));
						Scene dialogScene = new Scene(dialogVbox, 300, 200);
						dialog.setScene(dialogScene);
						dialog.show();

					}}
			}
		});


		anchorpane.getChildren().add(label);
		anchorpane.getChildren().add(button);
		anchorpane.getChildren().add(button6);

		anchorpane.getChildren().add(text0);
		anchorpane.getChildren().add(text1);
		anchorpane.getChildren().add(text2);
		anchorpane.getChildren().add(button0);
		anchorpane.getChildren().add(table);
		anchorpane.getChildren().add(button1);
		anchorpane.getChildren().add(button2);
		anchorpane.getChildren().add(button3);
		anchorpane.getChildren().add(text4);
		anchorpane.getChildren().add(text5);
		anchorpane.getChildren().add(text6);
		anchorpane.getChildren().add(text3);
		anchorpane.getChildren().add(spinner);
		anchorpane.getChildren().add(spinner0);
		anchorpane.getChildren().add(spinner1);



		//anchorpane.getChildren().add(t);
		scene = new Scene(anchorpane);
	}
	public Scene getScene() {
		return scene;
	}
	public static String[][] getArray(String f) throws FileNotFoundException{
		Scanner scan = new Scanner(f);
		ArrayList<String> a1 = new ArrayList<>();
		ArrayList<String> a2= new ArrayList<>();
		ArrayList<String> a3= new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("),");
		while(scan.hasNextLine()) {
			sb.append(scan.nextLine().replace(" ", ""));
		}
		sb.append("(");
		System.out.println(sb.toString().replace(";",",").replace(" ", ""));
		String sr = sb.toString().replace(";",",").replace(" ", "");
		String [] inputs = sr.split(Pattern.quote("),("));


		for(int i=1;i<inputs.length;i++) {
			if(i%2!=0) {
				a2.add(inputs[i]);
			}else {

				a3.add(inputs[i]);
			}
		}

		for(int i=0; i<a2.size();i++)
			a1.add(""+(i+1));
		String [][] st = new String[a1.size()+1][3];
		st[0][0]="#";
		st[0][1]="Inputs";
		st[0][2] ="Outputs";
		for(int i=0; i<a1.size();i++) {
			st[i+1][0] = a1.get(i);
			st[i+1][1] = a2.get(i);
			st[i+1][2] = a3.get(i);

		}

		return st;
	}
	 public static String[][] getArray(File f) throws FileNotFoundException{
	    	Scanner scan = new Scanner(f);
	    	ArrayList<String> a1 = new ArrayList<>();
	    	ArrayList<String> a2= new ArrayList<>();
	    	ArrayList<String> a3= new ArrayList<>();
	    	StringBuilder sb = new StringBuilder();
	    	sb.append("),");
	    	while(scan.hasNextLine()) {
	    		sb.append(scan.nextLine().replace(" ", ""));
	    	}
	    	sb.append("(");
	    	System.out.println(sb.toString().replace(";",",").replace(" ", ""));
	    	String sr = sb.toString().replace(";",",").replace(" ", "");
	    	String [] inputs = sr.split(Pattern.quote("),("));
	    
	    	
	    	for(int i=1;i<inputs.length;i++) {
	    		if(i%2!=0) {
	    			a2.add(inputs[i]);
	    		}else {
	    			
	    			a3.add(inputs[i]);
	    		}
	    	}
	    	
	    	for(int i=0; i<a2.size();i++)
	    		a1.add(""+(i+1));
	    	String [][] st = new String[a1.size()+1][3];
	    	st[0][0]="#";
	    	st[0][1]="Inputs";
	    	st[0][2] ="Outputs";
	    	for(int i=0; i<a1.size();i++) {
	    		st[i+1][0] = a1.get(i);
	    		st[i+1][1] = a2.get(i);
	    		st[i+1][2] = a3.get(i);

	    	}
	    		
	    	return st;
	    }
	private void openFile(File file) {
		try {
			desktop.open(file);
		} catch (IOException ex) {

		}
	}
}
