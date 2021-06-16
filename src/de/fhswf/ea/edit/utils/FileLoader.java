package de.fhswf.ea.edit.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Util-Klasse um Dateien einzulesen.
 * 
 * @author Timo R�der, Dominik M�ller, Gin-Wah Chau, Marcus Nolzen
 * @version 1.0
 */
public class FileLoader {

	/**
	 * Methode zum laden eines W�rterbuches.
	 *
	 * @param path
	 * 		Pfad zum W�rterbuch
	 * @param lang
	 * 		Sprache des W�rterbuches
	 * @return
	 * 		W�rterbuch-Objekt nach Laden
	 * @throws FileNotFoundException
	 * 		wenn mit dem angegebenen Pfad keine Datei gefunden wurde
	 */
	public static W�rterbuch loadFile(String path, String lang) throws FileNotFoundException {
		File f = new File(path);
		if(!f.exists())
			throw new IllegalArgumentException("Ung�ltiger Pfad zum W�rterbuch! Datei existiert nicht.");
		
		List<String> words = new ArrayList<String>();
		W�rterbuch wB = new W�rterbuch(words, lang);
		
		Scanner scanner = new Scanner(f);
		while(scanner.hasNextLine()) {
			String word = scanner.nextLine();
			words.add(word);
		}
		scanner.close();
		
		return wB;
	}
	
}
