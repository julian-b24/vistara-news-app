package thread;

import java.io.IOException;

import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUsernameException;
import model.Vistara;

public class ThreadImportData implements Runnable{
	
	private Vistara vistara;
	
	public ThreadImportData(Vistara vistara) {
		this.vistara = vistara;
	}
	
	@Override
	public void run() {
		try {
			vistara.importData();
		} catch (IOException | RepeatedUsernameException | EmptyFieldsException | InvalidUserException e) {
			e.printStackTrace();
		}
	}
}
