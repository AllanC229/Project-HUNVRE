package controller;

// --- java.* ---
import java.sql.PreparedStatement;  // Requêtes SQL paramétrées (lecture et update du score)
import java.sql.ResultSet;          // Résultat des requêtes SELECT
import java.sql.SQLException;       // Gestion des erreurs SQL

// --- Classes du projet ---
import app.MainApp;
import connection.DAOAcces;
import javafx.scene.layout.VBox;
import model.CarteJeu;
import model.DeckJoueur;
import view.Accueil;
import view.Partie;

public class ControleurEcranDeFin {

    public ControleurEcranDeFin(int choix) {

        // 2026-04-13 - Vitally Lubin
        // On sauvegarde le score en BDD avant tout changement de scène.
        // Si le score actuel est meilleur que le score existant → UPDATE.
        // Voir la méthode sauvegarderScore() ci-dessous.
        sauvegarderScore();

        if (choix == 1) {
            System.out.println("Nouvelle partie !");

            DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");
            try {
                ResultSet listeCarte = dao.getStatement().executeQuery(
                    "SELECT id_carte, valeur, recto, couleur FROM carte;"
                );

                // Création d'un nouveau deck avant de lancer la nouvelle partie
                ControleurConnexion.joueur.setDeck(new DeckJoueur());

                while (listeCarte.next()) {
                    ControleurConnexion.joueur.getDeck().add(new CarteJeu(
                        listeCarte.getInt(1),
                        listeCarte.getInt(2),
                        listeCarte.getString(3),
                        1,
                        listeCarte.getString(4)
                    ));
                }
            } catch (SQLException e) {
                System.out.println("ControleurEcranDeFin - Erreur SQL");
                e.printStackTrace();
            }
            dao.closeConnection();

            // 2026-04-13 - Vitally Lubin
            // Remise à zéro du score pour la nouvelle partie
            ControleurConnexion.joueur.setScore(0);

            Partie partie = new Partie();
            MainApp.jeu.setScene(partie);
            MainApp.jeu.show();
        }

        if (choix == 2) {
            System.out.println("Retour à l'accueil");

            // 2026-04-13 - Vitally Lubin
            // Remise à zéro du score avant retour à l'accueil
            ControleurConnexion.joueur.setScore(0);

            Accueil accueil = new Accueil(new VBox());
            MainApp.jeu.setScene(accueil);
            MainApp.jeu.show();
        }
    }

    // 2026-04-13 - Vitally Lubin
    // Sauvegarde du score en BDD uniquement si le score actuel est meilleur que l'existant.
    // On lit le score enregistré pour cet utilisateur (identifié par son mail),
    // on compare avec le score actuel en mémoire,
    // et on fait un UPDATE seulement si c'est un nouveau record.
    // La colonne score est NULL par défaut en BDD — tout score > 0 sera donc sauvegardé.
    private void sauvegarderScore() {
        int scoreActuel = ControleurConnexion.joueur.getScore();
        String mail = ControleurConnexion.joueur.getMail();

        DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");
        try {
            PreparedStatement pstLireScore = dao.getConn().prepareStatement(
                "SELECT score FROM utilisateur WHERE mail = ?"
            );
            pstLireScore.setString(1, mail);
            ResultSet rs = pstLireScore.executeQuery();

            if (rs.next()) {
                int scoreExistant = rs.getInt("score");
                if (scoreActuel > scoreExistant) {
                    PreparedStatement pstMisajour = dao.getConn().prepareStatement(
                        "UPDATE utilisateur SET score = ? WHERE mail = ?"
                    );
                    pstMisajour.setInt(1, scoreActuel);
                    pstMisajour.setString(2, mail);
                    pstMisajour.executeUpdate();
                    System.out.println("Nouveau record sauvegardé : " + scoreActuel);
                } else {
                    System.out.println("Score non mis à jour (pas meilleur que l'existant)");
                }
            }
        } catch (SQLException e) {
            System.out.println("sauvegarderScore - Erreur SQL");
            e.printStackTrace();
        } finally {
            dao.closeConnection();
        }
    }
}
