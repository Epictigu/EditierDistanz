package de.fhswf.ea.edit.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLoader {

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
