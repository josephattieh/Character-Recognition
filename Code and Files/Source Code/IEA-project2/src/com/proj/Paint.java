package com.proj;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Paint  {
    Scene scene;
    RenderedImage renderedImage;
   public Paint(Stage stage) {
        
                        
        ToggleButton drowbtn = new ToggleButton("Draw");
        ToggleButton rubberbtn = new ToggleButton("Rubber");
        ToggleButton[] toolsArr = {drowbtn, rubberbtn};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        Slider slider = new Slider(1, 4, 0.5);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        Label line_width = new Label("1.0");
        Button save = new Button("Save");
        Button[] basicArr = {save};
        for(Button btn : basicArr) {
            btn.setMinWidth(90);
            btn.setCursor(Cursor.HAND);
            btn.setTextFill(Color.WHITE);
            btn.setStyle("-fx-background-color: #666;");
        }
        save.setStyle("-fx-background-color: #80334d;");
        Button clr = new Button("Clear");
        VBox btns = new VBox(10);
        btns.getChildren().addAll(drowbtn, rubberbtn,   line_width, slider,  save,clr);
        btns.setPadding(new Insets(5));
        btns.setStyle("-fx-background-color: #999");
        btns.setPrefWidth(100);
        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc;
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);
   
                        
        canvas.setOnMousePressed(e->{
            if(drowbtn.isSelected()) {
                gc.beginPath();
                gc.lineTo(e.getX(), e.getY());
            }
            else if(rubberbtn.isSelected()) {
                double lineWidth = gc.getLineWidth();
                gc.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
            
        });
        
        canvas.setOnMouseDragged(e->{
            if(drowbtn.isSelected()) {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
            }
            else if(rubberbtn.isSelected()){
                double lineWidth = gc.getLineWidth();
                gc.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
        });
        
        canvas.setOnMouseReleased(e->{
            if(drowbtn.isSelected()) {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
                gc.closePath();
            }
            else if(rubberbtn.isSelected()) {
                double lineWidth = gc.getLineWidth();
                gc.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
            
            

            
        });
        
  clr.setOnAction(e->{
	  	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        });
        gc.setStroke(Color.BLACK);
        // slider
        slider.valueProperty().addListener(e->{
            double width = slider.getValue();
            
            line_width.setText(String.format("%.1f", width));
            gc.setLineWidth(width);
        });
        
       
        
        // Save
        save.setOnAction((e)->{
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