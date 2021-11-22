package application;


import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public  class Settings1  {
	int k;
	Button button;
	ArrayList <Spinner<Integer>> list;
    protected   Label label;
    protected   Label label0;
    protected   Label label1;
    protected   Label label2;
    protected   Label label3;
    protected   Label label4;
    protected   Label label5;





    protected   ScrollPane scrollPane;
    protected   AnchorPane anchorPane;
    protected   RadioButton radioButton;
    protected   RadioButton radioButton0;
    protected   RadioButton radioButton1;
    protected   Slider slider;

    protected   Spinner<Integer> spinner;
    protected  Spinner<Integer> spinner0 , spinner1;
    protected  Scene scene;

    public Settings1() {
    	button = new Button();
    	list = new ArrayList<>();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        label5 = new Label();



        scrollPane = new ScrollPane();
        anchorPane = new AnchorPane();
        radioButton = new RadioButton();
        radioButton0 = new RadioButton();
        radioButton1 = new RadioButton();
        slider = new Slider();
        spinner = new Spinner<Integer>();
        spinner0 = new Spinner<Integer>();
        spinner1 = new Spinner<Integer>();

        AnchorPane ap = new AnchorPane();
        ap.setMaxHeight(AnchorPane.USE_PREF_SIZE);
        ap.setMaxWidth(AnchorPane.USE_PREF_SIZE);
        ap.setMinHeight(AnchorPane.USE_PREF_SIZE);
        ap.setMinWidth(AnchorPane.USE_PREF_SIZE);
        ap.setPrefHeight(400.0);
        ap.setPrefWidth(600.0);
        
      
   
        
        SpinnerValueFactory factory0 =  new IntegerSpinnerValueFactory(0, 1000, 0);
        spinner0.setValueFactory(factory0);
        spinner0.setEditable(true);
        TextFormatter formatter0 = new TextFormatter(factory0.getConverter(), factory0.getValue());
        spinner0.getEditor().setTextFormatter(formatter0);
        factory0.valueProperty().bindBidirectional(formatter0.valueProperty());
        
        

        SpinnerValueFactory factory1 =  new IntegerSpinnerValueFactory(1, 1000, 1);
        spinner1.setValueFactory(factory1);
        spinner1.setEditable(true);
        TextFormatter formatter1 = new TextFormatter(factory1.getConverter(), factory1.getValue());
        spinner1.getEditor().setTextFormatter(formatter1);
        factory1.valueProperty().bindBidirectional(formatter1.valueProperty());
        
      
        
        SpinnerValueFactory factory =  new IntegerSpinnerValueFactory(0, 10000, 40);
        spinner.setValueFactory(factory);
        spinner.setEditable(true);
        TextFormatter formatter = new TextFormatter(factory.getConverter(), factory.getValue());
        spinner.getEditor().setTextFormatter(formatter);
        factory.valueProperty().bindBidirectional(formatter.valueProperty());
        
        label.setLayoutX(34.0);
        label.setLayoutY(53.0);
        label.setText("Enter the number of Layers");
//        label.setTextFill(Color.WHITE);

        scrollPane.setLayoutX(34.0);
        scrollPane.setLayoutY(160.0);
        scrollPane.setPrefHeight(175.0);
        scrollPane.setPrefWidth(218.0);

       
	
        k=0;
        spinner0.valueProperty().addListener(e->{
        	list= new ArrayList<>();

        	int i =spinner0.getValue();
        	if(k!=i) {
        		k=i;
        	Group root = new Group();
        	for(int j=0; j<i;j++) {
        		Text tx = new Text();
        		tx.setText("Layer"+(j+1));
        		tx.setLayoutX(10);
        		tx.setLayoutY(30*j+10);
        		Spinner<Integer> t = new Spinner<Integer>();
        	   
        	     
        	     
        	     SpinnerValueFactory tt =  new IntegerSpinnerValueFactory(1, 1000, 1);
        	        t.setValueFactory(tt);
        	        t.setEditable(true);
        	        TextFormatter ttt = new TextFormatter(tt.getConverter(), tt.getValue());
        	        t.getEditor().setTextFormatter(ttt);
        	        tt.valueProperty().bindBidirectional(ttt.valueProperty());
        	     
        	     t.setPrefHeight(20.0);
        	     t.setPrefWidth(70.0);
        		t.setLayoutX(60);
        		t.setLayoutY(30*j);
        		root.getChildren().add(tx);
        		root.getChildren().add(t);
        		list.add(t);
        	System.out.println(list.size());
        	}
        	scrollPane.setContent(root);
        }
        });
      
        
        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(128.0);
        anchorPane.setPrefWidth(227.0);
        scrollPane.setContent(anchorPane);
        
        label1.setLayoutX(34.0);
        label1.setLayoutY(140.0);
        label1.setText("Enter the number of perceptron per layer");

        label2.setLayoutX(340.0);
        label2.setLayoutY(66.0);
        label2.setText("Choose the error calculation method");
        
        ToggleGroup tg = new ToggleGroup();
        radioButton.setLayoutX(351.0);
        radioButton.setLayoutY(83.0);
        radioButton.setMnemonicParsing(false);
        radioButton.setText("Mean Difference Error");
        //radioButton.setText("Zoning");
        radioButton.setTextFill(Color.WHITE);
        radioButton.setToggleGroup(tg);
        radioButton.setSelected(true);
        
        radioButton0.setLayoutX(351.0);
        radioButton0.setLayoutY(103.0);
        radioButton0.setMnemonicParsing(false);
      radioButton0.setText("Mean Absolute Error");
      //  radioButton0.setText("Diagonal");

        radioButton0.setTextFill(Color.WHITE);
        radioButton0.setToggleGroup(tg);

        
        radioButton1.setLayoutX(351.0);
        radioButton1.setLayoutY(124.0);
        radioButton1.setMnemonicParsing(false);
       radioButton1.setText("Mean Square Error");
        //radioButton1.setText("Histogram");

        radioButton1.setTextFill(Color.WHITE);
        radioButton1.setToggleGroup(tg);

       
        
        label3.setLayoutX(340.0);
        label3.setLayoutY(195.0);
        label3.setText("Choose the error threshhold");

        slider.setBlockIncrement(1.0);
        slider.setLayoutX(344.0);
        slider.setLayoutY(220.0);
        slider.setMax(1.0);
        slider.setPrefHeight(14.0);
        slider.setPrefWidth(160.0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setValue(0.333);
        Label l = new Label();
        DecimalFormat fmt = new DecimalFormat("0.000");

        l.setText(fmt.format(slider.getValue()));
    	l.setLayoutX(344+slider.getValue()*(160));
    	l.setLayoutY(235);
        slider.valueProperty().addListener(e->{
        	l.setText(fmt.format(slider.getValue()));
        	l.setLayoutX(344+slider.getValue()*(160));
        	l.setLayoutY(235);
        });
        
        label4.setLayoutX(53.0);
        label4.setLayoutY(362.0);
        label4.setText("* Make sure to choose those values carefully since you will not be able to modify them later on");
        label4.setFont(new Font("System Italic", 12.0));

        label5.setLayoutX(340.0);
        label5.setLayoutY(277.0);
        label5.setText("Choose the maximum number of iterations");

        
        label0.setLayoutX(33.0);
        label0.setLayoutY(90.0);
        label0.setText("Enter the number of inputs"); 
        
        
      
        
        spinner.setLayoutX(355.0);
        spinner.setLayoutY(300.0);
        spinner.setPrefHeight(25.0);
        spinner.setPrefWidth(95.0);

        spinner0.setLayoutX(210.0);
        spinner0.setLayoutY(49.0);
        spinner0.setPrefHeight(25.0);
        spinner0.setPrefWidth(65.0);
        
        spinner1.setLayoutX(210.0);
        spinner1.setLayoutY(86.0);
        spinner1.setPrefHeight(25.0);
        spinner1.setPrefWidth(65.0);

        button.setLayoutX(523.0);
        button.setLayoutY(14.0);
        button.setMnemonicParsing(false);
        button.setText("Next");
        
        ap.getChildren().add(label);
        ap.getChildren().add(scrollPane);
        ap.getChildren().add(label1);
        ap.getChildren().add(label2);
        ap.getChildren().add(radioButton);
        ap.getChildren().add(radioButton0);
        ap.getChildren().add(radioButton1);
        ap.getChildren().add(label3);
        ap.getChildren().add(slider);
        ap.getChildren().add(label4);
        ap.getChildren().add(label5);
        ap.getChildren().add(spinner);
        ap.getChildren().add(spinner0);
        ap.getChildren().add(spinner1);
        ap.getChildren().add(l);
        ap.getChildren().add(label0);
        ap.getChildren().add(button);
    	scene = new Scene(ap);

    }
    public Scene getScene() {
    	return scene;
    }
    public Button getButton() {
    	return button;
    }
    
}
