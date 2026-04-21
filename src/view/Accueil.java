package view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Optional;

import controller.ControleurAccueil;
import controller.ControleurConnexion;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Accueil extends Scene {

	public Accueil(VBox vbox) {
		
		super(vbox, 800, 600);
		
			Label affichagepseudo = new Label("Bonjour " + ControleurConnexion.joueur.getPseudo());
					
			Button bprofil = new Button("Profil");	//bouton sur la page d'accueil qui permet d'accéder au profil
			VBox.setMargin(bprofil, new Insets(50, 0, 0, 0));
			bprofil.setOnAction(e -> { 
				System.out.println("bprofil cliqué");
				String sonPath = getClass().getResource("/applause.wav").toExternalForm();  //parametrage du son qui sera joué au clic du bouton
				AudioClip clic = new AudioClip(sonPath);	//pareil
				clic.setBalance(0.9);	//pareil
				clic.play();	//joue un petit son au clic du bouton
				new ControleurAccueil(1);	//initialise le ControleurAccueil avec la valeur 1 pour lui indiquer quelle instruction exécuter (cf ControleurAccueil)	
			});
		
			Button bnouvellepartie = new Button("Nouvelle Partie");  //bouton qui permet de lancer une nouvelle partie
			VBox.setMargin(bnouvellepartie, new Insets(50, 0, 0, 0));
			bnouvellepartie.setOnAction(e -> { 
				System.out.println("bnouvellepartiecliqué");
				new ControleurAccueil(2);  //initialise le ControleurAccueil avec la valeur 2
			});
		
			Button btableauscore = new Button("Tableau des scores");	//bouton qui permet d'accéder au tableau des scores
			VBox.setMargin(btableauscore, new Insets(50, 0, 0, 0));
			btableauscore.setOnAction(e -> { 
				System.out.println("btableauscore cliqué");
				new ControleurAccueil(3);	//initialise le ControleurAccueil avec la valeur 3
			});
			
			Button bquitter = new Button("Quitter le jeu");		//bouton pour fermer l'application
			VBox.setMargin(bquitter, new Insets(50, 0, 0, 0));
			bquitter.setOnAction(e -> { 
				System.out.println("bquitter cliqué");
			
				Alert alerte = new Alert(Alert.AlertType.CONFIRMATION,	//petite fenetre qui demande confirmation
			        "Voulez-vous vraiment quitter ?",
			        ButtonType.YES,
			        ButtonType.NO);

				alerte.setTitle("Vous allez quitter le jeu");
				alerte.setHeaderText(null);
	
				if (alerte.showAndWait().get() == ButtonType.YES) {		//si on clique oui sur la fenetre
				    Platform.exit();	//ferme l'application
				}
			
			});
			
			vbox.getChildren().addAll(affichagepseudo, bprofil, bnouvellepartie, btableauscore, bquitter);	//on ajoute tous les boutons à la vbox accueil
			
		
		Image lapin = new Image(getClass().getResourceAsStream("/accueil.jpg"));
		vbox.setPadding(new Insets(30));   
		vbox.setMaxWidth(350);
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("""
		    -fx-background-image: url("/accueil.jpg");
		    -fx-background-size: cover;
			-fx-background-position: center;
		""");
		BackgroundPosition bckP = new BackgroundPosition(null, getWidth(), isDepthBuffer(), null, getHeight(), isDepthBuffer());
		BackgroundSize bckS = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		Background bck = new Background(new BackgroundImage(lapin, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, bckP, bckS));
		vbox.setBackground(bck); 
			
	}
}
