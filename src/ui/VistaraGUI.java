package ui;

import model.Moderator;
import model.Post;
import model.State;
import model.User;
import model.Vistara;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUsernameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class VistaraGUI {
	
	public final static int MAX_TITLE_LENGTH = 70;
	public final static int MAX_CONTENT_LENGTH = 500;

	private Vistara vistara;
	
	//model fields
	private User currentUser;
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
   
    @FXML
    private TextField serachUser;

    @FXML
    private ChoiceBox<String> filterCategory;
    
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
    
    //stats pane
    @FXML
    private Pane averagesPane;

    @FXML
    private Label txtAverageReactionsGeneral;

    @FXML
    private Label txtAverageReactionsAmount;

    @FXML
    private Label txtAverageCommentsAmount;

    @FXML
    private Pane graphTimePane;

    @FXML
    private Label txtYourReactions;

    @FXML
    private Label txtYourComments;
    
    //general feed
    @FXML
    private GridPane postGrid;
    
    //verifyPosyt
    @FXML
    private AnchorPane postVerifyPost;
    
    @FXML
    private JFXRadioButton verifiedNew;

    @FXML
    private JFXRadioButton fakeNew;
    
    //create category
    
    @FXML
    private JFXTextField newCategoryName;

    @FXML
    private JFXTextField newCategoryImagePath;
    
    //create post
    
    @FXML
    private ChoiceBox<String> postCategory;
    
    @FXML
    private JFXTextArea postLink;
    
    @FXML
    private JFXTextArea postTittle;

    @FXML
    private JFXTextArea postDetails;

    @FXML
    private Label postImagePath;

    //
    @FXML
    private JFXButton upgradeBtn;
    
    //search user

    @FXML
    private AnchorPane searchPane;

    @FXML
    private Label searchedUsername;

    @FXML
    private Label searchedNumPosts;

    @FXML
    private Label followingText;
    
    @FXML
    private Label searchedNumVerPosts;

    @FXML
    private Label searchedNumFollowers;

    @FXML
    private Label searchedNumFollowing;
    
    @FXML
    private JFXButton upgradeModBtn;
    
    @FXML
    private AnchorPane profileSearchPane;
    
    @FXML
    private JFXButton followBtnText;
    
    @FXML
    private Label searchedNumFakePosts;
    
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
    	
    	User loggedUser;
    	//verify that fields are not empty
    	try {
    		loggedUser = vistara.verifyLogin(userNameLogin.getText().trim(), passwordLogin.getText().trim());
    		
        	//verify account type        	
        	if(loggedUser != null) {
        		currentUser = loggedUser;
        		vistara.addCategory("f");
        		vistara.addCategory("g");
        		vistara.addCategory("d");
        		vistara.addCategory("e");
        		vistara.addCategory("b");
        		vistara.addCategory("h");
        		vistara.addCategory("a");
        		
        		loadFeed(null);
        		
        		
        		
    			if(currentUser instanceof Moderator) {
    				//TEST
    				LocalDateTime lc = LocalDateTime.now();
    				Post postx = new Post(currentUser, "A", "CONTNET 1", null, lc, "www.1");
    				
    				((Moderator) currentUser).getPendingPosts().add(postx);
    				
    				postx = new Post(currentUser, "B", "CONTNET 2", null, lc, "www.2");
    				((Moderator) currentUser).getPendingPosts().add(postx);
    				System.out.println(((Moderator) currentUser).getPendingPosts().size());
    				//
    			}
        	}
        	
        	
        	
    	}catch(InvalidUserException e) {
    		invalidUsernameAlert();
    	}catch(EmptyFieldsException e) {
    		emptyFieldAlert();
    	}
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
			loadFeedPosts();
			
			//Load choicebox items
			ArrayList<String> categories = vistara.loadPossibleCategories();
			filterCategory.getItems().addAll(categories);			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadFeedPosts() {
    	
    	int columns = 0;
    	int rows = 1;
    	    	System.out.println(currentUser.getFeed().size());
    	try {
			for (int i = 0; i < currentUser.getFeed().size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
				
					AnchorPane postBox = fxmlLoader.load();	
					PostController postController = fxmlLoader.getController();
					postController.setData(currentUser.getFeed().get(i), vistara, currentUser);
					
					if(columns == 1) {
						 columns = 0;
						 rows++;
					}
					
					postGrid.add(postBox, columns++, rows);
			}
			
    	} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
    public void loadSignUp(ActionEvent event) {

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
			
			//boolean isMod = vistara.isMod(currentUser);
			boolean isMod = true;
			
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
	
	@FXML
    public void deletePost(ActionEvent event) {
		if(currentUser instanceof Moderator && ((Moderator) currentUser).getPendingPosts().size() > 0) {
			User creatorUser = ((Moderator) currentUser).getPendingPosts().get(0).getAuthor();
			LocalDateTime publicationTime = ((Moderator) currentUser).getPendingPosts().get(0).getDate();
			
			creatorUser.deletePost(publicationTime);
			vistara.reOrderModerators();
		}
		
    }

    @FXML
    public void verifyPost(ActionEvent event) {
    	if(currentUser instanceof Moderator) {
    		
    		State state = null;
    		
    		if(verifiedNew.isSelected()) {
    			state = State.VERIFIED;
    		}else if(fakeNew.isSelected()){
    			state = State.FAKE_NEW;
    		}
    		
    		if(state != null && ((Moderator) currentUser).getPendingPosts().size() > 0) {
    			//sets the state of the post to verified or fake
				((Moderator) currentUser).getPendingPosts().get(0).setState(state);
				
				//edit info of creator user
				User user = (((Moderator) currentUser).getPendingPosts().get(0).getAuthor());
				if(state == State.VERIFIED) {
					user.setVerifiedPosts(user.getVerifiedPosts()+1);
				}else if(state == State.FAKE_NEW) {
					user.setFakePosts(user.getFakePosts()+1);
				}
				
	    		((Moderator) currentUser).getPendingPosts().remove(0);
	    		
	    		showInfoAlert();
	    		vistara.reOrderModerators();
	    		
	    		loadVerifyPost(null);
    		}
    	}
    }
	
	private void showInfoAlert() {
		Alert warning = new Alert(AlertType.CONFIRMATION);
		warning.setTitle("Post verified successfully");
		warning.setContentText("The post has been verified");
		warning.showAndWait();		
	}
	
	private void genericSuccessMessage() {
		Alert warning = new Alert(AlertType.CONFIRMATION);
		warning.setTitle("Task done successfully");
		warning.setContentText("The task was completed");
		warning.showAndWait();		
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
		
		int amountComments = 0;
		if(amountComments > 0) {
			//load all the comments
			iconNoComments.setVisible(false);
			txtNoComments.setVisible(false);
		}
	}

	@FXML
    public void loadVerifyPost(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent verify = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(verify);
				
			loadProfileBar();
			loadVerifyPostTab();
			loadPostToVerify();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private void loadPostToVerify() {
		
		//Verify that there are elements in the list
		
		if(currentUser instanceof Moderator) {
			
			if(((Moderator) currentUser).getPendingPosts().size() > 0) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
				
				try {
					AnchorPane postToVerify = fxmlLoader.load();
					PostController postController = fxmlLoader.getController();					
					
					System.out.println(((Moderator) currentUser).getPendingPosts().get(0).getContent());
					postController.setData(((Moderator) currentUser).getPendingPosts().get(0), vistara, currentUser);
					
					postVerifyPost.getChildren().clear();
					postVerifyPost.getChildren().setAll(postToVerify);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
	}

	private void loadVerifyPostTab() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verify-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
    public void loadCreateCategory(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent verify = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(verify);
			
			
			loadProfileBar();
			loadCreateCategoryTab();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
    public void createCategory(ActionEvent event) {
		String categoryName = newCategoryName.getText().trim();
		
		if(categoryName.isEmpty()) {
			emptyFieldAlert();
		}else {
			boolean created = vistara.addCategory(categoryName);
			if(created) {
				genericSuccessMessage();
			}else {
				repeatedCategoryAlert();
			}
		}
    }
	
	private void repeatedCategoryAlert() {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("Repeated category");
		warning.setContentText("This category already exist. Maybe try another one");
		warning.showAndWait();		
	}

	private void loadCreateCategoryTab() {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("category-create-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
		} catch (IOException e) {
			e.printStackTrace();
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
	public void createAccount(ActionEvent event) {
		try {
			vistara.addUser(usernameSignUp.getText().trim(), emailSignUp.getText().trim(), passwordSignUp.getText().trim());
		} catch (RepeatedUsernameException e) {
			repeatedUsernameAlert();
		} catch (EmptyFieldsException e) {
			emptyFieldAlert();
		}
	}
	
	public void emptyFieldAlert() {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("There are empty fields");
		warning.setContentText("Please fill all the blanks, theres something missing!");
		warning.showAndWait();
	}
	
	public void repeatedUsernameAlert() {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("The username is already taken by another user");
		warning.setContentText("Please chose another username, this one is already taken");
		warning.showAndWait();
	}
	
	public void invalidUsernameAlert() {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("Username not found");
		warning.setContentText("This username is not registed in the platform or your password is incorrect, please try again");
		warning.showAndWait();
	}
	 
	
	
	@FXML
	public void loadProfile(ActionEvent event) {
	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(profile);
			
			loadProfileBar();
			loadProfileTabPane();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadProfileTabPane() {
	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-tab-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
			if(!(currentUser instanceof Moderator)) {
				upgradeBtn.setVisible(false);
				upgradeBtn.setDisable(true);
			}
			loadStatistics(null);
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    public void loadReactedPosts(ActionEvent event) {

		
    }

    @FXML
    public void loadStatistics(ActionEvent event) {
    	
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
    public void loadTrending() {
    	
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
    public void loadCalendar() {
    	
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
    public void loadCreatePost() {
    	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(profile);
			
			loadProfileBar();
			loadNewPostTab();
			
			ArrayList<String> categories = vistara.loadPossibleCategories();
			postCategory.getItems().addAll(categories);
			postCategory.setValue(categories.get(0));
			
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
	public void removeEvent() {
		
	}
	
	@FXML
	public void addPhotoToPost(ActionEvent event) {

    }

    @FXML
    public void uploadPost(ActionEvent event) {
    	if(!postTittle.getText().trim().isEmpty() && postTittle.getText().trim().length() < MAX_TITLE_LENGTH
    			&& !postDetails.getText().trim().isEmpty() && postDetails.getText().trim().length() < MAX_CONTENT_LENGTH
    			&& !postLink.getText().trim().isEmpty()) {
    		
    		if(postImagePath.getText().isEmpty()) {
    			vistara.createPost(postTittle.getText(), postDetails.getText(), postCategory.getValue(), currentUser, postLink.getText());
    		}
    	}else {
    		//warning something went wrong
    	}
    	
    }
    
    @FXML
    public void loadEditProfile(ActionEvent event) {
    	
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
	public void changeProfilePic(ActionEvent event) {

    }

    @FXML
    public void confirmPorfileEdition(ActionEvent event) {

    }
    
    @FXML
    public void turnModerator(ActionEvent event) {
    	 
    }
    
    @FXML
    void filterFeedPosts(ActionEvent event) {

    	if(filterCategory.getValue() != null) {
    		loadFeedByCategory(filterCategory.getValue());
    	}
    }

    private void loadFeedByCategory(String value) {
		
    	postGrid.getChildren().clear();
    	int columns = 0;
    	int rows = 1;
    	    	System.out.println(vistara.getPosts().size()+" ** POSTS VISTARA **");
    	    	System.out.println(value);
    	try {
			for (int i = 0; i < vistara.getPosts().size(); i++) {
				if(vistara.getPosts().get(i).getCategory().getName().equals(value)) {
					
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
					
						AnchorPane postBox = fxmlLoader.load();	
						PostController postController = fxmlLoader.getController();
						postController.setData(vistara.getPosts().get(i), vistara, currentUser);
						System.out.println("gen gen");
						if(columns == 1) {
							 columns = 0;
							 rows++;
						}
						
						postGrid.add(postBox, columns++, rows);
				}
			}
			
    	} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
    void searchUserInFeed(ActionEvent event) {

    	User searchedUser = vistara.searchUser(serachUser.getText().trim());
    	if(searchedUser != null) {
    		try {
    			
      			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("serach-user-pane.fxml"));
      			fxmlLoader.setController(this);
      			Parent searchFXML = fxmlLoader.load();
      			
      			mainPane.getChildren().clear();
      			mainPane.getChildren().setAll(searchFXML);
      			
      			
      			//Verify that the current user has the option to turn other into moderatoes or if the searched user is already a moderator
      			if(!(currentUser instanceof Moderator) || searchedUser instanceof Moderator) {
      				upgradeModBtn.setVisible(false);
      				upgradeModBtn.setDisable(true);
      			}      			
      			
      			loadProfileBar(); 
      			
      			if(searchedUser == currentUser) {
      				followBtnText.setVisible(false);
      				followBtnText.setDisable(true);
      			}
      			//set text for following
      			User userSearched = currentUser.searchUserFollowing(searchedUser.getUsername());
      			if(userSearched == null) {
      				followingText.setText("Not following");
      			}
      			else {
      				followBtnText.setText("Unfollow");
      			}
      			//Set searched user data
      			searchedUsername.setText(searchedUser.getUsername());
      			searchedNumPosts.setText(searchedUser.getOwnPosts().size()+"");
      			searchedNumVerPosts.setText(searchedUser.getVerifiedPosts()+"");
      			searchedNumFollowers.setText(searchedUser.getFollowers().size()+"");
      			searchedNumFollowing.setText(searchedUser.getFollowing().size()+"");
      			searchedNumFakePosts.setText(searchedUser.getFakePosts()+"");
      			
      		} catch (IOException e) {
      			e.printStackTrace();
      		}
    	}else {
    		//WARNING USER NOT FOUND
    		System.out.println("not found");
    	}
    }
    
    @FXML
    void followUser(ActionEvent event) {
    	
    	if(followBtnText.getText().equals("Follow")) {
    		vistara.followUser(currentUser, searchedUsername.getText());
    	}else {
    		vistara.unfollowUser(currentUser, searchedUsername.getText());
    	}
    }
    
    @FXML
    void upgradeUser(ActionEvent event) {
    	vistara.upgradeUser(searchedUsername.getText());
    }
    
    @FXML
    void commentOnPost(ActionEvent event) {

    }

    @FXML
    void reactToPost(ActionEvent event) {
    	
    }
}
