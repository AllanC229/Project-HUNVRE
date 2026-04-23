package view;

import java.awt.Font;

import controller.ControleurPartie;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontWeight;

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
