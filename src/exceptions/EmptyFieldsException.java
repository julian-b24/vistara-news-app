package exceptions;

public class EmptyFieldsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String[] emptyFields;
	
	public EmptyFieldsException(String[] emptyFields) {
		super("Some fields are empty");
		this.emptyFields = emptyFields;
	}
	
	public String getEmptyFields() {
		int empties = 0;
		for (String field : emptyFields) {
			if(field.isEmpty()) {
				empties++;
			}
		}
		
		return "Hay " + empties + " vacíos";
	}
	
}
