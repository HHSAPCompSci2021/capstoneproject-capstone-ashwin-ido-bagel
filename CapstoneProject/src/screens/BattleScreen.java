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
	//private List<Sprite> obstacles; //maybe needed not sure
	private Rectangle screenRect;
	private Player player;
	private Enemy enemy;
	private List<Sprite> obstacles;
	
	private int animationIndex;
	private boolean going1, going2;
	
	private final int animationTime = 10;  // This represents 1/4 of a second with normal framerate
	private int animationCounter;
	private int animationTimer;
	
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
		
		obstacles.add(new Sprite(0,DRAWING_HEIGHT-10,DRAWING_WIDTH,10));
		obstacles.add(new Sprite(0,0,10,DRAWING_HEIGHT));
		obstacles.add(new Sprite(DRAWING_WIDTH-10,0,10,DRAWING_HEIGHT));
		
		going1 = false;
		going2 = false;
		animationTimer = 60;
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
		player = new Player(surface.loadImage("img/Character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2-200,500, 20, 100, 50);
		player.setUp(surface);
	}
	
	/**
	 * Creates the enemy to be drawn onto the screen.
	 */
	public void spawnNewEnemy() {
		enemy = new Enemy(surface.loadImage("img/Enemy.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2+200,500,(int)(60 * 500d/679), (int)(90 * 737d/892), 2, 20, player, 150, 100);//spawn enemy here (once implementation is finished)
		enemy.setUp(surface);
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		background = surface.loadImage("img/battlescreen.png"); //change to png used for background of battle screen
		spawnNewPlayer();
		spawnNewEnemy();
	}
	
	
	public void draw() {
		surface.background(0,255,255);
		for (Sprite s : obstacles) {
			s.draw(surface);
		}
		loadBackground();
		
		if(going1 && going2) {
			player.animateAttack(animationIndex);
			player.attack();
			enemy.animateAttack(animationIndex);
			enemy.attack();
			animationCounter--;
			if (animationCounter <= 0) {
				animationCounter = animationTime;
				animationIndex = (animationIndex + 1) % 4;
				if (animationTimer < 0) {
						going1 = false;
						going2 = false;
						player.setImage(surface.loadImage("img/Character.png"));
						enemy.setImage(surface.loadImage("img/Enemy.png"));
					}
				}
				animationTimer--;
		} else if(going1) {
			player.animateAttack(animationIndex);
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
		} else if(going2) {
			enemy.animateAttack(animationIndex);
			enemy.attack();
			animationCounter--;
			if (animationCounter <= 0) {
				animationCounter = animationTime;
				animationIndex = (animationIndex + 1) % 4;
				if (animationTimer < 0) {
					going2 = false;
					enemy.setImage(surface.loadImage("img/Enemy.png"));
				}
			}
			animationTimer--;
		}
		
		if(player.getHealth() > 0)
			player.battleDraw(surface);
		else {
			int answer = JOptionPane.showConfirmDialog(null, "You were defeated, return to world?");
			if(answer == JOptionPane.YES_OPTION) {
				surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
			} else {
				spawnNewPlayer();
			}
		}
		if(enemy.getHealth() > 0)
			enemy.draw(surface);
		else {
			int answer = JOptionPane.showConfirmDialog(null, "Enemy defeated, return to world?");
			if(answer == JOptionPane.YES_OPTION) {
				surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
			} else {
				spawnNewEnemy();
			}
		}
		
		if(surface.mousePressed && player.getStamina() > 0) {
			going1 = true;
			animationTimer = 60;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT))
			player.walk(Player.Direction.Left, 10);
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			player.walk(Player.Direction.Right, 10);
		}
		if (surface.isPressed(KeyEvent.VK_SPACE))
			player.jump();
		
		enemy.act();
		if(enemy.isAttacking()) {
			going2 = true;
			animationTimer = 60;
		}
			
		player.battleAct(obstacles);
		
		if (!screenRect.intersects(player))
			spawnNewPlayer();
	}
	

}
