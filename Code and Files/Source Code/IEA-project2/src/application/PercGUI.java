package application;


import java.awt.Font;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public  class PercGUI {

    protected final Label label, label1, label2, label3;
    protected final ChoiceBox<String> choiceBox;
    protected final Pane anchorPane;
    protected final Scene s;
	ScrollPane scrollPane;
	Button button;
	ArrayList<TextField> al, wl;
	TextField textField;

    public PercGUI(Perceptron p, int layer, int nbr, Stage stage) {
    	button= new Button();
    	textField = new TextField();
    	scrollPane = new ScrollPane();
        label = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();

        choiceBox = new ChoiceBox<>();
        anchorPane = new Pane();
        wl  = new ArrayList<>();
        AnchorPane ap = new AnchorPane();
        ap.setMaxHeight(AnchorPane.USE_PREF_SIZE);
        ap.setMaxWidth(AnchorPane.USE_PREF_SIZE);
        ap.setMinHeight(AnchorPane.USE_PREF_SIZE);
        ap.setMinWidth(AnchorPane.USE_PREF_SIZE);
        ap.setPrefHeight(400.0);
        ap.setPrefWidth(600.0);

        label.setLayoutX(38.0);
        label.setLayoutY(74.0);
        label.setText("Choose this perceptron's activation function");

        choiceBox.setLayoutX(38.0);
        choiceBox.setLayoutY(100.0);
        choiceBox.setPrefWidth(150.0);
        choiceBox.getItems().add("Unit Function");
        choiceBox.getItems().add("Bounded Linear Function");
        choiceBox.getItems().add("Identity Function");
        choiceBox.getItems().add("Sigmoid Function");
        choiceBox.getItems().add("Guassian Function");
        
       
        choiceBox.getSelectionModel().select(p.functionNbr);;

    	if(p.functionNbr==0) {
    		Text t = new Text();
    		t.setText("a");
    		t.setLayoutX(10);
    		t.setLayoutY(25);
    		t.setFill(Color.WHITE);
    		
    		TextField tf = new TextField();
    		tf.setText(p.a_unit+"");
    		tf.setLayoutX(55);
    		tf.setLayoutY(10);
    		tf.setPrefWidth(100);
    		anchorPane.getChildren().clear();
    		anchorPane.getChildren().add(t);
    		anchorPane.getChildren().add(tf);
    		al = new ArrayList<>();

    		al.add(tf);
    	} else if(p.functionNbr==1) {
    	
    		Text t = new Text();
    		t.setText("a");
    		t.setLayoutX(10);
    		t.setLayoutY(25);
    		t.setFill(Color.WHITE);

    		TextField tf = new TextField();
    		tf.setLayoutX(55);
    		tf.setLayoutY(10);
    		tf.setPrefWidth(100);
    		tf.setText(p.a_bounded+"");

    		Text t1 = new Text();
    		t1.setText("v_min");
    		t1.setLayoutX(10);
    		t1.setLayoutY(55);
    		t1.setFill(Color.WHITE);

    		
    		TextField tf1 = new TextField();
    		tf1.setLayoutX(55);
    		tf1.setLayoutY(40);
    		tf1.setPrefWidth(100);
    		tf1.setText(p.v_min+"");

    		Text t2 = new Text();
    		t2.setText("v_max");
    		t2.setLayoutX(10);
    		t2.setLayoutY(85);
    		t2.setFill(Color.WHITE);

    		TextField tf2 = new TextField();
    		tf2.setLayoutX(55);
    		tf2.setLayoutY(70);
    		tf2.setPrefWidth(100);
    		tf2.setText(p.v_max+"");

    		anchorPane.getChildren().clear();

    		anchorPane.getChildren().add(t);
    		anchorPane.getChildren().add(tf);
    		anchorPane.getChildren().add(t1);
    		anchorPane.getChildren().add(tf1);
    		anchorPane.getChildren().add(t2);
    		anchorPane.getChildren().add(tf2);
    		al = new ArrayList<>();
    		al.add(tf);
    		al.add(tf1);
    		al.add(tf2);
    	}
    	else if(p.functionNbr==2) {
    		

    		/*Text t = new Text();
    		t.setText("v");
    		t.setLayoutX(10);
    		t.setLayoutY(25);
    		t.setFill(Color.WHITE);

    		TextField tf = new TextField();
    		tf.setLayoutX(55);
    		tf.setLayoutY(10);
    		tf.setPrefWidth(100);
    		tf.setText(p.v+"");

    		anchorPane.getChildren().clear();
    		anchorPane.getChildren().add(t);
    		anchorPane.getChildren().add(tf);
    		al = new ArrayList<>();

    		al.add(tf);*/
    	}else if(p.functionNbr==3) {
 
    		Text t = new Text();
    		t.setText("a");
    		t.setLayoutX(10);
    		t.setLayoutY(25);
    		t.setFill(Color.WHITE);

    		TextField tf = new TextField();
    		tf.setLayoutX(55);
    		tf.setLayoutY(10);
    		tf.setPrefWidth(100);
    		tf.setText(p.a_sigmoid+"");

    		Text t1 = new Text();
    		t1.setText("k");
    		t1.setLayoutX(10);
    		t1.setLayoutY(55);
    		t1.setFill(Color.WHITE);

    		TextField tf1 = new TextField();
    		tf1.setLayoutX(55);
    		tf1.setLayoutY(40);
    		tf1.setPrefWidth(100);
    		tf1.setText(p.k+"");

    		anchorPane.getChildren().clear();

    		anchorPane.getChildren().add(t);
    		anchorPane.getChildren().add(tf);
    		anchorPane.getChildren().add(t1);
    		anchorPane.getChildren().add(tf1);
    		al = new ArrayList<>();

    		al.add(tf);
    		al.add(tf1);
    		
    	}else if(p.functionNbr==4) {
    		
    		Text t = new Text();
    		t.setText("a");
    		t.setLayoutX(10);
    		t.setLayoutY(25);
    		t.setFill(Color.WHITE);

    		TextField tf = new TextField();
    		tf.setLayoutX(55);
    		tf.setLayoutY(10);
    		tf.setPrefWidth(100);
    		tf.setText(p.a_guassian+"");

    		Text t1 = new Text();
    		t1.setText("v0");
    		t1.setLayoutX(10);
    		t1.setLayoutY(55);
    		t1.setFill(Color.WHITE);

    		TextField tf1 = new TextField();
    		tf1.setLayoutX(55);
    		tf1.setLayoutY(40);
    		tf1.setPrefWidth(100);
    		tf1.setText(p.v0+"");

    		Text t2 = new Text();
    		t2.setText("σ");
    		t2.setLayoutX(10);
    		t2.setLayoutY(85);
    		t2.setFill(Color.WHITE);

    		TextField tf2 = new TextField();
    		tf2.setLayoutX(55);
    		tf2.setLayoutY(70);
    		tf2.setPrefWidth(100);
    		tf2.setText(p.sigma+"");

    		anchorPane.getChildren().clear();

    		anchorPane.getChildren().add(t);
    		anchorPane.getChildren().add(tf);
    		anchorPane.getChildren().add(t1);
    		anchorPane.getChildren().add(tf1);
    		anchorPane.getChildren().add(t2);
    		anchorPane.getChildren().add(tf2);
    		al = new ArrayList<>();

    		al.add(tf);
    		al.add(tf1);
    		al.add(tf2);
    	}
      
		
        choiceBox.valueProperty().addListener(e->{
        	
        	if(choiceBox.getValue().compareTo("Unit Function")==0) {
        		p.functionNbr=0;
        		Text t = new Text();
        		t.setText("a");
        		t.setLayoutX(10);
        		t.setLayoutY(25);
        		t.setFill(Color.WHITE);

        		TextField tf = new TextField();
        		tf.setText(p.a_unit+"");
        		tf.setLayoutX(55);
        		tf.setLayoutY(10);
        		tf.setPrefWidth(100);
        		anchorPane.getChildren().clear();
        		anchorPane.getChildren().add(t);
        		anchorPane.getChildren().add(tf);
        		al = new ArrayList<>();

        		al.add(tf);
        	} else if(choiceBox.getValue().compareTo("Bounded Linear Function")==0) {
        		p.functionNbr=1;
        		Text t = new Text();
        		t.setText("a");
        		t.setLayoutX(10);
        		t.setLayoutY(25);
        		t.setFill(Color.WHITE);

        		
        		TextField tf = new TextField();
        		tf.setLayoutX(55);
        		tf.setLayoutY(10);
        		tf.setPrefWidth(100);
        		tf.setText(p.a_bounded+"");

        		Text t1 = new Text();
        		t1.setText("v_min");
        		t1.setLayoutX(10);
        		t1.setLayoutY(55);
        		t1.setFill(Color.WHITE);

        		TextField tf1 = new TextField();
        		tf1.setLayoutX(55);
        		tf1.setLayoutY(40);
        		tf1.setPrefWidth(100);
        		tf1.setText(p.v_min+"");

        		Text t2 = new Text();
        		t2.setText("v_max");
        		t2.setLayoutX(10);
        		t2.setLayoutY(85);
        		t2.setFill(Color.WHITE);

        		TextField tf2 = new TextField();
        		tf2.setLayoutX(55);
        		tf2.setLayoutY(70);
        		tf2.setPrefWidth(100);
        		tf2.setText(p.v_max+"");

        		anchorPane.getChildren().clear();

        		anchorPane.getChildren().add(t);
        		anchorPane.getChildren().add(tf);
        		anchorPane.getChildren().add(t1);
        		anchorPane.getChildren().add(tf1);
        		anchorPane.getChildren().add(t2);
        		anchorPane.getChildren().add(tf2);
        		al = new ArrayList<>();
        		al.add(tf);
        		al.add(tf1);
        		al.add(tf2);
        	}
        	else if(choiceBox.getValue().compareTo("Identity Function")==0) {
        		p.functionNbr=2;

        		Text t = new Text();
        		t.setText("v");
        		t.setLayoutX(10);
        		t.setLayoutY(25);
        		t.setFill(Color.WHITE);

        		TextField tf = new TextField();
        		tf.setLayoutX(55);
        		tf.setLayoutY(10);
        		tf.setPrefWidth(100);
        		tf.setText(p.v+"");

        		anchorPane.getChildren().clear();
        		anchorPane.getChildren().add(t);
        		anchorPane.getChildren().add(tf);
        		al = new ArrayList<>();

        		al.add(tf);
        	}else if(choiceBox.getValue().compareTo("Sigmoid Function")==0) {
        		p.functionNbr=3;
        		Text t = new Text();
        		t.setText("a");
        		t.setLayoutX(10);
        		t.setLayoutY(25);
        		t.setFill(Color.WHITE);
 
        		TextField tf = new TextField();
        		tf.setLayoutX(55);
        		tf.setLayoutY(10);
        		tf.setPrefWidth(100);
        		tf.setText(p.a_sigmoid+"");

        		Text t1 = new Text();
        		t1.setText("k");
        		t1.setLayoutX(10);
        		t1.setLayoutY(55);
        		t1.setFill(Color.WHITE);

        		TextField tf1 = new TextField();
        		tf1.setLayoutX(55);
        		tf1.setLayoutY(40);
        		tf1.setPrefWidth(100);
        		tf1.setText(p.k+"");

        		anchorPane.getChildren().clear();

        		anchorPane.getChildren().add(t);
        		anchorPane.getChildren().add(tf);
        		anchorPane.getChildren().add(t1);
        		anchorPane.getChildren().add(tf1);
        		al = new ArrayList<>();

        		al.add(tf);
        		al.add(tf1);
        		
        	}else if(choiceBox.getValue().compareTo("Guassian Function")==0) {
        		p.functionNbr=4;
        		Text t = new Text();
        		t.setText("a");
        		t.setLayoutX(10);
        		t.setLayoutY(25);
        		t.setFill(Color.WHITE);

        		TextField tf = new TextField();
        		tf.setLayoutX(55);
        		tf.setLayoutY(10);
        		tf.setPrefWidth(100);
        		tf.setText(p.a_guassian+"");

        		Text t1 = new Text();
        		t1.setText("v0");
        		t1.setLayoutX(10);
        		t1.setLayoutY(55);
        		t1.setFill(Color.WHITE);

        		TextField tf1 = new TextField();
        		tf1.setLayoutX(55);
        		tf1.setLayoutY(40);
        		tf1.setPrefWidth(100);
        		tf1.setText(p.v0+"");

        		Text t2 = new Text();
        		t2.setText("σ");
        		t2.setLayoutX(10);
        		t2.setLayoutY(85);
        		t2.setFill(Color.WHITE);

        		TextField tf2 = new TextField();
        		tf2.setLayoutX(55);
        		tf2.setLayoutY(70);
        		tf2.setPrefWidth(100);
        		tf2.setText(p.sigma+"");

        		anchorPane.getChildren().clear();

        		anchorPane.getChildren().add(t);
        		anchorPane.getChildren().add(tf);
        		anchorPane.getChildren().add(t1);
        		anchorPane.getChildren().add(tf1);
        		anchorPane.getChildren().add(t2);
        		anchorPane.getChildren().add(tf2);
        		al = new ArrayList<>();

        		al.add(tf);
        		al.add(tf1);
        		al.add(tf2);
        	}

        });
        
        label2.setLayoutX(239.0);
        label2.setLayoutY(40.0);
        label2.setText("Perceptron #"+(nbr+1)+" in Layer #"+(layer+1));

        anchorPane.setLayoutX(38.0);
        anchorPane.setLayoutY(143.0);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        label1.setLayoutX(366.0);
		label1.setLayoutY(53.0);
		label1.setText("Enter the values of the weights:");
		
		scrollPane.setLayoutX(380.0);
		scrollPane.setLayoutY(94.0);
		scrollPane.setPrefHeight(201.0);
		scrollPane.setPrefWidth(170.0);
		
		label3.setLayoutX(38.0);
		label3.setLayoutY(315.0);
		label3.setText("Activation threshold");

        textField.setLayoutX(170.0);
        textField.setLayoutY(310.0);
        textField.setPrefHeight(25.0);
        textField.setPrefWidth(55.0);
        textField.setText(p.threshhold+"");
        
        button.setLayoutX(485.0);
        button.setLayoutY(306.0);
        button.setMnemonicParsing(false);
        button.setText("Save");
		Group root = new Group();
		int y=114;
		for(int i=0;i<p.weights.size();i++){
			Text tx = new Text();
			tx.setText("W" + (i + 1));
			tx.setLayoutX(380);
			tx.setLayoutY(y+15);
			tx.setFill(Color.CORNFLOWERBLUE);
			TextField tf= new TextField();
			tf.setLayoutX(415);
			tf.setLayoutY(y);
			tf.setMaxWidth(80);
			tf.setText(p.weights.get(i)+"");
			root.getChildren().add(tx);
			root.getChildren().add(tf);
			y+=30;
			wl.add(tf);
			
			
		}
		scrollPane.setContent(anchorPane);
		scrollPane.setContent(root);
		
        ap.getChildren().add(label);
        ap.getChildren().add(choiceBox);
        ap.getChildren().add(label2);
        ap.getChildren().add(anchorPane);
        ap.getChildren().add(scrollPane);
        ap.getChildren().add(button);
        ap.getChildren().add(label3);
        ap.getChildren().add(textField);

        button.pressedProperty().addListener(e->{
        	try {
        	p.threshhold = Double.parseDouble(textField.getText());
        	if(p.functionNbr==0) {
        		p.a_unit = Double.parseDouble(al.get(0).getText());

        	}else if(p.functionNbr==1) {
        		p.a_bounded=  Double.parseDouble(al.get(0).getText());
        		p.v_min =  Double.parseDouble(al.get(1).getText());
        		p.v_max = Double.parseDouble(al.get(2).getText());

        	}else if(p.functionNbr==2) {
        		//p.v =  Double.parseDouble(al.get(0).getText());

        	}else if(p.functionNbr==3) {
        		p.a_sigmoid=  Double.parseDouble(al.get(0).getText());
        		p.k =  Double.parseDouble(al.get(1).getText());
        		
        	}else if(p.functionNbr==4) {
        		p.a_guassian =  Double.parseDouble(al.get(0).getText());
        		p.v0 =  Double.parseDouble(al.get(1).getText());;
        		p.sigma =  Double.parseDouble(al.get(2).getText());
        	}
        	for(int i=0;i<wl.size();i++) {
        		p.weights.set(i, Double.parseDouble(wl.get(i).getText()));
        	}
        	stage.setResizable(false);

        	stage.close();
        	}
        	catch(Exception es) {
        	
        		       
        		
        	}
        });
        s = new Scene(ap);
        stage.setResizable(false);

    }
    public Scene getScene() {
    	return s;
    }
    
}
