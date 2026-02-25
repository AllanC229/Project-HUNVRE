package view;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import controller.ControleurConnexion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Connexion extends Scene {
	public static String valeur;

	public Connexion(VBox vbox) {
		super(vbox, 800, 600);
		
		Image lapin = new Image(getClass().getResourceAsStream("/lapin.png"));
		Button connexion = new Button("Se connecter");
		Button creation = new Button("Créer un compte");
		Label etiquette = new Label("Bienvenue dans JavaFX !");
		TextField identifiant = new TextField();
		PasswordField mdp = new PasswordField();
				
		vbox.setPadding(new Insets(30));
		vbox.setMaxWidth(350);
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("""
			    -fx-background-image: url("/lapin.png");
			    -fx-background-size: cover;
				-fx-background-position: center;
				""");
		
		BackgroundPosition bckP = new BackgroundPosition(null,
				getWidth(),
				isDepthBuffer(),
				null,
				getHeight(),
				isDepthBuffer());
		
		BackgroundSize bckS = new BackgroundSize(BackgroundSize.AUTO,
				BackgroundSize.AUTO,
				false,
				false,
				true,
				false);
		
		Background bck = new Background(new BackgroundImage(lapin, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, bckP, bckS));
		
		vbox.setBackground(bck);
		
		vbox.getChildren().add(etiquette);		
		
		vbox.getChildren().add(identifiant);
		vbox.getChildren().add(mdp);
		
		vbox.getChildren().add(connexion);
		connexion.setOnAction(e -> {
        	new ControleurConnexion(1);            
        });
		
		vbox.getChildren().add(creation);
		creation.setOnAction(e -> {
			new ControleurConnexion(2);   
        });
		
		// Personnalisation du VBox
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(20, 20, 20, 20));
		
		// Taille des children de vbox
		identifiant.setMaxWidth(200);
		mdp.setMaxWidth(200);
		connexion.setMaxWidth(200);
		creation.setMaxWidth(200);
		
		// Adaptation à la taille de la scène
		VBox.setVgrow(etiquette, Priority.ALWAYS);
		VBox.setVgrow(connexion, Priority.ALWAYS);

	}
}
