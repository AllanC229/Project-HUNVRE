package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.MainApp;
import connection.DAOAcces;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.CarteJeu;
import model.DeckJoueur;
import view.Accueil;
import view.Partie;

public class ControleurEcranDeFin {
	
	public ControleurEcranDeFin(int direction) {
		
		if(direction == 1) {
			System.out.println("Nouvelle partie !");
			
			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "sandman", "bringme4dream");
			try {
				ResultSet listeCarte = dao.getStatement().executeQuery("SELECT id_carte, valeur, recto, couleur FROM carte;");
				
				// Création d'un nouveau deck avant de lancer la nouvelle partie
				ControleurConnexion.joueur.setDeck(new DeckJoueur());
				
				while(listeCarte.next()) {	
					ControleurConnexion.joueur.getDeck().add(new CarteJeu(
							listeCarte.getInt(1),
							listeCarte.getInt(2),
							listeCarte.getString(3),
							1,
							listeCarte.getString(4)));
				}
			}
			catch(SQLException e) {
				System.out.println("ControleurEcranDeFin - Ereur SQL");
				e.printStackTrace();
			}
			dao.closeConnection();
			
			Partie partie = new Partie();
			MainApp.jeu.setScene(partie);
			MainApp.jeu.show();
		}
		
		if(direction == 2) {
			System.out.println("Retour à l'accueil");
			Accueil accueil = new Accueil(new VBox());
			
			MainApp.jeu.setScene(accueil);
			MainApp.jeu.show();
		}
	}
}
