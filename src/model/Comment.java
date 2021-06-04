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

}