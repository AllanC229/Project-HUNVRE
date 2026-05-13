package controller;
import java.sql.*;
// Import de la bibliothèque BCrypt pour le hashage et la vérification des mots de passe
import org.mindrot.jbcrypt.BCrypt;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import connection.DAOAcces;
import app.MainApp;
import model.CarteJeu;
import model.DeckJoueur;
import model.Utilisateur;
import view.Accueil;
import view.CreationCompte;

public class ControleurConnexion {
    public static Utilisateur joueur;

    public ControleurConnexion(int choix, String identifiant, String mdp, Stage stage) {
        if (choix == 1) {
            if (identifiant == null || identifiant.trim().isEmpty()
                || mdp == null || mdp.trim().isEmpty()) {
                System.out.println("Champs vides !");
                return;
            }
            DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");
            try {
                PreparedStatement pst = dao.getConn().prepareStatement(
                    // On récupère l'utilisateur par mail uniquement,
                    // la vérification du mot de passe est faite par BCrypt ensuite
                    "SELECT * FROM utilisateur WHERE mail = ?");
                pst.setString(1, identifiant);
                ResultSet rs = pst.executeQuery();
                
                // BCrypt.checkpw() reçoit deux paramètres :
                // - mdp : le mot de passe saisi par l'utilisateur, jamais hashé, tel quel
                // - rs.getString("mdp") : le hash stocké en BDD lors de la création du compte
                // BCrypt extrait le sel contenu dans le hash BDD, hashe mdp avec ce sel pour la première fois,
                // et compare le résultat avec le hash BDD. Si c'est identique → c'est le bon mot de passe.
                // Condition préalable : rs.next() doit être vrai, c'est-à-dire que le mail doit exister en BDD.
                // Sans mail valide, BCrypt n'a pas de hash à récupérer et checkpw() n'est jamais appelé.
                if (rs.next() && BCrypt.checkpw(mdp, rs.getString("mdp"))) {
                    joueur = new Utilisateur(rs.getString("pseudo"), rs.getString("mail"), new DeckJoueur(), rs.getString("role"));
                    try {
                        PreparedStatement pstprofil = dao.getConn().prepareStatement(
                            "SELECT * FROM deck_carte WHERE ref_utilisateur = ?");
                        pstprofil.setInt(1, rs.getInt(1));
                        ResultSet rsprofil = pstprofil.executeQuery();
                        if (rsprofil.next()) {
                            joueur.getDeck().add(new CarteJeu(
                                rsprofil.getInt(1),
                                rsprofil.getInt(2),
                                rsprofil.getString(3),
                                1,
                                rsprofil.getString(4)));
                        }
                    } catch(SQLException e) {}
                    stage.setScene(new Accueil(new VBox()));
                } else {
                    System.out.println("Identifiants incorrects !");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dao.closeConnection();
            }
        }
        if (choix == 2) {
            stage.setScene(new CreationCompte(new VBox()));
            MainApp.jeu.setScene(new CreationCompte(new VBox()));
            MainApp.jeu.show();
            System.out.println("totototo");
        }
    }
}