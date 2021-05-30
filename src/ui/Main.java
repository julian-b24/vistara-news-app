package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Vistara;

public class Main extends Application{

	private VistaraGUI vistaraGUI;
	private Vistara vistara;
	public Main() {
		vistara = new Vistara();
		vistaraGUI = new VistaraGUI(vistara);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		
		fxmlLoader.setController(vistaraGUI);
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Vistara");
		primaryStage.show();
		//vistaraGUI.loadLogIn(null);
	}

}
