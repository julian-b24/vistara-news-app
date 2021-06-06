package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
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

    @FXML
    private ImageView newsImage;

    @FXML
    private Label newsLink;
    
    public void setDate (Post post) {
    	//post image
    	//Image image = new Image(getClass().getResourceAsStream(post.getImgPath()));
    	//newsImage.setImage(image);
    	//post user image
    	//image = new Image(getClass().getResourceAsStream(post.getUserImagePath()));
    	//profilePicPost.setImage(image);
    	//texts
    	postUsername.setText(post.getContent());
    	verificationText.setText(post.getState()+"");
    	postTittle.setText(post.getTitle());
    	numberReactions.setText(post.getReactions()+"");
    	numberCommmentaries.setText(post.getComments()+"");
    	newsLink.setText(post.getFullNewLink());
    	
    }
}
