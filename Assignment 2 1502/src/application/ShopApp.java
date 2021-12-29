package application;

import mru.tsc.controller.ShopController;
import mru.tsc.view.Menu;

/**
 * The main class, gets the program started by loading the arraylist and then
 * loading the main menu
 * 
 * @author Evan Gadsby
 *
 */

public class ShopApp {

	public static void main(String[] args) throws Exception {
		new ShopController();
		new Menu();
	}
}
