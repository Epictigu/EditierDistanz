package de.fhswf.ea.edit.algorithmn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.fhswf.ea.edit.utils.Wörterbuch;

/**
 * 
 * Klasse zum Ausführen des Editier-Distanz Algorithmus.
 * Benötigt Wörterbuch um das gesuchte Wort zu finden.
 * 
 * Algorithmus arbeitet asynchron durch EditDistanceAsync interne Klasse.
 *
 * @author Timo Röder, Dominik Müller, Gin-Wah Chau, Marcus Nolzen
 * @version 1.0
 */
public class EditDistance {

	private final int WORDS_PER_THREAD = 1000;
	
	private Wörterbuch wB;
	private ConcurrentHashMap<String, Integer> results;
	
	/**
	 * Konstruktor zum Übergeben eines Wörterbuches.
	 */
	public EditDistance(Wörterbuch wB) {
		this.wB = wB;
	}
	
	/**
	 * Algorithmus-Teil des Programmes.
	 * Führt den Algorithmus aus bis durch alle Wörter des Wörterbuches durchgegangen wurde.
	 * Abschliessend warten auf asynchrone Klassen.
	 *
	 * @param word
	 * 		Gesuchtes Wort
	 * @return
	 * 		Gibt String-Array mit den 10 nächsten Wörtern zurück.
	 */
	public String[] getNextWords(String word){
		results = new ConcurrentHashMap<String, Integer>();
		
		long start = System.currentTimeMillis();
		
		ExecutorService es = Executors.newCachedThreadPool();
		
		int wordCount = 0;
		List<String> currentWords = new ArrayList<String>();
		for (String s : wB.getWords()) {
			wordCount++;
			currentWords.add(s);
			
			if(wordCount >= WORDS_PER_THREAD) {
				es.execute(new EditDistanceAsync(word, currentWords));
				currentWords = new ArrayList<String>();
				wordCount = 0;
			}
		}
		es.shutdown();
		try {
			es.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Integer[] lowestED = new Integer[10]; Arrays.fill(lowestED, Integer.MAX_VALUE);
		String[] lowestEDS = new String[10]; Arrays.fill(lowestEDS, "Keine gültigen Ergebnisse!");
		
		for(String s : results.keySet()) {
			if(lowestED[9] > results.get(s)) {
				replaceED(lowestED, lowestEDS, s, results.get(s), 9);
			}
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Laufzeit: " + (end - start));
		
		String[] words = new String[10];
		for(int i = 0; i <= 9; i++) {
			words[i] = lowestEDS[i] + " [" + lowestED[i] + "]";
		}
		return words;
	}
	
	private void replaceED(Integer[] lowestED, String[] lowestEDS, String s, Integer eD, int index) {
		if(index > 0) {
			if(lowestED[index - 1] > eD) {
				replaceED(lowestED, lowestEDS, s, eD, index - 1);
				return;
			}
		}
		lowestED[index] = eD;
		lowestEDS[index] = s;
	}
	
	/**
	 * 
	 * Klasse abgeleitet von Thread,
	 * zum asynchronen Ablauf der Editier-Distanz.
	 * 
	 * Benötigt Liste von Wörtern und gesuchtes Wort.
	 * Ergebnisse werden in "results"-ConcurrentHashMap eingespeichert von EditDistance.
	 *
	 */
	private class EditDistanceAsync extends Thread {
		
		private String finalWord = "";
		private List<String> calcWords;
		private String currentWord = "";
		private Integer[][] arrAsync = null;
		
		public EditDistanceAsync(String finalWord, List<String> calcWords) {
			this.finalWord = finalWord;
			this.calcWords = calcWords;
		}
		
		public void run() {
			for(String s : calcWords) {
				if(s.length() < finalWord.length() - 1 || s.length() > finalWord.length() + 1)
					continue;
				currentWord = s;
				arrAsync = new Integer[currentWord.length() + 1][finalWord.length() + 1];
				
				results.putIfAbsent(s, maxFormulaAsync(currentWord.length(), finalWord.length()));
			}
		}
		
		private int minAsync(int x, int y, int z) {
			if (x <= y && x <= z)
				return x;
			if (y <= x && y <= z)
				return y;
			return z;
		}

		private int maxFormulaAsync(int i, int j) {
			if(arrAsync[i][j] != null)
				return arrAsync[i][j];
			if (i == 0) {
				arrAsync[i][j] = j;
				return j;
			}
			if (j == 0) {
				arrAsync[i][j] = i;
				return i;
			}
			
			if (currentWord.charAt(i - 1) == finalWord.charAt(j - 1)) {
				int r = minAsync(maxFormulaAsync(i - 1, j - 1), maxFormulaAsync(i - 1, j) + 1, maxFormulaAsync(i, j - 1) + 1);
				arrAsync[i][j] = r;
				return (r);
			} else {
				int r = minAsync(maxFormulaAsync(i - 1, j - 1), maxFormulaAsync(i - 1, j), maxFormulaAsync(i, j - 1)) + 1;
				arrAsync[i][j] = r;
				return (r);
			}
		}
	}

}
