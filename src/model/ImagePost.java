package model;

import javafx.scene.image.Image;

public class ImagePost extends Post {

	private Image image;

	public ImagePost(User user, Image pic) {
		super(user, pic);
		// TODO Auto-generated constructor stub
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