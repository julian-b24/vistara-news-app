package thread;


import animatefx.animation.Bounce;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ui.VistaraGUI;

public class ThreadLoadingPane extends Thread{
	
	public final static int time = 10000; //Time of execution in milliseconds
	
	private VistaraGUI gui;
	private Circle circle1;
	private Circle circle2;
	private Circle circle3;
	private Rectangle lineRectangle;
	
	public ThreadLoadingPane(VistaraGUI gui, Circle circle1, Circle circle2, Circle circle3,
							 Rectangle lineRectangle) {
		this.gui = gui;
		this.circle1 = circle1;
		this.circle2 = circle2;
		this.circle3 = circle3;
		this.lineRectangle = lineRectangle;
	}
	
	@Override
	public void run() {
		
		//loads the pane
		gui.loadLoadingPane();
		
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		while((end-start) < time) {
			//circles animation
			new Bounce(circle1).setCycleDuration(4).setCycleCount(4).setDelay(Duration.valueOf("100ms")).play();;
			new Bounce(circle2).setCycleDuration(4).setCycleCount(4).setDelay(Duration.valueOf("100ms")).play();;
			new Bounce(circle3).setCycleDuration(4).setCycleCount(4).setDelay(Duration.valueOf("100ms")).play();;
			
			//line animation
			lineRectangle.setWidth(lineRectangle.getWidth() + 20);
			
			end = System.currentTimeMillis();
		}
		gui.loadFeed(null);
	}
}
