package sprites;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;


 /**
  * Sprite is the class that all enemies, NPCs, and players will extend from.
  * 
  *@author Ashwin S
  *@version 5/23/22
  */
public class Sprite extends Rectangle2D.Double {
	
	// FIELDS
	private PImage image;
	
	// CONSTRUCTORS
	
	/**
	 * Creates a new Sprite object without an Image. 
	 * @param x The x-coordinate of the Sprite.
	 * @param y The y-coordinate of the Sprite.
	 * @param w The width of the Sprite.
	 * @param h The height of the Sprite.
	 */
	public Sprite(int x, int y, int w, int h) {
		this(null, x, y, w, h);
	}
	
	/**
	 * Creates a new Sprite object with an Image.
	 * @param img The image associated with this Sprite. 
	 * @param x The x-coordinate of the Sprite.
	 * @param y The y-coordinate of the Sprite.
	 * @param w The width of the Sprite.
	 * @param h The height of the Sprite.
	 */
	public Sprite(PImage img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
	}
	
	
	// METHODS	
	
	/**
	 * Sets image to new Image.
	 * @param img The new Image.
	 */
	public void setImage(PImage img) {
		image = img;
	}

	/**
	 * Moves the Sprite to a specified location on the PApplet. 
	 * @param x The x-coordinate of the desired location to move to. 
	 * @param y The y-coordinate of the desired location to move to. 
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	/**
	 * Moves the Sprite a certain amount in the x and y directions. 
	 * @param x The amount to move in the x direction. 
	 * @param y The amount to move in the y direction. 
	 */
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	/**
	 * Moves the Sprite back to inside the window if it were to move out of it. 
	 * @param windowWidth The width of the PApplet window. 
	 * @param windowHeight The height of the PApplet window. 
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Draws the Sprite
	 * 
	 * @param g The PApplet on which the Sprite is drawn on.
	 */
	public void draw(PApplet g) {
		if (image != null)
			g.image(image,(float)x,(float)y,(float)width,(float)height);
		else {
			g.fill(100);
			g.rect((float)x,(float)y,(float)width,(float)height);
		}
	}
	
}










