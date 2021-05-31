package model;

import java.util.ArrayList;

import exceptions.EmptyFieldsException;
import exceptions.RepeatedUsernameException;

public class Vistara {

	private ArrayList<Moderator> mods;
	private User rootUser;
	private Category rootCategory;
	private ArrayList<Post> posts;
	private ArrayList<Post> trending;

	
	public void verifyAccount(String trim, String trim2) {
		
	}
	
	public boolean addUser(String username, String email, String password) throws RepeatedUsernameException, EmptyFieldsException {
		
		User user = new User(username, email, password);
		boolean added = true;
		
		if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
			throw new EmptyFieldsException(username, password, email);
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
}