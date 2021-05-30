package ui;

import model.Vistara;

import java.io.IOException;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;


public class VistaraGUI {

	private Vistara vistara;
	
	//model fields
	//private User currentUser;
	//private Post currentPost;
	
	//Main pane
	@FXML
    private AnchorPane mainPane;
	
	//Login
	@FXML
    private JFXTextField userNameLogin;

    @FXML
    private JFXTextField passwordLogin;
	
    //Feed
    @FXML
    private AnchorPane profileBarPane;

    @FXML
    private AnchorPane feedPane;

    @FXML
    private AnchorPane commentsPane;
    
	public VistaraGUI(Vistara app){
		vistara = app;
	}
	
	//Profile bar
	@FXML
    private Circle profileCircleBar;

    @FXML
    private Label txtUsernameBar;

    @FXML
    private Label txtBioUserBar;

    @FXML
    private Label txtFollowersBar;

    @FXML
    private Label txtFollowingBar;
    
    //Comments bar
    @FXML
    private AnchorPane commentsScroll;
    
    @FXML
    private JFXTextField txtNewComment;
    
    @FXML
    private JFXTextArea txtNoComments;

    @FXML
    private FontAwesomeIconView iconNoComments;
	
	@FXML
    public void loadLogIn(ActionEvent event) {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-pane.fxml"));
			fxmlLoader.setController(this);
			Parent login = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(login);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	public void loadSignUp(MouseEvent event) {
		
    }

    @FXML
    public void login(ActionEvent event) {
    	
    	//Gets and verify data
    	boolean logged = true;
    	if(logged) {
    		//currentUser = getUserByUserName();
    		loadFeed(null);
    	}
    	
    }

    @FXML
	public void loadFeed(Object object) {
		
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("feed-pane.fxml"));
			fxmlLoader.setController(this);
			Parent feedFXML = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(feedFXML);
			
			loadProfileBar();
			loadComments();
			//loadFeedPosts();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	private void loadProfileBar() {
		try {
			
			FXMLLoader fxmlLoader;
			
			//boolea is Mod = vistara.isMod(currentUser);
			boolean isMod = false;
			
			if(isMod) {
				fxmlLoader = new FXMLLoader(getClass().getResource("profile-bar-mod.fxml"));
			}else {
				fxmlLoader = new FXMLLoader(getClass().getResource("profile-bar-user.fxml"));
			}
			
			fxmlLoader.setController(this);
			Parent profileBar = fxmlLoader.load();
			
			profileBarPane.getChildren().clear();
			profileBarPane.getChildren().setAll(profileBar);
			setProfileBarInfo();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadComments() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("comments-bar-pane.fxml"));
			fxmlLoader.setController(this);
			Parent feedFXML = fxmlLoader.load();
			
			commentsPane.getChildren().clear();
			commentsPane.getChildren().setAll(feedFXML);
			loadCommentsOfPost();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadCommentsOfPost() {
		//Loads the comments of the currentPost
		
		int amountComments = 1;
		if(amountComments > 0) {
			//load all the comments
			iconNoComments.setVisible(false);
			txtNoComments.setVisible(false);
		}
		
	}

	private void setProfileBarInfo() {
		//Loads the info of the current user in the bar
	}
	
	@FXML
    public void addComment(ActionEvent event) {
		//Add a new comment to the currentPost
    }
	
}
