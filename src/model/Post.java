package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Post extends Content {

	private Vistara posts;
	private Vistara trending;
	private String title;
	private String content;
	private Category category;
	private LocalDateTime date;
	private String fullNewLink;
	private State state;
	private ArrayList<User> reactedUsers;
	private ArrayList<Comment> comments;
	private double rating;

	public Post(User user, Image pic) {
		super(user, pic);
		// TODO Auto-generated constructor stub
	}
	
	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public Category getCategory() {
		return this.category;
	}

	/**
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getFullNewLink() {
		return this.fullNewLink;
	}

	/**
	 * 
	 * @param fullNewLink
	 */
	public void setFullNewLink(String fullNewLink) {
		this.fullNewLink = fullNewLink;
	}

	public State getState() {
		return this.state;
	}

	/**
	 * 
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
	}

	public ArrayList<User> getReactedUsers() {
		return this.reactedUsers;
	}

	/**
	 * 
	 * @param reactedUsers
	 */
	public void setReactedUsers(ArrayList<User> reactedUsers) {
		this.reactedUsers = reactedUsers;
	}

	public ArrayList<Comment> getComments() {
		return this.comments;
	}

	/**
	 * 
	 * @param comments
	 */
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public double getRating() {
		return this.rating;
	}

	/**
	 * 
	 * @param rating
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

}