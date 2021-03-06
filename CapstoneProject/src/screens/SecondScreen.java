package screens;


import java.awt.Rectangle;
import processing.core.PGraphics;
import java.awt.event.*;

import core.DrawingSurface;
import sprites.NPC;
import sprites.Player;
import sprites.Sprite;

/**
 * Represents the open world game screen where the user explores and can find npcs.
 * 
 * @author Ido Haiby
 * @version 5/23/2022 
 */
public class SecondScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Rectangle screenRect;
	private int animationIndex;
	private boolean going;
	
	private final int animationTime = 10;  // This represents 1/4 of a second with normal framerate
	private int animationCounter;

	private Player player;
	private BackgroundLoader bl;
	
	/**
	 * Creates a game screen that has a drawing surface, and can have obstacles and a player.
	 * 
	 * @param surface The drawing surface that game is drawn upon.
	 */
	public SecondScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		
		
		going = false;
		bl = new BackgroundLoader(0);
		//currentArea = 1;
	}

	/**
	 * Creates the player to be drawn onto the screen.
	 */
	public void spawnNewPlayer() {
		player = new Player(surface.loadImage("img/Character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2,50, 20, 100, 50, Player.PLAYER_HEIGHT, Player.PLAYER_WIDTH);
		player.setUp(surface);
		movePlayerToSpawn();
	}
	
	private void movePlayerToSpawn() {	
		player.moveToLocation(bl.getSpawnPoints().get(bl.getScreenNum()).getX(), bl.getSpawnPoints().get(bl.getScreenNum()).getY());
	}
	
	
	private void setupBackground() {
		bl.addBackgrounds(surface);
		bl.addCharacters(surface);
		bl.addObstacles(surface);
		bl.addSpawnPoints(surface);
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		setupBackground();
		spawnNewPlayer();
	}
	

	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		surface.background(255,255,255);
		bl.draw(surface, this);
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
		
		if (!player.intersects(screenRect)) {
				bl.setScreenNum(bl.getScreenNum()+1);
				setupBackground();
				movePlayerToSpawn();
		}
		

		player.act(bl.getObstacles().get(bl.getScreenNum()));

		if (!screenRect.intersects(player))
			spawnNewPlayer();

	}

	/**
	 * Checks if a character is clicked and performs an action.
	 * 
	 */
	public void mousePressed() { 
		NPC currentnpc;
		for (Sprite npc : bl.getCharacters().get(bl.getScreenNum())) {
			if(npc instanceof NPC && npc.contains(surface.mouseX, surface.mouseY) && npc.intersects(player)) {
				currentnpc = (NPC)npc;
				currentnpc.displayText();
				if (currentnpc.getEnemyInfo() > -1) {
					BattleScreen.enemyIndex = currentnpc.getEnemyInfo();
					surface.switchScreen(ScreenSwitcher.BATTLE_SCREEN);
				}
			}
		}
		
	}
	
}