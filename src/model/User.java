package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

public class User implements StatsCalculable{

	private User rightUser;
	private User leftUser;
	private User parentUser;
	private ArrayList<User> followers;
	private ArrayList<User> following;
	private String username;
	private Image profilePic;
	private String description;
	private String email;
	private String password;
	private Event firstEvent;
	private LocalDateTime creationDate;
	private ArrayList<Post> feed;
	private ArrayList<Post> ownPosts;
	private ArrayList<Post> reactedPosts;

	/**
	* User: User class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username
 	* @param email
 	* @param password
	*/
	public User(String username, String email, String password) {
		
		feed = new ArrayList<>();

		this.username = username;
		this.email = email;
		this.password = password;
		setCreationDate(LocalDateTime.now());
		followers = new ArrayList<User>();
		following = new ArrayList<User>();
		feed = new ArrayList<Post>();
		ownPosts = new ArrayList<Post>();
		reactedPosts = new ArrayList<Post>();
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public Image getProfilePic() {
		return this.profilePic;
	}

	/**
	 * 
	 * @param profilePic
	 */
	public void setProfilePic(Image profilePic) {
		this.profilePic = profilePic;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<User> getFollowers() {
		return followers;
	}

	/**
	 * 
	 * @param followers
	 */
	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}

	public ArrayList<User> getFollowing() {
		return following;
	}

	/**
	 * 
	 * @param following
	 */
	public void setFollowing(ArrayList<User> following) {
		this.following = following;
	}

	public User getRightUser() {
		return this.rightUser;
	}

	/**
	 * 
	 * @param rightUser
	 */
	public void setRightUser(User rightUser) {
		this.rightUser = rightUser;
	}

	public User getLeftUser() {
		return this.leftUser;
	}

	/**
	 * 
	 * @param leftUser
	 */
	public void setLeftUser(User leftUser) {
		this.leftUser = leftUser;
	}

	/**
	 * Compares two users according to their username
	 * @param otherUser, User to compare
	 * @return int 1, in case the user which is being compared with otherUser is greater, -1 in case is lower, 0 in case their are equals
	 */
	public int compareTo(User otherUser) {
		int compare = 0;
		if(username.compareTo(otherUser.getUsername()) > 0) {
			compare = 1;
		}else if (username.compareTo(otherUser.getUsername()) < 0) {
			compare = -1;
		}
		return compare;
	}

	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public Event getFirstEvent() {
		return firstEvent;
	}

	public void setFirstEvent(Event firstEvent) {
		this.firstEvent = firstEvent;
	}

	public ArrayList<Post> getOwnPosts() {
		return ownPosts;
	}

	public void setOwnPosts(ArrayList<Post> ownPosts) {
		this.ownPosts = ownPosts;
	}

	public ArrayList<Post> getReactedPosts() {
		return reactedPosts;
	}

	public void setReactedPosts(ArrayList<Post> reactedPosts) {
		this.reactedPosts = reactedPosts;
	}

	public ArrayList<Post> getFeed() {
		return feed;
	}
	
	public void setFeed(ArrayList<Post> feed) {
		this.feed = feed;
	}

	@Override
	public HashMap<String, Double> generateStats() {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Generates a clone of the user
	 * @return the clone of the user
	 */
	public User getClone() {
		User clone = new User("", "", "");
		clone.setFollowers(this.getFollowers());
		clone.setFollowing(this.getFollowing());
		clone.setUsername(this.getUsername());
		clone.setProfilePic(this.getProfilePic());
		clone.setDescription(this.getDescription());
		clone.setEmail(this.getEmail());
		clone.setPassword(this.getPassword());
		clone.setFirstEvent(this.getFirstEvent());
		clone.setCreationDate(this.getCreationDate());
		clone.setFeed(this.getFeed());
		clone.setOwnPosts(this.getOwnPosts());
		clone.setReactedPosts(this.getReactedPosts());
		return clone;
	}

}