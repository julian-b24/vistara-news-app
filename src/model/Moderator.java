package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Moderator extends User implements ModeratorManagement, Comparable<Moderator>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Post> pendingPosts;				//Posts waiting for verification

	public Moderator(String username, String email, String password) {
		super(username, email, password);
		pendingPosts = new ArrayList<Post>();
	}
	
	public void setPendingPosts(ArrayList<Post> pendingPosts) {
		this.pendingPosts = pendingPosts;
	}

	@Override
	public void createCategory(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyPost() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Post> getPendingPosts() {
		return this.pendingPosts;
	}

	@Override
	public int compareTo(Moderator otherMod) {
		return getCreationDate().compareTo(otherMod.getCreationDate());
	}
}