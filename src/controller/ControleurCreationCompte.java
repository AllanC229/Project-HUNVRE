package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
// Import de la bibliothèque BCrypt pour le hashage et la vérification des mots de passe
import org.mindrot.jbcrypt.BCrypt;
import app.MainApp;
import connection.DAOAcces;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DeckJoueur;
import model.Utilisateur;
import view.CreationCompte;

public class ControleurCreationCompte {

    String pseudo;
    String mail;
    String mdp;
    String confirmMdp;
    String role;
    CreationCompte vueErreurMail;

    public ControleurCreationCompte(String pseudo, String mail, String mdp, String confirmMdp, String role, CreationCompte vueErreur, Stage stage) {

        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
        this.confirmMdp = confirmMdp;
        this.role = role;

        String mailCheck = mail;
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!pseudo.equals("") && !mail.equals("") && !mdp.equals("") && patternMatches(mailCheck, regexPattern)) {

            if (mdp.equals(confirmMdp)) {

                System.out.println("formulaire ok");

                try {
                    DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");

                    String verifMail = "SELECT mail FROM utilisateur;";
                    PreparedStatement pstVerifMail = dao.getConn().prepareStatement(verifMail);
                    ResultSet rsVerifMail = pstVerifMail.executeQuery();

                    boolean flag = false;

                    while (rsVerifMail.next()) {
                        if (mail.equals(rsVerifMail.getString("mail"))) {
                            flag = true;
                            System.out.println("le mail existe déjà");
                            vueErreur.ajouterMessage("Le mail existe déjà");
                        }
                    }

                    if (flag == false) {
                        // On hashe le mot de passe saisi par l'utilisateur avant de le stocker en BDD.
                        // BCrypt.gensalt() génère un sel aléatoire, intégré dans le hash résultant.
                        // C'est ce hash qui sera stocké en BDD, jamais le mot de passe en clair.
                        String mdpHashe = BCrypt.hashpw(mdp, BCrypt.gensalt());

                        String strInsertNouveauCompte = "INSERT INTO utilisateur (pseudo, mdp, mail, role) VALUES (?, ?, ?, ?);";
                        PreparedStatement pstNouveauCompte = dao.getConn().prepareStatement(strInsertNouveauCompte);
                        pstNouveauCompte.setString(1, pseudo);
                        pstNouveauCompte.setString(2, mdpHashe);
                        pstNouveauCompte.setString(3, mail);
                        pstNouveauCompte.setString(4, role);
                        pstNouveauCompte.execute();

                        System.out.println("requête exécutée, nouvel utilisateur instancié");
                        MainApp.utilisateur = new Utilisateur(pseudo, mail, new DeckJoueur(), role);
                        new ControleurConnexion(1, mail, mdp, stage);
                    }
                    dao.closeConnection();

                } catch (SQLException e) {
                    System.out.println("Problème SQL");
                    e.printStackTrace();
                }

            } else {
                vueErreur.ajouterMessage("Les mots de passe ne correspondent pas");
                System.out.println("les mdp ne correspondent pas");
            }

        } else {
            vueErreur.ajouterMessage("Veuillez remplir tout les champs");
            System.out.println("tout les champs ne sont pas remplis");
        }
    }

    public boolean patternMatches(String mailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
            .matcher(mailAddress)
            .matches();
    }
}