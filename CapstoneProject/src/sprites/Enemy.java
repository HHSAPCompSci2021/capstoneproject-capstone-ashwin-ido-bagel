package sprites;

import processing.core.PImage;

public class Enemy extends Sprite{
	
	//Weapon weapon;
	
	int enemySpeed;

	public Enemy(PImage img, int x, int y, int w, int h, int speed) {
		super(img, x, y, w, h);
		enemySpeed = speed;
	} 
	
	private double distanceFromPlayer(Player player) {
		double result = Math.abs(player.x - this.x);
		return result;
	}
	
	public void attack() {
		//animations and damage here
		System.out.println("Attacking!");
	}
	
	public void moveTowardsPlayer(Player player) {
		if (distanceFromPlayer(player) > 20) {
			if (player.x > this.x) {
				this.moveByAmount(enemySpeed, 0);
			} else {
				this.moveByAmount(-enemySpeed, 0);
			}
		}
	}
	
	public void act(Player player) {
		moveTowardsPlayer(player);
		if (distanceFromPlayer(player) <= 20) {
			attack();
		}
	}

}
