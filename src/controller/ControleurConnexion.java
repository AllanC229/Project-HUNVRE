package controller;
import java.sql.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import connection.DAOAcces;
import app.MainApp;
import model.Utilisateur;
import view.Accueil;
import view.Connexion;
import view.CreationCompte;
import view.TableauScore;

/**
 * Contrôleur de connexion : gère les actions depuis la vue Connexion.
 * Reçoit un choix (1 = connexion, 2 = création de compte).
 */
public class ControleurConnexion {
        public static Utilisateur j ;
    public ControleurConnexion(int choix, String identifiant, String mdp, Stage stage) {
        if (choix == 1) {
            // Vérification champs non vides
            if (identifiant == null || identifiant.trim().isEmpty()
                || mdp == null || mdp.trim().isEmpty()) {
                System.out.println("Champs vides !");
                // TODO : afficher message d'erreur dans la vue
                return;
            }
            // Vérification en BDD
            DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");
            try {
                PreparedStatement pst = dao.getConn().prepareStatement(
                    "SELECT * FROM utilisateur WHERE mail = ? AND mdp = ?");
                pst.setString(1, identifiant);
                pst.setString(2, mdp);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    stage.setScene(new Accueil(new VBox()));  //Il faudra rajouter la création d'une instance d'Utilisateur avec les infos récupérées depuis la base de données (rôle, deck, pseudo)
                } else {
                    System.out.println("Identifiants incorrects !");
                    // TODO : afficher message d'erreur dans la vue
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dao.closeConnection();
            }
        }
        if (choix == 2) {
            stage.setScene(new CreationCompte(new VBox()));
            // Affiche la vue Création de Compte
        	MainApp.jeu.setScene(new CreationCompte(new VBox()));
        	MainApp.jeu.show();
        	
        	System.out.println("totototo");
        }
    }
}