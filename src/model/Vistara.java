package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUsernameException;
import javafx.scene.image.Image;
import thread.ThreadAddPostFollowers;

public class Vistara {

	public final static String IMAGE_PATH = "file:imgs/";
	
	private ArrayList<Moderator> mods;
	private User rootUser;
	private Category rootCategory;
	private ArrayList<Post> posts;
	private ArrayList<Post> trending;
	
	public boolean addUser(String username, String email, String password, LocalDateTime dateOfCreation) throws RepeatedUsernameException, EmptyFieldsException {
		
		Moderator user = new Moderator(username, email, password, dateOfCreation);
		boolean added = true;
		
		if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
			throw new EmptyFieldsException(username, password);
		}
		
		if(rootUser != null) {
			//Search for any coincidence with the username	
			boolean repeatedName = searchUserByName(user.getUsername());
			if(!repeatedName) {
				addUser(rootUser, user);
			}else {
				throw new RepeatedUsernameException(username);
			}
			
		}else {
			rootUser = user;
		}
		return added;
	}

	public ArrayList<Moderator> getMods() {
		return mods;
	}

	public void setMods(ArrayList<Moderator> mods) {
		this.mods = mods;
	}

	public User getRootUser() {
		return rootUser;
	}

	public void setRootUser(User rootUser) {
		this.rootUser = rootUser;
	}

	public Category getRootCategory() {
		return rootCategory;
	}

	public void setRootCategory(Category rootCategory) {
		this.rootCategory = rootCategory;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public ArrayList<Post> getTrending() {
		return trending;
	}

	public void setTrending(ArrayList<Post> trending) {
		this.trending = trending;
	}

	private void addUser(User current, User user) {
		
		if(user.getUsername().compareTo(current.getUsername())<0) {
			if(current.getLeftUser() == null) {
				current.setLeftUser(user);
				user.setParentUser(current);
			}else {
				addUser(current.getLeftUser(), user);
			}
		}else {
			if(current.getRightUser() == null) {
				current.setRightUser(user);
				user.setParentUser(current);
			}else {
				addUser(current.getRightUser(), user);
			}
		}
	}

	public boolean searchUserByName(String username) {
		return searchUserByName(rootUser, username);		
	}
	
	private boolean searchUserByName(User current, String username) {
		if(current == null) {
			return false;
		}		
	
		if(username.compareTo(current.getUsername())<0) {
			return searchUserByName(current.getLeftUser(), username);
			
		}else if(username.compareTo(current.getUsername())>0) {
			return searchUserByName(current.getRightUser(), username);
			
		}else {
			return true;
		}
	}

	public User verifyLogin(String username, String password) throws InvalidUserException, EmptyFieldsException {
		
		if(username.isEmpty() || password.isEmpty()) {
			throw new EmptyFieldsException(username, password);
		}
		
		//search the user in the binary tree
		User searchedUser = searchUser(username);
		if(searchedUser == null) {
			throw new InvalidUserException(username, password);
		}
		
		if(!searchedUser.getPassword().equals(password)) {
			throw new InvalidUserException(username, password);
		}
		
		return searchedUser;
	}
	
	public User searchUser(String username) {
		return searchUser(rootUser, username);		
	}
	
	private User searchUser(User current, String username) {
		if(current == null) {
			return null;
		}		
	
		if(username.compareTo(current.getUsername())<0) {
			return searchUser(current.getLeftUser(), username);
			
		}else if(username.compareTo(current.getUsername())>0) {
			return searchUser(current.getRightUser(), username);
			
		}else {
			return current;
		}
	}
	
	/**
	 * Creates a post and add it to the list of posts of vistara, the author and the followers of the author
	 * @param title, String, the title of the post. It must be different from null and an empty string
	 * @param content, String, the content of the post. It must be different from null and an empty string
	 * @param categoryName, String, name of the category. It must be different from null and an empty string
	 * @param author, User, the user that is creating the post
	 */
	public void createPost(String title, String content, String categoryName, User author, String link) {
		
		Category category = searchCategory(categoryName);
		LocalDateTime date = LocalDateTime.now();
		Post post = new Post(author, title, content, category, date, link);
		posts.add(post);
		author.getOwnPosts().add(post);
		ThreadAddPostFollowers addThread = new ThreadAddPostFollowers(author, post);
		addThread.start();
		//Rating
	}
	
	public boolean addCategory(String newCat) {
		boolean added = true;
		Category newCategory = new Category(newCat);
		
		Category repeatedCategory = searchCategory(newCat);
		
		if(repeatedCategory == null) {
			if(rootCategory == null) {
				
				rootCategory = newCategory;
			}else {
				addCategory(rootCategory, newCategory);
			}
		}else {
			added = false;
		}
		
		return added;
	}
	
	private void addCategory(Category currentCat, Category newCat) {
		
		if(newCat.getName().compareTo(currentCat.getName())<0) {
			if(currentCat.getLeftCategory() == null) {
				currentCat.setLeftCategory(newCat);
				newCat.setParent(currentCat);
			}else {
				addCategory(currentCat.getLeftCategory(), newCat);
			}
		}else {
			if(currentCat.getRightCategory() == null) {
				currentCat.setRightCategory(newCat);
				newCat.setParent(currentCat);
			}else {
				addCategory(currentCat.getRightCategory(), newCat);
			}
		}
	}

	/**
	 * Search a category making a call to a recursive method, that goes through the BST of categories
	 * @param categoryName, String name of the category. It must be different from null and empty string
	 * @return The searched category if its on the BST any other case returns null 
	 */
	public Category searchCategory(String categoryName) {
		return searchCategory(categoryName, rootCategory);
	}

	/**
	 * Search a category according to its name in the BST of categories recursively
	 * @param categoryName, String name of the category. It must be different from null and empty string
	 * @param currentCategory, Current category in the recursive process
	 * @return The searched category if its on the BST returns null 
	 */
	private Category searchCategory(String categoryName, Category currentCategory) {
		if(currentCategory == null) {
			return null;
		}
		
		if(currentCategory.getName().equals(categoryName)) {
			return currentCategory;
		}else if(currentCategory.getName().compareTo(categoryName) > 0){
			return searchCategory(categoryName, currentCategory.getLeftCategory());
		}else {
			return searchCategory(categoryName, currentCategory.getRightCategory());
		}
	}
	
	/**
	 * Edits the information of an user in the BST
	 * @param user, User, the user that will be edited
	 * @param username, String, the new name of the user
	 * @param password, String, the new password of the user
	 * @param email, String, the new email of the user
	 * @param description, String, the new description of the user
	 * @param profilePicName, String, the new path of the user's profile picture
	 * @throws RepeatedUsernameException in case the new username already exists in the app
	 */
	public void editUser(User user, String username, String password, String email, String description, String profilePicName) throws RepeatedUsernameException {
		boolean repeatedName = searchUserByName(username);
		if(!repeatedName) {
			User copy = user.getClone();
			removeUser(user);
			copy.setUsername(username);
			copy.setPassword(password);
			copy.setEmail(email);
			copy.setDescription(description);
			Image profilePic = new Image(IMAGE_PATH + profilePicName);
			copy.setProfilePic(profilePic);
			addUser(rootUser, copy);
			
		}else {
			throw new RepeatedUsernameException(username);
		}
	}
	
	/**
	 * Removes an user from the BST of users
	 * @param user, the user that will be removed
	 */
	private void removeUser(User user) {
		
		if(user != null) {
			
			if(user.getLeftUser() == null && user.getRightUser() == null) {
				if(user == rootUser) {
					rootUser = null;
				}else if (user.compareTo(user.getParentUser()) <= 0) {
					user.getParentUser().setLeftUser(null);
				} else {
					user.getParentUser().setRightUser(null);
				}
				
				user.setParentUser(null);
			}else if(user.getLeftUser() == null || user.getRightUser() == null) {
				
				User child;
				if(user.getLeftUser() == null) {
					child = user.getLeftUser();
				} else {
					child = user.getRightUser();
				}
				
				child.setParentUser(user.getParentUser());
				
				if(user == rootUser) {
					rootUser = child;
				}else if (user.compareTo(user.getParentUser()) <= 0) {
					user.getParentUser().setLeftUser(child);
				} else {
					user.getParentUser().setRightUser(child);
				}
			}else {
				User successor = getMin(user.getRightUser());
				user.setFollowers(successor.getFollowers());
				user.setFollowing(successor.getFollowing());
				user.setUsername(successor.getUsername());
				user.setProfilePic(successor.getProfilePic());
				user.setDescription(successor.getDescription());
				user.setEmail(successor.getEmail());
				user.setPassword(successor.getPassword());
				user.setFirstEvent(successor.getFirstEvent());
				user.setCreationDate(successor.getCreationDate());
				user.setFeed(successor.getFeed());
				user.setOwnPosts(successor.getOwnPosts());
				user.setReactedPosts(successor.getReactedPosts());
				
				removeUser(successor);
			}
		}
	}

	private User getMin(User currentUser) {
		if(currentUser.getLeftUser() == null) {
			return currentUser;
		}else {
			return getMin(currentUser.getLeftUser());
		}
	}

	public void reOrderModerators(User currentUser) {
		Collections.sort(mods);
	}
	
}