package screens;

/**
 * Represents a screen that serves as a superclass to different screens of the game. This screen can be 
 * drawn on and can detect user input.
 * 
 * @author Ido Haiby
 * @version 5/5/2022 
 */
public abstract class Screen {

	/**
	 * The width and height of the screen.
	 */
	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	/**
	 * Creates a screen that can be drawn on with a width of width and a height of height
	 * 
	 * @param width width of the drawing screen
	 * @param height height of the drawing screen
	 */
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	/**
	 * Sets up the screen and things to be drawn on the screen. Executed once when the program begins.
	 * 
	 */
	public void setup() {
		
	}
	
	/**
	 * Draws the features of the screen onto the screen. Executed until the 
	 * program is stopped. Each statement is executed in 
	 * sequence and after the last line is read, the first 
	 * line is executed again.
	 * 
	 */
	public void draw() {
		
	}
	
	/**
	 * Actions that the screen does when the mouse is pressed.
	 * 
	 */
	public void mousePressed() {
		
	}
	
	/**
	 * Actions that the screen does when the mouse is moved.
	 * 
	 */
	public void mouseMoved() {
		
	}
	
	/**
	 * Actions that the screen does when the mouse is dragged.
	 * 
	 */
	public void mouseDragged() {
		
	}
	
	/**
	 * Actions that the screen does when the mouse is released.
	 * 
	 */
	public void mouseReleased() {
		
	}
	
	
	
}
