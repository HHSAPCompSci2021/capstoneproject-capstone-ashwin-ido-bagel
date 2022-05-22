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
	}
	
	private void addCharacters(PApplet surface) {
		ArrayList<Sprite> c = new ArrayList<Sprite>();
		//tutorial area
		c.add(new NPC(surface.loadImage("img/npc1.png"), 300, 290, "Welcome to the game. Have fun in your journey!"));
		characters.add(c);
		
		//area 1
		//c.add(new Enemy());
		
		//area 2
		
		
		//area 3
		
		
		//area 4
		
		
		//area 5
		
		
	}
	
	private void addObstacles(PApplet surface) {
		obstacles.add(null);
	}
	
	private void addBackgrounds(PApplet surface) {
		PImage background = surface.loadImage("Tutorial.png");
		backgrounds.add(background);
		background = surface.loadImage("background.png");
		backgrounds.add(background);
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
