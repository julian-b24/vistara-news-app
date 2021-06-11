package thread;


import ui.VistaraGUI;

public class ThreadImportData implements Runnable{
	
	public final static int time = 10000; //Time of execution in milliseconds
	
	private VistaraGUI gui;
	
	public ThreadImportData(VistaraGUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void run() {
		
		
	}
}
