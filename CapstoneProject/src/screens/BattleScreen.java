package screens;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import core.DrawingSurface;
import processing.core.PImage;
import sprites.Enemy;
import sprites.Player;
import sprites.Sprite;

public class BattleScreen extends Screen{
	
	private DrawingSurface surface;
	private PImage background;
	//private List<Sprite> obstacles; //maybe needed not sure
	private Rectangle screenRect;
	private Player player;
	private Enemy enemy;
	private List<Sprite> obstacles;
	
	private int animationIndex;
	private boolean going;
	
	private final int animationTime = 10;  // This represents 1/4 of a second with normal framerate
	private int animationCounter;
	
	public BattleScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Sprite>();
		
		obstacles.add(new Sprite(0,DRAWING_HEIGHT-10,DRAWING_WIDTH,10));
		
		going = false;
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
		player = new Player(surface.loadImage("img/character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2-200,500);
		player.setUp(surface);
	}
	
	public void spawnNewEnemy() {
		enemy = new Enemy(surface.loadImage("img/character_battle.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2+200,500,(int)(60 * 500d/679), (int)(90 * 737d/892), 2, 5, player);//spawn enemy here (once implementation is finished)
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		background = surface.loadImage("img/battlescreen.png"); //change to png used for background of battle screen
		spawnNewPlayer();
		spawnNewEnemy();
	}
	
	public void attack() {
		
	}
	
	public void draw() {
		
		surface.background(0,255,255);
		for (Sprite s : obstacles) {
			s.draw(surface);
		}
		loadBackground();
		if(going) {
			animationCounter--;
			if (animationCounter <= 0) {
				animationCounter = animationTime;
				animationIndex = (animationIndex + 1) % 4;
				if (!surface.isPressed(KeyEvent.VK_RIGHT) && !surface.isPressed(KeyEvent.VK_LEFT)) {
					going = false;
					player.setImage(surface.loadImage("img/character.png"));
				}
			}
		}
		
		player.draw(surface);
		enemy.draw(surface);
		
		if (surface.isPressed(KeyEvent.VK_LEFT))
			player.walk(Player.Direction.Left, 10);
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			player.walk(Player.Direction.Right, 10);
			going = true;
			player.animateAttack(animationIndex);
		}
		if (surface.isPressed(KeyEvent.VK_SPACE))
			player.jump();
		
		enemy.act();
		player.battleAct(obstacles);
		
		if (!screenRect.intersects(player))
			spawnNewPlayer();
	}
	

}
