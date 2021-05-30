package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.jar.Attributes.Name;

import javax.swing.event.AncestorEvent;

import com.jfoenix.controls.JFXScrollPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Post;

public class ProfileController implements Initializable{

	private List<Post> usersFeed;
	
	 @FXML
	 private JFXScrollPane scroll;

	 @FXML
	 private GridPane grid;
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		usersFeed = new ArrayList<>(usersFeed());
		int row = 0;
		int column = 0;
		
		try {
			for (int i = 0; i < usersFeed.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("ui/post-pane.fxml"));
					
				PostController postController = fxmlLoader.getController();
				postController.setData(usersFeed.get(i));
				
				if(column == 2) {
					column = 0;
					row ++;
				}
				
					AnchorPane anchorPane = fxmlLoader.load();
					grid.add(anchorPane, column++, row);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
	}

	private List<Post> usersFeed(){
		
		List<Post> lp = new ArrayList<>();
		String userN = "UsernameX";
		String pTit = "Post Titlte";
		String dets = "aaaaaaaaaaaaaaaaaaaaaaa";
		Post post = new Post(userN, pTit, dets);
		lp.add(post);
		
		return null;
	}
}
