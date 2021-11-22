package application2;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Welcome w = new Welcome();
		Scene s1 = w.getScene();
		stage.setScene(s1);

		s1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		w.getButton().pressedProperty().addListener(e -> {
			// Trackpad t= new Trackpad();
			TestingLetter t;

			try {
				t = new TestingLetter();
				Scene s2 = t.getScene();
				stage.setScene(s2);
				s2.getStylesheets().add(getClass().getResource("application2.css").toExternalForm());

			} catch (IOException e1) {
				
			}

		});

		stage.setTitle("Letter Recognition");
		stage.getIcons().add(new Image(getClass().getResource("icon.jpg").toExternalForm()));

		stage.show();
		stage.setResizable(false);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
