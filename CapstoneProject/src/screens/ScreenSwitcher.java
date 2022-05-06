package screens;

/**
 * An interface that can switch between screens.
 * 
 * @author Ido Haiby
 * @version 5/5/2022 
 */
public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int GAME_SCREEN = 1;
	
	public void switchScreen(int i);
}
