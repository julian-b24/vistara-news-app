package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Event extends Content {

	private LocalDateTime date;
	
	public Event(User user, Image pic) {
		super(user, pic);
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}