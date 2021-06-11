package thread;

import java.util.ArrayList;

import model.Post;
import model.Vistara;

public class ThreadRating extends Thread{
	//300000
	public final static int SLEEP = 70000;
	
	private Vistara vistara;
	
	public ThreadRating(Vistara vistara) {
		this.vistara = vistara;
	}
	
	@Override
	public void run() {
		
		ArrayList<Post> posts = vistara.getPosts();

		while(true) {
			
			ArrayList<Post> newTrending = new ArrayList<Post>();
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
