package sprites;


import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents a Player that the user can control.
 * 
 * @author Ashwin S. , Ido Haiby
 * @version 5/13/22
 */
public class Player extends Sprite {
	
	/**
	 * The width of the Player.
	 */
	public static final int PLAYER_WIDTH = (int)(60 * 500d/679) - 5;
	
	/**
	 * The height of the Player.
	 */
	public static final int PLAYER_HEIGHT = (int)(90 * 737d/892) - 5;
	
	public static final int BATTLEPLAYER_HEIGHT = (int)(200) - 5;
	
	public static final int BATTLEPLAYER_WIDTH = (int)(80) - 5;
	
	
	
	private double yVel;
	private boolean onSurface;
	
	private PImage[] animationsRight;
	private PImage[] animationsLeft;
	private PImage[] animationsAttack;
	private int attackPower, stamina, health;
	private boolean attacking;
	private int attackTimer;
	
	/**
	 * This documents the different directions the Player can move.
	 */
	public enum Direction {
		Up,Down,Left,Right
	}
	
	/**
	 * Creates a new Player object with an Image. 
	 * 
	 * @param img Image representing the player. 
	 * @param x X-coordinates of the Player. 
	 * @param y Y-coordinates of the Player.
	 * @param attackPower Attacking power of the player.
	 * @param health Health of the player.
	 * @param stamina Stamina of the player.
	 */
	public Player(PImage img, int x, int y, int attackPower, int health, int stamina, int height, int width) { //make the hitbox of player a rectangle
		super(img, x, y, width, height);
		yVel = 0;
		onSurface = true;
		this.attackPower = attackPower;
		this.stamina = stamina;
		this.health = health; 
		attacking = false;
		attackTimer = 0;
	}

	// METHODS
	/**
	 * Sets up the images of the player.
	 * 
	 * @param g The PApplet used to draw the player.
	 */
	public void setUp(PApplet g) {
		animationsRight = new PImage[6];
		animationsRight[0] = g.loadImage("img/Walk1.png");
		animationsRight[1] = g.loadImage("img/Walk2.png");
		animationsRight[2] = g.loadImage("img/Walk3.png");
		animationsRight[3] = g.loadImage("img/Walk4.png");
		animationsRight[4] = g.loadImage("img/Walk5.png");
		animationsRight[5] = g.loadImage("img/Walk6.png");
		animationsLeft = new PImage[6];
		animationsLeft[0] = g.loadImage("img/WalkL1.png");
		animationsLeft[1] = g.loadImage("img/WalkL2.png");
		animationsLeft[2] = g.loadImage("img/WalkL3.png");
		animationsLeft[3] = g.loadImage("img/WalkL4.png");
		animationsLeft[4] = g.loadImage("img/WalkL5.png");
		animationsLeft[5] = g.loadImage("img/WalkL6.png");
		animationsAttack = new PImage[4];
		animationsAttack[0] = g.loadImage("img/Attack1.png");
		animationsAttack[1] = g.loadImage("img/Attack2.png");
		animationsAttack[2] = g.loadImage("img/Attack3.png");
		animationsAttack[3] = g.loadImage("img/Attack4.png");
	}
	
	/**
	 * Animates the walking towards the right.
	 * 
	 * @param index Index of animation image
	 */
	public void animateWalkRight(int index) {
		setImage(animationsRight[index]);
	}
	
	/**
	 * Animates the walking towards the left.
	 * 
	 * @param index Index of animation image
	 */
	public void animateWalkLeft(int index) {
		setImage(animationsLeft[index]);
	}
	
	/**
	 * A method that makes the Player attack during battle.
	 * 
	 * @param index Index of animation image
	 */
	public void animateAttack(int index) {
		if(attackTimer <= 0) {
			attacking = true;
			setImage(animationsAttack[index]);
		}
	}
	
	
	/**
	 * Adds health to the player
	 * 
	 * @param amount Amount of health added
	 */
	public void addHealth(int amount) {
		health += amount;
	}
	
	/**
	 * Makes the character attack and uses stamina.
	 * 
	 */
	public void attack() {
		if(stamina > 0)
			stamina -= attackPower;
	}
	
	/**
	 * Gives the user the stamina of the character.
	 * @return The stamina of the character
	 */
	public int getStamina() {
		return stamina;
	}
	
	/**
	 * Gives the health of the Player.
	 * @return The health of the Player.
	 */
	public int getHealth() {
		return health;
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
			yVel -= 8;
		
	}
	
	/**
	 * Makes the character act when it is on the battle screen.
	 * 
	 * @param obstacles The obstacles the character interacts with.
	 */
	public void battleAct(List<Sprite> obstacles) {
		act(obstacles);
		if(!attacking && stamina < 50) {
		   stamina++;
		}
		attacking = false;
		onSurface = false;
		yVel += 0.5;
		 
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
	 * Gives the user the attack power of the character.
	 * 
	 * @return The attack power of the character
	 */
	public int getAttackPower() {
		return attackPower;
	}
	
	/**
	 * Checks if character is attacking.
	 * 
	 * @return the attacking state of the character.
	 */
	public boolean isAttacking() {
		return attacking;
	}
	
	/**
	 * Checks collisions with obstacles on screen.
	 * 
	 * @param obstacles Obstacles on the screen.
	 */
	public void checkCollisions(List<Sprite> obstacles) {
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
	

	/**
	 * Act methods checks for collisions and handles all those cases.
	 * 
	 * @param obstacles A List of all Sprites in the window. 
	 */
	public void act(List<Sprite> obstacles) { // update this so that uses rectangle intersection method to simplify
		checkCollisions(obstacles);
	}
	
	/**
	 * Draws the player when fighting
	 * 
	 * @param g The PApplet the character is drawn with.
	 */
	public void battleDraw(PApplet g) {
		super.draw(g);
		g.fill(255, 0, 0);
		if(health > 0)
			g.rect(10, 5, health, 10);
		else 
			g.rect(10, 5, 0, 10);
		g.fill(0, 255, 0);
		if(stamina > 0)
			g.rect(10, 20, stamina, 10);
		else {
			g.rect(10, 20, 0, 10);
		}
		if(attackTimer > 0)
			attackTimer--;
	}


}
