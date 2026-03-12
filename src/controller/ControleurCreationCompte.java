package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import app.MainApp;
import connection.DAOAcces;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.DeckJoueur;
import model.Utilisateur;
import view.CreationCompte;

public class ControleurCreationCompte {
	
	String pseudo ;
	String mail ;
	String mdp ;
	String confirmMdp ;
	String role;
	CreationCompte vueErreurMail;
	
	
	public ControleurCreationCompte(String pseudo, String mail, String mdp, String confirmMdp, String role, CreationCompte vueErreur, Stage stage) {
		
		this.pseudo = pseudo;
		this.mail = mail;
		this.mdp = mdp;
		this.confirmMdp = confirmMdp;
		this.role = role;
		
		// Vérification que le mail est construit comme ceci : lettres/chiffres + @ + des lettres/chiffres + des lettres
	    String mailCheck = mail ;
	    String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    
	    
        if (!pseudo.equals("") && !mail.equals("") && !mdp.equals("") && patternMatches(mailCheck, regexPattern)) {
        	
        		if (mdp.equals(confirmMdp)) {         		

        			System.out.println("formulaire ok");
        	
        			try {
        	    		
        	    		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "sandman", "bringme4dream"); 
        	    		
        	      		// vérification si le mail du formualire n'existe pas déjà dans la BDD
        	    		String verifMail = "SELECT mail "
        	    						  + "FROM utilisateur;";
        	    		
        	    		PreparedStatement pstVerifMail = dao.getConn().prepareStatement(verifMail);
        	    		
        	    		ResultSet rsVerifMail = pstVerifMail.executeQuery();
        	    		
        	    		boolean flag = false; // flag = false veut dire, le mail existe pas par défaut en BDD
        	    		
    	    			// Vérifier si le mail inséré existe déjà en BD
        	    		while (rsVerifMail.next()){
	        	    		if (mail.equals(rsVerifMail.getString("mail"))) {
	        	    			flag = true ;
	        	    			System.out.println("le mail existe déjà");
	        	    			// CreationCompte erreur = new CreationCompte(new VBox());
	        	        		String message = "Le mail existe déjà";
	        	               // erreur.ajouterMessage(message);
	        	        		vueErreur.ajouterMessage(message);
	        	        	}
        	    		}
        	    		
        	    		if (flag == false) {
        	    		String strInsertNouveauCompte = "INSERT INTO utilisateur "
        	    										+ "(pseudo, mdp, mail, role) "
        	    										+ "VALUES (?, ?, ?, ?);";

        	    		// Création d'une requête préparée
        	    		PreparedStatement pstNouveauCompte = dao.getConn().prepareStatement(strInsertNouveauCompte);
        	    		
        	    		pstNouveauCompte.setString(1, pseudo);
        	    		pstNouveauCompte.setString(2, mdp);
        	    		pstNouveauCompte.setString(3, mail);
        	    		pstNouveauCompte.setString(4, role);

        	    		pstNouveauCompte.execute();
        	    	        	    		
        	    		
        	    		//la requête est exécutée => instanciation Utilisateur
            	    	System.out.println("requête exécutée, nouvel utilisateur instancié");
        	    		MainApp.utilisateur = new Utilisateur(pseudo, mail, new DeckJoueur(), role); 
        	    		new ControleurConnexion(1, mail, mdp, stage); // renvoie vers l'accueil
        	           	    		
        	    		}
        	    		dao.closeConnection();

        	    	
        	    	} catch (SQLException e) {
        	    		System.out.println("Problème SQL");
        	    		e.printStackTrace();	
        	    	} 

        		
        		} else {
        	        // Afficher l'erreur de correspondance des mdp
        			String message = "Les mots de passe ne correspondent pas";
        			vueErreur.ajouterMessage(message);
        			System.out.println("les mdp ne correspondent pas");
        		}
        
        } else {
    	    // Afficher l'erreur de saisie
    		String message = "Veuillez remplir tout les champs";
    		vueErreur.ajouterMessage(message);
    		System.out.println("tout les champs ne sont pas remplis");
        }

    }

	public boolean patternMatches(String mailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(mailAddress)
	      .matches();
	}
	
	}
