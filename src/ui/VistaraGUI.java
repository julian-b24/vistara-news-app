package ui;

import model.Vistara;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class VistaraGUI {

	private Vistara vistara;
	
	@FXML
    private AnchorPane mainPane;
	
	
	public VistaraGUI(Vistara app){
		vistara = app;
	}
	
	
}
