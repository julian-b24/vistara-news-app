package exceptions;

public class RepeatedUsernameException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	public RepeatedUsernameException(String username) {
		super("The choosed username already exist");
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

}
