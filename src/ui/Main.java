package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Vistara;
import thread.ThreadRating;

public class Main extends Application{

	private VistaraGUI vistaraGUI;
	private Vistara vistara;
	private ThreadRating threadRating;
	
	public Main() {
		vistara = new Vistara();
		vistaraGUI = new VistaraGUI(vistara);
		threadRating = new ThreadRating(vistara);
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
		vistaraGUI.loadLogIn(null);
		//vistara.loadData();
		
		threadRating.start();
		threadRating.join();
	}
	
	public ThreadRating getThreadRating() {
		return threadRating;
	}

}
