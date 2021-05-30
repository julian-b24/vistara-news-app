package model;

import javafx.scene.image.Image;


public class Comment extends Content {

	private Comment nextComment;
	private Comment previouComment;
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