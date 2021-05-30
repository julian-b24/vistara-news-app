package ui;

import java.awt.Label;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;
import model.Post;

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
	  private ImageView profilePicPost;
	  
	  @FXML
	  private Label postDetails;

	  public void setData(Post post) {
		  
		  Image image = new Image(getClass().getResourceAsStream(post.getProfilePicSrc()));
		  profilePicPost.setImage(image);
		  
		  postUsername.setText(post.getUsernameCreator());
		  verificationText.setText(post.getVerificationState()+"");
		  postTittle.setText(post.getPostTittle());
		  numberReactions.setText(post.getNumReactions()+"");
		  numberCommmentaries.setText(post.getNumCommentaries()+"");
		  postDetails.setText(post.getPostDetails());
		  
	  }
}
