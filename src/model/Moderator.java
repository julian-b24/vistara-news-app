package model;

import java.util.ArrayList;

public class Moderator extends User {

	private Vistara firstMod;
	private Moderator nextMod;
	private ArrayList<Post> pendingPosts;

	public ArrayList<Post> getPendingPosts() {
		return this.pendingPosts;
	}

	/**
	 * 
	 * @param pendingPosts
	 */
	public void setPendingPosts(ArrayList<Post> pendingPosts) {
		this.pendingPosts = pendingPosts;
	}

}