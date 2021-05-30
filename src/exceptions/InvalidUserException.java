package exceptions;

public class InvalidUserException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public InvalidUserException(String username, String password) {
		super("The username or the password don't match with any user in the app");
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
