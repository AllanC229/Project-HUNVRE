package view;

// Imports JavaFX - Layout
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;

// Imports JavaFX - Composants UI
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

// Imports JavaFX - Géométrie
import javafx.geometry.Insets;
import javafx.geometry.Pos;

// Import contrôleur
import controller.ControleurConnexion;

/**
 * Vue de connexion : écran d'accueil avec identifiant, mot de passe,
 * boutons de connexion et de création de compte.
 */
public class Connexion extends Scene {

    public static String valeur;

    public Connexion(VBox vbox) {
        super(vbox, 800, 600);

        // --- Création des composants UI ---
        Label etiquette = new Label("Bienvenue dans JavaFX !");
        TextField identifiant = new TextField();
        PasswordField mdp = new PasswordField();
        Button connexion = new Button("Se connecter");
        Button creation = new Button("Créer un compte");

        // --- Image de fond via CSS ---
        vbox.setStyle("""
                -fx-background-image: url("/lapin.png");
                -fx-background-size: cover;
                -fx-background-position: center;
                """);

        // --- Mise en page du VBox ---
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(350);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        // --- Taille max des composants ---
        identifiant.setMaxWidth(200);
        mdp.setMaxWidth(200);
        connexion.setMaxWidth(200);
        creation.setMaxWidth(200);

        // --- Croissance verticale (adaptation à la fenêtre) ---
        VBox.setVgrow(etiquette, Priority.ALWAYS);
        VBox.setVgrow(connexion, Priority.ALWAYS);

        // --- Ajout des composants dans l'ordre d'affichage ---
        vbox.getChildren().addAll(etiquette, identifiant, mdp, connexion, creation);

        // --- Événements ---
        connexion.setOnAction(e -> new ControleurConnexion(1));
        creation.setOnAction(e -> new ControleurConnexion(2));
    }
}