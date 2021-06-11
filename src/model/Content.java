package model;

import java.io.Serializable;

import javafx.scene.image.Image;

public abstract class Content implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String author;

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

	/**
	 * 
	 * @param user
	 * @param pic
	 */
	public Content(String user) {
		author = user;
	}

}