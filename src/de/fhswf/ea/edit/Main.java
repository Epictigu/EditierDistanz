package de.fhswf.ea.edit;
	
import de.fhswf.ea.edit.components.MainComponent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * Main-Klasse zum Ausf�hren des Programmes.
 *
 * @author Timo R�der, Dominik M�ller, Gin-Wah Chau, Marcus Nolzen
 * @version 1.0
 */
public class Main extends Application {
	
	/**
	 * Start-Methode f�r JavaFX-Applikation.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Editier-Distanz");
			MainComponent root = new MainComponent();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(this.getClass().getResource("css/Main.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	/**
	 * Main-Methode zum Starten des Programmes
	 *
	 * @param args
	 * 		Kommandozeilenparameter
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
