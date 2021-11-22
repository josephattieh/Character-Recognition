package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Welcome {

    ImageView imageView;
    Button button;
    ImageView imageView0;
    Button button1;
    protected   RadioButton radioButton;
    protected   RadioButton radioButton0;
    protected   RadioButton radioButton1;
    Rectangle rectangle;
    Scene scene;
    public Welcome() {
    	AnchorPane ap = new AnchorPane();
    	imageView = new ImageView();
        rectangle = new Rectangle();
        button = new Button();
        imageView0 = new ImageView();
        button1 = new Button();

        radioButton = new RadioButton();
        radioButton0 = new RadioButton();
        radioButton1 = new RadioButton();
        
        
        
        ap.setMaxHeight(AnchorPane.USE_PREF_SIZE);
        ap.setMaxWidth(AnchorPane.USE_PREF_SIZE);
        ap.setMinHeight(AnchorPane.USE_PREF_SIZE);
        ap.setMinWidth(AnchorPane.USE_PREF_SIZE);
        ap.setPrefHeight(400.0);
        ap.setPrefWidth(600.0);

        imageView.setFitHeight(600.0);
        imageView.setFitWidth(631.0);
        imageView.setLayoutX(0.0);
        imageView.setLayoutY(0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("brain.jpg").toExternalForm()));

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.valueOf("#ffffffe3"));
        rectangle.setHeight(140.0);
        rectangle.setLayoutX(250.0);
        rectangle.setLayoutY(150.0);
        rectangle.setStroke(javafx.scene.paint.Color.WHITE);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(149.0);

        button.setLayoutX(45.0);
        button.setLayoutY(100.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(34.0);
        button.setPrefWidth(67.0);
        button.setText("Build");

        
        button1.setLayoutX(45.0);
        button1.setLayoutY(150.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(34.0);
        button1.setPrefWidth(67.0);
        button1.setText("Load");
        
        
        ToggleGroup tg = new ToggleGroup();
        radioButton.setLayoutX(50.0);
        radioButton.setLayoutY(190.0);
        radioButton.setMnemonicParsing(false);
       // radioButton.setText("Mean Difference Error");
        radioButton.setText("Zoning");
        radioButton.setTextFill(Color.WHITE);
        radioButton.setToggleGroup(tg);
        radioButton.setSelected(true);
        
        radioButton0.setLayoutX(50.0);
        radioButton0.setLayoutY(210.0);
        radioButton0.setMnemonicParsing(false);
      //  radioButton0.setText("Mean Absolute Error");
        radioButton0.setText("Diagonal");
        radioButton0.setTextFill(Color.WHITE);
        radioButton0.setToggleGroup(tg);

        
        radioButton1.setLayoutX(50.0);
        radioButton1.setLayoutY(230.0);
        radioButton1.setMnemonicParsing(false);
       // radioButton1.setText("Mean Square Error");
        radioButton1.setText("Histogram");

        radioButton1.setTextFill(Color.WHITE);
        radioButton1.setToggleGroup(tg);

        
        
        imageView0.setFitHeight(70.0);
        imageView0.setFitWidth(110.0);
        imageView0.setLayoutX(25.0);
        imageView0.setLayoutY(40.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("cooltext.png").toExternalForm()));

        ap.getChildren().add(imageView);
       ap.getChildren().add(button1);
        ap.getChildren().add(button);
        ap.getChildren().add(imageView0);
        ap.getChildren().add(radioButton);
        ap.getChildren().add(radioButton0);
        ap.getChildren().add(radioButton1);

        scene = new Scene(ap);
    }
    public  Scene getScene() {
    	return scene;
    }
    public Button getButton() {
    	return button;
    }
    public Button getButton2() {
    	return button1;
    }
}
