package de.fhswf.ea.edit.utils;

import java.util.Collections;
import java.util.List;

/**
 * 
 * W�rterbuch-Klasse um W�rterb�cher abzuspeichern.
 * Enth�lt eine Liste der W�rter und einen String mit dem Sprachenk�rzel.
 *
 * @author Timo R�der, Dominik M�ller, Gin-Wah Chau, Marcus Nolzen
 * @version 1.0
 */
public class W�rterbuch {

	private List<String> words;
	private String lang;
	
	/**
	 * Konstruktor zum Erstellen eines W�rterbuch-Objektes.
	 *
	 * @param words
	 * 		Liste der W�rter
	 * @param lang
	 * 		Sprachenk�rzel
	 */
	public W�rterbuch(List<String> words, String lang) {
		this.words = words;
		this.lang = lang;
	} 
	
	/**
	 * Getter-Methode f�r die W�rterliste
	 *
	 * @return
	 * 		W�rterliste des W�rterbuchs
	 */
	public List<String> getWords(){
		return Collections.unmodifiableList(words);
	}
	
	/**
	 * Getter-Methode f�r das Sprachenk�rzel
	 *
	 * @return
	 * 		Sprachenk�rzel des W�rterbuchs
	 */
	public String getLang() {
		return lang;
	}
	
}
