package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.CharacterSequenceGenerator;

public class Main extends Application {
	
	private CharacterSequenceGenerator sg;

	@Override
	public void start(Stage primaryStage) {
		System.out.println(System.nanoTime() + ": " + Main.class.getName() + " started");
		
		try {
			sg = new CharacterSequenceGenerator();
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			Scene scene = new Scene(root,200,100);
/*
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
*/
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop(){
		System.out.println(System.nanoTime() + ": " + Main.class.getName() + " stopped");	
	}
	
	public static void main(String[] args) {			
		launch(args);
	}

}
