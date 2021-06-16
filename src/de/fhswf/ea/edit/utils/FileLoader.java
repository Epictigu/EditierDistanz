package de.fhswf.ea.edit.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Util-Klasse um Dateien einzulesen.
 * 
 * @author Timo Röder, Dominik Müller, Gin-Wah Chau, Marcus Nolzen
 * @version 1.0
 */
public class FileLoader {

	/**
	 * Methode zum laden eines Wörterbuches.
	 *
	 * @param path
	 * 		Pfad zum Wörterbuch
	 * @param lang
	 * 		Sprache des Wörterbuches
	 * @return
	 * 		Wörterbuch-Objekt nach Laden
	 * @throws FileNotFoundException
	 * 		wenn mit dem angegebenen Pfad keine Datei gefunden wurde
	 */
	public static Wörterbuch loadFile(String path, String lang) throws FileNotFoundException {
		File f = new File(path);
		if(!f.exists())
			throw new IllegalArgumentException("Ungültiger Pfad zum Wörterbuch! Datei existiert nicht.");
		
		List<String> words = new ArrayList<String>();
		Wörterbuch wB = new Wörterbuch(words, lang);
		
		Scanner scanner = new Scanner(f);
		while(scanner.hasNextLine()) {
			String word = scanner.nextLine();
			words.add(word);
		}
		scanner.close();
		
		return wB;
	}
	
}
