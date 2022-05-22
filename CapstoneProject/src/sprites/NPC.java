package sprites;

import javax.swing.JOptionPane;

import processing.core.PImage;

/**
 * Represents an npc that can be interacted with by the Player.
 * 
 * @author Ido Haiby
 * @version 5/13/22
 */
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
	
	/**
	 * Creates an NPC character that the player cam interact with and gets prompted.
	 * 
	 * @param img image of the npc.
	 * @param x x-coordinate of the npc.
	 * @param y y-coordinate of the npc.
	 */
	public NPC(PImage img, int x, int y, String text) {
		super(img, x, y, NPC_WIDTH, NPC_HEIGHT);
		this.text = text;
	} 
	
	/**
	 * Sets the text that the npc prompts when clicked.
	 * 
	 * @param text Text to be prompted to the user
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Displays the text of the npc as a pop up.
	 * 
	 */
	public void displayText() {
		JOptionPane.showMessageDialog(null, text);
	}

}
