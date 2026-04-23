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
	
	public static int index = 0; //L'index qui servira à lancer le premier tirage de cartes, et qui indique où on en est dans le tirage des cartes
	

	private HBox mainCartes;
	private List<ImageView> cartesaffichees = new ArrayList<>(); //La liste qui contiendra les résultats de la fonction tiragecarte et qu'on utilise pour faire afficher les cartes dans la ZoneMain
	
	public ZoneMain(ControleurPartie controleurpartie) {
		
		DeckJoueur deck = controleurpartie.chargernouveaudeck();	//Appelle la fonction qui permet de charger un nouveau deck
		Collections.shuffle(deck);	//Mélange aléatoirement les cartes du deck : notre entité deck contient maintenant les CarteJeu dans un ordre aléatoire
	
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
        	//Pareil que quand on jette mais on rajoutera une fonction pour compter les points et les ajouter au score
        	ZoneScore.afficherscorezonescore(50);
        	affichernouveautirage(deck, controleurpartie.cartesSelectionnees.size(), ZoneMain.index, mainCartes);
        	controleurpartie.jetercartes(controleurpartie.cartesaffichees, mainCartes);
        });

        jeter.setOnAction(e -> {
        	affichernouveautirage(deck, controleurpartie.cartesSelectionnees.size(), ZoneMain.index, mainCartes);
        	controleurpartie.jetercartes(controleurpartie.cartesaffichees, mainCartes);
        	
        	
        });
 	// Fin code Vitally - Boutons Jouer et Bouton Jeter

	
	// Debut code d'Allan	

	mainCartes = new HBox(); //Lignes 72 à 79 : on définit les parametres de la HBox qui contiendra l'affichage de nos cartes
	mainCartes.setSpacing(10);
	mainCartes.setAlignment(Pos.CENTER);
	
	//mainCartes.setLayoutX(20);
	//mainCartes.setLayoutY(20);
	
	// Début modification Vitally - centrage des cartes
	mainCartes.layoutXProperty().bind(this.widthProperty().subtract(mainCartes.widthProperty()).divide(2));
	mainCartes.setLayoutY(20);
	// Fin modification Vitally
	
	
	cartesaffichees = (controleurpartie.tiragecartes(deck, 8, index)); //On fait un premier tirage dans le deck avec 8 cartes, en partant de la position 0 dans le deck
	
	for (ImageView carte : cartesaffichees) {
		mainCartes.getChildren().add(carte);
	}
	this.getChildren().add(mainCartes);
	
	
}

	public int getIndex() {
		return index;
	}
	public static void setIndex(int index) {
		ZoneMain.index = index;
	}
	
	private void affichernouveautirage(DeckJoueur deck, int taille, int index, HBox main) {	//Cette fonction permet de réaliser l'affichage d'un nouveau tirage de cartes dans ZoneMain. Elle prend en parametres le deck actuel, la taille du tirage, la position à partir de laquelle démarrer le tirage dans le deck (index) et la HBox zonemain actuelle
		cartesaffichees = controleurpartie.tiragecartes(deck, taille, index);
    	for (ImageView carte : cartesaffichees) {
    		main.getChildren().add(carte);
    	}
	}
	//Fin code d'Allan

}

