package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.stage.Stage;


public class Demo extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		
		File f = new File("C:\\Users\\Joseph Attieh\\Desktop\\Set1.txt");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		
		DecimalFormat fmt = new DecimalFormat("0.000");
		for(int i=0; i<=360;i++)
			pw.println("("+i+"),("+fmt.format(Math.sin(Math.toRadians(i)))+");");
		pw.close();
		

	}


	public static void main(String[] args) throws Exception {
		launch(args);
		
	}
	
}
