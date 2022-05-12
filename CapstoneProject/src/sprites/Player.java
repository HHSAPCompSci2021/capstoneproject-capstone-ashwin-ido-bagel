package sprites;


import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;

/**
 * This class represents a Player that the user can control.
 * 
 * @author Ashwin S.
 * @version 5/5/22
 */
public class Player extends Sprite {
	
	/**
	 * The width of the Player.
	 */
	public static final int PLAYER_WIDTH = (int)(60 * 500d/679);
	
	/**
	 * The height of the Player.
	 */
	public static final int PLAYER_HEIGHT = (int)(90 * 737d/892);
	
	private double yVel;
	private boolean onSurface;
	
	/**
	 * This documents the different directions the Player can move.
	 */
	public enum Direction {
		Up,Down,Left,Right
	}
	
	/**
	 * Creates a new Player object with an Image. 
	 * @param img Image representing the player. 
	 * @param x X-coordinates of the Player. 
	 * @param y Y-coordinates of the Player.
	 */
	public Player(PImage img, int x, int y) {
		super(img, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		yVel = 0;
		onSurface = true;
	}

	// METHODS
	
	public void animateWalk(PImage[] images, int index) {
		setImage(images[index]);
	}
	
	/**
	 * This method takes in a Direction and walks in that direction.
	 * @param dir The Direction used to represent what direction to walk in. 
	 */
	public void walk(Direction dir, int speed) {
		if (dir == Direction.Left) {
			moveByAmount(-speed,0);
		}
		if (dir == Direction.Right) {
			moveByAmount(speed,0);
		}
		if (dir == Direction.Down) {
			moveByAmount(0,speed);
		}
		if (dir == Direction.Up) {
			moveByAmount(0,-speed);
		}
	}
	
	/**
	 * A method that makes the Player jump.
	 */
	public void jump() {
	//	double maxHeight = this.y - PLAYER_HEIGHT;
		if(onSurface)
			yVel -= 4;
		
	}
	
	public void battleAct(List<Sprite> obstacles) {
		onSurface = false;
		yVel += 0.1;
		 
		y += yVel;
		 
		for (Sprite s : obstacles) {
			if (super.intersects(s)) {
				yVel = 0;
				super.y = s.y-super.height;
			    onSurface = true;
			}
		}
	}

	/**
	 * Act methods checks for collisions and handles all those cases. 
	 * @param obstacles A List<Sprite> of all Sprites in the window. 
	 */
	public void act(List<Sprite> obstacles) {

		 for (Sprite s : obstacles) {
			 if (super.intersects(s)) {
				 boolean vertical = false, horizontal = false;
				 Line2D[] chosenLine = new Line2D[2];
				 // check if we intersect with sprites box
				 if (super.intersectsLine(s.x, s.y, s.x + s.width, s.y)) { // top horiz
					 //System.out.println("top horiz");

					 horizontal = true;
					 chosenLine[0] = new Line2D.Double(s.x, s.y, s.x + s.width, s.y);
				 }
				 else if (super.intersectsLine(s.x, s.y + s.height, s.x + s.width, s.y + s.height)) { // bottom horiz
					 //System.out.println("bottom horiz");

					 horizontal = true;
					 chosenLine[0] = new Line2D.Double(s.x, s.y + s.height, s.x + s.width, s.y + s.height);
				 }
				 if (super.intersectsLine(s.x, s.y, s.x, s.y + s.height)) { // left vert
					// System.out.println("left vert");

					 vertical = true;
					 chosenLine[1] = new Line2D.Double(s.x, s.y, s.x, s.y + s.height);
				 }
				 else if (super.intersectsLine(s.x + s.width, s.y, s.x + s.width, s.y + s.height)) { // right vert
					// System.out.println("right vert");

					 vertical = true;
					 chosenLine[1] = new Line2D.Double(s.x + s.width, s.y, s.x + s.width, s.y + s.height);
				 }
	
				 if (vertical && horizontal) {
					 //vertical intersection
					 double interVert = 0, interHoriz = 0;
					 if (this.y < s.y) {
						 interVert = chosenLine[0].getY1() - (this.y + this.height);
					 } else {
						 interVert = chosenLine[0].getY1() - (this.y);
					 }
					 
					 if (this.x < s.x) {
						 interHoriz = chosenLine[1].getX1() - (this.x + this.width);
					 } else {
						 interHoriz = chosenLine[1].getX1() - this.x;
					 }
					 
					 if (Math.abs(interHoriz) < Math.abs(interHoriz)) {
						 this.moveByAmount(0, interVert);
					 } else {
						 this.moveByAmount(interHoriz, 0);
					 }
					 
				 } 
				 else if (vertical) {
					 if (this.x < s.x) { //left side
						 this.moveToLocation(s.x-this.width, this.y);
					 } else if (this.x > s.x) {
						 this.moveToLocation(s.x + s.width, this.y);
					 } else {
						 //System.out.println("x is equal to sprite x");
					 }
					 
				 } else {
					 if (this.y < s.y) {
						 this.moveToLocation(this.x, s.y - this.height);
					 }
					 else if (this.y > s.y) {
						 this.moveToLocation(this.x, s.y + s.height);
					 } else {
						 //System.out.println("y is equal to sprite y");
					 }
				 }
			 }
		 }
	}


}
