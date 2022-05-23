package screens;

import java.util.ArrayList;
import java.util.List;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import sprites.Enemy;
import sprites.NPC;
import sprites.Sprite;

public class BackgroundLoader {
	
	ArrayList<ArrayList<Sprite>> characters, obstacles;
	ArrayList<PImage> backgrounds;
	int screenNum;
	
	public BackgroundLoader(int screenNum) {
		this.screenNum = screenNum;
		characters = new ArrayList<ArrayList<Sprite>>();
		obstacles = new ArrayList<ArrayList<Sprite>>();
		backgrounds = new ArrayList<PImage>();
	}
	
	public void addCharacters(PApplet surface) {
		ArrayList<Sprite> c = new ArrayList<Sprite>();
		//tutorial area
		characters.add(c);
		c.add(new NPC(surface.loadImage("img/npc1.png"), 300, 290, "Welcome to the game. Have fun in your journey!"));
		characters.add(c);
		
		//area 1
		c = new ArrayList<Sprite>();
		
		//area 2
		
		
		//area 3
		
		
		//area 4
		
		
		//area 5
		
		
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
	
	public void addBackgrounds(PApplet surface) {
		PImage background = surface.loadImage("img/battlearena.png");
		backgrounds.add(background);
		background = surface.loadImage("img/Tutorial.png");
		backgrounds.add(background);
		background = surface.loadImage("img/background.png");
		backgrounds.add(background);
	}
	
	public ArrayList<ArrayList<Sprite>> getObstacles() {
		return obstacles;	
	}
	
	public ArrayList<ArrayList<Sprite>> getCharacters() {
		return characters;
	}
	
	public void draw(PApplet surface, Screen s) {
		
		for (Sprite a : obstacles.get(screenNum)) {
			a.draw(surface);
		}
		surface.image(backgrounds.get(screenNum), 0, 0, s.DRAWING_WIDTH,s.DRAWING_HEIGHT);
		for (Sprite b : characters.get(screenNum)) {
			b.draw(surface);
		}
		
	}

}
