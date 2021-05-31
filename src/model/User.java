package model;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

public class User implements StatsCalculable{

	private User rightUser;
	private ArrayList<User> followers;
	private ArrayList<User> following;
	private User leftUser;
	private User parentUser;
	private String username;
	private Image profilePic;
	private String description;
	private String email;
	private String password;
	private Event firstEvent;

	/**
	* User: User class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username
 	* @param email
 	* @param password
	*/
	public User(String username, String email, String password) {
		
		this.username = username;
		this.email = email;
		this.password = password;
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
		// TODO - implement User.getFollowers
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param followers
	 */
	public void setFollowers(ArrayList<User> followers) {
		// TODO - implement User.setFollowers
		throw new UnsupportedOperationException();
	}

	public ArrayList<User> getFollowing() {
		// TODO - implement User.getFollowing
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param following
	 */
	public void setFollowing(ArrayList<User> following) {
		// TODO - implement User.setFollowing
		throw new UnsupportedOperationException();
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
		// TODO - implement User.compareTo
		throw new UnsupportedOperationException();
	}


	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	@Override
	public HashMap<String, Double> generateStats() {
		// TODO Auto-generated method stub
		return null;
	}

}