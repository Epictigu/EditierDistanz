package de.fhswf.ea.edit.utils;

import java.util.Collections;
import java.util.List;

/**
 * 
 * Wörterbuch-Klasse um Wörterbücher abzuspeichern.
 * Enthält eine Liste der Wörter und einen String mit dem Sprachenkürzel.
 *
 * @author Timo Röder, Dominik Müller, Gin-Wah Chau, Marcus Nolzen
 * @version 1.0
 */
public class Wörterbuch {

	private List<String> words;
	private String lang;
	
	/**
	 * Konstruktor zum Erstellen eines Wörterbuch-Objektes.
	 *
	 * @param words
	 * 		Liste der Wörter
	 * @param lang
	 * 		Sprachenkürzel
	 */
	public Wörterbuch(List<String> words, String lang) {
		this.words = words;
		this.lang = lang;
	} 
	
	/**
	 * Getter-Methode für die Wörterliste
	 *
	 * @return
	 * 		Wörterliste des Wörterbuchs
	 */
	public List<String> getWords(){
		return Collections.unmodifiableList(words);
	}
	
	/**
	 * Getter-Methode für das Sprachenkürzel
	 *
	 * @return
	 * 		Sprachenkürzel des Wörterbuchs
	 */
	public String getLang() {
		return lang;
	}
	
}
