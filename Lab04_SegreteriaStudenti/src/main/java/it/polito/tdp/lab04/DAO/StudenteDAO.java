package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudentebyMatricola(int matricola) {
		
		String sql= "SELECT * FROM studente WHERE matricola= ? ";
		Studente result=null;
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs =st.executeQuery();
			
			while(rs.next()) {
				Studente s=new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				result=s;
			}
			
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	
}
