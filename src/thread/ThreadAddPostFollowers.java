package thread;

import java.util.ArrayList;

import model.Post;
import model.User;

public class ThreadAddPostFollowers extends Thread{

	private User author;				//The author of the post
	private Post post;					//The post to be added
	
	public ThreadAddPostFollowers(User author, Post post) {
		this.author = author;
		this.post = post;
	}
	
	@Override
	public void run() {
		ArrayList<User> followers = author.getFollowers();
		for (User follower : followers) {
			follower.getFeed().add(post);
		}
	}
	
}
