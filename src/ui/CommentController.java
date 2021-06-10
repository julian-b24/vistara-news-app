package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.Comment;
import model.Vistara;

public class CommentController {

    @FXML
    private Text commentUser;

    @FXML
    private Label commentContent;
    
    public void setData(Comment comment, Vistara vis, VistaraGUI visG) {
    	commentUser.setText(comment.getAuthor());
    	commentContent.setText(comment.getContent());
    }
}
