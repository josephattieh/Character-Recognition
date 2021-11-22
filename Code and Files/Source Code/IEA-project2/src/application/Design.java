package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Design {
	protected Button button;
	protected Button button0;
Button button1;
	public Scene scene;

	public Design(ANN ann, Stage stage, int feature) {
		try {
			Group root = new Group();
			scene = new Scene(root, 600, 600);
			button = new Button();
			button0 = new Button();
			button1 = new Button();
			ArrayList<Circle> nodes = new ArrayList<>();
			ArrayList<Circle> in = new ArrayList<>();
			ArrayList<Circle> out = new ArrayList<>();

			AnchorPane ap = new AnchorPane();
			ap.setMaxHeight(AnchorPane.USE_PREF_SIZE);
			ap.setMaxWidth(AnchorPane.USE_PREF_SIZE);
			ap.setMinHeight(AnchorPane.USE_PREF_SIZE);
			ap.setMinWidth(AnchorPane.USE_PREF_SIZE);
			ap.setPrefHeight(400.0);
			ap.setPrefWidth(600.0);

			ScrollPane s1 = new ScrollPane();
			s1.setPannable(true);

			Group r = new Group();
			ArrayList<Double> listX = new ArrayList<>();
			ArrayList<Double> listY = new ArrayList<>();

			s1.setLayoutX(8.0);
			s1.setLayoutY(7.0);
			s1.setPrefHeight(320.0);
			s1.setPrefWidth(584.0);

			int maxy = 0;
			int x0 = 100;
			int y0 = 100;
			int x = x0;
			int y = y0;
			int sx = 150;
			int sy = 70;
			int rad = 30;
			int nbrInputs = ann.layers.get(0).get(0).inputNbr;
			InnerShadow innerShadow = new InnerShadow();
			// Setting the offset values of the inner shadow
			innerShadow.setOffsetX(4);
			innerShadow.setOffsetY(4);
			// Setting the color of the inner shadow
			innerShadow.setColor(Color.GRAY);
			for (int i = 0; i < nbrInputs; i++) {
				Circle c = new Circle(x + 20, y, 10);
				c.setFill(Color.LIGHTCYAN);

				c.setStroke(Color.BLUE);
				in.add(c);
				r.getChildren().add(c);
				listX.add(x / 1.0);
				listY.add(y / 1.0);
				y += sy;
				if (maxy < y)
					maxy = y;
			}
			x += sx;
			for (int i = 0; i < ann.layers.size(); i++) {
				y = y0;
				// layer first
				for (int j = 0; j < ann.layers.get(i).size(); j++) {
					// perceptrons
					listX.add(x / 1.0);
					listY.add(y / 1.0);
					Circle c = new Circle(x, y, rad);
					c.setFill(Color.CORNFLOWERBLUE);

					// Applying inner shadow effect to the circle
					c.setEffect(innerShadow);
					Text text = new Text("  f,θ");
					text.setTextAlignment(TextAlignment.RIGHT);

					StackPane stack = new StackPane();
					stack.getChildren().addAll(c, text);
					stack.setLayoutX(x - 15);
					stack.setLayoutY(y - 15);

					r.getChildren().add(c);
					r.getChildren().add(stack);
					System.out.println(x + " " + y);
					y += sy;
					nodes.add(c);

					if (maxy < y)
						maxy = y;
				}
				x += sx;

			}
			y = y0;
			x -= sx / 2;
			for (int i = 0; i < ann.layers.get(ann.layers.size() - 1).size(); i++) {
				Circle c = new Circle(x, y, 10);
				c.setFill(Color.MIDNIGHTBLUE);
				c.setEffect(innerShadow);
				c.setStroke(Color.BLUE);
				r.getChildren().add(c);
				r.getChildren().add(new Line(x - sx / 2 + rad, y, x - 10, y));
				out.add(c);
				y += sy;
			}
			for (int i = 0; i < listX.size(); i++) {
				for (int j = 0; j < listX.size(); j++) {
					if (listX.get(i) != listX.get(j) && listY.get(i) != listY.get(j)) {
						if (listX.get(i) < listX.get(j) && listX.get(i) == (listX.get(j) - sx)) {
							r.getChildren()
									.add(new Line(listX.get(i) + rad, listY.get(i), listX.get(j) - rad, listY.get(j)));

						}
					}
				}
			}

			for (int i = 0; i < nodes.size(); i++) {
				int j = i;
				nodes.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

					 Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(stage);

					Perceptron p = getPerceptron(ann, j);
					StringBuilder sb = new StringBuilder();
					for (int k = 0; k < p.weights.size(); k++)
						sb.append(" \n w" + (k + 1) + " : " + p.weights.get(k));

					int[] info = getPerceptronInfo(ann, j);
					Scene dialogScene = new PercGUI(p, info[0], info[1], dialog).getScene();
					dialogScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

					dialog.setScene(dialogScene);
					dialog.show();

				});
			}

			button.setLayoutX(65.0);
			button.setLayoutY(342.0);
			button.setMnemonicParsing(false);
			button.setPrefHeight(45.0);
			button.setPrefWidth(106.0);
			button.setText("Train");
			
			button1.setLayoutX(190.0);
			button1.setLayoutY(342.0);
			button1.setMnemonicParsing(false);
			button1.setPrefHeight(45.0);
			button1.setPrefWidth(106.0);
			button1.setText("Edit");
			
			button1.setOnAction(e->{
			Stage s= new Stage();	
			Scene ss = new Multiple(ann, s).getScene();
			s.setScene(ss);
			s.show();
			});
			button.setOnAction(e->{
				Training t = null;
					Stage s = new Stage();
					s.setTitle("Training");
					try{ t= new Training(ann,s,feature);}
					catch(Exception a) {}
					Scene ss1;
					if(t!=null)
						
					{ ss1 = t.getScene();
					ss1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

						s.setScene(ss1);
					
					s.show();
					s.setResizable(false);

					}System.out.println("PRESSED");
				
			});
			button0.setLayoutX(315.0);
			button0.setLayoutY(342.0);
			button0.setMnemonicParsing(false);
			button0.setPrefHeight(45.0);
			button0.setPrefWidth(106.0);
			button0.setText("Test");
			button0.setOnAction(e->{
				Stage s = new Stage();
				Scene s2 = new Testing(ann, feature).getScene();
				s2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				s.setTitle("Testing");
				s.setScene(s2);
			
				s.setResizable(false);

				s.show();
				
			});
			s1.setContent(r);
			s1.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			s1.setHbarPolicy(ScrollBarPolicy.ALWAYS);
			Button b1 = new Button("Load");
			Button b2 = new Button("Save");
			b1.setLayoutX(30.0);
			b1.setLayoutY(342.0);
			b1.setPrefHeight(45.0);
			b1.setPrefWidth(106.0);
			b2.setLayoutX(440.0);
			b2.setLayoutY(342.0);
			b2.setPrefHeight(45.0);
			b2.setPrefWidth(106.0);
			FileChooser fileChooser = new FileChooser();

			b1.setOnAction(e->{
				
						FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
						fileChooser.getExtensionFilters().add(extFilter);
						File file = fileChooser.showOpenDialog(scene.getWindow());
						if (file != null) {
							try {
								ann.loadNetwork(file);
								Design d = new Design(ann, stage, feature);
								d.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								stage.setScene(d.getScene());
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
			});
			b2.setOnAction(e->{
			try {
				ann.saveNetwork("ANN");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
				
			});
			//ap.getChildren().add(b1);
			ap.getChildren().add(b2);
			ap.getChildren().add(button);
			ap.getChildren().add(button0);
			ap.getChildren().add(s1);
			ap.getChildren().add(button1);
			// root.getChildren().add(s1);
			// root.getChildren().add(button);
			// root.getChildren().add(button0);

			scene = new Scene(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Perceptron getPerceptron(ANN a, int nbr) {
		int k = 0;
		for (int i = 0; i < a.layers.size(); i++) {
			for (int j = 0; j < a.layers.get(i).size(); j++) {
				if (k == nbr)
					return a.layers.get(i).get(j);

				k++;

			}
		}
		return null;
	}

	public int[] getPerceptronInfo(ANN a, int nbr) {
		int k = 0;
		for (int i = 0; i < a.layers.size(); i++) {
			for (int j = 0; j < a.layers.get(i).size(); j++) {
				if (k == nbr) {
					int[] toR = new int[2];
					toR[0] = i;
					toR[1] = j;
					return toR;
				}

				k++;

			}
		}
		return null;
	}
	public Button getButton() {
		return button;
	}
	public Scene getScene() {
		return scene;
	}
}