package thread;

import java.util.ArrayList;

import model.Post;
import model.Vistara;

public class ThreadRating extends Thread{
	
	public final static int SLEEP = 300000;
	
	private Vistara vistara;
	
	@Override
	public void run() {
		
		ArrayList<Post> posts = vistara.getPosts();
		ArrayList<Post> newTrending = new ArrayList<Post>();
		
		while(true) {
			if(posts.size() > 0) {	
				for (Post post : posts) {
					double rating = post.getRating();
					post.increaseReport();
					if(rating >= Vistara.MIN_RATING && post.isRateable()) {
						newTrending.add(post);
					}
				}
				
				vistara.setTrending(newTrending);
			}
			try {
				sleep(SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
