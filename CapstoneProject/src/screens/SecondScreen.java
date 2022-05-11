package screens;


import java.awt.Rectangle;
import processing.core.PGraphics;
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
	private PImage[] animationsRight;
	private PImage[] animationsLeft;
	private int animationIndex;
	private boolean going;
	
	private final int animationTime = 10;  // This represents 1/4 of a second with normal framerate
	private int animationCounter;

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
		player = new Player(surface.loadImage("img/Character.png"), DRAWING_WIDTH/2-Player.PLAYER_WIDTH/2,50);
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
		animationsRight = new PImage[6];
		animationsRight[0] = surface.loadImage("img/Walk1.png");
		animationsRight[1] = surface.loadImage("img/Walk2.png");
		animationsRight[2] = surface.loadImage("img/Walk3.png");
		animationsRight[3] = surface.loadImage("img/Walk4.png");
		animationsRight[4] = surface.loadImage("img/Walk5.png");
		animationsRight[5] = surface.loadImage("img/Walk6.png");
		animationsLeft = new PImage[6];
		animationsLeft[0] = surface.loadImage("img/WalkL1.png");
		animationsLeft[1] = surface.loadImage("img/WalkL2.png");
		animationsLeft[2] = surface.loadImage("img/WalkL3.png");
		animationsLeft[3] = surface.loadImage("img/WalkL4.png");
		animationsLeft[4] = surface.loadImage("img/WalkL5.png");
		animationsLeft[5] = surface.loadImage("img/WalkL6.png");
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
		for (Sprite s : obstacles) {
			s.draw(surface);
		}
		loadBackground();
		if(going) {
			animationCounter--;
			if (animationCounter <= 0) {
				animationCounter = animationTime;
				animationIndex = (animationIndex + 1) % animationsRight.length;
				if (!surface.isPressed(KeyEvent.VK_RIGHT) && !surface.isPressed(KeyEvent.VK_LEFT)) {
					going = false;
					
				}
			}
		}

		player.draw(surface);
		
		// modifying stuff
		
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT)) {
			player.walk(Player.Direction.Left);
			going = true;
			player.animateWalk(animationsLeft, animationIndex);
		}
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			player.walk(Player.Direction.Right);
			going = true;
			player.animateWalk(animationsRight, animationIndex);
		}
		if (surface.isPressed(KeyEvent.VK_UP))
			player.walk(Player.Direction.Up);
		if (surface.isPressed(KeyEvent.VK_DOWN))
			player.walk(Player.Direction.Down);
		

		player.act(obstacles);

		if (!screenRect.intersects(player))
			spawnNewPlayer();

	}

	
}