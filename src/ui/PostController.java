package ui;

import exceptions.InvalidUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import model.Post;
import model.User;
import model.Vistara;

public class PostController {

	@FXML
    private Circle postProfileImage;

    @FXML
    private Label postUsername;

    @FXML
    private Label verificationText;

    @FXML
    private Label postTittle;

    @FXML
    private Label numberReactions;

    @FXML
    private Label numberCommmentaries;

    @FXML
    private Label postDetails;

    @FXML
    private ImageView newsImage;

    @FXML
    private Label newsLink;
    
    private Post post;
    private Vistara vistara;
    private User currentUser;
    private VistaraGUI vistaraGUI;
    
    public void setData (Post post, Vistara vis, User user, VistaraGUI visGui) {
    	
    	this.post = post;
    	vistara = vis;
    	currentUser = user;
    	vistaraGUI = visGui;
    	postUsername.setText(post.getAuthor());
    	postDetails.setText(post.getContent());
    	verificationText.setText(post.getState().toString());
    	postTittle.setText(post.getTitle());
    	numberReactions.setText(post.getReactions()+"");
    	numberCommmentaries.setText(post.getComments()+"");
    	newsLink.setText(post.getFullNewLink());
    	try {
			visGui.loadProfilePic(postProfileImage, vistara.searchUser(post.getAuthor()));
		} catch (InvalidUserException e) {
			visGui.invalidUsernameAlert();
		}
    }
    
    @FXML
    void commentOnPost(ActionEvent event) {
    	vistaraGUI.setCurrentPost(post);
    	vistaraGUI.loadCommentsOfPost(post);
    }
    
    @FXML
    void reactToPost(ActionEvent event) {
    	vistara.reactToPost(post, currentUser);
    	vistaraGUI.loadFeed(null);
    }
    
    @FXML
    void loadPostStats(ActionEvent event) {
    	vistaraGUI.setCurrentPost(post);
    	vistaraGUI.loadStatsPost(null);
    }
}
