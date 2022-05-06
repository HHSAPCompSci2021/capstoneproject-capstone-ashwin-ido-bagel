package screens;



import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;

/**
 * Represents the main menu screen where the user is prompted to play.
 * 
 * @author Ido Haiby
 * @version 5/5/2022 
 */
public class FirstScreen extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;

	/**
	 * Creates the main menu screen that prompts the player to begin playing.
	 * 
	 * @param surface The drawing surface the main menu is drawn upon.
	 */
	public FirstScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		button = new Rectangle(800/2-100,600/2-50,200,100);
	}


	public void draw() {

		surface.background(255,255,255);
		surface.image(surface.loadImage("img/best_main_menu.png"), 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.fill(0,255,0);
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "Play";
		surface.textSize(20);
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2+5);
		str = "Reaper's Scythe";
		surface.textSize(30);
		surface.fill(255,255,255);
		w = surface.textWidth(str);
		surface.text(str, surface.width/2-w/2, 50);
		
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
	}
	

}

