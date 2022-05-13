package sprites;

import processing.core.PImage;

public class NPC extends Sprite {
	
	String text;
	
	public NPC(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		text = null;
	} 
	
	public void setText(String text) {
		this.text = text;
	}

}
