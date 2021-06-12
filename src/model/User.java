package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import sun.security.action.GetBooleanAction;

public class User implements StatsCalculable, Serializable{

	private static final long serialVersionUID = 1L;
	
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
	private LocalDateTime creationDate;
	private ArrayList<Post> feed;
	private ArrayList<Post> ownPosts;
	private ArrayList<Post> reactedPosts;
	private int verifiedPosts;
	private int fakePosts;

	/**
	* User: User class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username String
 	* @param email String
 	* @param password String
	*/
	public User(String username, String email, String password) {
		
		feed = new ArrayList<>();
		this.username = username;
		this.email = email;
		this.password = password;
		creationDate = LocalDateTime.now();
		followers = new ArrayList<User>();
		following = new ArrayList<User>();
		feed = new ArrayList<Post>();
		ownPosts = new ArrayList<Post>();
		reactedPosts = new ArrayList<Post>();
		verifiedPosts = 0;
		fakePosts = 0;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	* setUsername: Sets the username <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username String
	*/
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	* getProfilePic: Gets the profile pic<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return profilePic Image
	*/
	public Image getProfilePic() {
		return this.profilePic;
	}

	/**
	* setProfilePic: Sets the profile pic<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param profilePic Image
	*/
	public void setProfilePic(Image profilePic) {
		this.profilePic = profilePic;
	}

	/**
	* getDescription: Gets description<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return description String
	*/
	public String getDescription() {
		return this.description;
	}

	/**
	* setDescription: Sets description<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param description String
	*/
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	* getEmail: Gets Email<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return email String
	*/
	public String getEmail() {
		return this.email;
	}

	/**
	* setEmail: sets Email<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param email String
	*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	* getPassword: Gets password<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return password String
	*/
	public String getPassword() {
		return this.password;
	}

	/**
	* setPassword: Gets password<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param password String
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	* getFollowers: Gets followers<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return followers ArrayList users
	*/
	public ArrayList<User> getFollowers() {
		return followers;
	}

	/**
	* setFollowers: Sets followers<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param followers ArrayList users
	*/
	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}

	/**
	* getFollowing: Gets following<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return following ArrayList users
	*/
	public ArrayList<User> getFollowing() {
		return following;
	}

	/**
	* setFollowing: Sets following<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param following ArrayList users
	*/
	public void setFollowing(ArrayList<User> following) {
		this.following = following;
	}

	/**
	* getRightUser: Gets right user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return rightUser Right user
	*/
	public User getRightUser() {
		return this.rightUser;
	}

	/**
	* setRightUser: Sets right user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param rightUser Right user
	*/
	public void setRightUser(User rightUser) {
		this.rightUser = rightUser;
	}

	/**
	* getLeftUser: Gets left user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return leftUser Left user
	*/
	public User getLeftUser() {
		return this.leftUser;
	}

	/**
	* setLeftUser: Sets left user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param leftUser Left user
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

	/**
	* getParentUser: Gets parent user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return parentUser Parent user
	*/
	public User getParentUser() {
		return parentUser;
	}

	/**
	* setParentUser: Sets parent user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param parentUser Parent user
	*/
	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
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
	public HashMap<String, HashMap<String, Double>> generateStats() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	* getCreationDate: Gets date of creation of the user <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return creationDate LocalDateTime
	*/
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	/**
	* setCreationDate: Sets date of creation of the user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param creationDate LocalDateTime
	*/
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
		clone.setCreationDate(this.getCreationDate());
		clone.setFeed(this.getFeed());
		clone.setOwnPosts(this.getOwnPosts());
		clone.setReactedPosts(this.getReactedPosts());
		return clone;
	}
	
	/**
	* deletePost: Deletes a post <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param publicationTime LocalDateTime
	*/
	public void deletePost(LocalDateTime publicationTime) {
		
		for (int i = 0; i < ownPosts.size(); i++) {
			if(ownPosts.get(i).getDate() == publicationTime) {
				ownPosts.remove(i);
			}
		}
		
	}

	/**
	 * Gets the integer number for the amount of verified posts that the user has
	 * @return verifiedPosts the amount if verified posts of the user
	 */
	public int getVerifiedPosts() {
		return verifiedPosts;
	}
	
	/**
	* setVerifiedPosts: Sets the amount of verified posts of the user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param verifiedPosts int
	*/
	public void setVerifiedPosts(int verifiedPosts) {
		this.verifiedPosts = verifiedPosts;
	}

	/**
	* searchUserFollowing: Searches a user with a given username in the list of users following <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username String
	* @return user User
	*/
	public User searchUserFollowing(String username) {
		User user = null;
		for (int i = 0; i < following.size(); i++) {
			if(following.get(i).getUsername().equals(username)) {
				user = following.get(i);
			}
		}
		return user;
	}
	
	/**
	* getFakePosts: Gets The amount of fake posts of the user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return fakeposts;
	*/
	public int getFakePosts() {
		return fakePosts;
	}

	/**
	* setFakePosts: Sets The amount of fake posts of the user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param fakePosts int
	*/
	public void setFakePosts(int fakePosts) {
		this.fakePosts = fakePosts;
	}

	public boolean searchReactedPost(Post reacted) {
		boolean reactedAlready = false;
		for (int i = 0; i < getReactedPosts().size() && !reactedAlready; i++) {
			if(reactedPosts.get(i) == reacted) {
				reactedAlready = true;
			}
		}
		return reactedAlready;
	}

	@Override
	public String reportToString() {
		// TODO Auto-generated method stub
		return null;
	}
}