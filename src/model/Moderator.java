package model;

import java.util.ArrayList;

public class Moderator extends User implements ModeratorManagement{

	private ArrayList<Post> pendingPosts;

	public ArrayList<Post> getPendingPosts() {
		return this.pendingPosts;
	}

	public Moderator(String username, String email, String password) {
		super(username, email, password);
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

}