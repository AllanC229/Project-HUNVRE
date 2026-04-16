package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import connection.DAOAcces;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.ControleurConnexion;
import controller.ControleurPartie;
import javafx.scene.control.Button;
import model.CarteJeu;
import model.DeckJoueur;

public class ZoneMain extends Pane {
	
	private ControleurPartie controleurpartie;
	
	//public void setControleur(ControleurPartie controleurpartie) { //La fonction qui permet d'associer  la zonemain le controleurpartie instancié dans la vue Partie
		
		
	
	//}
	
	private HBox mainCartes;
	
	public ZoneMain(ControleurPartie controleurpartie) {

		this.controleurpartie = controleurpartie;
		
		Image bandofond = new Image(getClass().getResource("/BandobaHunvre3.jpg").toExternalForm());

		BackgroundImage bg = new BackgroundImage(
		        bandofond,
		        BackgroundRepeat.NO_REPEAT,
		        BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.CENTER,
		        new BackgroundSize(
		                100, 100,
		                true, true,
		                true, false
		        )
		);

		this.setBackground(new Background(bg));
	
	// Début code Vitally - Boutons Jouer et Bouton Jeter	
		// 	--- Boutons ---
		Button jouer = new Button("Jouer");
		Button jeter = new Button("Jeter");
		
		jouer.setLayoutX(300);
		jouer.setLayoutY(160);
		jeter.setLayoutX(400);
		jeter.setLayoutY(160);
				
		// Début modification Vitally - centrage des boutons Jouer et Jeter
		// On place les boutons dans une HBox pour pouvoir les centrer ensemble
		// Même principe que pour les cartes : (largeur du Pane - largeur de la HBox) / 2
		HBox boutons = new HBox(10);
		boutons.getChildren().addAll(jouer, jeter);
		boutons.layoutXProperty().bind(this.widthProperty().subtract(boutons.widthProperty()).divide(2));
		boutons.setLayoutY(160);
		this.getChildren().add(boutons);
		// Fin modification Vitally
		     
		// --- Taille max des composants ---
		jouer.setMaxWidth(200);
		jeter.setMaxWidth(200);
		
		// --- Événements ---
        jouer.setOnAction(e -> {
            // à écrire
        });

        jeter.setOnAction(e -> {
        	// à écrire
        });
 	// Fin code Vitally - Boutons Jouer et Bouton Jeter

	
	// Debut code d'Allan	
	// DeckJoueur deck = new DeckJoueur();

	//List<CarteJeu> deck = new ArrayList<>(); //On instancie une entité deck

	//Fonction chargerdeck ici
	
	
	DeckJoueur deck = controleurpartie.chargernouveaudeck();	//Appelle la fonction qui permet de charger un nouveau deck
	Collections.shuffle(deck);	//Mélange aléatoirement les cartes du deck : notre entité deck contient maintenant les CarteJeu dans un ordre aléatoire
	
	mainCartes = new HBox(); //Lignes 72 à 79 : on définit les parametres de la HBox qui contiendra l'affichage de nos cartes
	mainCartes.setSpacing(10);
	mainCartes.setAlignment(Pos.CENTER);
	
	//mainCartes.setLayoutX(20);
	//mainCartes.setLayoutY(20);
	
	// Début modification Vitally - centrage des cartes
	mainCartes.layoutXProperty().bind(this.widthProperty().subtract(mainCartes.widthProperty()).divide(2));
	mainCartes.setLayoutY(20);
	// Fin modification Vitally
	
	this.getChildren().add(mainCartes);
	
	
	mainCartes = (controleurpartie.tiragecartes(deck));
	
	this.getChildren().add(mainCartes);	
}
	
	//Fin code d'Allan

}

