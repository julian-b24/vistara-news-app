package model;

import javafx.scene.image.Image;

public class Content {

	private User author;
	private Image authorPic;

	public User getAuthor() {
		return this.author;
	}

	/**
	 * 
	 * @param author
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	public Image getAuthorPic() {
		return this.authorPic;
	}

	/**
	 * 
	 * @param authorPic
	 */
	public void setAuthorPic(Image authorPic) {
		this.authorPic = authorPic;
	}

	/**
	 * 
	 * @param user
	 * @param pic
	 */
	public Content(User user, Image pic) {
		// TODO - implement Content.Content
		throw new UnsupportedOperationException();
	}

}