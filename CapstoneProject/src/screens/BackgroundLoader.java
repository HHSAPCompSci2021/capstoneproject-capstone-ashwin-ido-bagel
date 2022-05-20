package screens;

import java.util.ArrayList;
import java.util.List;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import sprites.Sprite;

public class BackgroundLoader {
	
	ArrayList<ArrayList<Sprite>> characters, obstacles;
	ArrayList<PImage> backgrounds;
	int screenNum;
	
	public BackgroundLoader(int screenNum) {
		this.screenNum = screenNum;
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
