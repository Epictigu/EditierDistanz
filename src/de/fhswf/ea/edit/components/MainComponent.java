package de.fhswf.ea.edit.components;

import java.io.FileNotFoundException;

import de.fhswf.ea.edit.algorithmn.EditDistance;
import de.fhswf.ea.edit.utils.FileLoader;
import de.fhswf.ea.edit.utils.Wörterbuch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * 
 * Klasse zum erstellen einer Pane in der die einzelnen Graphikobjekte eingetragen werden.
 * Enthält ein TextField, TextLabel und ein ListView.
 * - TextField: Nutzereingabe des gesuchten Wortes.
 * - TextLabel: Label der ListView.
 * - ListView: Anzeige der 10 ähnlichsten Wörter laut Algorithmus.
 * 
 * @author Timo Röder, Dominik Müller, Gin-Wah Chau, Marcus Nolzen
 * @version 1.0
 */
public class MainComponent extends BorderPane {

	/**
	 * Konstruktor zum Erstellen der Pane und der einzelnen Objekte. 
	 * Gibt dem TextField einen ChangeListener zum Ausführen des Algorithmus.
	 *
	 * @throws FileNotFoundException
	 * 		Wenn der Pfad für das Wörterbuch inakkurat ist.
	 */
	public MainComponent() throws FileNotFoundException {
		super();

		TextField textField = new TextField("");
		textField.setId("mainTextField");
		setTop(textField);

		Label textLabel = new Label("Ähnliche Wörter:");
		textLabel.setId("mainTextLabel");
		setCenter(textLabel);

		ListView<String> listView = new ListView<String>();
		listView.setPrefHeight(24D * 10);
		setBottom(listView);

		Wörterbuch wB = FileLoader.loadFile("resources/t1-german.txt", "DE");

		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
				if (newVal.equals("")) {
					listView.getItems().clear();
					return;
				}
				final String val = newVal;
				new Thread() {
					public void run() {
						EditDistance eD = new EditDistance(wB);
						String[] words = eD.getNextWords(newVal);
						Platform.runLater(new Runnable() {
							public void run() {
								if (textField.getText().equals(val)) {
									listView.getItems().clear();
									for (String s : words) {
										listView.getItems().add(s);
									}
								}
							}
						});
					}
				}.start();
			}

		});
	}

}
