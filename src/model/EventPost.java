package model;

import javafx.scene.image.Image;

public class EventPost extends Post {

	private Event event;

	public EventPost(User user, Image pic) {
		super(user, pic);
		// TODO Auto-generated constructor stub
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