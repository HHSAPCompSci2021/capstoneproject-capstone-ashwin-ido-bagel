package sprites;

import javax.swing.JOptionPane;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import screens.Screen;

/**
 * Represents an Enemy that is fought by the Player.
 * 
 * @author Ashwin S.
 * @version 5/13/22
 */
public class Enemy extends Sprite{
	
	//Weapon weapon;
	
	private int enemySpeed, attackPower;
	private Player player;
	private int health, stamina;
	private int attackTimer;
	
	
	/**
	 * Creates an Enemy object with a desired speed.
	 * @param img The image drawn to represent the Enemy.
	 * @param x The x-coordinate of the Enemy. 
	 * @param y The y-coordinate of the Enemy.
	 * @param w The width of the Enemy.
	 * @param h The height of the Enemy.
	 * @param speed The speed that the Enemy moves.
	 */
	public Enemy(PImage img, int x, int y, int w, int h, int speed, int attackPower, Player player, int health, int stamina) {
		super(img, x, y, w, h);
		enemySpeed = speed;
		this.attackPower = attackPower;
		this.player = player;
		this.health = health;
		this.stamina = stamina;
		attackTimer = 0;
	} 
	
	/**
	 * Calculates the distance from this Enemy to the Player.
	 * @param player The Player that the method calculates the distance from.
	 * @return The distance from this Enemy to the Player.
	 */
	private double distanceFromPlayer() {
		double result = Math.abs(player.x - this.x);
		return result;
	}
	
	/**
	 * Attacks the Player.
	 */
	public void attack() {
		//animations and damage here
		System.out.println("Enemy is Attacking!");
	}
	
	/**
	 * Returns the health
	 * 
	 * @return Health of the player.
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Moves this Enemy towards the Player.
	 * 
	 */
	public void moveTowardsPlayer() {
		if (!(this.intersects(player))) {
			if (player.x > this.x) {
				this.moveByAmount(enemySpeed, 0);
			} else {
				this.moveByAmount(-enemySpeed, 0);
			}
		}
	}
	
	/**
	 * Continuously moves towards the Player and attacks if close enough.
	 *
	 */
	public void act() {
		moveTowardsPlayer();
		if (this.intersects(player) && attackTimer == 0 && health > 0 && player.isAttacking()) {
			health -= player.getAttackPower();
			attackTimer = 60;
		}
	}
	
	@Override
	public void draw(PApplet g) {
		super.draw(g);
		g.fill(255, 0, 0);
		if(health > 0)
			g.rect(g.width/2 + 225, 5, health, 10);
		else
			g.rect(g.width/2 + 225, 5, 0, 10);
		g.fill(0, 255, 0);
		if(stamina > 0)
			g.rect(g.width/2 + 225, 20, stamina, 10);
		else
			g.rect(g.width/2 + 225, 20, 0, 10);
		if(attackTimer > 0)
			attackTimer--;
	}

}
