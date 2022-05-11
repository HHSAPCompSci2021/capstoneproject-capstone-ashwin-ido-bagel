package screens;

/**
 * An interface that can switch between screens.
 * 
 * @author Ido Haiby
 * @version 5/5/2022 
 */
public interface ScreenSwitcher {
	/**
	 * Integer value of the menu screen
	 */
	public static final int MENU_SCREEN = 0;
	/**
	 * Integer value of the game screen
	 */
	public static final int GAME_SCREEN = 1;
	
	/**
	 * Integer value of the battle screen
	 */
	public static final int BATTLE_SCREEN = 2;
	
	/**
	 * Switches between the current screen and the screen whose value was inputed.
	 * 
	 * @param i The value of the screen to be switched to.
	 */
	public void switchScreen(int i);
}
