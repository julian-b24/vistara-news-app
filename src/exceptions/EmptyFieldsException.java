package exceptions;

public class EmptyFieldsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String email;
	
	public EmptyFieldsException(String username, String password) {
		super("Some fields are empty");
		this.username = username;
		this.password = password;
	}
	
	public String getEmptyFields() {
		String empty = "";
		if(username.isEmpty()) {
			empty+= "username ";
		}
		
		if(password.isEmpty()) {
			empty+= "password ";
		}
		
		return empty;
	}
	
}
