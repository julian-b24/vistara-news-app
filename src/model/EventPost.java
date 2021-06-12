package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EventPost extends Post implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Event event;

	public EventPost(String user, String title, String content, Category category, LocalDateTime date, String link, Event event) {
		
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
	* setEvent: Sets event <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param event Event
	*/
	public void setEvent(Event event) {
		this.event = event;
	}

}