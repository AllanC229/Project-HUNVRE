package view;

import controller.ControleurPartie;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.CarteJeu;

public class ZoneSeb extends Pane {
	
	private HBox affichercombinaison = new HBox();	//La HBox qui sert à afficher des infos dans la zoneSeb
	
	public ZoneSeb () {
			
		affichercombinaison.setSpacing(10);	
		affichercombinaison.setAlignment(Pos.CENTER);
		affichercombinaison.setLayoutX(20);
		affichercombinaison.setLayoutY(20);
		this.getChildren().add(affichercombinaison);
		
	
	}

	
	public void afficherCarte(CarteJeu carte) { //La fonction qui permet d'afficher des élements de façon dynamique dans la zoneseb
		   
		    	Label combinaison = new Label(carte.getRecto());
		    	this.affichercombinaison.getChildren().add(combinaison);	   
		    
		}
	}


