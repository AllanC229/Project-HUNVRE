package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DAOAcces;

public class ControleurCreationCompte {
	
	public ControleurCreationCompte(String nom, String mail, String mdp) {
		
		
    	try {
    		
    		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", ""); 
    		
    		Connection conn = dao.getConn();
    	//	conn.setAutoCommit(false); ???


    		String strInsertNouveauCompte = "INSERT INTO acces "
    										+ "(pseudo, mail, mdp) "
    										+ "VALUES (?, ?, ?);";

    		// Création d'un PreparedStatement
    		PreparedStatement pstNouveauCompte = conn.prepareStatement(strInsertNouveauCompte);
    		
    				
    		pstNouveauCompte.setString(1, "Josiane");
    		pstNouveauCompte.setString(2, "josiane@gmail.com");
    		pstNouveauCompte.setString(3, "1234");
    		
    		pstNouveauCompte.execute();

    		
    		conn.close();

    	
    	} catch (SQLException e) {
    		System.out.println("Problème SQL");
    		e.printStackTrace();
    		
    	} 
		
		
		
		
		
    	}

	}
