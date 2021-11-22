package application2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Welcome {

    ImageView imageView;
    Button button;
    ImageView imageView0;
    Rectangle rectangle;
    Scene scene;
    public Welcome() {
    	AnchorPane ap = new AnchorPane();
    	imageView = new ImageView();
        rectangle = new Rectangle();
        button = new Button();
        imageView0 = new ImageView();

        
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
        imageView.setImage(new Image(getClass().getResource("comp.jpg").toExternalForm()));

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.valueOf("#ffffffe3"));
        rectangle.setHeight(140.0);
        rectangle.setLayoutX(250.0);
        rectangle.setLayoutY(150.0);
        rectangle.setStroke(javafx.scene.paint.Color.WHITE);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(149.0);

        button.setLayoutX(270.0);
        button.setLayoutY(120.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(34.0);
        button.setPrefWidth(89.0);
        button.setText("START");

//        imageView0.setFitHeight(70.0);
//        imageView0.setFitWidth(110.0);
//        imageView0.setLayoutX(25.0);
//        imageView0.setLayoutY(40.0);
//        imageView0.setPickOnBounds(true);
//        imageView0.setPreserveRatio(true);
//        imageView0.setImage(new Image(getClass().getResource("cooltext.png").toExternalForm()));

        ap.getChildren().add(imageView);
//       ap.getChildren().add(rectangle);
        ap.getChildren().add(button);
//        ap.getChildren().add(imageView0);
    	
        scene = new Scene(ap);
    }
    public  Scene getScene() {
    	return scene;
    }
    public Button getButton() {
    	return button;
    }
}
