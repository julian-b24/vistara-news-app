package model;

import java.io.Serializable;

import javafx.scene.image.Image;

public abstract class Content implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String author;
	private Image authorPic;

	public String getAuthor() {
		return this.author;
	}

	/**
	 * 
	 * @param author
	 */
	public void setAuthor(String author) {
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
	public Content(String user) {
		author = user;
		//authorPic = author.getProfilePic();
	}

}