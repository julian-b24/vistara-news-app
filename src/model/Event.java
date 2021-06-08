package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Event extends Content implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalDateTime date;
	
	public Event(String user, LocalDateTime date) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}