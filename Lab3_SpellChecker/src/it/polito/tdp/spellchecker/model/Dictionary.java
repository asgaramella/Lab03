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
	 * Algorito della ricerca dicotomica: 
	 * i(inidce inizio)
	 * f(indice fine)
	 * m(indidce medio)
	 * se mi serve seconda metà della lista:aggiorno indice iniziale 
	 * se mi serve prima metà della lista tengo fermo indice i e invece scalo indice finale
	 * algoritmo dicotomico prestazioni migliori vs contains che scorre tutta la lista !!
	 *  OSS: scalare -1  e  +1 ad m necessari per far sì che i>f, per uscita dal ciclo    !!
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
			}
		
			
			
			if (trovata==true)
				rtemp.setCorretta(true);
			else
				rtemp.setCorretta(false);
			}
				
		
		return ltemp;
	}
	
	
}