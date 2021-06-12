package model;

import java.io.Serializable;

public abstract class Content implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String author;

	/**
	* getAuthor: Gets author <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return author String
	*/
	public String getAuthor() {
		return this.author;
	}

	/**
	* setAuthor: Sets the author <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param author String
	*/
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	* Content: Content class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param user String
	*/
	public Content(String user) {
		author = user;
	}

}