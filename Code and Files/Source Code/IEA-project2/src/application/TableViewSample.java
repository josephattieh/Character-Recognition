package application;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableViewSample extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        StackPane root = new StackPane();
        String[][] staffArray = getArray(new File("C:\\Users\\Joseph Attieh\\Desktop\\TEST.txt"));
        for(int i =0; i<staffArray.length;i++) {
        	for(int j=0; j<staffArray[i].length;j++) {
        		System.out.println(staffArray[i][j]);
        	}
        	System.out.println();
        }
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(staffArray));
        data.remove(0);//remove titles from data
        TableView<String[]> table = new TableView<>();
        for (int i = 0; i < staffArray[0].length; i++) {
            TableColumn tc = new TableColumn(staffArray[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }
        table.setItems(data);
        root.getChildren().add(table);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    public static void main(String[] args) {
		launch(args);
	}
    public static String[][] getArray(File f) throws FileNotFoundException{
    	Scanner scan = new Scanner(f);
    	ArrayList<String> a1 = new ArrayList<>();
    	ArrayList<String> a2= new ArrayList<>();
    	ArrayList<String> a3= new ArrayList<>();
    	StringBuilder sb = new StringBuilder();
    	sb.append("),");
    	while(scan.hasNextLine()) {
    		sb.append(scan.nextLine().replace(" ", ""));
    	}
    	sb.append("(");
    	System.out.println(sb.toString().replace(";",",").replace(" ", ""));
    	String sr = sb.toString().replace(";",",").replace(" ", "");
    	String [] inputs = sr.split(Pattern.quote("),("));
    
    	
    	for(int i=1;i<inputs.length;i++) {
    		if(i%2!=0) {
    			a2.add(inputs[i]);
    		}else {
    			
    			a3.add(inputs[i]);
    		}
    	}
    	
    	for(int i=0; i<a2.size();i++)
    		a1.add(""+(i+1));
    	String [][] st = new String[a1.size()+1][3];
    	st[0][0]="#";
    	st[0][1]="Inputs";
    	st[0][2] ="Outputs";
    	for(int i=0; i<a1.size();i++) {
    		st[i+1][0] = a1.get(i);
    		st[i+1][1] = a2.get(i);
    		st[i+1][2] = a3.get(i);

    	}
    		
    	return st;
    }
}