package screens;


import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;

import core.DrawingSurface;
import sprites.Mario;
import sprites.Sprite;


public class SecondScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Rectangle screenRect;
	private PImage background;

	private Mario mario;
	private List<Sprite> obstacles;

	public SecondScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Sprite>();
	}


	public void spawnNewMario() {
		mario = new Mario(surface.loadImage("img/character.png"), DRAWING_WIDTH/2-Mario.MARIO_WIDTH/2,50);
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		background = surface.loadImage("img/background.png");
		spawnNewMario();
	}

	public void loadBackground() { 
		surface.image(background, 0,0, DRAWING_WIDTH,DRAWING_HEIGHT);
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		
		// drawing stuff
		
		surface.background(0,255,255);   
		loadBackground();
		for (Sprite s : obstacles) {
			s.draw(surface);
		}

		mario.draw(surface);
		
		// modifying stuff

		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT))
			mario.walk(Mario.Direction.Left);
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			mario.walk(Mario.Direction.Right);
		if (surface.isPressed(KeyEvent.VK_UP))
			mario.walk(Mario.Direction.Up);
		if (surface.isPressed(KeyEvent.VK_DOWN))
			mario.walk(Mario.Direction.Down);
		

		mario.act(obstacles);

		if (!screenRect.intersects(mario))
			spawnNewMario();

	}

	
}