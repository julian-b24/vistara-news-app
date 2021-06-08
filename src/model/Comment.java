package model;

import java.io.Serializable;

public class Comment extends Content implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Comment nextComment;
	private Comment previousComment;
	private String content;

	/**
	 * 
	 * @param user
	 * @param cont
	 */
	public Comment(String user, String cont) {
		super(user);
		throw new UnsupportedOperationException();
	}

	public Comment getNextComment() {
		return nextComment;
	}

	public void setNextComment(Comment nextComment) {
		this.nextComment = nextComment;
	}

	public Comment getPreviousComment() {
		return previousComment;
	}

	public void setPreviousComment(Comment previouComment) {
		this.previousComment = previouComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}