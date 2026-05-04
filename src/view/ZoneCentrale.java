package view;

import javafx.scene.layout.*;
import model.CarteJeu;

import javafx.util.Duration;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ZoneCentrale extends Pane {
	
	static HBox affichagecartes = new HBox();
	static HBox affichagescore = new HBox();
	static int bando = 1;
	
	public ZoneCentrale() {
		
		Image image = new Image(getClass().getResource("/bandomilieu1.jpg").toExternalForm());
		
		BackgroundSize backgroundSize = new BackgroundSize(
				100, 100, true, true, true, false);
		
		BackgroundImage backgroundImage = new BackgroundImage(
				image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				backgroundSize);
		
		this.setBackground(new Background(backgroundImage));
		
		affichagecartes.setSpacing(30);
		affichagecartes.setAlignment(Pos.CENTER);
		
		affichagecartes.layoutXProperty().bind(widthProperty().subtract(affichagecartes.widthProperty()).divide(2)); //Ces deux lignes là sont pour centrer la HBox affichagecartes dans le Pane ZoneCentrale
		affichagecartes.layoutYProperty().bind(heightProperty().subtract(affichagecartes.heightProperty()).divide(2));
		
		affichagescore.setAlignment(Pos.CENTER);
		affichagescore.layoutXProperty().bind( widthProperty().subtract(affichagescore.widthProperty()).divide(2)); 

		
		this.getChildren().addAll(affichagecartes, affichagescore);
	}
	
	/*
	 * La fonction ci-dessous permet, au moment où on clique sur le bouton "jouer" dans la ZoneMain, de faire s'afficher les cartes qu'on a jouées dans la zonecentrale
	 */
	public static void affichercartesjouees (List<CarteJeu> listecarte, int score) { //Il faudra lui passer en parametre la liste cartesaffichees de controleurpartie

	    Label scoreaffiche = new Label(""+ score +" points marqués!");
	    
		scoreaffiche.setStyle("""
	    		 -fx-font-size: 25px;
					-fx-font-weight: bold;
					-fx-text-fill: linear-gradient(to right, #ffffff, #cfcfcf);
					-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.25), 8, 0.5, 0, 0);
	    		""");
	    
	    affichagescore.getChildren().add(scoreaffiche);
	    
        FadeTransition fadescore = new FadeTransition(Duration.seconds(5), scoreaffiche);
        fadescore.setFromValue(1.0);
        fadescore.setToValue(0.0);

        fadescore.setOnFinished(event -> {
            affichagescore.getChildren().remove(scoreaffiche);
        });

        fadescore.play();
	    	    
		for (CarteJeu carte : listecarte) {
			
			Image image = new Image(ZoneCentrale.class.getResource("/"+ carte.getRecto() +".jpg").toExternalForm()); //On récupère l'image recto associée à la carte
		    ImageView carteaffichee = new ImageView(image);	
		    
			affichagecartes.getChildren().add(carteaffichee);
			
	        FadeTransition fadecarte = new FadeTransition(Duration.seconds(5), carteaffichee);
	        fadecarte.setFromValue(1.0);
	        fadecarte.setToValue(0.0);

	        fadecarte.setOnFinished(event -> {
	            affichagecartes.getChildren().remove(carteaffichee);
	        });

	        fadecarte.play();
		}
		
	}
	
	public static void affichescoremarque (int score) {
		Label scoreaffiche = new Label(""+ score +" points marqués!");
		
	}
}