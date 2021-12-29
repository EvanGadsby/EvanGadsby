package mru.tsc.view;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.GamePlayerMismatch;
import exceptions.NegativeInputPrice;
import mru.tsc.controller.ShopController;
import mru.tsc.model.Toy;

/**
 * This class displays the menus and does calculations for various things
 * 
 * @author Evan Gadsby & Jurriel Tapado
 *
 */

public class Menu extends ShopController {
	private final String FILE_PATH = "res/toys.txt";
	static Scanner in = new Scanner(System.in);
	ArrayList<Toy> toys = new ArrayList<>();

	/**
	 * Starts the startmenu up
	 * 
	 * @throws Exception
	 */
	public Menu() throws Exception {
		startMenu();
	}

	/**
	 * Displays the start menu for the program and allows the user to select from
	 * multiple different options.
	 * 
	 * @throws Exception
	 */
	public void startMenu() throws Exception {
		System.out.println("***********************************");
		System.out.println("***Welcome to Toy Store Company!***");
		System.out.println("***********************************");
		System.out.println(" ");
		System.out.println("How May We Help You?");
		System.out.println(" ");
		System.out.println("\t (1) Search Inventory and Purchase Toy");
		System.out.println("\t (2) Add New Toy");
		System.out.println("\t (3) Remove Toy");
		System.out.println("\t (4) Save and Exit");
		System.out.println(" ");
		int selection = getInt(4);
		switch (selection) {
		case 1:
			// Search Toy Function
			searchMenu();
			break;
		case 2:
			addToy();
			break;
		case 3:
			removeToy();
			break;
		case 4:
			super.Save();
			break;
		}

	}

	/**
	 * Calls methods to Remove Toys from the arraylist
	 * 
	 * @throws Exception
	 */
	public void removeToy() throws Exception {
		String SN = promptSN();
		Toy t1 = SNSearch(SN);
		ShowRemoveSN(t1);
		if (t1 != null) {
			System.out.println("Do you want to remove this toy? Y/N");
			char option = in.next().toUpperCase().charAt(0);
			if (option == 'Y') {
				super.RemoveToy(SN);
				System.out.println("Toy Removed");
			}
		}
		startMenu();
	}

	/**
	 * This method is used to validate a users int input, it also stops them from
	 * entering a int that is lower or higher than the options currently given to
	 * them by the menu (the lastOption int represents the largers number that the
	 * user is allowed to input)
	 * 
	 * @param lastOption
	 * @return input
	 */
	public int getInt(int lastOption) {
		boolean validint = false;
		int input = 0;
		while (validint == false) {
			System.out.print("Please enter an option > ");
			if (in.hasNextInt()) {
				input = in.nextInt();
				in.nextLine();
				if (input > lastOption || input <= 0) {
					System.out.println("Please enter a valid option\n");
				} else {
					validint = true;
				}
			} else {
				System.out.println("Please input a valid integer\n");
				in.nextLine();
			}
		}
		return input;
	}

	/**
	 * Calls methods to read the users SN, then checks to make sure it is a valid
	 * and unique SN. Lastly it determines what type of toy the SN is for based on
	 * the first digit and calls the appropriate method for that Toy type.
	 * 
	 * @throws Exception
	 */
	public void addToy() throws Exception {
		String SN = checkSN();
		boolean takenSN = super.repeatSN(SN);
		while (takenSN) {
			System.out.println("That SN already exists, please enter a new Serial Number");
			SN = checkSN();
			takenSN = super.repeatSN(SN);
		}
		String toyType = super.checkType(("" + SN).charAt(0));
		// Calls Figure type
		if (toyType == "F") {
			addFigure(SN);
		}
		// Calls Animal Type
		if (toyType == "A") {
			addAnimal(SN);
		}
		// Calls Puzzle Type
		if (toyType == "P") {
			addPuzzle(SN);
		}
		// Calls Board Game Type
		if (toyType == "B") {
			addBoardGame(SN);
		}
		startMenu();
	}

	/**
	 * This method is used to open the search menu up. THe first case will make you
	 * search for a toy with a Serial Number, the second makes you search by name,
	 * and the third searches by type.
	 * 
	 * @throws Exception
	 */
	public void searchMenu() throws Exception {
		System.out.println("Find Toys with:");
		System.out.println(" ");
		System.out.println("\t (1) Serial Number ( SN )");
		System.out.println("\t (2) Toy Name");
		System.out.println("\t (3) Type");
		System.out.println("\t (4) Back to Main Menu");
		System.out.println(" ");
		// int option = getInt(4);

		int option = getInt(4);
		switch (option) {
		case 1:
			String SN = promptSN();
			Toy t1 = SNSearch(SN);
			showSN(t1);
			searchMenu();
			break;
		case 2:
			String name = promptName();
			toySearch(name);
			searchMenu();
			break;
		case 3:
			// String type = promptType();
			// Toy t3 = typeSearch(type);
			// showType(t3);
			String choice = promptType();
			super.typeSearching(choice);
			searchMenu();
			break;
		case 4:
			startMenu();
		}
	}

	/**
	 * This method will bring a message up telling you to input the Serial Number of
	 * the toy in order to find it in the database.
	 * 
	 * @return
	 */

	public String promptSN() {
		System.out.println(" ");
		System.out.print("Enter Serial Number here:");
		String SN = in.nextLine().trim();
		return SN;
	}

	/**
	 * This method will print out the Toy's information after inputting it on the
	 * promptSN method. If it is not found you will get a message, and will be sent
	 * back to the Search Menu.
	 * 
	 * @param tys
	 * @throws Exception
	 */

	public void showSN(Toy tys) throws Exception {
		if (tys != null)
			System.out.print(tys);
		else {
			System.out.println(" ");
			System.out.println("Serial Number not found");
		}
		System.out.println(" ");
		System.out.println("Press Enter to continue");
		in.nextLine();
		searchMenu();
	}

	/**
	 * Displays the toy for removal during the removal method
	 * 
	 * @param tys
	 * @throws Exception
	 */

	public void ShowRemoveSN(Toy tys) throws Exception {
		if (tys != null)
			System.out.print(tys);
		else {
			System.out.println(" ");
			System.out.println("Serial Number not found");
		}
		System.out.println(" ");
		System.out.println("Press Enter to continue");
		in.nextLine();
	}

	/*
	 * private Type toySearch(String type) { // TODO Auto-generated method stubf }
	 */
	private void typeSearch() {
		// TODO Auto-generated method stub

	}

	/**
	 * This method reads serial number validates that the user inputed a 10 digit
	 * long SN by using the .length function
	 * 
	 * @return input
	 */
	public String checkSN() {
		boolean validlong = false;
		String input = "0";
		System.out.println("Enter Serial Number: ");
		while (validlong == false) {
			if (in.hasNextLong()) {
				input = in.next();
				in.nextLine();
				int length = String.valueOf(input).length();
				if (length != 10) {
					System.out.println("Please enter a 10 digit number\n");
				} else {
					validlong = true;
				}
			} else {
				System.out.println("Please input a number");
				in.nextLine();
			}
		}
		return input;
	}

	/**
	 * This method calls other methods which allows the user to fill out the
	 * information neccesary to create a Figure object. It also validates that they
	 * inputted a valid figure classification
	 * 
	 * @param SN
	 * @throws NegativeInputPrice
	 */
	public void addFigure(String SN) throws NegativeInputPrice {
		boolean valid = false;
		String name = addName();
		String brand = addBrand();
		double price = addPrice();
		int count = addCount();
		int age = addAge();
		String type = null;
		while (valid == false) {
			System.out.println("choose a classificaton for this figure\n(A)ction \n(D)oll \n(H)istoric");
			char classification = in.nextLine().toUpperCase().charAt(0);
			if (classification == 'A' || classification == 'D' || classification == 'H') {
				valid = true;
				type = "" + classification;
			} else {
				System.out.println("Please enter a valid classification");
				in.nextLine();
			}

		}
		// calling the superclass to add the data to the arraylist
		super.addFigure(SN, name, brand, price, count, age, type);
		System.out.println("New toy added");
		System.out.println(" ");
		System.out.println("Press Enter to Continue");
		in.nextLine();
	}

	/**
	 * This method calls other methods which allows the user to fill out the
	 * information neccesary to create a Animal object. It also asks for the animals
	 * meterial and siaze and validates that they inputted a valid size for animal.
	 * 
	 * @param SN
	 * @throws NegativeInputPrice
	 */
	public void addAnimal(String SN) throws NegativeInputPrice {
		boolean valid = false;
		String name = addName();
		String brand = addBrand();
		double price = addPrice();
		int count = addCount();
		int age = addAge();
		System.out.println("What Material is this toy made out of?");
		String material = in.next();
		in.nextLine();
		String type = null;
		while (valid == false) {
			System.out.println("choose a size for the animal \n(S)mall \n(M)edium \n(L)arge");
			char classification = in.nextLine().toUpperCase().charAt(0);
			if (classification == 'S' || classification == 'M' || classification == 'L') {
				valid = true;
				type = "" + classification;
			} else {
				System.out.println("Please enter a valid size");
				in.nextLine();
			}

		}
		// Calling the superclass to add the data to the arraylist
		super.addAnimal(SN, name, brand, price, count, age, material, type);
		System.out.println("New toy added");
		System.out.println(" ");
		System.out.println("Press Enter to Continue");
		in.nextLine();
	}

	/**
	 * This method calls other methods which allows the user to fill out the
	 * information neccesary to create a puzzle object. It also validates that they
	 * inputted a valid puzzle type.
	 * 
	 * @param SN
	 * @throws NegativeInputPrice
	 */
	public void addPuzzle(String SN) throws NegativeInputPrice {
		boolean valid = false;
		String name = addName();
		String brand = addBrand();
		double price = addPrice();
		int count = addCount();
		int age = addAge();
		String type = null;
		while (valid == false) {
			System.out
					.println("What type of puzzle is this? \n(M)echanical \n(C)ryptic \n(L)ogic \n(T)rivia \n(R)iddle");
			char classification = in.nextLine().toUpperCase().charAt(0);
			if (classification == 'C' || classification == 'M' || classification == 'L' || classification == 'T'
					|| classification == 'R') {
				valid = true;
				type = "" + classification;
			} else {
				System.out.println("Please enter a valid puzzle type");
				in.nextLine();
			}
			// calling superclass to add item to the arraylist
			super.addPuzzle(SN, name, brand, price, count, age, type);
			System.out.println("New toy added");
			System.out.println(" ");
			System.out.println("Press Enter to Continue");
			in.nextLine();
		}
	}

	/**
	 * This method calls other methods which allows the user to fill out the
	 * information neccesary to create a BoardGame object. it also asks the user to
	 * enter the name of the board games designers
	 * 
	 * @param SN
	 * @throws GamePlayerMismatch, NegativeInputPrice
	 */
	public void addBoardGame(String SN) throws GamePlayerMismatch, NegativeInputPrice {
		String name = addName();
		String brand = addBrand();
		double price = addPrice();
		int count = addCount();
		int age = addAge();
		String players = addPlayers();
		System.out.println("Enter Designer Names(Use ',' to seperate the names if there is more than one name): ");
		String Designers = in.nextLine();
		// Calling superclass to add boardgame to arraylist
		super.addBoardGame(SN, name, brand, price, count, age, players, Designers);
		System.out.println("New toy added");
		System.out.println(" ");
		System.out.println("Press Enter to Continue");
		in.nextLine();

	}

	/**
	 * Method to add Name of toy
	 * 
	 * @return Toy Name
	 */
	public String addName() {
		System.out.println("What is this toys name?");
		return in.next();
	}

	/**
	 * Method to add Brand of toy
	 * 
	 * @return Toy Brand
	 */
	public String addBrand() {
		System.out.println("What is the brand of this toy?");
		return in.next();
	}

	/**
	 * Asks the user to input the price for the toy. Validates that the price
	 * inputted is not negative by using the NegativeInputPrice Exception
	 * 
	 * @return price
	 */
	public double addPrice() {
		boolean validinput = false;
		String n1;
		double price = 0;
		while (validinput == false) {
			do {
				in.nextLine();
				System.out.println("What is the price of this toy?");
				n1 = in.nextLine();
				price = Double.parseDouble(n1);
				validinput = true;

			} while (n1 == null);
			try {
				checkPrice(price);
			} catch (NegativeInputPrice e) {
				System.out.println(e.getMessage());
				System.out.println(" ");
				System.out.println("Press Enter To Continue...");
				validinput = false;
			}
		}
		return price;
	}

	/**
	 * Used for Board Games. Allows the user to input the minimum and maximum amount
	 * of players. It then returns that info in a string format. It also validates
	 * that the minimum players is not more than the maximum players using
	 * exeptions.
	 * 
	 * @return playerRange
	 * @throws GamePlayerMismatch
	 */
	public String addPlayers() throws GamePlayerMismatch {
		boolean validinput = false;
		String n1;
		String n2;
		int minPlayers = 0;
		int maxPlayers = 0;
		while (validinput == false) {
			do {
				System.out.println("What is the minumum players for this board game?");
				n1 = in.nextLine();
				minPlayers = Integer.parseInt(n1);
			} while (n1 == null);

			do {
				System.out.println("What is the maximum players for this board game?");
				n2 = in.nextLine();
				maxPlayers = Integer.parseInt(n2);
				validinput = true;
			} while (n2 == null);

			try {
				checkPlayers(minPlayers, maxPlayers);
			} catch (GamePlayerMismatch e) {
				System.out.println(e.getMessage());
				System.out.println(" ");
				validinput = false;
			}
		}

		String playerRange = "" + minPlayers + "-" + maxPlayers;
		return playerRange;
	}

	/**
	 * method for entering amount of toy available. Calls intCheck method to
	 * validate user input
	 * 
	 * @return Number of Toy available
	 */
	public int addCount() {
		System.out.println("How many are available?");
		return intCheck();
	}

	/**
	 * method for entering the appropriate age of the toy. Calls intCheck method to
	 * validate user input
	 * 
	 * @return Appropriate age for toy
	 */
	public int addAge() {
		System.out.println("What is the appropriate age for this toy?");
		return intCheck();
	}

	/**
	 * validates that the user did not enter a negative price
	 * 
	 * @param price
	 * @return price
	 * @throws NegativeInputPrice
	 */
	public double checkPrice(double price) throws NegativeInputPrice {
		if (price < 0) {
			throw new NegativeInputPrice();
		}
		return price;
	}

	/**
	 * validates that the user did not enter a higher amount of minimum players
	 * compared to maximum players
	 * 
	 * @param min
	 * @param max
	 * @throws GamePlayerMismatch
	 */
	public void checkPlayers(int min, int max) throws GamePlayerMismatch {
		if (max < min) {
			throw new GamePlayerMismatch();
		}
	}

	/**
	 * Method to validate user int inputs.
	 * 
	 * @return input
	 */
	public int intCheck() {
		boolean validInt = false;
		int input = 0;
		while (validInt == false) {
			if (in.hasNextInt()) {
				input = in.nextInt();
				in.nextLine();
				validInt = true;
			} else {
				System.out.println("Please input an integer");
				in.nextLine();
			}
		}
		return input;

	}

	/**
	 * This method asks you to input the toy name in order for it to search in the
	 * database.
	 * 
	 * @return
	 */
	public String promptName() {
		System.out.println(" ");
		System.out.print("Enter Toy Name here:");
		String SN = in.nextLine().trim();
		return SN;
	}

	/**
	 * This method will print out the toys information after inputting it on the
	 * promptName method. Once again, if it is not found you will get a message, and
	 * will be sent back to the Search Menu.
	 * 
	 * @param nme
	 * @throws Exception
	 */
	public void showName(Toy nme) throws Exception {
		if (nme != null)
			System.out.print(nme);
		else {
			System.out.println(" ");
			System.out.println("Toy name not found");
		}
		System.out.println(" ");
		System.out.println("Press Enter to continue >");
		in.nextLine();
		searchMenu();
	}

	/**
	 * This method asks you to input the toy name in order for it to search in the
	 * database.
	 * 
	 * @return
	 */
	public String promptType() {
		boolean correctType = false;
		String name = null;
		char choice = 0;
		while (correctType == false) {
			System.out.println(" ");
			System.out.println("What type of toy are you looking for?");
			System.out.println("(A)nimal");
			System.out.println("(B)oard Game");
			System.out.println("(F)igure");
			System.out.println("(P)uzzle");
			System.out.println(" ");
			System.out.println("Enter Toy Type Here:");
			name = in.nextLine().trim();
			choice = name.toUpperCase().charAt(0);
			if (choice == 'A' || choice == 'B' || choice == 'F' || choice == 'P') {
				correctType = true;
			} else {
				System.out.println("Please input a valid type:");
			}
		}
		return name;
	}
}