package ui;

import model.Comment;
import model.ImagePost;
import model.Moderator;
import model.Post;
import model.User;
import model.Vistara;
import thread.ThreadImportData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import animatefx.animation.Bounce;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUsernameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.util.Duration;


public class VistaraGUI {
	
	public final static int MAX_TITLE_LENGTH = 70;
	public final static int MAX_CONTENT_LENGTH = 500;

	private Vistara vistara;
	
	//model fields
	private User currentUser;
	//private Post currentPost;
	private Post currentPost;
	
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
    private Circle searchCircleProfile;
    
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
    
    //profile
    
    @FXML
    private GridPane reactedPostsGrid;
    
    @FXML
    private GridPane ownPostsGrid;
    
    //comments
    @FXML
    private GridPane commentsGrid;
    
    @FXML
    private Label commentNewTitle;
    
    @FXML
    private Label commentCreatorTitle;
    
    //trending
    @FXML
    private GridPane trendingGrid;
    
    //stats posts

    @FXML
    private Label statsPostHeader;

    @FXML
    private LineChart<?, ?> lineChartPost;

    @FXML
    private Label statsPostTitle;

    @FXML
    private Label statsPostStatus;

    @FXML
    private Label statsPostsReactions;

    @FXML
    private Label statsPostComments;

    @FXML
    private JFXTextArea statsPostContent;

    //edit profile
    @FXML
    private Circle editCircleProfile;
    
    @FXML
    private JFXTextField editName;

    @FXML
    private JFXTextField editEmail;

    @FXML
    private JFXTextArea editBio;
    
    //profileTabPane
    @FXML
    private Circle profileCircle;

    @FXML
    private Label numPostProfile;

    @FXML
    private Label numFollowersProfile;

    @FXML
    private Label numFollowingProfile;

    @FXML
    private Label numVerifiedPostsProfile;
    
    @FXML
    private Label usernameProfile;
    
    @FXML
    private Label descriptionProfile;
    
    //loading pane
    @FXML
    private Circle circle1;
    
    @FXML
	private Circle circle2;
    
    @FXML
	private Circle circle3;
    
    @FXML
    private Rectangle square1;

    @FXML
    private Rectangle square3;

    @FXML
    private Rectangle square4;

    @FXML
    private Rectangle square2;
    
    @FXML
    private ImageView logoImg;
    
	public Circle getCircle1() {
		return circle1;
	}

	public void setCircle1(Circle circle1) {
		this.circle1 = circle1;
	}

	public Circle getCircle2() {
		return circle2;
	}

	public void setCircle2(Circle circle2) {
		this.circle2 = circle2;
	}

	public Circle getCircle3() {
		return circle3;
	}

	public void setCircle3(Circle circle3) {
		this.circle3 = circle3;
	}

	@FXML
    public void loadLogIn(ActionEvent event) {
   
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-pane.fxml"));
			fxmlLoader.setController(this);
			Parent login = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(login);
			
		} catch (IOException e) {
			executionAlert();
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
        		try {
					vistara.createCategory("f");
					vistara.createCategory("g");
	        		vistara.createCategory("d");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		
        		loadFeed(null);

    			if(currentUser instanceof Moderator) {
    				//TEST
    				LocalDateTime lc = LocalDateTime.now();
    				Post postx = new Post(currentUser.getUsername(), "A", "CONTNET 1", null, lc, "www.1");
    				
    				((Moderator) currentUser).getPendingPosts().add(postx);
    				
    				postx = new Post(currentUser.getUsername(), "B", "CONTNET 2", null, lc, "www.2");
    				((Moderator) currentUser).getPendingPosts().add(postx);
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
			executionAlert();
		}
	}

	private void loadFeedPosts() {
    	
    	int columns = 0;
    	int rows = 1;
    	    	
    	try {
			for (int i = 0; i < currentUser.getFeed().size(); i++) {
				if(currentUser.getFeed().get(i) instanceof ImagePost) {
					
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-image-pane.fxml"));
					
						AnchorPane postBox = fxmlLoader.load();	
						PostImageController postController = fxmlLoader.getController();
						postController.setData(currentUser.getFeed().get(i), vistara, currentUser, this);
						
						if(columns == 1) {
							 columns = 0;
							 rows++;
						}
	
						postGrid.add(postBox, columns++, rows);
				}else {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
					
						AnchorPane postBox = fxmlLoader.load();	
						PostController postController = fxmlLoader.getController();
						postController.setData(currentUser.getFeed().get(i), vistara, currentUser, this);
						
						if(columns == 1) {
							 columns = 0;
							 rows++;
						}
						
						postGrid.add(postBox, columns++, rows);
				}
				
			}
			
    	} catch (IOException e) {
    		executionAlert();
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
			executionAlert();
		}
    }
    
	private void loadProfileBar() {
		try {
			
			FXMLLoader fxmlLoader;
			
			boolean isMod;
			if(currentUser instanceof Moderator) {
				isMod = true;
			}else {
				isMod = false;
			}
			
			if(isMod) {
				fxmlLoader = new FXMLLoader(getClass().getResource("profile-bar-mod.fxml"));
			}else {
				fxmlLoader = new FXMLLoader(getClass().getResource("profile-bar-user.fxml"));
			}
			
			fxmlLoader.setController(this);
			Parent profileBar = fxmlLoader.load();
			profileBarPane.getChildren().clear();
			profileBarPane.getChildren().setAll(profileBar);
			loadProfilePic(profileCircleBar, currentUser);
			setProfileBarInfo();
						
		} catch (IOException e) {
			executionAlert();
		}
	}
	
	public void loadProfilePic(Circle circle, User user) {
		Image image;
		if(user.getProfilePicPath() == null) {
			image = new Image("file:imgs/no-profile.png");
		}else {
			image = new Image(user.getProfilePicPath()) ;
		}
		circle.setFill(new ImagePattern(image));
	}

	@FXML
    public void deletePost(ActionEvent event) {
		if(currentUser instanceof Moderator && ((Moderator) currentUser).getPendingPosts().size() > 0) {
			
			String creatorString = ((Moderator) currentUser).getPendingPosts().get(0).getAuthor();
			Post postToRemove = ((Moderator) currentUser).getPendingPosts().get(0);
			
			try {
				vistara.deletePost(creatorString, currentUser.getUsername(), postToRemove);
				vistara.reOrderModerators();
				loadVerifyPost(null);
			} catch (EmptyFieldsException e) {
				emptyFieldAlert();
			} catch (InvalidUserException e) {
				invalidUsernameAlert();
			} catch (IndexOutOfBoundsException e) {
				executionAlert();
			} catch (IOException e) {
				executionAlert();
			}
		}
		
    }

    @SuppressWarnings("static-access")
	@FXML
    public void verifyPost(ActionEvent event) {
    	if(currentUser instanceof Moderator) {
    		String state = null;
    		
    		if(verifiedNew.isSelected()) {
    			state = vistara.VERIFIED;
    		}else if(fakeNew.isSelected()){
    			state = vistara.FAKE;
    		}
    		
    		if(state != null) {
    			Post postToVerify = ((Moderator) currentUser).getPendingPosts().get(0);
        		String creatorUser = ((Moderator) currentUser).getPendingPosts().get(0).getAuthor();
        		
        		try {
					vistara.verifyPost(creatorUser, currentUser.getUsername(), postToVerify, state);
				} catch (InvalidUserException e) {
					invalidUsernameAlert();
				} catch (IOException e) {
					executionAlert();
				}
    	    		
    	    	showInfoAlert();
    	    	try {
					vistara.reOrderModerators();
				} catch (IOException e) {
					executionAlert();
				}
    	    		
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
			
		} catch (IOException e) {
			executionAlert();
		}
	}

	public void loadCommentsOfPost(Post post) {
		//Loads the comments of the currentPost
		
		commentsGrid.getChildren().clear();

		if(currentPost.getComments() > 0) {
			//load all the comments
			iconNoComments.setVisible(false);
			txtNoComments.setVisible(false);
		}else {
			iconNoComments.setVisible(true);
			txtNoComments.setVisible(true);
		}
		
		if(post.getFirstComment() != null) {
			Comment current = post.getFirstComment();
			int columns = 0;
	    	int rows = 1;
			do {
		    	User userCreatorComment = null;
				try {
					userCreatorComment = vistara.searchUser(current.getAuthor());
				} catch (InvalidUserException e1) {
					invalidUsernameAlert();
				}
		    	try {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("comment-pane.fxml"));
					
					AnchorPane postBox = fxmlLoader.load();	
					CommentController commentController = fxmlLoader.getController();
					commentController.setData(current, vistara, this, userCreatorComment);
						
					if(columns == 1) {
						 columns = 0;
						rows++;
					}
							
					commentsGrid.add(postBox, columns++, rows);
					
		    	} catch (IOException e) {
		    		executionAlert();
				}
				
				current = current.getNextComment();
			}while(current != post.getFirstComment());
		}
		
	}
	
	public void updateCommentsTap() {
		if(currentPost != null) {
			commentNewTitle.setText(currentPost.getTitle());
			commentCreatorTitle.setText(currentPost.getAuthor());
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
			executionAlert();
		}
    }
	
	private void loadPostToVerify() {
		
		//Verify that there are elements in the list
		
		if(currentUser instanceof Moderator) {
			
			if(((Moderator) currentUser).getPendingPosts().size() > 0) {
				if(((Moderator) currentUser).getPendingPosts().get(0) instanceof ImagePost) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-image-pane.fxml"));
					
					try {
						AnchorPane postToVerify = fxmlLoader.load();
						PostImageController postController = fxmlLoader.getController();					
						
						postController.setData(((Moderator) currentUser).getPendingPosts().get(0), vistara, currentUser, this);
						
						postVerifyPost.getChildren().clear();
						postVerifyPost.getChildren().setAll(postToVerify);
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}else {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
					
					try {
						AnchorPane postToVerify = fxmlLoader.load();
						PostController postController = fxmlLoader.getController();					
						
						postController.setData(((Moderator) currentUser).getPendingPosts().get(0), vistara, currentUser, this);
						
						postVerifyPost.getChildren().clear();
						postVerifyPost.getChildren().setAll(postToVerify);
						
					} catch (IOException e) {
						executionAlert();
					}
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
			executionAlert();
		}
	}
	
	@FXML
	public void loadStatsPost(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent stats = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(stats);

			loadProfileBar();
			loadStatsPane(null);
			loadStatsPostData();
			loadReportChartPost();
			
		} catch (IOException e) {
			executionAlert();
		}
	}
	
	@FXML
	public void loadStatsPane(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post-stats.fxml"));
			fxmlLoader.setController(this);
			Parent stats = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(stats);
			
		} catch (IOException e) {
			executionAlert();
		}
	}

	private void loadStatsPostData() {
		statsPostHeader.setText(currentPost.getTitle());
		statsPostComments.setText(String.valueOf(currentPost.getComments()).replace(".0",""));
		statsPostsReactions.setText(String.valueOf(currentPost.getReactions()).replace(".0",""));
		statsPostTitle.setText(currentPost.getTitle());
		statsPostStatus.setText(currentPost.getState().toString());
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
			executionAlert();
		}
    }
	
	@FXML
    public void createCategory(ActionEvent event) {
		String categoryName = newCategoryName.getText().trim();
		
		if(categoryName.isEmpty()) {
			emptyFieldAlert();
		}else {
			if(categoryName.length() <= 20) {
				boolean created;
				try {
					created = vistara.createCategory(categoryName);
					if(created) {
						genericSuccessMessage();
					}else {
						repeatedCategoryAlert();
					}
				} catch (IOException e) {
					executionAlert();
				}
				
			}else {
				lengthWarning();
			}			
		}
    }
	
    @FXML
    public void chooseCategoryImage(ActionEvent event) {
    	
    	FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile != null) {
			newCategoryImagePath.setText("file:"+selectedFile.getAbsolutePath());
		}
    }
	
	private void lengthWarning() {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("Maximun amount of characters exceded");
		warning.setContentText("more characters than the ones allowed");
		warning.showAndWait();		
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
			
			newCategoryImagePath.setEditable(false);
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
		} catch (IOException e) {
			executionAlert();
		}
	}

	private void setProfileBarInfo() {
		txtUsernameBar.setText(currentUser.getUsername());;
		txtBioUserBar.setText(currentUser.getDescription());;
		txtFollowersBar.setText(String.valueOf(currentUser.getFollowers().size()));;
		txtFollowingBar.setText(String.valueOf(currentUser.getFollowing().size()));;
	}
	
	@FXML
    public void addComment(ActionEvent event) {
		//Add a new comment to the currentPost
		if(!txtNewComment.getText().isEmpty()) {
			try {
				vistara.createComment(currentUser, txtNewComment.getText(), currentPost);
			} catch (IOException e) {
				executionAlert();
			}
			txtNewComment.clear();
			loadCommentsOfPost(currentPost);
		}else {
			emptyFieldAlert();
		}
    }
	
	@FXML
	public void createAccount(ActionEvent event) {
		try {
			vistara.addUser(usernameSignUp.getText().trim(), emailSignUp.getText().trim(), passwordSignUp.getText().trim());
		} catch (RepeatedUsernameException e) {
			repeatedUsernameAlert();
		} catch (EmptyFieldsException e) {
			emptyFieldAlert();
		} catch (IOException e) {
			executionAlert();
		}
	}
	
	private void notFoundUserAlert() {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("Not User found");
		warning.setContentText("Any user has the username you are looking for");
		warning.showAndWait();
	}
	
	public void executionAlert() {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("Executions errors");
		warning.setContentText("An unexpected error occurred in the process!");
		warning.showAndWait();
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
			executionAlert();
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
			
			loadProfilePic(profileCircle, currentUser);
			usernameProfile.setText(currentUser.getUsername());
			numPostProfile.setText(currentUser.getOwnPosts().size()+"");
			numFollowersProfile.setText(currentUser.getFollowers().size()+"");
			numFollowingProfile.setText(currentUser.getFollowing().size()+"");
			numVerifiedPostsProfile.setText(currentUser.getVerifiedPosts()+"");
			descriptionProfile.setText(currentUser.getDescription());
			
			loadOwnPosts(null);
			loadStatistics(null);
			loadReactedPosts(null);
			
		} catch (IOException e) {
			executionAlert();
		}
	}
	
	@FXML
	private void loadOwnPosts(ActionEvent event) {
		
		int columns = 0;
    	int rows = 1;
    	    
    	try {
			for (int i = 0; i < currentUser.getOwnPosts().size(); i++) {
				if(currentUser.getOwnPosts().get(i) instanceof ImagePost) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-image-pane.fxml"));
					
						AnchorPane postBox = fxmlLoader.load();	
						PostImageController postController = fxmlLoader.getController();
						postController.setData(currentUser.getOwnPosts().get(i), vistara, currentUser, this);
						
						if(columns == 1) {
							 columns = 0;
							 rows++;
						}
						
						ownPostsGrid.add(postBox, columns++, rows);
				}else {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
					
						AnchorPane postBox = fxmlLoader.load();	
						PostController postController = fxmlLoader.getController();
						postController.setData(currentUser.getOwnPosts().get(i), vistara, currentUser, this);
						
						if(columns == 1) {
							 columns = 0;
							 rows++;
						}
						
						ownPostsGrid.add(postBox, columns++, rows);
				}
			}
			
    	} catch (IOException e) {
    		executionAlert();
		}
	}

	@FXML
    public void loadReactedPosts(ActionEvent event) {
		
		int columns = 0;
    	int rows = 1;
    	    
    	try {
			for (int i = 0; i < currentUser.getReactedPosts().size(); i++) {
				if(currentUser.getReactedPosts().get(i) instanceof ImagePost) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-image-pane.fxml"));
					
						AnchorPane postBox = fxmlLoader.load();	
						PostImageController postController = fxmlLoader.getController();
						postController.setData(currentUser.getReactedPosts().get(i), vistara, currentUser, this);
						
						if(columns == 1) {
							 columns = 0;
							 rows++;
						}
						
						reactedPostsGrid.add(postBox, columns++, rows);
				}else {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
					
						AnchorPane postBox = fxmlLoader.load();	
						PostController postController = fxmlLoader.getController();
						postController.setData(currentUser.getReactedPosts().get(i), vistara, currentUser, this);
						
						if(columns == 1) {
							 columns = 0;
							 rows++;
						}
						
						reactedPostsGrid.add(postBox, columns++, rows);
				}
			}
			
    	} catch (IOException e) {
    		executionAlert();
		}
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
			executionAlert();
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
			executionAlert();
		}
    }
    
    private void loadTrendingTab() {
    	
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trending-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
			loadTrendingPosts();
			
			
		} catch (IOException e) {
			executionAlert();
		}
	}

	private void loadTrendingPosts() {
		
		int columns = 0;
    	int rows = 1;
    	    	
    	try {
    		
			for (int i = 0; i < vistara.getTrending().size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
				
					AnchorPane postBox = fxmlLoader.load();	
					PostController postController = fxmlLoader.getController();
					postController.setData(vistara.getTrending().get(i), vistara, currentUser, this);
					
					if(columns == 1) {
						 columns = 0;
						 rows++;
					}
					
					trendingGrid.add(postBox, columns++, rows);
			}
			
    	} catch (IOException e) {
    		executionAlert();
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
			executionAlert();
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
			executionAlert();
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
			executionAlert();
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
			executionAlert();
		}
	}

	@FXML
	public void removeEvent() {
		
	}
	
	@FXML
	public void addPhotoToPost(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		if(selectedFile != null) {
			postImagePath.setText(selectedFile.getAbsolutePath());
		}
    }

    @FXML
    public void uploadPost(ActionEvent event) {
    	if(!postTittle.getText().trim().isEmpty() && postTittle.getText().trim().length() < MAX_TITLE_LENGTH
    			&& !postDetails.getText().trim().isEmpty() && postDetails.getText().trim().length() < MAX_CONTENT_LENGTH
    			&& !postLink.getText().trim().isEmpty()) {
    		
    		if(postImagePath.getText().isEmpty()) {
    			try {
					vistara.createPost(postTittle.getText(), postDetails.getText(), postCategory.getValue(), currentUser, postLink.getText());
				} catch (EmptyFieldsException e) {
					emptyFieldAlert();
				} catch (IOException e) {
					executionAlert();
				}
    		}else {
    			try {
					vistara.createImagePost(currentUser, postTittle.getText(), postDetails.getText(), postCategory.getValue(), postLink.getText(), postImagePath.getText());
				} catch (IOException e) {
					executionAlert();
				}
    		}
    	}else {
    		executionAlert();
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
			executionAlert();
		}
    }
    
    private void loadEditProfileTab() {
		
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit-profile-pane.fxml"));
			fxmlLoader.setController(this);
			Parent profile = fxmlLoader.load();
			loadEditData();
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(profile);
			
			
		} catch (IOException e) {
			executionAlert();
		}
	}

	private void loadEditData() {
		editName.setText(currentUser.getUsername());
		editEmail.setText(currentUser.getEmail());
		editBio.setText(currentUser.getDescription());
		loadProfilePic(editCircleProfile, currentUser);
	}

	@FXML
	public void changeProfilePic(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile != null) {
			try {
				vistara.setUserProfilePic(currentUser.getUsername(), selectedFile);
			} catch (InvalidUserException e) {
				executionAlert();
			}
		}
    }

    @FXML
    public void confirmPorfileEdition(ActionEvent event) {
    	if(!editName.getText().isEmpty() && !editEmail.getText().isEmpty()) {

			try {
				vistara.confirmPorfileEdition(currentUser.getUsername(), editName.getText().trim(), editEmail.getText().trim(), editBio.getText());
			} catch (InvalidUserException e) {
				invalidUsernameAlert();
			} catch (RepeatedUsernameException e) {
				repeatedUsernameAlert();
			} catch (IOException e) {
				executionAlert();
			}
    	}
    }
    
    @FXML
    public void filterFeedPosts(ActionEvent event) {

    	if(filterCategory.getValue() != null) {
    		loadFeedByCategory(filterCategory.getValue());
    	}
    }

    private void loadFeedByCategory(String value) {
		
    	postGrid.getChildren().clear();
    	int columns = 0;
    	int rows = 1;
    	
    	try {
			for (int i = 0; i < vistara.getPosts().size(); i++) {
				if(vistara.getPosts().get(i).getCategory().getName().equals(value)) {
					if(vistara.getPosts().get(i) instanceof ImagePost) {
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(getClass().getResource("post-image-pane.fxml"));
						
							AnchorPane postBox = fxmlLoader.load();	
							PostImageController postController = fxmlLoader.getController();
							postController.setData(vistara.getPosts().get(i), vistara, currentUser, this);
							
							if(columns == 1) {
								 columns = 0;
								 rows++;
							}
							
							postGrid.add(postBox, columns++, rows);
					}else {
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(getClass().getResource("post-pane.fxml"));
						
							AnchorPane postBox = fxmlLoader.load();	
							PostController postController = fxmlLoader.getController();
							postController.setData(vistara.getPosts().get(i), vistara, currentUser, this);
							
							if(columns == 1) {
								 columns = 0;
								 rows++;
							}
							
							postGrid.add(postBox, columns++, rows);
					}
				}
			}
			
    	} catch (IOException e) {
    		executionAlert();
		}
	}

	@FXML
    public void searchUserInFeed(ActionEvent event) {

    	User searchedUser = null;
		try {
			searchedUser = vistara.searchUser(serachUser.getText().trim());
		} catch (InvalidUserException e1) {
			invalidUsernameAlert();
		}
		
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
  			loadProfilePic(searchCircleProfile, searchedUser);
  			
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
  			executionAlert();
  		} catch (NullPointerException e) {
  			notFoundUserAlert();
  		}
    }

	@FXML
    public void followUser(ActionEvent event) {
    	
    	if(followBtnText.getText().equals("Follow")) {
    		try {
				vistara.followUser(currentUser, searchedUsername.getText());
			} catch (InvalidUserException e) {
				invalidUsernameAlert();
			} catch (IOException e) {
				executionAlert();
			}
    	}else {
    		try {
				vistara.unfollowUser(currentUser, searchedUsername.getText());
			} catch (InvalidUserException e) {
				invalidUsernameAlert();
			} catch (IOException e) {
				executionAlert();
			}
    	}
    }
    
    @FXML
    public void upgradeUser(ActionEvent event) {
		try {
			vistara.upgradeUser(searchedUsername.getText());
		} catch (InvalidUserException e) {
			invalidUsernameAlert();
		} catch (RepeatedUsernameException e) {
			repeatedUsernameAlert();
		} catch (IOException e) {
			executionAlert();
		}
    }

    /**
     * Loads the line chart according to the post report and stats
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public void loadReportChartPost() {
    	XYChart.Series serieComments = new XYChart.Series();
    	XYChart.Series serieReactions = new XYChart.Series();
    	XYChart.Series serieRating = new XYChart.Series();
    	
    	for (Map.Entry<String, HashMap<String, Double>> entry : currentPost.getReport().entrySet()) {
			serieComments.getData().add(new XYChart.Data(entry.getKey(), entry.getValue().get(currentPost.MAP_KEY_COMMENTS)));
			serieReactions.getData().add(new XYChart.Data(entry.getKey(), entry.getValue().get(currentPost.MAP_KEY_REACTIONS)));
			serieRating.getData().add(new XYChart.Data(entry.getKey(), entry.getValue().get(currentPost.MAP_KEY_RATING)));
    	}
    	
    	lineChartPost.getData().addAll(serieComments, serieReactions, serieRating);
    }
    
	public Post getCurrentPost() {
		return currentPost;
	}

	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}
	
    @FXML
    public void importData(MouseEvent event) {
		ThreadImportData threadImportData = new ThreadImportData(vistara);
		Thread thread = new Thread(threadImportData);
		loadLoadingPane();
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			executionAlert();
		}
    }

	public void loadLoadingPane() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-main-pane.fxml"));
			fxmlLoader.setController(this);
			Parent loading = fxmlLoader.load();
			
			loadProfileBar();
			loadLoadingPaneAnimation();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(loading);
			
		} catch (IOException e) {
			executionAlert();
		}
	}
	
	
	public void loadLoadingPaneAnimation() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("load-pane.fxml"));
			fxmlLoader.setController(this);
			Parent loading = fxmlLoader.load();
	
			profilePane.getChildren().clear();
			profilePane.getChildren().setAll(loading);
			executeLoading();
			
		} catch (IOException e) {
			executionAlert();
		}
	}
	
	public void executeLoading() {

		//circles animation
		new Bounce(circle1).setCycleDuration(10).setCycleCount(10).setDelay(Duration.valueOf("500ms")).play();
		new Bounce(circle2).setCycleDuration(10).setCycleCount(10).setDelay(Duration.valueOf("1000ms")).play();
		new Bounce(circle3).setCycleDuration(10).setCycleCount(10).setDelay(Duration.valueOf("1100ms")).play();
	
		new Bounce(square1).setCycleDuration(10).setCycleCount(10).setDelay(Duration.valueOf("250ms")).play();
		new Bounce(square2).setCycleDuration(10).setCycleCount(10).setDelay(Duration.valueOf("500ms")).play();
		new Bounce(square3).setCycleDuration(10).setCycleCount(10).setDelay(Duration.valueOf("1000ms")).play();
		new Bounce(square4).setCycleDuration(10).setCycleCount(10).setDelay(Duration.valueOf("1250ms")).play();
		
	}
}
