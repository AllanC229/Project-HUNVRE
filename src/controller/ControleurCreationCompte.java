package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import connection.DAOAcces;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Utilisateur;
import view.CreationCompte;

public class ControleurCreationCompte {
	
	String pseudo ;
	String mail ;
	String mdp ;
	String confirmMdp ;
	
	
	public ControleurCreationCompte(String pseudo, String mail, String mdp, String confirmMdp) {
		
		this.pseudo = pseudo;
		this.mail = mail;
		this.mdp = mdp;
		this.confirmMdp = confirmMdp;
		
		// Vérification que le mail est construit comme ceci : lettres/chiffres + @ + des lettres/chiffres + des lettres
	    String mailCheck = mail ;
	    String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		
        if (!pseudo.equals("") && !mail.equals("") && !mdp.equals("") && patternMatches(mailCheck, regexPattern)) {
        	
        		if (mdp.equals(confirmMdp)) {         		

        			System.out.println("formulaire ok");
        	
        			try {
        	    		
        	    		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", ""); 
        	    		
        	    		Connection conn = dao.getConn();
        	    	//	conn.setAutoCommit(false); ???

        	    		// if (email existe pas déjà)
        	    		String strInsertNouveauCompte = "INSERT INTO utilisateur, deck_carte "
        	    										+ "(pseudo, mail, mdp, , role) "
        	    										+ "VALUES (?, ?, ?, ?, ?);";

        	    		// Création d'un PreparedStatement
        	    		PreparedStatement pstNouveauCompte = conn.prepareStatement(strInsertNouveauCompte);
        	    		
        	    		pstNouveauCompte.setString(1, pseudo);
        	    		pstNouveauCompte.setString(2, mail);
        	    		pstNouveauCompte.setString(3, mdp);
        	    		pstNouveauCompte.setString(4, deck);
        	    		pstNouveauCompte.setString(5, role);

        	    		
        	    		if(pstNouveauCompte.execute()) {
        	    			Utilisateur nouveauUtilisateur = new Utilisateur(pseudo, mail, deck, role, dao);
        	    		}

        	    		
        	    		conn.close();

        	    	
        	    	} catch (SQLException e) {
        	    		System.out.println("Problème SQL");
        	    		e.printStackTrace();	
        	    	} 

        		
        		} else {
                // TO DO Afficher l'erreur de correspondance des mdp
        		new CreationCompte(new VBox());
        		
                //confirmationLabel.setText("Les mots de passe ne correspondent pas");
                //confirmationLabel.setTextFill(Color.RED);
        		System.out.println("les mdp ne correspondent pas");
        		}
        

        	} else {
            // TO DO Afficher l'erreur de saisie
        	//confirmationLabel.setText("Veuillez remplir tout les champs");
            //confirmationLabel.setTextFill(Color.RED);
        	System.out.println("tout les champs ne sont pas remplis");

        	}	
		
    	}

	public boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
	
	}
