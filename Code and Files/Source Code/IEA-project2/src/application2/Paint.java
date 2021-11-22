package application2;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Paint {
	Scene scene;
	RenderedImage renderedImage;

	public Paint(Stage stage) {

		ImageView im0 = new ImageView(new Image(getClass().getResourceAsStream("draw3.png")));
		im0.setFitHeight(30);
		im0.setFitWidth(35);

		ImageView im01 = new ImageView(new Image(getClass().getResourceAsStream("draw4.png")));
		im01.setFitHeight(30);
		im01.setFitWidth(35);

		ImageView im1 = new ImageView(new Image(getClass().getResourceAsStream("rubber.png")));
		im1.setFitHeight(30);
		im1.setFitWidth(35);

		ImageView im11 = new ImageView(new Image(getClass().getResourceAsStream("rubber2.png")));
		im11.setFitHeight(30);
		im11.setFitWidth(35);

		ImageView im2 = new ImageView(new Image(getClass().getResourceAsStream("save.png")));
		im2.setFitHeight(30);
		im2.setFitWidth(35);

		ImageView im21 = new ImageView(new Image(getClass().getResourceAsStream("save2.png")));
		im21.setFitHeight(30);
		im21.setFitWidth(35);

		ImageView im3 = new ImageView(new Image(getClass().getResourceAsStream("clear.png")));
		im3.setFitHeight(30);
		im3.setFitWidth(35);

		ImageView im31 = new ImageView(new Image(getClass().getResourceAsStream("clear2.png")));
		im31.setFitHeight(30);
		im31.setFitWidth(35);

		ToggleButton drowbtn = new ToggleButton("Draw", im01);
		drowbtn.setLayoutX(0);
		drowbtn.setPrefSize(150, 50);
		drowbtn.setSelected(true);
		drowbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");

		ToggleButton rubberbtn = new ToggleButton("Rubber", im11);
		rubberbtn.setPrefSize(150, 50);
		rubberbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");


		ToggleButton[] toolsArr = { drowbtn, rubberbtn };
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		Slider slider = new Slider(2, 4, 0.5);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setValue(4);

		Label line_width = new Label("1.0");
		Button save = new Button("Save", im21);
		save.setLayoutX(0);
		save.setPrefSize(150, 50);
		save.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");
		
		Button[] basicArr = { save };
		for (Button btn : basicArr) {
			btn.setMinWidth(90);
			btn.setCursor(Cursor.HAND);
			btn.setTextFill(Color.WHITE);
			btn.setStyle("-fx-background-color: CORNFLOWERBLUE;");
		}
		// save.setStyle("-fx-background-color: rgba(0,0,139,0.7);");
		
		
		Button clr = new Button("Clear", im31);
		clr.setPrefSize(150, 50);
		clr.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");
		
		
		
		if(drowbtn.isSelected()){
			drowbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");
			rubberbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");

		}
		if(rubberbtn.isSelected()){
			rubberbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");
			drowbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");

		}
		
		
		
		RotateTransition rt = new RotateTransition(Duration.millis(1000),im0);
		rt.setFromAngle(20);
		rt.setToAngle(-40);
        rt.setAutoReverse(true);
        
		drowbtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				drowbtn.setGraphic(im0);
				rt.play();
				drowbtn.setStyle("-fx-background-color: #ffffff;" +" -fx-text-fill: BLACK");
				if(drowbtn.isSelected()){
					drowbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");

				}
				if(rubberbtn.isSelected()){
					rubberbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");

				}
			}
		});
		
		
		
		drowbtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				drowbtn.setGraphic(im01);
				drowbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");
				if(drowbtn.isSelected()){
					drowbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");
					rubberbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");

				}
				if(rubberbtn.isSelected()){
					rubberbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");
					drowbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");

				}
			}
		});
		
		 TranslateTransition tt = new TranslateTransition(Duration.millis(1000),im1);
		    tt.setFromX(-15f);
		    tt.setToX(2f);
		    tt.setCycleCount(2);
		    tt.setAutoReverse(true);
		
		rubberbtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				rubberbtn.setGraphic(im1);
				tt.play();

				rubberbtn.setStyle("-fx-background-color: #ffffff;" +" -fx-text-fill: BLACK");
				if(drowbtn.isSelected()){
					drowbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");

				}
				if(rubberbtn.isSelected()){
					rubberbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");

				}
				
			}
			
		});

		rubberbtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				rubberbtn.setGraphic(im11);
				rubberbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");
				if(drowbtn.isSelected()){
					drowbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");
					rubberbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");

				}
				if(rubberbtn.isSelected()){
					rubberbtn.setStyle("-fx-background-color: #d3d3d3;" +" -fx-text-fill: WHITE;");
					drowbtn.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");

				}
			}
		});
		
		
		
		
		
		
		clr.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				clr.setGraphic(im3);
				clr.setStyle("-fx-background-color: #ffffff;" +" -fx-text-fill: BLACK");

			}
		});

		clr.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				clr.setGraphic(im31);
				clr.setStyle("-fx-background-color: #313031;" +" -fx-text-fill: WHITE");

			}
		});
		
		
		
		
		
		VBox btns = new VBox(0);
		btns.setMinWidth(150);
		btns.getChildren().addAll(drowbtn, rubberbtn, line_width, slider, clr, save);
		btns.setPadding(new Insets(5));
		btns.setStyle("-fx-background-color: #313031");
		btns.setPrefWidth(100);
		
		
		
		
		   
		
       
    	
		
		Canvas canvas = new Canvas(600, 400);
		GraphicsContext gc;
		gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(4);

		canvas.setOnMousePressed(e -> {
			if (drowbtn.isSelected()) {
				gc.beginPath();
				gc.lineTo(e.getX(), e.getY());
			} else if (rubberbtn.isSelected()) {
				double lineWidth = gc.getLineWidth();
				gc.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
			}

		});

		canvas.setOnMouseDragged(e -> {
			if (drowbtn.isSelected()) {
				gc.lineTo(e.getX(), e.getY());
				gc.stroke();
			} else if (rubberbtn.isSelected()) {
				double lineWidth = gc.getLineWidth();
				gc.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
			}
		});

		canvas.setOnMouseReleased(e -> {
			if (drowbtn.isSelected()) {
				gc.lineTo(e.getX(), e.getY());
				gc.stroke();
				gc.closePath();
			} else if (rubberbtn.isSelected()) {
				double lineWidth = gc.getLineWidth();
				gc.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
			}

		});

		
		clr.setOnAction(e -> {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		});
		gc.setStroke(Color.BLACK);
		// slider
		slider.valueProperty().addListener(e -> {
			double width = slider.getValue();
			line_width.setText(String.format("%.1f", width));
			gc.setLineWidth(width);
		});
		
		// Save
		save.setOnAction((e) -> {
			WritableImage writableImage = new WritableImage(1080, 790);
			canvas.snapshot(null, writableImage);
			renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
			try {
				ImageIO.write(renderedImage, "png", new File("Img.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			stage.close();

		});

		/* ----------STAGE & SCENE---------- */
		BorderPane pane = new BorderPane();
		pane.setLeft(btns);
		pane.setCenter(canvas);

		scene = new Scene(pane, 600, 400);

	}

	public Scene getScene() {
		return scene;
	}

	public RenderedImage getImg() {
		return renderedImage;
	}
}