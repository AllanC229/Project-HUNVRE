package view;

// Imports JavaFX - Layout
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

// Imports JavaFX - Composants UI
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

// Imports JavaFX - Géométrie
import javafx.geometry.Insets;
import javafx.geometry.Pos;

// Imports JavaFX - Application
import javafx.stage.Stage;
import javafx.application.Platform;

// Import contrôleur
import controller.ControleurConnexion;

/**
 * Vue de connexion : écran d'accueil avec email, mot de passe,
 * boutons de connexion et de création de compte.
 */
public class Connexion extends Scene {

    public Connexion(VBox vbox) {
        super(vbox, 800, 600);

        // --- Barre du haut avec bouton Quitter ---
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Button quitter = new Button("Quitter");
        HBox topBar = new HBox(spacer, quitter);
        topBar.setPadding(new Insets(10));

        // --- Titre ---
        Label titre = new Label("HUNVRE");
        titre.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        // --- Description ---
        Label description = new Label(
            "Un jeu inspiré de Balatro pour plonger\ndans les profondeurs du rêve lucide.");
        description.setStyle("-fx-font-size: 14px; -fx-text-alignment: center;");

        // --- Champs de saisie avec labels ---
        Label labelEmail = new Label("Adresse email :");
        TextField email = new TextField();
        email.setPromptText("exemple@mail.com");
        labelEmail.setStyle("-fx-text-fill: white;");

        
        Label labelMdp = new Label("Mot de passe :");
        PasswordField mdp = new PasswordField();
        mdp.setPromptText("Votre mot de passe");
        labelMdp.setStyle("-fx-text-fill: white;");

        // --- Boutons ---
        Button connexion = new Button("Se connecter");
        Button creation = new Button("Créer un compte");

        // --- Image de fond via CSS ---
        vbox.setStyle("""
                -fx-background-image: url("/accueil.jpg");
                -fx-background-size: cover;
                -fx-background-position: center;
                """);

        // --- Mise en page du VBox ---
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(350);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        // --- Taille max des composants ---
        email.setMaxWidth(200);
        mdp.setMaxWidth(200);
        connexion.setMaxWidth(200);
        creation.setMaxWidth(200);

        // --- Ajout des composants dans l'ordre d'affichage ---
        vbox.getChildren().addAll(
            topBar, titre, description,
            labelEmail, email,
            labelMdp, mdp,
            connexion, creation
        );

        // --- Événements ---
        connexion.setOnAction(e -> {
            Stage stage = (Stage) connexion.getScene().getWindow();
            new ControleurConnexion(1, email.getText(), mdp.getText(), stage);
        });

        creation.setOnAction(e -> {
            Stage stage = (Stage) creation.getScene().getWindow();
            new ControleurConnexion(2, "", "", stage);
        });

        quitter.setOnAction(e -> Platform.exit());
    }
}