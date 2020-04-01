package it.polito.tdp.lab04.model;

import java.io.PrintStream;

public class Corso {

	private String codIns;
	private int crediti;
	private String nome;
	private int pd; //Periodo Didattico
	
	
	public Corso(String codIns, int crediti, String nome, int pd) {
		this.codIns = codIns;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}


	public String getCodIns() {
		return codIns;
	}


	public void setCodIns(String codIns) {
		this.codIns = codIns;
	}


	public int getCrediti() {
		return crediti;
	}


	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getPd() {
		return pd;
	}


	public void setPd(int pd) {
		this.pd = pd;
	}


	
	public String toString() {
		//PrintStream s=
		//System.out.format("%-10s", codIns, "%-3s",crediti, "%-40s", nome,"%-3s", pd);
		//System.out.format("%-3s",crediti)
		//System.out.format("%-40s", nome)
		//System.out.format("%-3s", pd)
		//System.out.print("\n")
		return codIns+"  "+crediti+"   "+nome+"   "+pd;
	}
	
	
}
