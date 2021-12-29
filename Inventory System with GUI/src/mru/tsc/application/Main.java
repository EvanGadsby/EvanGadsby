package mru.tsc.application;

import javafx.application.Application;
import javafx.stage.Stage;
import mru.tsc.controller.ShopController;
import mru.tsc.view.myGUI;

//change the application as necessary and add your own code
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		ShopController s = new ShopController();
		myGUI gui = new myGUI(primaryStage, s);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
