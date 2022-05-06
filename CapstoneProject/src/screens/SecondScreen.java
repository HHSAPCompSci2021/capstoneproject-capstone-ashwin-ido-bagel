package screens;


import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;

import core.DrawingSurface;
import sprites.Player;
import sprites.Sprite;

/**
 * Represents the open world game screen where the user explores and can find npcs.
 * 
 * @author Ido Haiby
 * @version 5/5/2022 
 */
public class SecondScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Rectangle screenRect;
	private PImage background;

	private Player player;
	private List<Sprite> obstacles;

	/**
	 * Creates a game screen that has a drawing surface, and can have obstacles and a player.
	 * 
	 * @param surface The drawing surface that game is drawn upon.
	 */
	public SecondScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Sprite>();
	}

	/**
	 * Creates the player to be drawn onto the screen.
	 */
	public void spawnNewPlayer() {
		player = new Player(surface.loadImage("img/character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2,50);
	}
	/**
	 * Loads the background of the current area.
	 */
	public void loadBackground() { 
		surface.image(background, 0,0, DRAWING_WIDTH,DRAWING_HEIGHT);
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		background = surface.loadImage("img/background.png");
		spawnNewPlayer();
	}

	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		
		// drawing stuff
		
		surface.background(0,255,255);   
		loadBackground();
		for (Sprite s : obstacles) {
			s.draw(surface);
		}

		player.draw(surface);
		
		// modifying stuff

		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT))
			player.walk(Player.Direction.Left);
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			player.walk(Player.Direction.Right);
		if (surface.isPressed(KeyEvent.VK_UP))
			player.walk(Player.Direction.Up);
		if (surface.isPressed(KeyEvent.VK_DOWN))
			player.walk(Player.Direction.Down);
		

		player.act(obstacles);

		if (!screenRect.intersects(player))
			spawnNewPlayer();

	}

	
}