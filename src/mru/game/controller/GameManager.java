package mru.game.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.application.GameApp;
import mru.game.model.Player;
import mru.game.model.PuntoBancoGame;
import mru.game.view.Menu;

public class GameManager extends Menu {

	/**
	 * The GameManager class is what runs the program as a whole. It is where most
	 * of the menu stuff is being controlled, as well as run the game itself from here.
	 *
	 * @author Jurriel Tapado
	 */

	/**
	 * This locates where the txt file is giving access to using it in this class.
	 */
	private final String FILE_PATH = "res/playersdb.txt";
	Scanner input = new Scanner(System.in);
	ArrayList<Player> players;
	Menu appMen;
	GameApp ga;
	String playerName;

	/**
	 * Loads data from the playersdb.txt document (ex. previous players, balance,
	 * number of wins), and opens the main menu.
	 * 
	 * @throws Exception
	 */
	public GameManager() throws Exception {
		players = new ArrayList<>();
		appMen = new Menu();

		loadData();

		launchApplication();
	}

	/**
	 * This opens the main menu, giving you the option to pick whether you want to
	 * play game, search or save and exit the program. Each option case has a
	 * certain command it will run. For example 'p' will run the PlayGame command
	 * which will make you enter your name, then runs the Punto Banco Game.
	 * 
	 * @throws IOException
	 */
	public void launchApplication() throws IOException {

		boolean flag = true;
		int option;

		while (flag) {
			option = appMen.showMainMenu();
			switch (option) {
			case 'p':
				PlayGame();
				break;
			case 's':
				Search();
				break;
			case 'e':
				Save();
				flag = false;
			}
		}
	}

	/**
	 * Gives you the message to enter your name and search for it in the database If
	 * the name was not found(null), a new player will be created and added into the
	 * database if you were to play it again in the future, it will give you a
	 * starting balance of 100, and prints out a welcome message.
	 * 
	 * @throws IOException
	 */
	private void PlayGame() throws IOException {
		String name = appMen.promptName();
		playerName = name;
		Player p = SearchByName(name);
		int balance;

		if (p == null) {
			balance = 100;
			players.add(new Player(name, balance, 0));
			String welcome = "Welcome " + name + " Your current balance is " + balance;
			System.out.println(welcome);
		} else {
			balance = p.getId();
		}
		PuntoBancoGame game = new PuntoBancoGame(balance);
		Saveaftergame(game.gettotalcash(), game.getwins());

	}

	/**
	 * The following method opens the SubMenu also known as the search menu. Here,
	 * you will be able to search for the top players (players with the most wins,
	 * search by player name showing their balance and number of wins, and a back
	 * option to be brought back to the Main Menu of the game.
	 */
	private void Search() {
		char option = appMen.showSubMenu();

		switch (option) {
		case 't':
			FindTopPlayer();
			System.out.println(" ");
			System.out.println("Press enter to continue");
			input.nextLine();
			break;
		case 'n':
			String name = appMen.promptName();
			Player ply = SearchByName(name);
			appMen.showPlayer(ply);
			System.out.println(" ");
			System.out.println("Press enter to continue");
			input.nextLine();
			break;
		case 'b':
			break;
		}
	}

	/**
	 * Used to search for players by name. It gets the players info from the
	 * database, using getName from the Player Class.
	 * 
	 * @param name
	 * @return
	 */
	private Player SearchByName(String name) {

		Player ply = null;

		for (Player p : players) {
			if (p.getName().equals(name)) {
				ply = p;
				break;
			}

		}
		return ply;
	}

	/**
	 * Used to find top player with the most numbers of wins by looking into the
	 * database (playersdb.txt) who has the highest number of wins.
	 */
	private void FindTopPlayer() {

		int max = -1;

		for (Player p : players) {
			if (max < p.getNumOfWins())
				;
			max = p.getNumOfWins();
		}

		System.out.println(players.get(1));

	}

	/**
	 * saves all information into database, and exit the program.
	 * 
	 * @throws IOException
	 */
	public void Save() throws IOException {
		File db = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(db);

		for (Player p : players) {
			pw.println(p.format());
		}

		pw.close();
	}

	/**
	 * Loads the database (playersdb.txt) and reads it. It is then splits the info
	 * from the database into separate categories using ";" symbol as the splitter.
	 * 0 = name, 1 = balance, 2 = number of games won.
	 * 
	 * @throws Exception
	 */
	public void loadData() throws Exception {
		File db = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;

		if (db.exists()) {
			Scanner fileReader = new Scanner(db);

			while (fileReader.hasNextLine()) {

				currentLine = fileReader.nextLine();

				splittedLine = currentLine.split(";");
				Player p = new Player(splittedLine[0], Integer.parseInt(splittedLine[1]),
						Integer.parseInt(splittedLine[2]));
				players.add(p);
			}

			fileReader.close();
		}
	}

	/**
	 * Saves all data once you save and exit. Updates the balance and number of wins
	 * of the player in database.
	 * 
	 * @param c
	 * @param w
	 * @throws IOException
	 */
	public void Saveaftergame(int c, int w) throws IOException {
		Player ply = SearchByName(playerName);
		ply.updatebalance(c);
		ply.updateWins(w);
		Save();
	}
}