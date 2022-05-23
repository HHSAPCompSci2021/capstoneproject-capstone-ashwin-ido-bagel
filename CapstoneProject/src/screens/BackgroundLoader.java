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

public class BackgroundLoader {
	
	ArrayList<ArrayList<Sprite>> characters, obstacles, doors;
	ArrayList<PImage> backgrounds;
	ArrayList<Point2D> spawnPoints;
	private int screenNum;
	
	public BackgroundLoader(int screenNum) {
		this.screenNum = screenNum;
		characters = new ArrayList<ArrayList<Sprite>>();
		obstacles = new ArrayList<ArrayList<Sprite>>();
		doors = new ArrayList<ArrayList<Sprite>>();
		backgrounds = new ArrayList<PImage>();
		spawnPoints = new ArrayList<Point2D>();
	}
	
	public void addCharacters(PApplet surface) {
		ArrayList<Sprite> c = new ArrayList<Sprite>();
		//tutorial area
		c.add(new NPC(surface.loadImage("img/npc1.png"), 300, 290, "Welcome to the game. Have fun in your journey!", -1));
		c.add(new NPC(surface.loadImage("img/Ghost.png"), 500, 290, "Fight me", 1));
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
	
	public void addDoors(PApplet surface) {
		ArrayList<Sprite> d = new ArrayList<Sprite>();
		d.add(new Sprite(250, 500, 100, 10));
		doors.add(d);
		d = new ArrayList<Sprite>();
		d.add(new Sprite(250, 500, 100, 10));
		doors.add(d);
	}
	
	public void addSpawnPoints(PApplet surface) { //make these arraylists of arraylists if want more spawn points
		spawnPoints.add(new Point2D.Double(300, 200));
		spawnPoints.add(new Point2D.Double(300, 20));
	}
	
	public void addObstacles(PApplet surface) {
		ArrayList<Sprite> o = new ArrayList<Sprite>();
		o.add(new Sprite(0, 0, 10, 10));
		obstacles.add(o);
		o = new ArrayList<Sprite>();
		o.add(new Sprite(0,0,10,10));
		obstacles.add(o);
		o = new ArrayList<Sprite>();
	}
	
	public int getScreenNum() {
		return screenNum;
	}
	
	public void setScreenNum(int num) {
		screenNum = num;
	}
	
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
	
	public ArrayList<ArrayList<Sprite>> getObstacles() {
		return obstacles;	
	}
	
	public ArrayList<ArrayList<Sprite>> getCharacters() {
		return characters;
	}
	
	public ArrayList<ArrayList<Sprite>> getDoors() {
		return doors;
	}
	
	public ArrayList<Point2D> getSpawnPoints() {
		return spawnPoints;
	}
	
	public void draw(PApplet surface, Screen s) {
		
		for (Sprite a : obstacles.get(screenNum)) {
			a.draw(surface);
		}
//		for (Sprite d : doors.get(screenNum)) {
//			d.draw(surface);
//		}
		surface.image(backgrounds.get(screenNum), 0, 0, s.DRAWING_WIDTH,s.DRAWING_HEIGHT);
		//System.out.println(screenNum);
		for (Sprite b : characters.get(screenNum)) {
			b.draw(surface);
		}
		for (Sprite d : doors.get(screenNum)) {
			d.draw(surface);
		}
		
	}

}
