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
	
	/**
	* Post: Post class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param user String creator username
	* @param title String post title
	* @param content String 
	* @param category Category
	* @param date LocalDateTime time of creations
	* @param link String link to the full new
	*/
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
	
	/**
	* getTitle: Gets the post title<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return title
	*/
	public String getTitle() {
		return this.title;
	}

	/**
	* setTitle: Sets the post title<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param title String
	*/
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	* getContent: Gets the post content<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return content
	*/
	public String getContent() {
		return this.content;
	}

	/**
	* setContent: Sets the post content<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param content String
	*/
	public void setContent(String content) {
		this.content = content;
	}

	/**
	* getCategory: Gets the post category<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return title
	*/
	public Category getCategory() {
		return this.category;
	}

	/**
	* setCategory: Sets the post category<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param category Category
	*/
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	* getDate: Gets the post creation date<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return date
	*/
	public LocalDateTime getDate() {
		return this.date;
	}

	/**
	* setDate: Sets the post creation date<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param date LocalDateTime
	*/
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	* getFullNewLink: Gets the full link<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return fillNewLink
	*/
	public String getFullNewLink() {
		return this.fullNewLink;
	}

	/**
	* setFullNewLink: Sets the full link<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param fullNewLink String
	*/
	public void setFullNewLink(String fullNewLink) {
		this.fullNewLink = fullNewLink;
	}

	/**
	* getState: Gets the state<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return state
	*/
	public State getState() {
		return this.state;
	}

	/**
	* setState: Sets the state<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param state State
	*/
	public void setState(State state) {
		this.state = state;
	}

	/**
	* getReactedUsers: Gets the reacted users<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return reactedUsers
	*/
	public ArrayList<User> getReactedUsers() {
		return this.reactedUsers;
	}

	/**
	* setReactedUsers: Sets the reacted users<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param reactedUsers ArrayList users
	*/
	public void setReactedUsers(ArrayList<User> reactedUsers) {
		this.reactedUsers = reactedUsers;
	}

	/**
	* getFirstComment: Gets the first comment<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return firstComment
	*/
	public Comment getFirstComment() {
		return this.firstComment;
	}

	/**
	* setFirstComment: Sets the first comment<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param comments Comment comments
	*/
	public void setFirstComment(Comment comments) {
		this.firstComment = comments;
	}

	/**
	* getRating: Gets the rating<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return rating
	*/
	public double getRating() {
		calculateRating();
		return this.rating;
	}

	/**
	* setRating: Sets the rating<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param rating double
	*/
	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public HashMap<String, HashMap<String, Double>> generateStats() {
		return report;
	}

	/**
	* calculateRating: Calculates rating<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	*/
	@Override
	public void calculateRating() {	
		int amountReactions = reactedUsers.size();
		int amountComments = (int)comments;
		
		LocalDateTime now = LocalDateTime.now();
		long difference = ChronoUnit.MINUTES.between(date, now);
		rating = (amountComments + amountReactions)/difference;
	}

	/**
	 * Define if a post is rateable according to the trending restrictions
	 * @return true in case the post is not older than a day, false any otherwise
	 */
	public boolean isRateable() {
		boolean rateable = false;
		LocalDateTime now = LocalDateTime.now();
		long difference = ChronoUnit.DAYS.between(date, now);
		
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
	* compareTo: Compares posts <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param otherPost Post
	* @return dif int containing of post according to title
	*/
	public int compareTo(Post otherPost) {
		int dif = title.compareTo(otherPost.getTitle());
		return dif;
	}

	/**
	* getReactions: Gets the reactions<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return reactions
	*/
	public double getReactions() {
		return reactions;
	}

	/**
	* setReactions: Sets the reactions<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param reactions double
	*/
	public void setReactions(double reactions) {
		this.reactions = reactions;
	}

	/**
	* getComments: Gets the amount of comments<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return reactions
	*/
	public double getComments() {
		return comments;
	}

	/**
	* setComments: Gets the amount of comments<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param comments int
	*/
	public void setComments(int comments) {
		this.comments = comments;
	}

	/**
	* getLastCommment: Gets the last comments<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return reactions
	*/
	public Comment getLastComment() {
		return lastComment;
	}

	/**
	* setLastCommment: Sets the last comments<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param lastComment Comment
	*/
	public void setLastComment(Comment lastComment) {
		this.lastComment = lastComment;
	}
	
	public HashMap<String, HashMap<String, Double>> getReport() {
		return report;
	}
	
	public void setReport(HashMap<String, HashMap<String, Double>> report) {
		this.report = report;
	}
	
	/**
	* reportToString: Generates reports<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return reportString
	*/
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

	/**
	* editState: Edit state<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param statePost String new state
	*/
	public void editState(String statePost) {
		State newState;
		if(statePost.equals(State.VERIFIED.toString())) {
			newState = State.VERIFIED;
		}else {
			newState = State.FAKE_NEW;
		}
		setState(newState);
	}

	/**
	* addComment: Add comment to post<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param newComment Comment new comment
	*/
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