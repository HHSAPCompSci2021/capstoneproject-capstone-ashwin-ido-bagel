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
	
	ArrayList<ArrayList<Sprite>> characters, obstacles;
	ArrayList<PImage> backgrounds;
	ArrayList<Point2D> spawnPoints;
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
		//c.add(new NPC(surface.loadImage("img/Ghost.png"), 500, 290, "Fight me!", 3));
		characters.add(c);
		
		//area 1
		c = new ArrayList<Sprite>();
		//c.add(new NPC(surface.loadImage("img/npc1.png"), 300, 290, "Welcome to the game. Have fun in your journey!", -1));
		characters.add(c);
		
		//area 2
		
		
		//area 3
		
		
		//area 4
		
		
		//area 5
		
		
	}
	
	/**
	 * Adds the spawn points for each background to spawnPoints.
	 * @param surface The surface on which the images are loaded on.
	 */
	public void addSpawnPoints(PApplet surface) {
		spawnPoints.add(new Point2D.Double(150, 150));
		spawnPoints.add(new Point2D.Double(320, 20));
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
		o.add(new Sprite(0,0,10,10));
		obstacles.add(o);
		o = new ArrayList<Sprite>();
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
	
	public void draw(PApplet surface, Screen s) {
		
//		for (Sprite a : obstacles.get(screenNum)) {
//			a.draw(surface);
//		}
		surface.image(backgrounds.get(screenNum), 0, 0, s.DRAWING_WIDTH,s.DRAWING_HEIGHT);
		//System.out.println(screenNum);
		for (Sprite b : characters.get(screenNum)) {
			b.draw(surface);
		}
		for (Sprite a : obstacles.get(screenNum)) {
			a.draw(surface);
		}
		
	}

}
