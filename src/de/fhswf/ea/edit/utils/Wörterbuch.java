package de.fhswf.ea.edit.utils;

import java.util.Collections;
import java.util.List;

public class W�rterbuch {

	private List<String> words;
	private String lang;
	
	public W�rterbuch(List<String> words, String lang) {
		this.words = words;
		this.lang = lang;
	} 
	
	public List<String> getWords(){
		return Collections.unmodifiableList(words);
	}
	
	public String getLang() {
		return lang;
	}
	
}
