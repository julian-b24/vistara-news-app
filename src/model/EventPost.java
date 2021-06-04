package model;

import java.time.LocalDateTime;

public class EventPost extends Post {

	private Event event;

	public EventPost(User user, String title, String content, Category category, LocalDateTime date, String link, Event event) {
		
		super(user, title, content, category, date, link);
		this.event = event;
	}
	
	public EventPost(Post post, Event event) {
		super(post.getAuthor(), post.getTitle(), post.getContent(), post.getCategory(), post.getDate(), post.getFullNewLink());
		this.event = event;
	}
	
	public Event getEvent() {
		return this.event;
	}

	/**
	 * 
	 * @param event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

}