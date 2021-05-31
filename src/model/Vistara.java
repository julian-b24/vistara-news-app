package model;

import java.util.ArrayList;

public class Vistara {

	private ArrayList<Moderator> mods;
	private User rootUser;
	private Category rootCategory;
	private ArrayList<Post> posts;
	private ArrayList<Post> trending;

	
	public void verifyAccount(String trim, String trim2) {
		
	}
	
	public void addUser(String username, String email, String password) {
		
		User user = new User(username, email, password);
		
		if(rootUser != null) {
			//Search for any coincidence with the username
			
			boolean repeatedName = searchUserByName(user.getUsername());
			addUser(rootUser, user);
			
			
		}else {
			rootUser = user;
		}
		
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

	private boolean searchUserByName(String username) {
		return searchUserByName(rootUser, username);		
	}
	
	private boolean searchUserByName(User root, String username) {
		if(root == null) {
			return false;
		}else		
	
		if(root.getUsername().compareTo(username) == 0) {
			return true;
		}
		else if(username.compareTo(root.getUsername())<0) {
			return searchUserByName(root.getLeftUser(), username);
		}else if(username.compareTo(root.getUsername())>0) {
			return searchUserByName(root.getRightUser(), username);
		}else {
			return false;
		}
	}
}