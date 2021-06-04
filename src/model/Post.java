package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

public class Post extends Content implements StatsCalculable, Rateable{

	private String title;
	private String content;
	private Category category;
	private LocalDateTime date;
	private String fullNewLink;
	private State state;
	private ArrayList<User> reactedUsers;
	private Comment firstComment;
	private double rating;

	public Post(User user, String title, String content, Category category, LocalDateTime date, String link) {
		super(user);
		this.title = title;
		this.content = content;
		this.category = category;
		this.date = date;
		fullNewLink = link;
		state = State.UNVERIFIED;
		reactedUsers = new ArrayList<User>();
		rating = 0;
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

	public Comment getFirstComment() {
		return this.firstComment;
	}

	/**
	 * 
	 * @param comments
	 */
	public void setFirstComment(Comment comments) {
		this.firstComment = comments;
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

	@Override
	public HashMap<String, Double> generateStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateRating() {
		// TODO Auto-generated method stub
		return 0;
	}

}