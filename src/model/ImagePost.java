package model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ImagePost extends Post implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String imagePath;

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
	public ImagePost(String user, String title, String content, Category category, LocalDateTime date, String link, String image) {
		super(user, title, content, category, date, link);
		this.imagePath = image;
	}
	
	/**
	* getImage: Gets the image<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return image Image
	*/
	public String getImage() {
		return this.imagePath;
	}

	/**
	* setImage: Sets the image<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param image Image
	*/
	public void setImage(String image) {
		this.imagePath = image;
	}

}