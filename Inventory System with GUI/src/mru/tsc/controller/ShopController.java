package mru.tsc.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Figure;
import mru.tsc.model.Puzzle;
import mru.tsc.model.Toy;
import mru.tsc.view.Controller;

public class ShopController {

	Controller S;

	/**
	 * This class interacts with the arraylist and the database file
	 * 
	 * @throws Exception
	 * @author Evan Gadsby & Jurriel Tapado
	 */

	public ShopController() throws Exception {
		loadData();
	}

	private final String FILE_PATH = "res/toys.txt"; // Declaring the filepath for the database,
	Scanner input = new Scanner(System.in); // Creating the scanner to read user inputs
	ArrayList<Toy> toys = new ArrayList<>(); // initializing the arraylist for the toys

	/**
	 * The loaddata function reads the text from the txt file. It determines what
	 * type of toy it is reading based on the SN . After knowing the type of toy, it
	 * splits the data up into the appropriate pieces and uses those pieces to
	 * initialize correct type of toy and then adds those toys to the toy arraylist.
	 * 
	 * @throws Exception
	 */

	public void loadData() throws Exception {
		File db = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;

		// reading the file
		if (db.exists()) {
			Scanner fileReader = new Scanner(db);

			while (fileReader.hasNextLine()) {

				currentLine = fileReader.nextLine();
				splittedLine = currentLine.split(";");
				char SN = splittedLine[0].charAt(0);

				// mytext mytext+filereader
				// determining the file type
				if (checkType(SN) == "F") {
					Toy F = new Figure(splittedLine[0], splittedLine[1], splittedLine[2],
							Double.parseDouble(splittedLine[3]), Integer.parseInt(splittedLine[4]),
							Integer.parseInt(splittedLine[5]), splittedLine[6]);
					toys.add(F);

				}
				if (checkType(SN) == "A") {
					Toy A = new Animal(splittedLine[0], splittedLine[1], splittedLine[2],
							Double.parseDouble(splittedLine[3]), Integer.parseInt(splittedLine[4]),
							Integer.parseInt(splittedLine[5]), splittedLine[6], splittedLine[7]);
					toys.add(A);
				}
				if (checkType(SN) == "P") {
					Toy P = new Puzzle(splittedLine[0], splittedLine[1], splittedLine[2],
							Double.parseDouble(splittedLine[3]), Integer.parseInt(splittedLine[4]),
							Integer.parseInt(splittedLine[5]), splittedLine[6]);
					toys.add(P);
				}
				if (checkType(SN) == "B") {
					Toy B = new BoardGame(splittedLine[0], splittedLine[1], splittedLine[2],
							Double.parseDouble(splittedLine[3]), Integer.parseInt(splittedLine[4]),
							Integer.parseInt(splittedLine[5]), splittedLine[6], splittedLine[7]);
					toys.add(B);

				}
			}

			// closing the filereader
			fileReader.close();
		}
	}

	/**
	 * This method takes in the first digit of the serial number and then uses a
	 * case system to determine what type of toy it is dealing with. once it knows
	 * the type of toy it returns it as a char
	 * 
	 * @param SN
	 * @return type
	 */
	public String checkType(char SN) {
		String Type = null;
		switch (SN) {
		case '1':
		case '0':
			Type = "F";
			break;
		case '2':
		case '3':
			Type = "A";
			break;
		case '4':
		case '5':
		case '6':
			Type = "P";
			break;
		case '7':
		case '8':
		case '9':
			Type = "B";
			break;
		}
		return Type;
	}

	/**
	 * This method takes a seriel number (that the user inputted earlier) and checks
	 * the arraylist to make sure the serial number is unique. If the serial number
	 * is already in use by another toy it returns a true and the user must
	 * re-enter.
	 * 
	 * @param SN
	 * @return repeatSN
	 * @throws Exception
	 */
	public boolean repeatSN(String SN) throws Exception {
		boolean repeatSN = false;
		String StoredSN = "0";
		int i = 0;
		while (i < toys.size()) {
			StoredSN = toys.get(i).getSN();
			if (StoredSN.equals(SN)) {
				repeatSN = true;
			}
			i++;
		}
		return repeatSN;

	}

	/**
	 * This searches for the Serial number in the Array list later then printing it
	 * out with the showSN method in Menu.java.
	 * 
	 * @param SN
	 * @return
	 */

	public Toy SNSearch(String SN) {
		Toy toy = null;
		for (Toy t : toys) {
			if (t.getSN().equals(SN)) {
				toy = t;
				break;
			}
		}
		return toy;
	}

	/**
	 * Removes a Toy from the database based on the SN
	 * 
	 * @param SN
	 */
	public void RemoveToy(String SN) {
		int i = 0;
		while (i < toys.size()) {
			String StoredSN = toys.get(i).getSN();
			if (StoredSN.equals(SN)) {
				toys.remove(i);
			}
			i++;
		}
	}

	/**
	 * This method takes several parameters from the menu sub class and uses them to
	 * create a new Figure toy and then add that Figure to the arraylist.
	 * 
	 * @param sN
	 * @param name
	 * @param brand
	 * @param price
	 * @param count
	 * @param age
	 * @param type
	 */
	public void addFigure(String sN, String name, String brand, double price, int count, int age, String type) {
		Toy F = new Figure(sN, name, brand, price, count, age, type);
		toys.add(F);
	}

	/**
	 * This method takes several parameters from the menu sub class and uses them to
	 * create a new Animal toy and then add that Figure to the arraylist.
	 * 
	 * @param sN
	 * @param name
	 * @param brand
	 * @param price
	 * @param count
	 * @param age
	 * @param material
	 * @param Size
	 */
	public void addAnimal(String sN, String name, String brand, double price, int count, int age, String material,
			String Size) {
		Toy A = new Animal(sN, name, brand, price, count, age, material, Size);
		toys.add(A);
	}

	/**
	 * This method takes several parameters from the menu sub class and uses them to
	 * create a new puzzle toy and then add that Figure to the arraylist
	 * 
	 * @param SN
	 * @param name
	 * @param brand
	 * @param price
	 * @param count
	 * @param age
	 * @param type
	 */
	public void addPuzzle(String SN, String name, String brand, double price, int count, int age, String type) {
		Toy P = new Puzzle(SN, name, brand, price, count, age, type);
		toys.add(P);
	}

	/**
	 * This method takes several parameters from the menu sub class and uses them to
	 * create a new board game toy and then add that Figure to the arraylist
	 * 
	 * @param sN
	 * @param name
	 * @param brand
	 * @param price
	 * @param count
	 * @param age
	 * @param players
	 * @param designers
	 */

	public void addBoardGame(String sN, String name, String brand, double price, int count, int age, String players,
			String designers) {
		Toy B = new BoardGame(sN, name, brand, price, count, age, players, designers);
		toys.add(B);
	}

	/**
	 * saves all information into database, and exit the program.
	 * 
	 * @throws IOException
	 */
	public void Save() throws IOException {
		File db = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(db);
		int i = 0;
		while (i < toys.size()) {
			if (toys.get(i) instanceof Animal) {
				Animal A = (Animal) toys.get(i);
				pw.println(A.format());
			}
			if (toys.get(i) instanceof BoardGame) {
				BoardGame B = (BoardGame) toys.get(i);
				pw.println(B.format());
			}
			if (toys.get(i) instanceof Figure) {
				Figure F = (Figure) toys.get(i);
				pw.println(F.format());
			}
			if (toys.get(i) instanceof Puzzle) {
				Puzzle P = (Puzzle) toys.get(i);
				pw.println(P.format());
			}
			i++;
		}

		pw.close();
	}

	/**
	 * This method allows the user to search the toy by type
	 * 
	 * @param choice
	 */
	public String typeSearching(String choice) {
		String output = "Matches Found:";
		int i;
		char type = choice.toUpperCase().charAt(0);
		if (type == 'A') {
			i = 0;
			while (i < toys.size()) {
				if (toys.get(i) instanceof Animal) {
					Animal A = (Animal) toys.get(i);
					output = output + "\n" + A.toString();
				}
				i++;
			}
		}
		if (type == 'B') {
			i = 0;
			while (i < toys.size()) {
				if (toys.get(i) instanceof BoardGame) {
					BoardGame B = (BoardGame) toys.get(i);
					output = output + "\n" + B.toString();
				}
				i++;
			}
		}
		if (type == 'F') {
			i = 0;
			while (i < toys.size()) {

				if (toys.get(i) instanceof Figure) {
					Figure F = (Figure) toys.get(i);
					output = output + "\n" + F.toString();
				}
				i++;
			}
		}
		if (type == 'P') {
			i = 0;
			while (i < toys.size()) {

				if (toys.get(i) instanceof Puzzle) {
					Puzzle P = (Puzzle) toys.get(i);
					output = output + "\n" + P.toString();
				}
				i++;
			}
		}
		if (type != 'P' && type != 'F' && type != 'B' && type != 'A') {
			output = "Please Enter a Valid Toy Type, the valid types are\n(A)nimal\n(B)oard Game\n(F)igure\n(P)uzzle";
		}
		return output;
	}

	/**
	 * This searches for the name in the Array list later then printing it out with
	 * the ToString method
	 * 
	 * @param name
	 * 
	 */

	public String test() {
		return "hello";
	}

	public String toySearch(String name) {
		Toy toy = null;
		String output = "Matches Found";
		for (Toy t1 : toys) {
			if ((t1.getName().toUpperCase()).contains(name.toUpperCase())) {
				toy = t1;
				output = output + "\n" + toy.toString();
				/*
				 * if (toy instanceof Puzzle) { Puzzle P = (Puzzle) toy;
				 * S.Output.setText(P.toString()); } if (toy instanceof Animal) { Animal A =
				 * (Animal) toy; S.Output.setText(A.toString()); } if (toy instanceof BoardGame)
				 * { BoardGame B = (BoardGame) toy; S.Output.setText(B.toString());; } if (toy
				 * instanceof Figure) { Figure F = (Figure) toy;
				 * S.Output.setText(F.toString());; }
				 */
				// break;
			}
		}
		return output;
	}

}
