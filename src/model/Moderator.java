package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Moderator extends User implements Comparable<Moderator>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Post> pendingPosts;				//Posts waiting for verification

	/**
	* Moderator: Moderator class constructor<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username String
	* @param email String
	* @param password String
	*/
	public Moderator(String username, String email, String password) {
		super(username, email, password);
		pendingPosts = new ArrayList<Post>();
	}
	
	/**
	* setPendingPosts: Sets list of pending posts <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param pendingPosts ArrayLitst of posts
	*/
	public void setPendingPosts(ArrayList<Post> pendingPosts) {
		this.pendingPosts = pendingPosts;
	}
	
	/**
	* getPendingPosts: Gets list of pending posts <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return pendingPosts ArrayLitst of posts
	*/
	public ArrayList<Post> getPendingPosts() {
		return this.pendingPosts;
	}

	@Override
	public int compareTo(Moderator otherMod) {
		return pendingPosts.size() - otherMod.getPendingPosts().size();
	}

	/**
	 * Sorts the pending posts of all moderators list according to their amount of reactions <br>
	 * Implements bubble sort
	 */
	public void sortPendingPosts() {
		boolean changed = true;
		for (int i = 1; i < pendingPosts.size() - 1 && changed; i++) {
			changed = false;
			
			for (int j = 0; j < pendingPosts.size() - i; j++) {
				
				if (pendingPosts.get(j).getReactions() > pendingPosts.get(j + 1).getReactions()) {
					Post temp = pendingPosts.get(j);
					pendingPosts.set(j, pendingPosts.get(j + 1));
					pendingPosts.set(j + 1, temp);
					changed = true;
				}
			}	
		}
	}

	/**
	* ModeremovePost: Remove post from moderator list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param postToRemove Post, post to remove
	*/
	public void removePost(Post postToRemove) throws IndexOutOfBoundsException{
		int pos = -1;
		if(pendingPosts.size() > 0) {
			
			int low = 0;
			int top = pendingPosts.size();
			boolean found = false;
			while(low <= top && !found) {
				int mid = (low + top)/2;
				if (pendingPosts.get(mid).getReactions() == postToRemove.getReactions()) {
					pos = mid;
					found = true;
				
				} else if (pendingPosts.get(mid).getReactions() < postToRemove.getReactions()) {
					low = mid + 1;
				} else {
					top = mid - 1;
				}
			}
		}
		pendingPosts.remove(pos);
	}
}