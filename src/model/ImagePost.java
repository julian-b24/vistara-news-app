package model;

import java.time.LocalDateTime;

import javafx.scene.image.Image;

public class ImagePost extends Post {

	private Image image;

	public ImagePost(User user, String title, String content, Category category, LocalDateTime date, String link, Image image) {
		super(user, title, content, category, date, link);
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
	}

	/**
	 * 
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

}