package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class FXMLController {
	@FXML private Label label1;
	@FXML private BorderPane mainPane;
	
	@FXML protected void mouseEnteredKek(){
		label1.setText("mouseEntered");
		System.out.println("enter");
	}
	
	@FXML protected void mouseExitedKek(){
		label1.setText("");
		System.out.println("exit");
	}
}
