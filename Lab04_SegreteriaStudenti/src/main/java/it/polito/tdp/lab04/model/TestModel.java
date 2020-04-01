package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		System.out.println(model.getStudentebyMatricola(146101));
		System.out.println(model.getStudentiIscrittiCorso(new Corso("02CIXPG",8,"Sistemi informativi aziendali", 1)));
	}

}
