package screens;

import java.util.ArrayList;
import java.util.List;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import sprites.Sprite;

public class BackgroundLoader {
	
	List<Sprite> characters, obstacles;
	PImage background;
	//int screenNum;
	
	public BackgroundLoader(PImage background, List<Sprite> characters, List<Sprite> obstacles) {
		this.background = background;
		this.characters = characters;
		this.obstacles = obstacles;
		//this.screenNum = screenNum;
	}
	
	public void draw(PApplet surface, Screen s) {
		for (Sprite a : obstacles) {
			a.draw(surface);
		}
		surface.image(background, 0, 0, s.DRAWING_WIDTH,s.DRAWING_HEIGHT);
		for (Sprite b : characters) {
			b.draw(surface);
		}
		
	}

}
