package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.MainApp;
import connection.DAOAcces;
import javafx.scene.layout.VBox;
import view.Accueil;

public class ControleurEcranDeFin {
	
	public ControleurEcranDeFin(int direction) {
		
		if(direction == 1) {
			System.out.println("Nouvelle partie !");
			
			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "sandman", "bringme4dream");
			try {
				ResultSet listeCarte = dao.getStatement().executeQuery("SELECT id_carte, valeur, recto, couleur FROM carte;");
				while(listeCarte.next()) {
					/* Il faudra ajouter un objet deckJoueur en static dans ControleurAccueil
					 * pour pouvoir accéder à deckJoueur partout
					 */
					
					ControleurAccueil.deckJoueur.add(new CarteJeu(
							listeCarte.getInt(1),
							listeCarte.getInt(2),
							listeCarte.getString(3),
							"01",
							listeCarte.getString(4)));
				}
			}
			catch(SQLException e) {
				System.out.println("ControleurEcranDeFin - Ereur SQL");
				e.printStackTrace();
			}
			/* Partie partie = new Partie(new VBox();
			 * MainApp.jeu.setScene(partie);
			 * MainApp.jeu.show();
			 */
			
		}
		
		if(direction == 2) {
			System.out.println("Retour à l'accueil");
			Accueil accueil = new Accueil(new VBox());
			
			MainApp.jeu.setScene(accueil);
			MainApp.jeu.show();
		}
	}
}
