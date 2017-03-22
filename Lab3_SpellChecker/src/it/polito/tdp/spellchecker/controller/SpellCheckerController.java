package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;

import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.RichWord;
import it.polito.tdp.spellchecker.model.Dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	Dictionary dictionary;

   

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ComboBox<String> cmbBox;
    
    @FXML
    private TextArea txtInserisci;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label LblError;

    @FXML
    private Button btnClear;

    @FXML
    private Label LblTime;

    @FXML
    void doClearText(ActionEvent event) {
    	txtResult.clear();
    	txtInserisci.clear();
    	LblTime.setText("");
    	LblError.setText("");
    }

   

    @FXML
    void doSpellCheck(ActionEvent event) {
    	Long inizio=System.nanoTime();
    	dictionary.loadDictionary(cmbBox.getValue());
    	String[] inserimento=txtInserisci.getText().toLowerCase().split(" ");
    	List<String> ltemp=new LinkedList<String>();
    	for(int i=0;i<inserimento.length;i++){
    		ltemp.add(inserimento[i].replaceAll("[ \\p{Punct}]", ""));
    	}
    	
    	LinkedList<RichWord> inserite=new LinkedList<RichWord>(dictionary.spellCheckText(ltemp));
    	int errate=0;
    	for(RichWord rw:inserite){
    		if(!rw.isCorretta()){
    			txtResult.appendText(rw.getParola()+"\n");
    			errate++;
    		}
    	}
    	
    	Long fine=System.nanoTime();
    	LblError.setText("The text contains "+Integer.toString(errate)+" errors");
    	LblTime.setText("Spell checked completed in "+Double.toString((fine-inizio)/ 1e9)+" seconds");
    	

    }

    @FXML
    void initialize() {
       
        assert txtInserisci != null : "fx:id=\"txtInserisci\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert LblError != null : "fx:id=\"LblError\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert LblTime != null : "fx:id=\"LblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        cmbBox.getItems().addAll("English","Italian");
        cmbBox.setValue(cmbBox.getItems().get(0));
     
    }
    
    public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
}


