package ui;

import model.Vistara;

import java.io.IOException;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import exceptions.EmptyFieldsException;
import exceptions.RepeatedUsernameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
	
	@FXML
    private AnchorPane profilePane;
	
	@FXML
    private AnchorPane postsPane;

    @FXML
    private AnchorPane statisticsPane;

    @FXML
    private AnchorPane reactedPane;
	
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
	
    //sign up
    @FXML
    private JFXTextField usernameSignUp;

    @FXML
    private JFXTextField emailSignUp;

    @FXML
    private JFXTextField passwordSignUp;
   
    @FXML
    private AnchorPane secondaryPane;
    
    
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
    public void login(ActionEvent event) {
    	
    	//verify that fields are not empty
    	
    	//verify that username or email exists
        //vistara.verifyAccount(userNameLogin.getText().trim(), passwordLogin.getText().trim());
        //verify that password matches email or username
    	
    	
    	
    	loadFeed(null);
    	
    	//Gets and verify data
    	boolean logged = true;
    	if(logged) {
    		//currentUser = getUserByUserName();
    		loadFeed(null);
    	}
    
    }

    @FXML
	public void loadFeed() {
    	loadFeed(null);
    }
    
    @FXML
	public void loadFeed(ActionEvent event) {
		
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
    
    @FXML
    void loadSignUp(ActionEvent event) {

    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign-up-pane.fxml"));
			fxmlLoader.setController(this);
			Parent signup = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(signup);
			
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
	
	@FXML
	void createAccount(ActionEvent event) {
		try {
			vistara.addUser(usernameSignUp.getText().trim(), emailSignUp.getText().trim(), passwordSignUp.getText().trim());
		} catch (RepeatedUsernameException e) {
			//WARNING
		} catch (EmptyFieldsException e) {
			// WARNING
		}
	}

	 
	@FXML
	void loadProfile(ActionEvent event) {
	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(profile);
			
			//mainPane.getChildren().clear();
			//mainPane.getChildren().setAll(login);
			loadProfileBar();
			loadProfileTabPane();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void loadProfileTabPane() {
	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-tab-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
			//mainPane.getChildren().clear();
			//mainPane.getChildren().setAll(login);			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    void loadReactedPosts(ActionEvent event) {

		
    }

    @FXML
    void loadStatistics(ActionEvent event) {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-stats-pane.fxml"));
			fxmlLoader.setController(this);
			Parent statistics = fxmlLoader.load();
			
			statisticsPane.getChildren().clear();
			statisticsPane.getChildren().setAll(statistics);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void loadTrending() {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(profile);
			
			loadProfileBar();
			loadTrendingTab();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void loadTrendingTab() {
    	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trending-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
    void loadCalendar() {
    	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(profile);
			
			loadProfileBar();
			loadCalendartab();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void loadCalendartab() {
		
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("calendar-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
    void loadCreatePost() {
    	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(profile);
			
			loadProfileBar();
			loadNewPostTab();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private void loadNewPostTab() {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-post-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void removeEvent() {
		
	}
	
	@FXML
    void addPhotoToPost(ActionEvent event) {

    }

    @FXML
    void uploadPost(ActionEvent event) {

    }
    
    @FXML
    void loadEditProfile(ActionEvent event) {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(profile);
			
			loadProfileBar();
			loadEditProfileTab();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void loadEditProfileTab() {
		
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit-profile-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
    void changeProfilePic(ActionEvent event) {

    }

    @FXML
    void confirmPorfileEdition(ActionEvent event) {

    }
}
