package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	private List<String> dizionario;
	
	

	public Dictionary() {
		super();
		dizionario=new ArrayList<String>();
	}
	
	
	public void loadDictionary(String language){
	
	try {
	FileReader fr = new FileReader("rsc/"+language+".txt");
	BufferedReader br = new BufferedReader(fr);
	String word;
	while ((word = br.readLine()) != null) {
	 dizionario.add(word);
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
		Collections.sort(dizionario);
	
		for(String s:inputTextList){
			boolean trovata=false;
			RichWord rtemp=new RichWord(s);
			ltemp.add(rtemp);
			int i=0;
			int f=dizionario.size();
			int m=dizionario.size()/2;
			while(i<f && trovata==false){
				String stemp=dizionario.get(m);
				System.out.println(stemp);
				System.out.println(i);
				System.out.println(f);
				System.out.println();
				
				if(s.compareTo(stemp)>0)
					i= m +1;
				else{
					if(s.compareTo(stemp)<0)
						f=m -1;
					else
						trovata=true;
				}
			
				m=(i+f)/2;
				
				if (i+1==f){
					trovata=false;
				}
				
			}
		
			
			
			if (trovata==true)
				rtemp.setCorretta(true);
			else
				rtemp.setCorretta(false);
			}
				
		
		return ltemp;
	}
	
	
}