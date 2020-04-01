package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
				
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}
			conn.close();
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String nomeCorso) {
		String sql = "SELECT * FROM corso WHERE nome= ?";
		Corso result=null;
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, nomeCorso);
			
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Corso c=new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result=c;
			}
			
			conn.close();
			return result;
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		String sql ="SELECT s.matricola,s.cognome,s.nome,s.CDS FROM studente s,iscrizione i"+
		            " WHERE i.codins=? AND s.matricola = i.matricola";
		
		List<Studente>result=new LinkedList<Studente>();
		
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, corso.getCodIns());
			
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Studente s=new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
				result.add(s);
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		String sql="SELECT s.matricola, s.cognome, s.nome, s.cds FROM studente s, iscrizione i "+
		           " WHERE s.matricola=i.matricola AND s.matricola= ? AND i.codins= ? ";
		
		String sql2="INSERT INTO iscrizione (matricola, codins)"+
		            " VALUES (?, ?)";
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodIns());
			
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				return false;
			}
			
			PreparedStatement st2=conn.prepareStatement(sql2);
			st2.setInt(1, studente.getMatricola());
			st2.setString(2, corso.getCodIns());
			ResultSet rs2=st2.executeQuery();
		
			conn.close();
			return true;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		// ritorna true se l'iscrizione e' avvenuta con successo
	}
	
	public List<Corso> getcorsiDelloStudente(Studente s){
		
		//SELECT c.codins, c.crediti, c.nome, c.pd FROM corso c, iscrizione i 
		//WHERE i.codins = c.codins AND i.matricola=146101
		
		String sql="SELECT c.codins, c.crediti, c.nome, c.pd FROM corso c, iscrizione i"+
		           " WHERE i.codins = c.codins AND i.matricola= ? ";
		List<Corso>result=new LinkedList<Corso>();
		
		try{
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Corso c=new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			
			conn.close();
			return result;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
