package screens;

import java.util.ArrayList;
import java.util.List;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import sprites.Sprite;

public class BackgroundLoader {
	
	List<Sprite> characters, obstacles;
	ArrayList<PImage> backgrounds;
	int screenNum;
	
	public BackgroundLoader(ArrayList<PImage> backgrounds, List<Sprite> characters, List<Sprite> obstacles, int screenNum) {
		this.backgrounds = backgrounds;
		this.characters = characters;
		this.obstacles = obstacles;
		this.screenNum = screenNum;
	}
	
	public void draw(PApplet surface, Screen s) {
		for (Sprite a : obstacles) {
			a.draw(surface);
		}
		surface.image(backgrounds.get(screenNum), 0, 0, s.DRAWING_WIDTH,s.DRAWING_HEIGHT);
		for (Sprite b : characters) {
			b.draw(surface);
		}
		
	}

}
