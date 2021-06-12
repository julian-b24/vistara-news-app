package thread;

import java.io.IOException;

import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUsernameException;
import model.Vistara;
import ui.VistaraGUI;

public class ThreadImportData implements Runnable{
	
	private Vistara vistara;
	private VistaraGUI gui;
	
	public ThreadImportData(Vistara vistara, VistaraGUI gui) {
		this.vistara = vistara;
		this.gui = gui;
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
