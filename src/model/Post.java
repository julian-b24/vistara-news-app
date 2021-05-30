package model;

public class Post {
	
	private String usernameCreator;		//The username of the account who publishes the post
	private String profilePicSrc;		//The source path to the profile picture
	private String postTittle;			//The tittle of the new
	private String postDetails;			//The post text or information
	private int verificationState;		//Determines whether the post is not verified, verified or fake
	private int numReactions;			//The amount of reactions that the post possess
	private int numCommentaries;		//The amount of commentaries that the post possess
	
	public Post(String userN, String tit, String dets) {
		
		verificationState = 1;		//
		numReactions = 0;
		numCommentaries = 0;
		
		usernameCreator = userN;
		postTittle = tit;
		postDetails = dets;
		
	}

	public String getUsernameCreator() {
		return usernameCreator;
	}

	public void setUsernameCreator(String usernameCreator) {
		this.usernameCreator = usernameCreator;
	}

	public String getProfilePicSrc() {
		return profilePicSrc;
	}

	public void setProfilePicSrc(String profilePicSrc) {
		this.profilePicSrc = profilePicSrc;
	}

	public String getPostTittle() {
		return postTittle;
	}

	public void setPostTittle(String postTittle) {
		this.postTittle = postTittle;
	}

	public String getPostDetails() {
		return postDetails;
	}

	public void setPostDetails(String postDetails) {
		this.postDetails = postDetails;
	}

	public int getVerificationState() {
		return verificationState;
	}

	public void setVerificationState(int verificationState) {
		this.verificationState = verificationState;
	}

	public int getNumReactions() {
		return numReactions;
	}

	public void setNumReactions(int numReactions) {
		this.numReactions = numReactions;
	}

	public int getNumCommentaries() {
		return numCommentaries;
	}

	public void setNumCommentaries(int numCommentaries) {
		this.numCommentaries = numCommentaries;
	}
	
	//Getters and setters
	
}
