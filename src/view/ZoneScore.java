package view;

import java.awt.Font;

import controller.ControleurPartie;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontWeight;

// --- Classes du projet ---
import controller.ControleurConnexion;

/**
 * ZoneScore — Vue qui affiche le score du coup en cours et le score total.
 *
 * 2026-04-13 - Vitally Lubin
 * Cette classe était vide dans le code du 12 avril.
 *
 * TODO : ajouter un label "Objectif blinde : X"
 * La valeur cible est stockée dans la colonne "blinde" de la table utilisateur en BDD.
 * Elle doit être chargée à l'initialisation de la partie
 * et comparée à joueur.getScore() après chaque coup.
 * La vérification doit se faire dans ControleurPartie.jouer(), pas ici.
 *
 * TODO : renommer labelScoreBlinde en labelScoreCoup — "score blinde" est trompeur,
 * c'est le score du coup joué, pas l'objectif blinde à atteindre.
 */
public class ZoneScore extends Pane {
	
	private HBox afficherscore = new HBox();
	public Label scoreaffiche = new Label("0");
	public int score = 0;
	
	public ZoneScore () {

		afficherscore.setAlignment(Pos.CENTER);
		afficherscore.getChildren().add(scoreaffiche);


		scoreaffiche.setStyle("""
    		 -fx-font-size: 28px;
				-fx-font-weight: bold;
				-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
				-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
    		""");
		
		afficherscore.setSpacing(10);	
		afficherscore.setLayoutX(20);
		afficherscore.setLayoutY(20);

		this.getChildren().add(afficherscore);
		
		
	}

	
    public void afficherscorezonescore(int score) { //Permet d'afficher le score donné en paramêtre dans la zoneScore
    	
    	this.afficherscore.getChildren().clear();
    	HBox affiche = new HBox();
    	affiche.setAlignment(Pos.CENTER);
    	String scoreText = "Score actuel : " + Integer.toString(score);
    	Label affichescore = new Label(scoreText);
    	
    	affichescore.setStyle("""
    		-fx-font-size: 28px;
			-fx-font-weight: bold;
			-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
			-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
    		""");
    	
    	affiche.getChildren().add(affichescore);
    	this.afficherscore.getChildren().add(affiche);   	
    }
}
