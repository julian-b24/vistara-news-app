package model;

import java.util.ArrayList;

import javafx.scene.image.Image;


public class Comment extends Content {

	private ArrayList<Post> comments;
	private String content;

	/**
	 * 
	 * @param user
	 * @param pic
	 * @param cont
	 */
	public Comment(User user, Image pic, String cont) {
		super(user, pic);
		throw new UnsupportedOperationException();
	}

}