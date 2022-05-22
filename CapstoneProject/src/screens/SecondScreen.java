package screens;


import java.awt.Rectangle;
import processing.core.PGraphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PImage;

import core.DrawingSurface;
import sprites.Enemy;
import sprites.NPC;
import sprites.Player;
import sprites.Sprite;

/**
 * Represents the open world game screen where the user explores and can find npcs.
 * 
 * @author Ido Haiby
 * @version 5/132022 
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
	private NPC npc, enemy;
	//private Map<PImage, List<Sprite>> backgrounds = new HashMap<PImage, List<Sprite>>();
	// trying to make it so that when character walks through a 'door', a new background loads up
	// with all obstacles in it and characters loaded up
	// problem: need to separate obstacles that get drawn first and npcs, enemies that get drawn later
	// possible solution: make two entries into map for each background-> kind of annoying and prone to manual errors
	
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
		obstacles.add(new Sprite(200,200,110,230));
		obstacles.add(new Sprite(275,0,100, 100));
		obstacles.add(new Sprite(270,80,120, 150));
		obstacles.add(new Sprite(560, 0, 120, 105));
		obstacles.add(new Sprite(610, 120, 120, 150));
		obstacles.add(new Sprite(540, 300, 110, 130));
		
		
		going = false;
	}

	/**
	 * Creates the player to be drawn onto the screen.
	 */
	public void spawnNewPlayer() {
		player = new Player(surface.loadImage("img/Character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2,50, 20, 100, 50, Player.PLAYER_HEIGHT, Player.PLAYER_WIDTH);
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
		background = surface.loadImage("img/Tutorial.png");
		spawnNewPlayer();
		npc = new NPC(surface.loadImage("img/npc1.png"), 300, 290); //make sprite transparent
		enemy = new NPC(surface.loadImage("img/Enemy.png"), 450, 290);
		obstacles.add(new Sprite((int)npc.x+5, (int)npc.y+5, (int)npc.width-10, (int)npc.height-15));
		obstacles.add(new Sprite((int)enemy.x+5, (int)enemy.y+5, (int)enemy.width-10, (int)enemy.height-15));
		npc.setText("Welcome to the game. Have fun in your journey!");
		enemy.setText("Fight me!");
	}
	
//	public void setupMap() {
//		backgrounds.put(background, obstacles);
//		backgrounds.put(background, obstacles);
//	}

	
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
				animationIndex = (animationIndex + 1) % 3;
				if (!surface.isPressed(KeyEvent.VK_RIGHT) && !surface.isPressed(KeyEvent.VK_LEFT) && !surface.isPressed(KeyEvent.VK_A) && !surface.isPressed(KeyEvent.VK_D)
					&& !surface.isPressed(KeyEvent.VK_UP) && !surface.isPressed(KeyEvent.VK_DOWN) && !surface.isPressed(KeyEvent.VK_W) && !surface.isPressed(KeyEvent.VK_S)) {
					going = false;
					player.setImage(surface.loadImage("img/Character.png"));
				}
			}
		}

		player.draw(surface);
		npc.draw(surface);
		enemy.draw(surface);
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
		if (surface.isPressed(KeyEvent.VK_LEFT) ||
			surface.isPressed(KeyEvent.VK_A)) {
			player.walk(Player.Direction.Left, 2);
			going = true;
			player.animateWalkLeft(animationIndex);
		}
		else if (surface.isPressed(KeyEvent.VK_RIGHT) ||
				surface.isPressed(KeyEvent.VK_D)) {
			player.walk(Player.Direction.Right, 2);
			going = true;
			player.animateWalkRight(animationIndex);
		}
		else if (surface.isPressed(KeyEvent.VK_UP) || 
				surface.isPressed(KeyEvent.VK_W)) {
			player.walk(Player.Direction.Up, 2);
			going = true;
			player.animateWalkForward(animationIndex);
		}
		else if (surface.isPressed(KeyEvent.VK_DOWN) || 
				 surface.isPressed(KeyEvent.VK_S)) {
			player.walk(Player.Direction.Down, 2);
			going = true;
			player.animateWalkBack(animationIndex);
		}
		

		player.act(obstacles);

		if (!screenRect.intersects(player))
			spawnNewPlayer();

	}

	/**
	 * Checks if a character is clicked and performs an action.
	 * 
	 */
	public void mousePressed() { 
		if(npc.contains(surface.mouseX, surface.mouseY) && npc.intersects(player)) {
			npc.displayText();
		}
		if (enemy.contains(surface.mouseX, surface.mouseY) && enemy.intersects(player)) {
			enemy.displayText();
			surface.switchScreen(ScreenSwitcher.BATTLE_SCREEN);
			
		}
	}
	
}