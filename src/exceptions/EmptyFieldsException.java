package exceptions;

public class EmptyFieldsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String email;
	
	public EmptyFieldsException(String username, String password, String email) {
		super("Some fields are empty");
		this.username = username;
		this.email = email;
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
		
		if(email.isEmpty()) {
			empty+= "email";
		}
		
		return empty;
	}
	
}
