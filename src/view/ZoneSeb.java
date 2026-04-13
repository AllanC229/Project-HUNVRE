package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.CarteJeu;

public class ZoneSeb extends Pane {
	
	public void afficherCarte(CarteJeu carte) {

		    Image image = new Image(getClass().getResourceAsStream("/" + carte.getRecto() + ".jpg"));
		    ImageView carteshow = new ImageView(image);

		    carteshow.setFitWidth(80);
		    carteshow.setPreserveRatio(true);

		    getChildren().clear();
		    getChildren().add(carteshow);
		    System.out.println("Carte dans ZoneSeb : " + carte.getRecto());
		}
	}


