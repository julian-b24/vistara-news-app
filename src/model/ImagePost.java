package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javafx.scene.image.Image;

public class ImagePost extends Post implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Image image;

	/**
	* ImagePost: ImagePost class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param user String creator username
	* @param title String post title
	* @param content String 
	* @param category Category
	* @param date LocalDateTime time of creations
	* @param link String link to the full new
	* @param image Image, profile picture
	*/
	public ImagePost(String user, String title, String content, Category category, LocalDateTime date, String link, Image image) {
		super(user, title, content, category, date, link);
		this.image = image;
	}
	
	/**
	* getImage: Gets the image<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return image Image
	*/
	public Image getImage() {
		return this.image;
	}

	/**
	* setImage: Sets the image<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param image Image
	*/
	public void setImage(Image image) {
		this.image = image;
	}

}