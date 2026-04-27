package view;

import java.awt.Font;
import java.util.Collections;

import controller.ControleurPartie;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

// --- Classes du projet ---
import controller.ControleurConnexion;

public class ZoneScore extends Pane {
	
	private static VBox afficher = new VBox();
	public static int scoretotal = 0;
	public static float scoreamarquer = 100;
	public static Label scoreaffiche = new Label("Score actuel : " + scoretotal + "");
	public static Label mancheencours = new Label("Manche en cours : " + Partie.manche +" /5");
	public static Label blindeencours = new Label("Blinde en cours : " + Partie.blinde +" /3");
	public static Label affichescoreamarquer = new Label("Score à marquer : "+ scoreamarquer +"");
	
	public ZoneScore () {

		afficher.setAlignment(Pos.CENTER);
		
		
		scoreaffiche.setStyle("""
    		 -fx-font-size: 14px;
				-fx-font-weight: bold;
				-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
				-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
    		""");
		
		affichescoreamarquer.setStyle("""
	    		 -fx-font-size: 14px;
					-fx-font-weight: bold;
					-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
					-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
	    		""");
		
		mancheencours.setStyle("""
	    		 -fx-font-size: 14px;
					-fx-font-weight: bold;
					-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
					-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
	    		""");
		
		blindeencours.setStyle("""
	    		 -fx-font-size: 14px;
					-fx-font-weight: bold;
					-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
					-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
	    		""");
		
		afficher.setSpacing(10);	
		afficher.setLayoutX(20);
		afficher.setLayoutY(20);
		
		afficher.getChildren().addAll(scoreaffiche, affichescoreamarquer, mancheencours, blindeencours);
 
		this.getChildren().add(afficher);

		
	}

	
    public static void afficherscorezonescore() { //Permet d'afficher le score donné en paramêtre dans la zoneScore
    	
    	String scoreText = "Score actuel : " + Integer.toString(scoretotal);
    	scoreaffiche.setText(scoreText);
  	
    }
    
    public static void findeblindeencours() {
    	
    	if (Partie.blinde == 3) {
    		Partie.blinde = 1;
    		Partie.manche++ ;
    		mancheencours.setText("Manche en cours : "+Partie.manche+" /3");
    	}
    	
    	else {
    		Partie.blinde++ ;    		
    	}
    	
    	blindeencours.setText("Blinde en cours : " + Partie.blinde +" /3");

    	ControleurConnexion.joueur.cumulscore(scoretotal); //ajout le score réalisé lors de la blinde au score total du joueur
    	System.out.println(ControleurConnexion.joueur.getScore());
    	augmentescore();
    	affichescoreamarquer.setText("Score à marquer : "+ scoreamarquer +"");
    	
    	ZoneMain.mainsjetables = 4;
    	ZoneMain.affichemainsjetables.setText("Jouables : "+ ZoneMain.mainsjetables +"");
    	ZoneMain.mainsjouables = 4;
    	ZoneMain.affichemainsjouables.setText("Jouables : "+ ZoneMain.mainsjouables +"");
    	ZoneMain.index = 0;
    	scoretotal = 0;
    	afficherscorezonescore();
    	Collections.shuffle(ZoneMain.deck);
    	ControleurPartie.tiragecartes(ZoneMain.deck, 8, ZoneMain.index);

    }
    
  /*  public static void findemancheencours() {		Fonction finalement inutile
    	
    	Partie.manche++;
    	findeblindeencours();
    	mancheencours.setText("Manche en cours : " + Partie.manche +" /3");
    	Partie.blinde = 1;
    }
    */
    public static void augmentescore () {
    		scoreamarquer *= 1.2;    	
    }
}
