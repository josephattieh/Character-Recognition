package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {
	int features;

	public void start(Stage stage) throws FileNotFoundException {
		Welcome w = new Welcome();
		Scene s1 = w.getScene();
		s1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Settings1 set1 = new Settings1();
		stage.setScene(s1);
		w.getButton().pressedProperty().addListener(e->{
			Scene s2 = set1.getScene();
			s2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(s2);
		});
		FileChooser fileChooser = new FileChooser();
		w.getButton2().setOnAction(e->{
			int i=0;
			ArrayList<Integer> in = new ArrayList<>();
			in.add(1);
			ANN ann = new ANN(in, 1, 1, 1, 40);
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(s1.getWindow());
			if(file!=null) {
			try {
				ann.loadNetwork(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(w.radioButton.isSelected()) {
				//MDE
				features=0;
			}else if(w.radioButton0.isSelected()) {
				features=1; //MAE
			}else {
				features=2;
			}
			Design dd = new Design(ann, stage, features);
			Scene ss = dd.getScene();
			ss.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(dd.getScene());}
		});
		set1.getButton().pressedProperty().addListener(e->{
			//nbr of layers
			if(set1.spinner0.getValue()!=0)
			{
			int l =set1.spinner0.getValue();
			ArrayList<Integer> list = new ArrayList<>();
			for(Spinner<Integer> s :set1.list) {
				list.add(s.getValue());
			}
			
			
			//nbr of inputs
			int nbrInput = set1.spinner1.getValue();
			//Error
			int errfct;
			if(set1.radioButton.isSelected()) {
				//MDE
				errfct=0;
			}else if(set1.radioButton0.isSelected()) {
				errfct=1; //MAE
			}else {
				errfct=2;
			}
			//Thresshhold
			double threshold = set1.slider.getValue();

			//Nbr of Iterations
			int iteration = set1.spinner.getValue();
			
			ANN ann =new ANN(list, nbrInput,threshold,errfct, iteration);
			
			Design d=  new Design(ann, stage,features);
			Scene s3 = d.getScene();
			s3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(d.getScene());
			
			}
		});
		
		stage.setTitle("ANN");
		stage.getIcons().add(new Image(getClass().getResource("nn.png").toExternalForm())); 

		stage.show();	
		stage.setResizable(false);

	}


	public static void main(String[] args) {
		launch(args);
	}
	
}
