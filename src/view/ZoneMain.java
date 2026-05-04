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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.ControleurConnexion;
import controller.ControleurPartie;
import javafx.scene.control.Button;
import model.CarteJeu;
import model.DeckJoueur;

public class ZoneMain extends Pane {
	
	private static ControleurPartie controleurpartie;
	
	public static int index; //L'index qui servira à lancer le premier tirage de cartes, et qui indique où on en est dans le tirage des cartes
	public static DeckJoueur deck;

	private HBox mainCartes;
	public static int mainsjouables;
	public static int mainsjetables;
	static Label affichemainsjouables = new Label();	
	static Label affichemainsjetables = new Label();
	private List<ImageView> cartesaffichees; //La liste qui contiendra les résultats de la fonction tiragecarte et qu'on utilise pour faire afficher les cartes dans la ZoneMain
	
	public ZoneMain(ControleurPartie controleurpartie) {
		index = 0;
		cartesaffichees = new ArrayList<>();
		mainsjouables = 4;
		mainsjetables = 4;
		affichemainsjouables.setText("Jouables : "+ mainsjouables +"");
		affichemainsjetables.setText("Jetables : "+ mainsjetables +"");
		
		affichemainsjouables.setStyle("""
		   		-fx-font-size: 19px;
				-fx-font-weight: bold;
				-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
				-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
		   		""");
		
		affichemainsjetables.setStyle("""
		   		-fx-font-size: 19px;
				-fx-font-weight: bold;
				-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
				-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
		   		""");
		
	
		ZoneMain.controleurpartie = controleurpartie;
		
		deck = controleurpartie.chargernouveaudeck();	//Appelle la fonction qui permet de charger un nouveau deck
		Collections.shuffle(deck);	//Mélange aléatoirement les cartes du deck : notre entité deck contient maintenant les CarteJeu dans un ordre aléatoire
		
		Image bandofond = new Image(getClass().getResource("/BandobaHunvre3.jpg").toExternalForm());
		BackgroundImage bg = new BackgroundImage(
		        bandofond,
		        BackgroundRepeat.NO_REPEAT,
		        BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.CENTER,
		        BackgroundSize.DEFAULT
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
		boutons.getChildren().addAll(affichemainsjouables, jouer, jeter, affichemainsjetables);
		boutons.layoutXProperty().bind(this.widthProperty().subtract(boutons.widthProperty()).divide(2));
		boutons.setLayoutY(160);
		this.getChildren().add(boutons);
		// Fin modification Vitally
		     
		// --- Taille max des composants ---
		jouer.setMaxWidth(200);
		jeter.setMaxWidth(200);
		
		// --- Événements ---
        jouer.setOnAction(e -> {
        	
	        if (ControleurPartie.cartesSelectionnees.size() > 0) {
	        	
	        	mainsjouables --;
	        	
	        	if (mainsjouables >= 0) {
	        		
	        		affichemainsjouables.setText("Jouables : "+ mainsjouables +"");
	        		
	        	}
	        	
	        	for (CarteJeu carte : ControleurPartie.cartesSelectionnees) {
	        		ZoneDeck.distribuerCarte();    		
	        	}
	
	        	ZoneCentrale.affichercartesjouees(ControleurPartie.cartesSelectionnees, controleurpartie.comptagepoints(ControleurPartie.cartesSelectionnees));
	        	
	        	ZoneScore.afficherscorezonescore();
	        	
	        	controleurpartie.gagneouperd(controleurpartie.verifgagneperd(ZoneScore.scoretotal));
	        		        		        	
	        	affichernouveautirage(deck, ControleurPartie.cartesSelectionnees.size(), ZoneMain.index, mainCartes);
	        	
	        	controleurpartie.jetercartes(ControleurPartie.cartesaffichees, mainCartes);
        	 
        	}
        });

        jeter.setOnAction(e -> {
        	
        	if (ControleurPartie.cartesSelectionnees.size() > 0) {
	        	
	        	if (mainsjetables != 0) {
	        		mainsjetables --;
	        		affichemainsjetables.setText("Jetables : "+ mainsjetables +"");
	        	
	        	for (CarteJeu carte : ControleurPartie.cartesSelectionnees) {
	        		ZoneDeck.distribuerCarte();    		
	        	}
	        	
	        	affichernouveautirage(deck, ControleurPartie.cartesSelectionnees.size(), ZoneMain.index, mainCartes);
	        	controleurpartie.jetercartes(ControleurPartie.cartesaffichees, mainCartes);
	        	}
	        	else {
	        		System.out.println("Plus de mains jetables!!");
	        	}
        	}
        });
 	// Fin code Vitally - Boutons Jouer et Bouton Jeter

	
	// Debut code d'Allan	

	mainCartes = new HBox(); //Lignes 72 à 79 : on définit les parametres de la HBox qui contiendra l'affichage de nos cartes
	mainCartes.setSpacing(10);
	mainCartes.setAlignment(Pos.CENTER);
	
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
		cartesaffichees = ControleurPartie.tiragecartes(deck, taille, index);
    	for (ImageView carte : cartesaffichees) {
    		main.getChildren().add(carte);
    	}
	}
	//Fin code d'Allan

}

