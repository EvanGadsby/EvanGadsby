package mru.game.view;

import java.io.IOException;
import java.util.Scanner;

import mru.game.model.Player;
import mru.game.model.PuntoBancoGame;

public class Menu {
	/**
	 * This class will be used to show the menus and sub menus to the user It also
	 * prompts the user for the inputs and validates them
	 */

	Scanner input;

	public Menu() {
		input = new Scanner(System.in);
	}

	/**
	 * When you first run the game it will show you this main menu where you select
	 * whether you want to play the game, Search brings you into the SubMenu, and
	 * Exit closes the program.
	 *
	 * @return option
	 */
	public char showMainMenu() {
		System.out.println("Select one of the following options");
		System.out.println("\t(P) Play game");
		System.out.println("\t(S) Search");
		System.out.println("\t(E) Save and Exit");
		System.out.print("Enter a choice:");
		char option = input.nextLine().toLowerCase().charAt(0);
		return option;

	}

	/**
	 * In the SubMenu you have the option to see who's the player with the most
	 * wins, search for a name in the database, and return back to the main menu.
	 * 
	 * @return option
	 */
	public char showSubMenu() {
		System.out.println("Select one of these options");
		System.out.println("\t(T) Top player (Most number of wins)");
		System.out.println("\t(N) Looking for a Name");
		System.out.println("\t(B) Back to Main Menu");
		System.out.print("Enter a choice:");
		char option = input.nextLine().toLowerCase().charAt(0);
		return option;
	}

	/**
	 * The following method prints out a message telling you to type in your name.
	 * 
	 * @return name
	 */
	public String promptName() {
		System.out.print("Enter name here:");
		String name = input.nextLine().trim().toLowerCase();
		return name;
	}

	/**
	 * showPlayer is the command that is run when you look for a name in the
	 * subMenu. If the name you searched was found it will give you the users name,
	 * balance, and number of wins. If the name was not found, you will receive the
	 * message below.
	 */
	public void showPlayer(Player ply) {
		if (ply != null)
			System.out.println(ply);
		else
			System.out.println("No record found under this name");
	}

	/**
	 * This will run at the same time as the mainMenu, and it gives you the ability
	 * to select where you want to go by typing in the case input.
	 * 
	 * @throws IOException
	 */
	public void launchApplication(char option) throws IOException {
		boolean flag = true;

		while (flag) {
			switch (option) {
			case 'P':
				openGame();
			case 'S':
				showSubMenu();
			case 'E':

			}
		}
	}

	/**
	 * Opens game through the launch application option case 'P'
	 * 
	 * @throws IOException
	 */
	private void openGame() throws IOException {
		new PuntoBancoGame(0);
	}

}
