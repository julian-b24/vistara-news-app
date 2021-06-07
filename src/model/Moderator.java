package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Moderator extends User implements ModeratorManagement, Comparable<Moderator>{

	private ArrayList<Post> pendingPosts;				//Posts waiting for verification

	public Moderator(String username, String email, String password, LocalDateTime creationDay) {
		super(username, email, password, creationDay);
		pendingPosts = new ArrayList<Post>();
	}
	
	/**
	 * 
	 * @param pendingPosts
	 */
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