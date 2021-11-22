package application;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PerceptronGui {
	int k;
	ArrayList<Spinner<Integer>> list;
	protected Label label;
	protected AnchorPane anchorPane;
	protected Text text;
	protected Text text0;
	protected RadioButton radioButton;
	protected RadioButton radioButton0;
	protected RadioButton radioButton1;
	protected RadioButton radioButton2;
	protected RadioButton radioButton3;
	protected Slider slider;
	protected ScrollPane scrollPane;


	protected Scene scene;

	public PerceptronGui(int inp) {
		list = new ArrayList<>();
		anchorPane = new AnchorPane();
		text = new Text();
		text0 = new Text();
		radioButton = new RadioButton();
		radioButton0 = new RadioButton();
		radioButton1 = new RadioButton();
		radioButton2 = new RadioButton();
		radioButton3 = new RadioButton();
		slider = new Slider();
		label = new Label();
		scrollPane=new ScrollPane();
		k=inp;

		AnchorPane ap = new AnchorPane();
		ap.setMaxHeight(AnchorPane.USE_PREF_SIZE);
		ap.setMaxWidth(AnchorPane.USE_PREF_SIZE);
		ap.setMinHeight(AnchorPane.USE_PREF_SIZE);
		ap.setMinWidth(AnchorPane.USE_PREF_SIZE);
		ap.setPrefHeight(400.0);
		ap.setPrefWidth(600.0);

		
		anchorPane.setMinHeight(0.0);
		anchorPane.setMinWidth(0.0);
		anchorPane.setPrefHeight(200.0);
		anchorPane.setPrefWidth(219.0);

		text.setLayoutX(34.0);
		text.setLayoutY(66.0);
		text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
		text.setStrokeWidth(0.0);
		text.setText("Choose the activation method");

		ToggleGroup tg = new ToggleGroup();

		radioButton.setLayoutX(40.0);
		radioButton.setLayoutY(83.0);
		radioButton.setMnemonicParsing(false);
		radioButton.setText("Unit Function");
		radioButton.setToggleGroup(tg);

		radioButton0.setLayoutX(40.0);
		radioButton0.setLayoutY(103.0);
		radioButton0.setMnemonicParsing(false);
		radioButton0.setText("Bounded Linear Function");
		radioButton0.setToggleGroup(tg);

		radioButton1.setLayoutX(40.0);
		radioButton1.setLayoutY(123.0);
		radioButton1.setMnemonicParsing(false);
		radioButton1.setText("Identity Function");
		radioButton1.setToggleGroup(tg);

		radioButton2.setLayoutX(40.0);
		radioButton2.setLayoutY(143.0);
		radioButton2.setMnemonicParsing(false);
		radioButton2.setText("Sigmoid Function");
		radioButton2.setToggleGroup(tg);

		radioButton3.setLayoutX(40.0);
		radioButton3.setLayoutY(163.0);
		radioButton3.setMnemonicParsing(false);
		radioButton3.setText("Gaussian Function");
		radioButton3.setToggleGroup(tg);

		text0.setLayoutX(34.0);
		text0.setLayoutY(235.0);
		text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
		text0.setStrokeWidth(0.0);
		text0.setText("Choose the activation threshhold");

		slider.setBlockIncrement(1.0);
		slider.setLayoutX(34.0);
		slider.setLayoutY(250.0);
		slider.setMax(1.0);
		slider.setPrefHeight(14.0);
		slider.setPrefWidth(160.0);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setValue(0.5);
		Label l = new Label();
		DecimalFormat fmt = new DecimalFormat("0.000");

		l.setText(fmt.format(slider.getValue()));
		l.setLayoutX(34 + slider.getValue() * (160));
		l.setLayoutY(265);
		slider.valueProperty().addListener(e -> {
			l.setText(fmt.format(slider.getValue()));
			l.setLayoutX(34 + slider.getValue() * (160));
			l.setLayoutY(265);
		});
		
		label.setLayoutX(366.0);
		label.setLayoutY(53.0);
		label.setText("Enter the values of the weights:");

		
		scrollPane.setLayoutX(380.0);
		scrollPane.setLayoutY(94.0);
		scrollPane.setPrefHeight(201.0);
		scrollPane.setPrefWidth(170.0);
		
		Group root = new Group();
		int y=114;
		for(int i=0;i<k;i++){
			Text tx = new Text();
			tx.setText("W" + (i + 1));
			tx.setLayoutX(380);
			tx.setLayoutY(y+15);
			tx.setFill(Color.CORNFLOWERBLUE);
			TextField tf= new TextField();
			tf.setLayoutX(415);
			tf.setLayoutY(y);
			tf.setMaxWidth(80);
			tf.setText("1.0");
			root.getChildren().add(tx);
			root.getChildren().add(tf);
			y+=30;
			
		}
		scrollPane.setContent(anchorPane);

		scrollPane.setContent(root);	

		ap.getChildren().add(label);
		ap.getChildren().add(text);
		ap.getChildren().add(text0);
		ap.getChildren().add(radioButton);
		ap.getChildren().add(radioButton0);
		ap.getChildren().add(radioButton1);
		ap.getChildren().add(radioButton2);
		ap.getChildren().add(radioButton3);
		ap.getChildren().add(slider);
		ap.getChildren().add(scrollPane);



		ap.getChildren().add(l);
		scene = new Scene(ap);

	}

	public Scene getScene() {
		return scene;
	}

}
