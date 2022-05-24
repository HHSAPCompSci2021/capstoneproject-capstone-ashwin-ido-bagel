package sprites;


import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an Enemy that is fought by the Player.
 * 
 * @author Ashwin S., Ido Haiby
 * @version 5/23/22
 */
public class Enemy extends Sprite{
	
	private int enemySpeed, attackPower;
	private Player player;
	private int health, stamina;
	private int attackTimer, staminaTimer;
	private boolean attacking, isAnimating;
	private PImage[] animationsAttackR, animationsAttackL;
	private PImage[] animationsWalk;
	private int staminaCost;
	private int attackTimer2;
	private boolean movingL, movingR;
	private int enemyIndex;
	private PImage defaultImg;
	
	/**
	 * Creates an Enemy object with a desired speed.
	 * 
	 * @param img The image drawn to represent the Enemy.
	 * @param x The x-coordinate of the Enemy. 
	 * @param y The y-coordinate of the Enemy.
	 * @param w The width of the Enemy.
	 * @param h The height of the Enemy.
	 * @param speed The speed that the Enemy moves.
	 * @param attackPower Attacking power of the enemy.
	 * @param player Player the enemy is fighting.
	 * @param health Health of the enemy.
	 * @param stamina Stamina of the enemy.
	 */
	public Enemy(PImage img, int x, int y, int w, int h, int speed, int attackPower, Player player, int health, int stamina, int enemyIndex) {
		super(img, x, y, w, h);
		defaultImg = img;
		enemySpeed = speed;
		this.enemyIndex = enemyIndex;
		this.attackPower = attackPower;
		this.player = player;
		this.health = health;
		this.stamina = stamina;
		attackTimer = 0;
		staminaTimer = 0;
		attackTimer2 = 0;
	} 
	
	/**
	 * Sets the stamina cost of some attacks depending on their attack power.
	 */
	public void setCost() {
		if (attackPower >= 20) {
			staminaCost = 40;
		} else if (attackPower >= 10) {
			staminaCost = 20;
		} else {
			staminaCost = 10;
		}
			
	}
	
	/**
	 * Sets up the images of the player.
	 * 
	 * @param g The PApplet used to draw the player.
	 */
	public void setUp(PApplet g) {
		setCost();
		if(enemyIndex == 0) {
			animationsAttackR = new PImage[4];
			animationsAttackR[0] = g.loadImage("img/EnemyAttackR1.png");
			animationsAttackR[1] = g.loadImage("img/EnemyAttackR2.png");
			animationsAttackR[2] = g.loadImage("img/EnemyAttackR3.png");
			animationsAttackR[3] = g.loadImage("img/EnemyAttackR4.png");
			animationsAttackL = new PImage[4];
			animationsAttackL[0] = g.loadImage("img/EnemyAttack1.png");
			animationsAttackL[1] = g.loadImage("img/EnemyAttack2.png");
			animationsAttackL[2] = g.loadImage("img/EnemyAttack3.png");
			animationsAttackL[3] = g.loadImage("img/EnemyAttack4.png");
			animationsWalk = new PImage[12];
			animationsWalk[0] = g.loadImage("img/EnemyWalk1.png");
			animationsWalk[1] = g.loadImage("img/EnemyWalk2.png");
			animationsWalk[2] = g.loadImage("img/EnemyWalk3.png");
			animationsWalk[3] = g.loadImage("img/EnemyWalk4.png");
			animationsWalk[4] = g.loadImage("img/EnemyWalk5.png");
			animationsWalk[5] = g.loadImage("img/EnemyWalk6.png");
			animationsWalk[6] = g.loadImage("img/EnemyWalk7.png");
			animationsWalk[7] = g.loadImage("img/EnemyWalk8.png");
			animationsWalk[8] = g.loadImage("img/EnemyWalk9.png");
			animationsWalk[9] = g.loadImage("img/EnemyWalk10.png");
			animationsWalk[10] = g.loadImage("img/EnemyWalk11.png");
			animationsWalk[11] = g.loadImage("img/EnemyWalk12.png");
		}
		else if(enemyIndex == 1) {
			animationsAttackR = new PImage[5];
			animationsAttackR[0] = g.loadImage("img/ghostAttackR1.png");
			animationsAttackR[1] = g.loadImage("img/ghostAttackR2.png");
			animationsAttackR[2] = g.loadImage("img/ghostAttackR3.png");
			animationsAttackR[3] = g.loadImage("img/ghostAttackR4.png");
			animationsAttackR[4] = g.loadImage("img/ghostAttackR5.png");
			animationsAttackL = new PImage[5];
			animationsAttackL[0] = g.loadImage("img/ghostAttack1.png");
			animationsAttackL[1] = g.loadImage("img/ghostAttack2.png");
			animationsAttackL[2] = g.loadImage("img/ghostAttack3.png");
			animationsAttackL[3] = g.loadImage("img/ghostAttack4.png");
			animationsAttackL[4] = g.loadImage("img/ghostAttack5.png");
			animationsWalk = new PImage[8];
			animationsWalk[0] = g.loadImage("img/ghostWalk1.png");
			animationsWalk[1] = g.loadImage("img/ghostWalk2.png");
			animationsWalk[2] = g.loadImage("img/ghostWalk3.png");
			animationsWalk[3] = g.loadImage("img/ghostWalk4.png");
			animationsWalk[4] = g.loadImage("img/ghostWalk5.png");
			animationsWalk[5] = g.loadImage("img/ghostWalk6.png");
			animationsWalk[6] = g.loadImage("img/ghostWalk7.png");
			animationsWalk[7] = g.loadImage("img/ghostWalk8.png");
		}
		else if (enemyIndex == 2) {
			//
		}
	}
	
	/**
	 * Gets the default image of a Sprite.
	 * @return The PImage representing the default image of a Sprite.
	 */
	public PImage getDefaultImage() {
		return defaultImg;
	}
	
	/**
	 * Calculates the distance from this Enemy to the Player.
	 * @param player The Player that the method calculates the distance from.
	 * @return The distance from this Enemy to the Player.
	 */
	public double distanceFromPlayer() {
		double result = Math.abs(player.x - this.x);
		return result;
	}
	
	/**
	 * Gets the animation size of a particular attack animation.
	 * @return The int representing the attack animation size.
	 */
	public int getAttackAnimationSize() {
		return animationsAttackR.length;
	}
	
	/**
	 * Gets the animation size of a particular walking animation.
	 * @return The int representing the walking animation size.
	 */
	public int getWalkAnimationSize() {
		return animationsWalk.length/2;
	}
	
	/**
	 * Animates the attack to the right.
	 * @param index The index of the next image to switch to.
	 */
	public void animateAttackR(int index) {
		if (isAnimating)
			setImage(animationsAttackR[index]);
	}
	
	/**
	 * Animates the attack to the left.
	 * @param index The index of the next image to switch to.
	 */
	public void animateAttackL(int index) {
		if (isAnimating)
			setImage(animationsAttackL[index]);
	}
	
	/**
	 * Animates the walk to the left.
	 * @param index The index of the next image to switch to.
	 */
	public void animateWalkLeft(int index) {
		if(!isAnimating)
			setImage(animationsWalk[index]);
	}
	
	/**
	 * Animates the walk to the right.
	 * @param index The index of the next image to switch to.
	 */
	public void animateWalkRight(int index) {
		if(!isAnimating)
			setImage(animationsWalk[index+animationsWalk.length/2]);
	}
	
	/**
	 * Attacks the Player.
	 */
	public void attack() {
			attackTimer2 = 120;
			isAnimating = true;
			stamina -= staminaCost;
			attacking = true;
			if(this.intersects(player)) {
				player.addHealth(-attackPower/2);
			}
	}
	
	/**
	 * Checks if enemy is attacking.
	 * 
	 * @return the attacking state of the enemy.
	 */
	public boolean isAttacking() {
		return attacking;
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
	 */
	public void moveTowardsPlayer() {
		if (!(this.intersects(player))) {
			if (player.x > this.x) {
				movingL = false;
				movingR = true;
				this.moveByAmount(enemySpeed, 0);
			} else {
				movingR = false;
				movingL = true;
				this.moveByAmount(-enemySpeed, 0);
			}
		} else {
			movingR = false;
			movingL = false;
		}
	}
	
	/**
	 * Gets the whether or not the Enemy is moving right or not.
	 * @return The boolean representing whether or not the Enemy is moving right or not.
	 */
	public boolean isMovingRight() {
		return movingR;
	}
	
	/**
	 * Gets the whether or not the Enemy is moving left or not.
	 * @return The boolean representing whether or not the Enemy is moving left or not.
	 */
	public boolean isMovingLeft() {
		return movingL;
	}
	
	/**
	 * Continuously moves towards the Player and attacks if close enough.
	 *
	 */
	public void act(PApplet g) {
		if(!isAttacking())
			moveTowardsPlayer();
		if(stamina < 100 && staminaTimer <= 0) {
			staminaTimer = 5; // change this to change stamina regen rate
			stamina++;
		}
		if (this.intersects(player) && attackTimer == 0 && health > 0 && player.isAttacking()) {
			health -= player.getAttackPower();
			attackTimer = 60;
		}
		if(distanceFromPlayer() <= 80 && attackTimer2 <= 0) {
			attack();
		} else if(attackTimer2 <= 60 && isAnimating){
			isAnimating = false;
			attacking = false;
			setImage(defaultImg);
		}
			
		staminaTimer--;
		attackTimer2--;
	}
	
	@Override
	public void draw(PApplet g) {
		super.draw(g);
		g.fill(255, 0, 0);
		if(health > 0)
			g.rect(g.width - health*2-5, 5, health * 2, 20);
		else
			g.rect(g.width/2 + 225, 5, 0, 10);
		g.fill(0, 255, 0);
		if(attackTimer > 0)
			attackTimer--;
	}

}
