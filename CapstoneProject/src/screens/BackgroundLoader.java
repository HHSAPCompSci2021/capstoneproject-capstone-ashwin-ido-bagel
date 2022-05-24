package screens;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import sprites.Enemy;
import sprites.NPC;
import sprites.Sprite;

/**
 * This class creates a BackgroundLoader that loads all components needed for the background.
 * @author Ashwin Senthilvasan
 * @version 5/23/22
 */
public class BackgroundLoader {
	
	private ArrayList<ArrayList<Sprite>> characters, obstacles;
	private ArrayList<PImage> backgrounds;
	private ArrayList<Point2D> spawnPoints;
	private int screenNum;
	
	/**
	 * Creates a BackgroundLoader object.
	 * @param screenNum The screen which the BackgroundLoader should display.
	 */
	public BackgroundLoader(int screenNum) {
		this.screenNum = screenNum;
		characters = new ArrayList<ArrayList<Sprite>>();
		obstacles = new ArrayList<ArrayList<Sprite>>();
		backgrounds = new ArrayList<PImage>();
		spawnPoints = new ArrayList<Point2D>();
	}
	
	/**
	 * Adds the NPCs for each background to characters.
	 * @param surface The surface on which the images are loaded on.
	 */
	public void addCharacters(PApplet surface) {
		ArrayList<Sprite> c = new ArrayList<Sprite>();
		//tutorial area
		c.add(new NPC(surface.loadImage("img/npc1.png"), 300, 290, "Welcome to the game. Have fun in your journey!", -1));
		c.add(new NPC(surface.loadImage("img/npc2.png"), 505, 245, "Click on an enemy or NPC to interact with it or fight it. Use the WASD controls to move around.", -1));
		characters.add(c);
		
		//area 1
		c = new ArrayList<Sprite>();
		c.add(new NPC(surface.loadImage("img/npc3.png"), 329, 246, "I think... I just saw that mushroom... move? Remember that while in battle, use left click to shoot.", -1));
		c.add(new NPC(surface.loadImage("img/Enemy.png"), 550, 439, "Shroooooooom!", 0));
		characters.add(c);
		
		//area 2
		c = new ArrayList<Sprite>();
		c.add(new NPC(surface.loadImage("img/npc4.png"), 475, 214, "Ugh... Why is it so cold all of a sudden?", -1));
		c.add(new NPC(surface.loadImage("img/Ghost.png"), 540, 214, "Boo!", 1));
		characters.add(c);
		
		//area 3
		c = new ArrayList<Sprite>();
		c.add(new NPC(surface.loadImage("img/npc5.png"), 80, 263, "Isn't that one of the Corrupted Knight's warriors... Run!", -1));
		c.add(new NPC(surface.loadImage("img/skeleton_idle.png"), 341, 276, "Clack-Clack-Clack", 2));
		characters.add(c);
		
		//area 4
		c = new ArrayList<Sprite>();
		c.add(new NPC(surface.loadImage("img/idleKnight.png"), 360, 320, "Player... please... bring... me... peace...", 3));
		characters.add(c);
	}
	
	/**
	 * Adds the spawn points for each background to spawnPoints.
	 * @param surface The surface on which the images are loaded on.
	 */
	public void addSpawnPoints(PApplet surface) {
		spawnPoints.add(new Point2D.Double(150, 150));
		spawnPoints.add(new Point2D.Double(320, 20));
		spawnPoints.add(new Point2D.Double(10, 260));
		spawnPoints.add(new Point2D.Double(10, 260));
		spawnPoints.add(new Point2D.Double(335, 15));
	}
	
	/**
	 * Adds obstacles for each background to osbtacles.
	 * @param surface The surface on which the images are loaded on.
	 */
	public void addObstacles(PApplet surface) {
		ArrayList<Sprite> o = new ArrayList<Sprite>();
		o.add(new Sprite(0, 0, 250, 150));
		o.add(new Sprite(220, 0, 265, 230));
		o.add(new Sprite(470, 0, 300, 120));
		o.add(new Sprite(590, 111, 80, 50));
		o.add(new Sprite(0, 139, 130, 210));
		o.add(new Sprite(128, 330, 165, 250));
		o.add(new Sprite(745, 113, 50, 140));
		o.add(new Sprite(560, 265, 220, 280));
		o.add(new Sprite(400, 330, 170, 250));
		obstacles.add(o);
		
		o = new ArrayList<Sprite>();
		o.add(new Sprite(0,0,780, 15));
		o.add(new Sprite(0,0,180, 560));
		o.add(new Sprite(170,545,610, 50));
		o.add(new Sprite(615,0,180, 200));
		o.add(new Sprite(615,365,180, 200));
		obstacles.add(o);
		
		o = new ArrayList<Sprite>();
		o.add(new Sprite(0,0,750, 25));
		o.add(new Sprite(0,0,100, 200));
		o.add(new Sprite(95,0,85, 110));
		o.add(new Sprite(615,0,180, 200));
		o.add(new Sprite(615,360,180, 220));
		o.add(new Sprite(0,360,100, 220));
		o.add(new Sprite(95,455,90, 130));
		o.add(new Sprite(180,540,440, 50));
		obstacles.add(o);
		
		o = new ArrayList<Sprite>();
		o.add(new Sprite(0,0,750, 25));
		o.add(new Sprite(0,0,180, 115));
		o.add(new Sprite(0,0,100, 200));
		o.add(new Sprite(620,0,180, 550));
		o.add(new Sprite(0,355,101, 200));
		o.add(new Sprite(95,450,85, 100));
		o.add(new Sprite(0, 540, 290, 50));
		o.add(new Sprite(395, 540, 390, 30));
		obstacles.add(o);
		
		o = new ArrayList<Sprite>();
		o.add(new Sprite(0,0,750, 25));
		o.add(new Sprite(200,0,60, 450));
		o.add(new Sprite(0,450,780, 160));
		o.add(new Sprite(460,0,110, 450));
		obstacles.add(o);
	}
	/**
	 * Returns the screen number.
	 * @return The int representing the screen number.
	 */
	public int getScreenNum() {
		return screenNum;
	}
	
	/**
	 * Sets the screen number to a desired value.
	 * @param num The new integer representing screen number.
	 */
	public void setScreenNum(int num) {
		screenNum = num;
	}
	
	/**
	 * Adds PImage backgrounds to backgrounds.
	 * @param surface The surface on which the images are loaded on.
	 */
	public void addBackgrounds(PApplet surface) {
		PImage background = surface.loadImage("img/Tutorial.png");
		backgrounds.add(background);
		background = surface.loadImage("img/Area2.png");
		backgrounds.add(background);
		background = surface.loadImage("img/Area3.png");
		backgrounds.add(background);
		background = surface.loadImage("img/Area4.png");
		backgrounds.add(background);
		background = surface.loadImage("img/Area5.png");
		backgrounds.add(background);
	}
	
	/**
	 * Returns all the obstacles of each background.
	 * @return An ArrayList<ArrayList<Sprite>> representing the obstacles of all backgrounds.
	 */
	public ArrayList<ArrayList<Sprite>> getObstacles() {
		return obstacles;	
	}
	
	/**
	 * Returns all the characters of each background.
	 * @return An ArrayList<ArrayList<Sprite>> representing the characters of all backgrounds.
	 */
	public ArrayList<ArrayList<Sprite>> getCharacters() {
		return characters;
	}
	
	/**
	 * Returns all the spawnPoints of each background.
	 * @return An ArrayList<Point2D> representing the spawnPoints of each backgrounds.
	 */
	public ArrayList<Point2D> getSpawnPoints() {
		return spawnPoints;
	}
	
	/**
	 * Draws the features of the area onto the screen. Executed until the 
	 * program is stopped. Each statement is executed in 
	 * sequence and after the last line is read, the first 
	 * line is executed again.
	 * 
	 * @param surface The PApplet the area is draw with.
	 * @param s The screen the area is drawn on to.
	 */
	public void draw(PApplet surface, Screen s) {
		
		for (Sprite a : obstacles.get(screenNum)) {
			a.draw(surface);
		}
		surface.image(backgrounds.get(screenNum), 0, 0, s.DRAWING_WIDTH,s.DRAWING_HEIGHT);
		//System.out.println(screenNum);
		for (Sprite b : characters.get(screenNum)) {
			b.draw(surface);
		}
		
	}

}
