package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Post extends Content implements StatsCalculable, Rateable, Serializable{

	private static final long serialVersionUID = 1L;
	public static final String MAP_KEY_COMMENTS = "comments";
	public static final String MAP_KEY_REACTIONS = "reactions";
	public static final String MAP_KEY_RATING = "rating";
	public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	private String title;
	private String content;
	private Category category;
	private LocalDateTime date;
	private String fullNewLink;
	private State state;
	private ArrayList<User> reactedUsers;
	private Comment firstComment;
	private Comment lastComment;
	private double rating;
	private double reactions;
	private double comments;
	private HashMap<String, HashMap<String, Double>> report;

	public Post(String user, String title, String content, Category category, LocalDateTime date, String link) {
		super(user);
		this.title = title;
		this.content = content;
		this.category = category;
		this.date = date;
		fullNewLink = link;
		state = State.UNVERIFIED;
		reactedUsers = new ArrayList<User>();
		rating = 0;
		reactions = 0;
		comments = 0;
		report = new HashMap<String, HashMap<String, Double>>();
		increaseReport();
		
	}
	
	/**
	 * Increase the report and its fields: Amount of comments, reactions and rating
	 */
	public void increaseReport() {
		
		HashMap<String, Double> temp = new HashMap<String, Double>();
		temp.put(MAP_KEY_COMMENTS, comments);
		temp.put(MAP_KEY_RATING, rating);
		temp.put(MAP_KEY_REACTIONS, reactions);
		  
		report.put(LocalDateTime.now().format(FORMAT), temp);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getFullNewLink() {
		return this.fullNewLink;
	}

	public void setFullNewLink(String fullNewLink) {
		this.fullNewLink = fullNewLink;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ArrayList<User> getReactedUsers() {
		return this.reactedUsers;
	}

	public void setReactedUsers(ArrayList<User> reactedUsers) {
		this.reactedUsers = reactedUsers;
	}

	public Comment getFirstComment() {
		return this.firstComment;
	}

	public void setFirstComment(Comment comments) {
		this.firstComment = comments;
	}

	public double getRating() {
		calculateRating();
		return this.rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public HashMap<String, HashMap<String, Double>> generateStats() {
		return report;
	}

	@Override
	public void calculateRating() {	
		int amountReactions = reactedUsers.size();
		int amountComments = (int)comments;
		
		LocalDateTime now = LocalDateTime.now();
		long difference = ChronoUnit.MINUTES.between(now, date);
		rating = (amountComments + amountReactions)/difference;
	}

	/**
	 * Define if a post is rateable according to the trending restrictions
	 * @return true in case the post is not older than a day, false any otherwise
	 */
	public boolean isRateable() {
		boolean rateable = false;
		LocalDateTime now = LocalDateTime.now();
		long difference = ChronoUnit.DAYS.between(now, date);
		
		if(difference < 1) {
			rateable = true;
		}
		
		return rateable;
	}

	/**
	 * Calculates the amount of comments in the post
	 * @return the amount of comments
	 */
	public int getAmountOfComments() {
		if(firstComment != null) {
			return getAmountOfComments(firstComment, 1);
		}else {
			return 0;
		}
	}

	/**
	 * Calculates recursively the amount of comments in the post
	 * @param currentComment, the current comment in the recursion
	 * @param amount, int the current amount of comments
	 * @return the amount of comments
	 */
	private int getAmountOfComments(Comment currentComment, int amount) {
		if(currentComment.getNextComment().equals(firstComment)) {
			return amount;
		}else {
			return getAmountOfComments(currentComment, amount + 1);
		}
	}
	
	/**
	 * Compare the posts according to their title
	 * @param otherPost
	 * @return
	 */
	public int compareTo(Post otherPost) {
		return title.compareTo(otherPost.getTitle());
	}

	public double getReactions() {
		return reactions;
	}

	public void setReactions(double reactions) {
		this.reactions = reactions;
	}

	public double getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public Comment getLastComment() {
		return lastComment;
	}

	public void setLastComment(Comment lastComment) {
		this.lastComment = lastComment;
	}
	
	public HashMap<String, HashMap<String, Double>> getReport() {
		return report;
	}
	
	public void setReport(HashMap<String, HashMap<String, Double>> report) {
		this.report = report;
	}

	@Override
	public String reportToString() {
		String reportString = "date;rating;comments;reactions\n";
		for (Map.Entry<String, HashMap<String, Double>> entry : report.entrySet()) {
			String tempDate = entry.getKey();
			double tempRating = entry.getValue().get(MAP_KEY_RATING);
			double tempComments = entry.getValue().get(MAP_KEY_COMMENTS);
			double tempReactions = entry.getValue().get(MAP_KEY_REACTIONS);
			String line = tempDate + ";" + tempRating + ";" + tempComments + ";" + tempReactions + "\n";
			reportString += line;
		}
		return reportString;
	}

	public void editState(String statePost) {
		State newState;
		if(statePost.equals(State.VERIFIED.toString())) {
			newState = State.VERIFIED;
		}else {
			newState = State.FAKE_NEW;
		}
		setState(newState);
	}

	public void addComment(Comment newComment) {
		if(firstComment == null) {
			firstComment = newComment;
			lastComment = firstComment;
			firstComment.setNextComment(lastComment);
		}else {
			lastComment.setNextComment(newComment);
			newComment.setNextComment(firstComment);
			lastComment = newComment;
		}
		comments++;
	}

	
	
}