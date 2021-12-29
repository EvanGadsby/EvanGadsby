package mru.tsc.view;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mru.tsc.controller.ShopController;

public class myGUI {
	public myGUI(Stage primaryStage, ShopController S){
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("GUI.fxml"));
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			//scene.getStylesheets().add("application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
