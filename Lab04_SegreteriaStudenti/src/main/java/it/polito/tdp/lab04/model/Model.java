package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	StudenteDAO sdao;
	CorsoDAO cdao;
	
	public Model() {
		sdao=new StudenteDAO();
		cdao=new CorsoDAO();
	}
	
	public List<Corso> getTuttiCorsi(){
		return cdao.getTuttiICorsi();
	}
	
	public ArrayList<String> getTuttiCorsiPopolazione(){
		List<Corso> lc=new ArrayList<Corso>(cdao.getTuttiICorsi());
		ArrayList<String> result=new ArrayList<String>();
		
		for(Corso c: lc) {
			if(lc!=null)
			result.add(c.getNome());
		}
		result.add("");
		return result;
	}
	
	public Studente getStudentebyMatricola(int matricola) {
		return sdao.getStudentebyMatricola(matricola);
	}
	
	public List<Studente> getStudentiIscrittiCorso(Corso c) {
		return cdao.getStudentiIscrittiAlCorso(c);
	}
	
	public List<Studente> getStudentiIscrittiCorso(String nomeCorso){
		Corso tempC=cdao.getCorso(nomeCorso);
		return cdao.getStudentiIscrittiAlCorso(tempC);
	}
	
	public List<Corso> corsiDelloStudente(Studente s){
		return cdao.getcorsiDelloStudente(s);
	}
	
	public boolean iscriviStudenteAlCorso (Studente s, Corso c) {
		return cdao.inscriviStudenteACorso(s, c);
	}
	
	public Corso getCorso(String nomeCorso) {
		return cdao.getCorso(nomeCorso);
	}
	
}
