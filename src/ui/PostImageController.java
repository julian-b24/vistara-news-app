package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.ImagePost;
import model.Post;
import model.User;
import model.Vistara;

public class PostImageController {

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
    
    @FXML
    private Rectangle imagePost;
    
    private Post post;
    private Vistara vistara;
    private User currentUser;
    private VistaraGUI vistaraGUI;
    
    public void setData (Post post, Vistara vis, User user, VistaraGUI visGui) {
    	
    	if(post instanceof ImagePost) {
    		System.out.println("adjdsj");
    		imagePost.setFill(new ImagePattern(((ImagePost) post).getImage()));
    	}
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
    	visGui.loadProfilePic(postProfileImage, user);
    }
    
    @FXML
    void commentOnPost(ActionEvent event) {
    	vistaraGUI.setCurrentPost(post);
    	vistaraGUI.loadCommentsOfPost(post);
    	vistaraGUI.updateCommentsTap();
    }
    
    @FXML
    void reactToPost(ActionEvent event) {
    	vistara.reactToPost(post, currentUser);
    	vistaraGUI.loadFeed(null);
    	vistaraGUI.updateCommentsTap();
    }
    
    @FXML
    void loadPostStats(ActionEvent event) {
    	vistaraGUI.setCurrentPost(post);
    	vistaraGUI.loadStatsPost(null);
    }
}
