package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.Comment;
import model.User;
import model.Vistara;

public class CommentController {

    @FXML
    private Text commentUser;

    @FXML
    private Label commentContent;
    
    @FXML
    private Circle commentProfilePic;
    
    public void setData(Comment comment, Vistara vis, VistaraGUI visG, User commentCreator) {
    	commentUser.setText(comment.getAuthor());
    	commentContent.setText(comment.getContent());
    	
    	Image image;
		if(commentCreator.getProfilePic() == null) {
			image = new Image("file:imgs/no-profile.png");
		}else {
			image = commentCreator.getProfilePic();
		}
		commentProfilePic.setFill(new ImagePattern(image));
    }
}
