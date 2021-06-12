package model;

import java.io.Serializable;

public class Comment extends Content implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Comment nextComment;
	private Comment previousComment;
	private Comment nextCommentVistara;
	private String content;

	/**
	* Comment: Comment class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param user String
	* @param cont String
	*/
	public Comment(String user, String cont) {
		super(user);
		content = cont;
	}

	/**
	* getNextComment: Gets the next comment of the current comment <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return  nextComment Comment, next comment
	*/
	public Comment getNextComment() {
		return nextComment;
	}

	/**
	* setNextComment: Sets the next comment of the current comment <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param  nextComment Comment, next comment
	*/
	public void setNextComment(Comment nextComment) {
		this.nextComment = nextComment;
	}

	/**
	* getPreviousComment: Gets the previous comment of the current comment <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return  previousComment Comment, previous comment
	*/
	public Comment getPreviousComment() {
		return previousComment;
	}

	/**
	* setPreviousComment: Sets the previous comment of the current comment <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param previouComment Comment, previous comment
	*/
	public void setPreviousComment(Comment previouComment) {
		this.previousComment = previouComment;
	}

	/**
	* getContent: Gets the comment content <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return  content String
	*/
	public String getContent() {
		return content;
	}

	/**
	* setContent: Sets the comment content <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param  content String
	*/
	public void setContent(String content) {
		this.content = content;
	}

	public Comment getNextCommentVistara() {
		return nextCommentVistara;
	}

	public void setNextCommentVistara(Comment nextCommentVistara) {
		this.nextCommentVistara = nextCommentVistara;
	}

	
	
}