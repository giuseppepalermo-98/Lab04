/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.fxml.FXML;

public class FXMLController {

	Model model=new Model();
	private ObservableList <String> list= FXCollections.observableArrayList(model.getTuttiCorsiPopolazione());
	
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ChoiceBox<String> chioceBox;

	    @FXML
	    private Button btnCercaIscritti;

	    @FXML
	    private TextField txtMatricola;

	    @FXML
	    private Button btnCompleta;

	    @FXML
	    private TextField txtNome;

	    @FXML
	    private TextField txtCognome;

	    @FXML
	    private Button btnCercaCorsi;

	    @FXML
	    private Button btnIscrivi;

	    @FXML
	    private TextArea txtResult;

	    @FXML
	    private Button btnReset;

	    @FXML
	    void doCercaCorsi(ActionEvent event) {
	    	this.txtResult.clear();
	    	int matricola;
	    	String result="";
	    	
	    	try {
	    	matricola=Integer.parseInt(this.txtMatricola.getText());
	    	}
	    	catch(NumberFormatException e){
	    		this.txtResult.appendText("La matricola è composta da soli numeri!");
	    		return;
	    	}
	    	
	    	Studente s=model.getStudentebyMatricola(matricola);
	    	if(s==null)
	    		this.txtResult.appendText("Studente non inserito nel DB! \n");
	    	//Lo studente esiste devo trovare a quali corsi e' iscritto!
	    	
	    	for(Corso temp: model.corsiDelloStudente(s)) {
	    		if(temp!=null)
	    			result+=temp.toString()+"\n";
	    		else
	    			this.txtResult.appendText("Non frequenta nessun CORSO! \n");
	    	}
	    	
	    	this.txtResult.setText(result);
	    }

	    @FXML
	    void doIscritti(ActionEvent event) {
	    	this.txtResult.clear();
	    	
	    	String corsoSelezionato="";
	    	corsoSelezionato=this.chioceBox.getValue();
	    	String result="";
	    	
	    	if(!corsoSelezionato.equals("")) {
	    		for(Studente s: model.getStudentiIscrittiCorso(corsoSelezionato)) {
	    			if(s!=null)
	    			result+=s.toString()+"\n";
	    			else {
	    				this.txtResult.appendText("Nessuno studente iscritto al corso! \n");
	    				return;
	    			}
	    				
	    		}
	    		this.txtResult.appendText(result);
	    	}
	    	else
	    		this.txtResult.appendText("Selezionare un CORSO! \n");
	    }

	    @FXML
	    void doIscrizione(ActionEvent event) {
	    	this.txtResult.clear();
	    	boolean result;
	    	Studente s=model.getStudentebyMatricola(Integer.parseInt(this.txtMatricola.getText()));
	    	Corso c=model.getCorso(this.chioceBox.getValue());
	    	
	    	result=model.iscriviStudenteAlCorso(s, c);
	    	
	    	if(result)
	    		this.txtResult.appendText("Studente inserito al corso correttamente! \n");
	    	else
	    		this.txtResult.appendText("Studente già iscritto a questo corso! \n");
	    	

	    }

	    @FXML
	    void doNomeCognome(ActionEvent event) {
	    	int matricola;
	    	try {
	    	matricola=Integer.parseInt(this.txtMatricola.getText());
	    	}
	    	catch(NumberFormatException e){
	    		System.out.println("La matricola è composta da soli numeri!");
	    		return;
	    	}
	    	
	    	Studente s=this.model.getStudentebyMatricola(matricola);
	    	if(s!=null) {
	    	this.txtCognome.appendText(s.getCognome());
	    	this.txtNome.appendText(s.getNome());
	    	}
	    	else
	    		this.txtResult.appendText("Studente non inserito nel DB! \n");
	    }

	    @FXML
	    void doReset(ActionEvent event) {
	    	this.txtResult.setText("");
	    	this.txtMatricola.setText("");
	    	this.chioceBox.setValue("");
	    	this.txtCognome.clear();
	    	this.txtNome.clear();
	    }

	    @FXML
	    void initialize() {
	        assert chioceBox != null : "fx:id=\"chioceBox\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert btnCompleta != null : "fx:id=\"btnCompleta\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
	        
	        chioceBox.setItems(list);
	        chioceBox.setValue("");
	    }

	public void setModel(Model model) {
		this.model=model;
	}
}