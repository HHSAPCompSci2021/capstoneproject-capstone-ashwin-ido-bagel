package screens;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import core.DrawingSurface;
import processing.core.PImage;
import sprites.Enemy;
import sprites.Player;
import sprites.Sprite;

/**
 * Represents the screen where the player fights enemies and players.
 * 
 * @author Ido Haiby, Ashwin S.
 * @version 5/13/22
 */
public class BattleScreen extends Screen {
	
	private DrawingSurface surface;
	private PImage background;
	private int backgroundLoc;
	//private List<Sprite> obstacles; //maybe needed not sure
	private Rectangle screenRect;
	private Player player;
	private Enemy enemy;
	private ArrayList<Sprite> obstacles;
	
	private int animationIndex, animationIndex2, animationIndex3, animationIndex4;
	private boolean going1, going2, going3;
	
	private final int animationTime = 15;  // This represents 1/4 of a second with normal framerate
	private final int animationTime2 = 15;  // This represents 1/4 of a second with normal framerate
	private final int animationTime3 = 10;  // This represents 1/6 of a second with normal framerate
	private final int animationTime4 = 10;  // This represents 1/6 of a second with normal framerate
	private int animationCounter, animationCounter2, animationCounter3, animationCounter4;
	private int animationTimer, animationTimer2;
	
	/**
	 * Creates a the screen where the player fights enemies.
	 * 
	 * @param surface The DrawingSurface the screen draws on to.
	 */
	public BattleScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Sprite>();
		
		obstacles.add(new Sprite(0,DRAWING_HEIGHT-20,DRAWING_WIDTH,20));
		obstacles.add(new Sprite(0,0,20,DRAWING_HEIGHT));
		obstacles.add(new Sprite(DRAWING_WIDTH-20,0,20,DRAWING_HEIGHT));
		
		going1 = false;
		going2 = false;
		going3 = false;
		animationTimer = 60;
		animationTimer2 = 60;
		backgroundLoc = 1000; // change spawn location
	}
	
	
	/**
	 * Loads the background of the current area.
	 */
	public void loadBackground() { 
		surface.image(background, 0,0, DRAWING_WIDTH,DRAWING_HEIGHT);
	}
	
	/**
	 * Creates the player to be drawn onto the screen.
	 */
	public void spawnNewPlayer() {
		player = new Player(surface.loadImage("img/Character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2-200,DRAWING_HEIGHT - Player.BATTLEPLAYER_HEIGHT -30, 20, 100, 50, Player.BATTLEPLAYER_HEIGHT, Player.BATTLEPLAYER_WIDTH);
		player.setUp(surface);
	}
	
	/**
	 * Creates the enemy to be drawn onto the screen.
	 */
	public void spawnNewEnemy() {
		if(enemyIndex == 0)
			enemy = new Enemy(surface.loadImage("img/Enemy.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2+200,DRAWING_HEIGHT - Player.BATTLEPLAYER_HEIGHT -30,Player.BATTLEPLAYER_WIDTH, Player.BATTLEPLAYER_HEIGHT, 2, 20, player, 150, 100, enemyIndex);//spawn enemy here (once implementation is finished)
		enemy.setUp(surface);
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		background = surface.loadImage("img/battlearena.png"); //change to png used for background of battle screen
		spawnNewPlayer();
		spawnNewEnemy();
	}
	
	
	public void draw() {
		
		for (Sprite s : obstacles) {
			s.draw(surface);
		}
		
		//System.out.println(backgroundLoc);
		// NB image is wider than screen
		if (backgroundLoc >= 0) {
			int x = (int)backgroundLoc % background.width;
		    surface.copy(background, x, 0, background.width, background.height, 0, 0, background.width, background.height);
		    int x2 = background.width - x;
		    if (x2 < DRAWING_WIDTH) {
		      surface.copy(background, 0, 0, background.width, background.height, x2, 0, background.width, background.height);
		    }
		}
	    
	  
		//surface.background(0,255,255);
		
		//loadBackground();
		
		if(going1) {
			if(animationIndex > 3)
				animationIndex = 0;
			if(player.x < enemy.x)
				player.animateAttackR(animationIndex);
			if(player.x > enemy.x)
				player.animateAttackL(animationIndex);
			player.attack();
			animationCounter--;
			if (animationCounter <= 0) {
				animationCounter = animationTime;
				animationIndex = (animationIndex + 1) % 4;
				if (animationTimer < 0) {
					going1 = false;
					player.setImage(surface.loadImage("img/Character.png"));
				}
			}
			animationTimer--;
		}
		if(going2) {
			if(animationIndex2 > 3)
				animationIndex2 = 0;
			if(enemy.x < player.x)
				enemy.animateAttackR(animationIndex2);
			if(enemy.x > player.x)
				enemy.animateAttackL(animationIndex2);
			animationCounter2--;
			if (animationCounter2 <= 0) {
				animationCounter2 = animationTime2;
				animationIndex2 = (animationIndex2 + 1) % enemy.getAttackAnimationSize();
				if (animationTimer2 < 0) {
					going2 = false;
					enemy.setImage(enemy.getDefaultImage());
				}
			}
			animationTimer2--;
		}
		
		if(going3 && !player.isAttacking()) {
			animationCounter3--;
			if (animationCounter3 <= 0) {
				animationCounter3 = animationTime3;
				animationIndex3 = (animationIndex3 + 1) % 3;
				if (!surface.isPressed(KeyEvent.VK_RIGHT) && !surface.isPressed(KeyEvent.VK_LEFT)) {
					going3 = false;
					player.setImage(surface.loadImage("img/Character.png"));
				}
			}
		}
		
		if(enemy.isMovingRight() || enemy.isMovingLeft()) {
			if(enemy.isMovingRight())
				enemy.animateWalkRight(animationIndex4);
			if(enemy.isMovingLeft())
				enemy.animateWalkLeft(animationIndex4);
			animationCounter4--;
			if (animationCounter4 <= 0) {
				animationCounter4 = animationTime4;
				animationIndex4 = (animationIndex4 + 1) % enemy.getWalkAnimationSize();
			}
		}
		
		//get rid of option to return -> automatically return
		if(player.getHealth() > 0)
			player.battleDraw(surface);
		else {
			JOptionPane.showMessageDialog(null, "You were defeated!");
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
		}
		if(enemy.getHealth() > 0)
			enemy.draw(surface);
		else {
			JOptionPane.showMessageDialog(null, "Enemy defeated!");
			JOptionPane.showMessageDialog(null, "Level up! Health increased!");
			player.addHealth(50);
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
		}
		
		if(surface.mousePressed && player.getStamina() > 0) {
			going1 = true;
			if(animationTimer <= 0)
				animationTimer = 60;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT)) {
			if (player.x <= 50) {
				backgroundLoc-=10;
			}
			if(!player.isAttacking()) {
				player.walk(Player.Direction.Left, 10);
				going3 = true;
				player.animateWalkLeft(animationIndex3);
			}
		}
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			if (player.x >= DRAWING_WIDTH -50 - Player.BATTLEPLAYER_WIDTH) {
				backgroundLoc+=10;
			}
			if(!player.isAttacking()) {
				player.walk(Player.Direction.Right, 10);
				going3 = true;
				player.animateWalkRight(animationIndex3);
			}
		}
		if (surface.isPressed(KeyEvent.VK_SPACE)) {
			player.jump();
			
		}
		
		enemy.act(surface);
		if(enemy.isAttacking()) {
			going2 = true;
			if(animationTimer2 <= 0)
				animationTimer2 = 60;
		}
			
		player.battleAct(obstacles);
		
		if (!screenRect.intersects(player))
			spawnNewPlayer();
	}
	

}