package screens;


import java.awt.Rectangle;
import processing.core.PGraphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;

import core.DrawingSurface;
import sprites.NPC;
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
	private int animationIndex;
	private boolean going;
	
	private final int animationTime = 10;  // This represents 1/4 of a second with normal framerate
	private int animationCounter;

	private Player player;
	private List<Sprite> obstacles;
	private NPC npc;

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
		
		//these add obstacles so Player cannot go into the water
		obstacles.add(new Sprite(215,425,425,55));
		obstacles.add(new Sprite(107,480,120,75));
		obstacles.add(new Sprite(54,540,120,60));
		obstacles.add(new Sprite(630,480,180,50));
		
		
		going = false;
	}

	/**
	 * Creates the player to be drawn onto the screen.
	 */
	public void spawnNewPlayer() {
		player = new Player(surface.loadImage("img/Character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2,50, 20, 100, 50);
		player.setUp(surface);
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
		npc = new NPC(surface.loadImage("img/Enemy.png"), 300, 290);
		obstacles.add(new Sprite((int)npc.x+5, (int)npc.y+5, (int)npc.width-10, (int)npc.height-10));
		npc.setText("Press P to fight boss.");
	}

	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		
		// drawing stuff
		surface.background(255,255,255);
		for (Sprite s : obstacles) {
			s.draw(surface);
		}
		loadBackground();
		if(going) {
			animationCounter--;
			if (animationCounter <= 0) {
				animationCounter = animationTime;
				animationIndex = (animationIndex + 1) % 6;
				if (!surface.isPressed(KeyEvent.VK_RIGHT) && !surface.isPressed(KeyEvent.VK_LEFT)) {
					going = false;
					player.setImage(surface.loadImage("img/Character.png"));
				}
			}
		}

		player.draw(surface);
		npc.draw(surface);
		surface.fill(0);
		surface.textSize(14);
		surface.text("Click on me!", (float)npc.x, (float)npc.y - 5);

		// modifying stuff
		
		if (surface.isPressed(KeyEvent.VK_P)) {
			surface.switchScreen(ScreenSwitcher.BATTLE_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT)) {
			player.walk(Player.Direction.Left, 2);
			going = true;
			player.animateWalkLeft(animationIndex);
		}
		else if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			player.walk(Player.Direction.Right, 2);
			going = true;
			player.animateWalkRight(animationIndex);
		}
		else if (surface.isPressed(KeyEvent.VK_UP))
			player.walk(Player.Direction.Up, 2);
		else if (surface.isPressed(KeyEvent.VK_DOWN))
			player.walk(Player.Direction.Down, 2);
		

		player.act(obstacles);

		if (!screenRect.intersects(player))
			spawnNewPlayer();

	}

	/**
	 * Checks if the npc is clicked, if it is it prompts the user.
	 * 
	 */
	public void mousePressed() { 
		if(npc.contains(surface.mouseX, surface.mouseY) && npc.intersects(player)) {
			npc.displayText();
		}
	}
	
}