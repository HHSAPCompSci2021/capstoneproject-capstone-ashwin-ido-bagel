package sprites;

import javax.swing.JOptionPane;

import processing.core.PImage;

public class NPC extends Sprite {
	
	String text;
	/**
	 * The width of the NPC.
	 */
	public static final int NPC_WIDTH = (int)(60 * 500d/679);
	
	/**
	 * The height of the NPC.
	 */
	public static final int NPC_HEIGHT = (int)(90 * 737d/892);
	
	
	public NPC(PImage img, int x, int y) {
		super(img, x, y, NPC_WIDTH, NPC_HEIGHT);
		text = "Hello";
	} 
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void displayText() {
		JOptionPane.showMessageDialog(null, text);
	}

}
