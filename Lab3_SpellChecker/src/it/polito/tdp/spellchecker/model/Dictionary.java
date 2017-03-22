package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	private List<RichWord> dizionario;
	
	

	public Dictionary() {
		super();
		dizionario=new ArrayList<RichWord>();
	}
	
	
	public void loadDictionary(String language){
	
	try {
	FileReader fr = new FileReader("rsc/"+language+".txt");
	BufferedReader br = new BufferedReader(fr);
	String word;
	while ((word = br.readLine()) != null) {
	 dizionario.add(new RichWord(word));
	}
	br.close();
	} catch (IOException e){
	System.out.println("Errore nella lettura del file");
	 }
	}
	
	/**
	 * Per ogni parola della lista controllo se è presente,
	 * se sì imposto boolean a true
	 * se no impòosto boolean a false
	 * @param inputTextList
	 * @return
	 */
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> ltemp=new ArrayList<RichWord>();
		for(String s: inputTextList){
			RichWord rtemp=new RichWord(s);
			ltemp.add(rtemp);
		
			if (dizionario.contains(rtemp))
				rtemp.setCorretta(true);

				
			else
				rtemp.setCorretta(false);
				
		}
		return ltemp;
	}
	
	
}