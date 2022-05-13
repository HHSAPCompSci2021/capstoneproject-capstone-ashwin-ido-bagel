package sprites;

import processing.core.PImage;

/**
 * Creates an Enemy that is fought by the Player.
 * @author Ashwin S.
 *@version 5/13/22
 */
public class Enemy extends Sprite{
	
	//Weapon weapon;
	
	int enemySpeed;
	
	
	/**
	 * Creates an Enemy object with a desired speed.
	 * @param img The image drawn to represent the Enemy.
	 * @param x The x-coordinate of the Enemy. 
	 * @param y The y-coordinate of the Enemy.
	 * @param w The width of the Enemy.
	 * @param h The height of the Enemy.
	 * @param speed The speed that the Enemy moves.
	 */
	public Enemy(PImage img, int x, int y, int w, int h, int speed) {
		super(img, x, y, w, h);
		enemySpeed = speed;
	} 
	
	/**
	 * Calculates the distance from this Enemy to the Player.
	 * @param player The Player that the method calculates the distance from.
	 * @return The distance from this Enemy to the Player.
	 */
	private double distanceFromPlayer(Player player) {
		double result = Math.abs(player.x - this.x);
		return result;
	}
	
	/**
	 * Attacks the Player.
	 */
	public void attack() {
		//animations and damage here
		System.out.println("Attacking!");
	}
	
	/**
	 * Moves this Enemy towards the Player.
	 * @param player
	 */
	public void moveTowardsPlayer(Player player) {
		if (distanceFromPlayer(player) > 20) {
			if (player.x > this.x) {
				this.moveByAmount(enemySpeed, 0);
			} else {
				this.moveByAmount(-enemySpeed, 0);
			}
		}
	}
	
	/**
	 * Continuously moves towards the Player and attacks if close enough.
	 * @param player
	 */
	public void act(Player player) {
		moveTowardsPlayer(player);
		if (distanceFromPlayer(player) <= 20) {
			attack();
		}
	}

}
