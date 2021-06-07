package model;

public class Comment extends Content {

	private Comment nextComment;
	private Comment previouComment;
	private String content;

	/**
	 * 
	 * @param user
	 * @param pic
	 * @param cont
	 */
	public Comment(User user, String cont) {
		super(user);
		throw new UnsupportedOperationException();
	}

	public Comment getNextComment() {
		return nextComment;
	}

	public void setNextComment(Comment nextComment) {
		this.nextComment = nextComment;
	}

	public Comment getPreviouComment() {
		return previouComment;
	}

	public void setPreviouComment(Comment previouComment) {
		this.previouComment = previouComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}