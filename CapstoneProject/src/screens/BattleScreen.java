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
	
	public BattleScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Sprite>();
		
		obstacles.add(new Sprite(215,425,425,55));
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
		player = new Player(surface.loadImage("img/Character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2,50);
	}
	
	public void spawnNewEnemy() {
		//spawn enemy here (once implementation is finished)
	}
	
	// The statements in the setup() function 
		// execute once when the program begins
		public void setup() {
			background = surface.loadImage("img/background.png"); //change to png used for background of battle screen
			spawnNewPlayer();
		}
		
		public void draw() {
			
			surface.background(0,255,255);
			for (Sprite s : obstacles) {
				s.draw(surface);
			}
			loadBackground();
			player.draw(surface);
			
			if (surface.isPressed(KeyEvent.VK_LEFT))
				player.walk(Player.Direction.Left);
			if (surface.isPressed(KeyEvent.VK_RIGHT))
				player.walk(Player.Direction.Right);
			
			
			if (!screenRect.intersects(player))
				spawnNewPlayer();
		}
	

}
