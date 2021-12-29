package mru.tsc.view;

import java.io.IOException;
import java.util.ArrayList;

import exceptions.GamePlayerMismatch;
import exceptions.NegativeInputPrice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import mru.tsc.controller.ShopController;
import mru.tsc.model.Toy;

/**
 * This class is where all the Java FX coding is including SearchToy, AddToy,
 * and RemoveToy.
 * 
 * @author Evan Gadsby & Jurriel Tapado
 *
 */

public class Controller {
	ObservableList<String> CategoryList = FXCollections.observableArrayList("Animal", "Board Game", "Figure", "Puzzle");
	@FXML
	public RadioButton rdSN;
	@FXML
	public RadioButton rdName;
	@FXML
	public RadioButton rdType;
	@FXML
	public Button Search;
	@FXML
	public TextField SNField;
	@FXML
	public TextField NameField;
	@FXML
	public TextField TypeField;
	@FXML
	public TextArea Output;
	@FXML
	public TextArea ErrorOutput;
	@FXML
	public Button Reset;
	@FXML
	public ToggleGroup RadioButtonSelection;
	@FXML
	public ChoiceBox<String> Category;
	@FXML
	public TextField AddSNField;
	@FXML
	public TextField AddNameField;
	@FXML
	public TextField AddBrandField;
	@FXML
	public TextField AddPriceField;
	@FXML
	public TextField AddAvaiableField;
	@FXML
	public TextField AddAgeField;
	@FXML
	public TextField AddClassificationField;
	@FXML
	public TextField AddMaterialField;
	@FXML
	public TextField AddSizeField;
	@FXML
	public TextField AddPuzzleField;
	@FXML
	public TextField AddDesignersField;
	@FXML
	public TextField MinPlayersField;
	@FXML
	public TextField MaxPlayersField;
	@FXML
	public Button Save;
	@FXML
	public String SearchType = null;
	@FXML
	public TextField Remove;
	@FXML
	public Button RemoveToy;
	@FXML
	public TextField SNInput;
	@FXML
	public TextArea OutputSN;

	ShopController S;

	ArrayList<Toy> toys = new ArrayList<>();

	@FXML
	private void initialize() {
		Category.setValue("Figure");
		Category.setItems(CategoryList);
		SNField.setEditable(false);
		NameField.setEditable(false);
		TypeField.setEditable(false);
		Search.setDisable(true);
	}

	public Controller() throws Exception {
		S = new ShopController();
	}

	/**
	 * Sets the search type based on the radio button selected.
	 * 
	 * @param e
	 */
	public void ChangeSearchType(ActionEvent e) {

		if (rdSN.isSelected()) {
			SearchType = "SN";
			SNField.setEditable(true);
			NameField.setEditable(false);
			TypeField.setEditable(false);
		}

		else if (rdName.isSelected()) {
			SearchType = "Name";
			SNField.setEditable(false);
			NameField.setEditable(true);
			TypeField.setEditable(false);

		} else if (rdType.isSelected()) {
			SearchType = "Type";
			SNField.setEditable(false);
			NameField.setEditable(false);
			TypeField.setEditable(true);

		}

		SNField.setText("");
		NameField.setText("");
		TypeField.setText("");
		Search.setDisable(false);
	}

	/**
	 * Searches for the toy in the arraylist depending on which radio button the
	 * user has selected
	 * 
	 * @param e
	 * @throws Exception
	 */
	public void SearchToy(ActionEvent e) throws Exception {

		if (SearchType.equalsIgnoreCase("SN")) {

			if (S.repeatSN(SNField.getText()) == true) {
				Toy t = S.SNSearch(SNField.getText());
				Output.setText(t.toString());
			} else {
				Output.setText("A toy with that SN could not be located");
			}
		} else if (SearchType.equalsIgnoreCase("Name")) {
			String ToyNames = S.toySearch(NameField.getText());
			if (ToyNames.equalsIgnoreCase("Matches Found:")) {
				ToyNames = "A toy of that name could not be located";
			}
			Output.setText(ToyNames);
		} else if (SearchType.equalsIgnoreCase("Type")) {
			if (TypeField.getText().isEmpty()) {
				Output.setText("Please enter either (A)nimal (B)oardGame (F)igure or (P)uzzle");
			} else {
				Output.setText(S.typeSearching(TypeField.getText()));
			}
		}
	}

	/**
	 * Resets all fields in the Search Toy Tab
	 * 
	 * @param e
	 */
	public void Resetfields(ActionEvent e) {
		SNField.setText("");
		NameField.setText("");
		TypeField.setText("");
		Output.setText("");
		RadioButtonSelection.selectToggle(null);
	}

	/**
	 * Searches for the SN Number provided in the TextField in the Arraylist, then
	 * print out the toy later on removing it from the list. If the SN was not
	 * found, a message will be printed out.
	 * 
	 * @param e
	 * @throws Exception
	 */
	public void RemoveToy(ActionEvent e) throws Exception {
		// Looks whether or not the SN is found in the arraylist.
		if (S.repeatSN(SNInput.getText()) == true) {
			Toy t = S.SNSearch(SNInput.getText());
			// Prints out the toString of the SN, also saying that the toy has been removed.
			OutputSN.setText(t.toString() + "\n" + "Toy has been successfully removed!");
			// Removes the toy from the arraylist.
			S.RemoveToy(SNInput.getText());
			// Saves toy removal.
			S.Save();
		} else {
			// Prints out that the SN was not found in the arraylist.
			OutputSN.setText("A toy with this Serial Number could not be found");
		}
	}

	/**
	 * Sets which text fields the user is allowed to interact with after they switch
	 * toy types. Also resets all type specific fields
	 * 
	 * @param e
	 */
	public void chooseType(ActionEvent e) {
		if (Category.getValue().equalsIgnoreCase("Figure")) {
			AddMaterialField.setEditable(false);
			AddSizeField.setEditable(false);
			AddPuzzleField.setEditable(false);
			MinPlayersField.setEditable(false);
			MaxPlayersField.setEditable(false);
			AddDesignersField.setEditable(false);
			AddClassificationField.setEditable(true);
		}
		if (Category.getValue().equalsIgnoreCase("Animal")) {
			AddMaterialField.setEditable(true);
			AddSizeField.setEditable(true);
			AddPuzzleField.setEditable(false);
			MinPlayersField.setEditable(false);
			MaxPlayersField.setEditable(false);
			AddDesignersField.setEditable(false);
			AddClassificationField.setEditable(false);
		}
		if (Category.getValue().equalsIgnoreCase("Puzzle")) {
			AddMaterialField.setEditable(false);
			AddSizeField.setEditable(false);
			AddPuzzleField.setEditable(true);
			MinPlayersField.setEditable(false);
			MaxPlayersField.setEditable(false);
			AddDesignersField.setEditable(false);
			AddClassificationField.setEditable(false);
		}
		if (Category.getValue().equalsIgnoreCase("Board Game")) {
			AddMaterialField.setEditable(false);
			AddSizeField.setEditable(false);
			AddPuzzleField.setEditable(false);
			MinPlayersField.setEditable(true);
			MaxPlayersField.setEditable(true);
			AddDesignersField.setEditable(true);
			AddClassificationField.setEditable(false);
		}
		AddToyReset();
	}

	/**
	 * This method resets all the type specific fields in the add toy tab, it is
	 * called whenever the user switches to a different toy type
	 */
	public void AddToyReset() {
		AddMaterialField.setText("");
		AddSizeField.setText("");
		AddPuzzleField.setText("");
		MinPlayersField.setText("");
		MaxPlayersField.setText("");
		AddDesignersField.setText("");
		AddClassificationField.setText("");
	}

	public void AddToyErrorCheck(ActionEvent e) throws Exception {
		boolean errorfree = true;
		String errors = "Toy cannot be added:";
		char firstdigit = 'f';
		// Checks to make sure the Name field is not empty
		if (AddNameField.getText().isEmpty()) {
			errors = errors + "\n" + "Please enter a Name";
			errorfree = false;
		}
		// Checks to make sure the Name field is not empty
		if (AddBrandField.getText().isEmpty()) {
			errors = errors + "\n" + "Please enter a Brand";
			errorfree = false;
		}
		// Checks to make sure the SNfield is not empty
		if (AddSNField.getText().isEmpty()) {
			errors = errors + "\n" + "Please enter a SN";
			errorfree = false;
		}
		// If the SN field is not empty, checks to make sure that SN dosnt already
		// exist, contains only numbers, and is 10 digits long
		else {
			// checking if that SN already exists
			firstdigit = AddSNField.getText().charAt(0);
			if (S.repeatSN(AddSNField.getText()) == true) {
				errors = errors + "\n" + "A toy with that SN already exists";
				errorfree = false;
			}

			// Checking if the SN contains only numbers
			try {
				Long.parseLong(AddSNField.getText());
			} catch (NumberFormatException e1) {
				errors = errors + "\n" + "Your SN can only contain numbers";
				errorfree = false;
			}
			// checking if the SN is 10 digits long
			if (AddSNField.getText().length() != 10) {
				errors = errors + "\n" + "Your SN must be ten digits long";
				errorfree = false;
			}
		}
		// checking if the price field is empty, and checks to make sure it is a valid
		// price if it is not empty
		if (AddPriceField.getText().isEmpty()) {
			errors = errors + "\n" + "Please enter a Price";
			errorfree = false;
		} else {
			try {
				Double.parseDouble(AddPriceField.getText());
				checkPrice(Double.parseDouble(AddPriceField.getText()));
			} catch (NumberFormatException | NegativeInputPrice e1) {
				errors = errors + "\n" + "You must enter a valid price";
				errorfree = false;
			}
		}

		// checking if the amount field is empty, and checks to make sure it is a valid
		// amount if it is not empty (uses the price exception as it
		// measures if the number passed into it is less than zero)

		if (AddAvaiableField.getText().isEmpty()) {
			errors = errors + "\n" + "Please enter an available amount";
			errorfree = false;
		} else {
			try {
				Integer.parseInt(AddAvaiableField.getText());
				checkPrice(Double.parseDouble(AddAvaiableField.getText()));
			} catch (NumberFormatException | NegativeInputPrice e1) {
				errors = errors + "\n" + "You must enter a valid available amount";
				errorfree = false;
			}
		}

		// Checking to make sure the Age field is not empty and is a valid number
		try {
			Integer.parseInt(AddAgeField.getText());
		} catch (NumberFormatException e1) {
			errors = errors + "\n" + "You must enter a valid appropriate age";
			errorfree = false;
		}

		// calls the method to check Figures, if the method returns any errors it adds
		// those errors to the error list.
		if (Category.getValue().equalsIgnoreCase("Figure")) {
			if (checkFigure(firstdigit).equalsIgnoreCase("")) {

			} else {
				errorfree = false;
				errors = errors + checkFigure(firstdigit);
			}
		}
		// calls the method to check Animals, if the method returns any errors it adds
		// those errors to the error list.
		if (Category.getValue().equalsIgnoreCase("Animal")) {
			if (checkAnimal(firstdigit).equalsIgnoreCase("")) {

			} else {
				errorfree = false;
				errors = errors + checkAnimal(firstdigit);
			}
		}
		// calls the method to check Puzzles, if the method returns any errors it adds
		// those errors to the error list.
		if (Category.getValue().equalsIgnoreCase("Puzzle")) {
			if (checkPuzzle(firstdigit).equalsIgnoreCase("")) {

			} else {
				errorfree = false;
				errors = errors + checkPuzzle(firstdigit);
			}
		}
		// calls the method to check Board Games, if the method returns any errors it
		// adds those errors to the error list.
		if (Category.getValue().equalsIgnoreCase("Board Game")) {
			if (MaxPlayersField.getText().isEmpty() || MinPlayersField.getText().isEmpty()) {
				errors = errors + "\n" + "Please enter a minimum and maximum players";
				errorfree = false;
			} else {
				try {
					checkPlayers(Integer.parseInt(MinPlayersField.getText()),
							Integer.parseInt(MaxPlayersField.getText()));
				} catch (GamePlayerMismatch | NumberFormatException e1) {
					errors = errors + "\n" + "Your minimum players cannot be more than your maximum players";
					errorfree = false;
				}

			}
			if (checkBoardGame(firstdigit).equalsIgnoreCase("")) {

			} else {
				errorfree = false;
				errors = errors + checkBoardGame(firstdigit);
			}
		}
		if (errorfree == false) {
			ErrorOutput.setText(errors);
		}
		if (errorfree == true) {
			ErrorOutput.setText("Toy Added");
			AddToy();
		}
	}

	/**
	 * Adds the toys to the arraylist and saves the program
	 * 
	 * @throws IOException
	 */
	public void AddToy() throws IOException {
		if (Category.getValue().equalsIgnoreCase("Figure")) {
			S.addFigure(AddSNField.getText(), AddNameField.getText(), AddBrandField.getText(),
					Double.parseDouble(AddPriceField.getText()), Integer.parseInt(AddAvaiableField.getText()),
					Integer.parseInt(AddAgeField.getText()), AddClassificationField.getText());
		}
		if (Category.getValue().equalsIgnoreCase("Animal")) {
			S.addAnimal(AddSNField.getText(), AddNameField.getText(), AddBrandField.getText(),
					Double.parseDouble(AddPriceField.getText()), Integer.parseInt(AddAvaiableField.getText()),
					Integer.parseInt(AddAgeField.getText()), AddMaterialField.getText(), AddSizeField.getText());
		}
		if (Category.getValue().equalsIgnoreCase("Puzzle")) {
			S.addPuzzle(AddSNField.getText(), AddNameField.getText(), AddBrandField.getText(),
					Double.parseDouble(AddPriceField.getText()), Integer.parseInt(AddAvaiableField.getText()),
					Integer.parseInt(AddAgeField.getText()), AddPuzzleField.getText());
		}
		if (Category.getValue().equalsIgnoreCase("Board Game")) {
			S.addBoardGame(AddSNField.getText(), AddNameField.getText(), AddBrandField.getText(),
					Double.parseDouble(AddPriceField.getText()), Integer.parseInt(AddAvaiableField.getText()),
					Integer.parseInt(AddAgeField.getText()),
					MinPlayersField.getText() + "-" + MaxPlayersField.getText(), AddDesignersField.getText());
		}
		S.Save();
	}

	/**
	 * This method checks if there are any errors in the figure the user is trying
	 * to input. It returns any found errors as a String
	 * 
	 * @param firstdigit
	 * @return extraerror
	 */
	public String checkFigure(char firstdigit) {
		String extraerror = "";
		if (firstdigit != '0' && firstdigit != '1') {
			extraerror = extraerror + "\n" + "The first digit of a Figures SN must be a 0 or a 1";
		}
		if (AddClassificationField.getText().isEmpty()) {
			extraerror = extraerror + "\n" + "Please enter a classification";
		} else {
			char classification = AddClassificationField.getText().toUpperCase().charAt(0);
			if (classification != 'A' && classification != 'D' && classification != 'H') {
				extraerror = extraerror + "\n"
						+ "The classification of a Figure must be either (A)ction (D)oll or (H)istoric";
			}
		}
		return extraerror;
	}

	/**
	 * This method checks if there are any errors in the animal the user is trying
	 * to input. It returns any found errors as a String
	 * 
	 * @param firstdigit
	 * @return extraerror
	 */
	public String checkAnimal(char firstdigit) {
		String extraerror = "";
		if (firstdigit != '2' && firstdigit != '3') {
			extraerror = extraerror + "\n" + "The first digit of a Animals SN must be a 2 or a 3";
		}
		if (AddSizeField.getText().isEmpty()) {
			extraerror = extraerror + "\n" + "Please enter a size";
		} else {
			char classification = AddSizeField.getText().toUpperCase().charAt(0);
			if (classification != 'S' && classification != 'M' && classification != 'L') {
				extraerror = extraerror + "\n" + "The size of a Animal must be either (S)mall (M)edium or (L)arge";
			}
		}
		if (AddMaterialField.getText().isEmpty()) {
			extraerror = extraerror + "\n" + "Please enter a material";
		}
		return extraerror;
	}

	/**
	 * This method checks if there are any errors in the puzzle the user is trying
	 * to input. It returns any found errors as a String
	 * 
	 * @param firstdigit
	 * @return extraerror
	 */
	public String checkPuzzle(char firstdigit) {
		String extraerror = "";
		if (firstdigit != '4' && firstdigit != '5' && firstdigit != '6') {
			extraerror = extraerror + "\n" + "The first digit of a Puzzles SN must be a 4, 5, or 6";

		}
		if (AddPuzzleField.getText().isEmpty()) {
			extraerror = extraerror + "\n" + "Please enter a Puzzle type";
		} else {
			char classification = AddPuzzleField.getText().toUpperCase().charAt(0);
			if (classification != 'C' && classification != 'M' && classification != 'L' && classification != 'T'
					&& classification != 'R') {
				extraerror = extraerror + "\n"
						+ "The puzzle type must be either (M)echanical (C)ryptic (L)ogic (T)rivia or (R)iddle";
			}
		}
		return extraerror;
	}

	/**
	 * This method checks if there are any errors in the board game the user is
	 * trying to input. It returns any found errors as a String
	 * 
	 * @param firstdigit
	 * @return extraerror
	 */
	public String checkBoardGame(char firstdigit) {
		String extraerror = "";
		if (firstdigit != '7' && firstdigit != '8' && firstdigit != '9') {
			extraerror = extraerror + "\n" + "The first digit of a Puzzles SN must be a 7, 8, or 9";

		}
		if (AddDesignersField.getText().isEmpty()) {
			extraerror = extraerror + "\n" + "Please enter the board games designers";
		}

		return extraerror;
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
	 * compared to maximum players.
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

}
