package model;

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

	public int compareTo() {
		return 0;
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

	@Override
	public HashMap<String, Double> generateStats() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return feed
	 */
	public ArrayList<Post> getFeed() {
		return feed;
	}

	/**
	 * 
	 * @param feed
	 */
	public void setFeed(ArrayList<Post> feed) {
		this.feed = feed;
	}


	
}