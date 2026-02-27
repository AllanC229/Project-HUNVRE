package view;

import controller.ControleurEcranDeFin;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EcranDeFin extends Scene {
	public EcranDeFin (VBox vbox, boolean perdu) {
				
		super(vbox, 800, 600);
		
		int score = 300;
		/* Récupération du score du joueur
		 * 
		 * int score = joueur.getScore();
		 */
		
		if(perdu) {
			Label lignePerdu1 = new Label("Perdu");
			vbox.getChildren().add(lignePerdu1);
		}
		
		else {			
			Label message1 = new Label("Vous ouvrez les yeux dans votre rêve.");
			Label message2 = new Label("Vous savez que vous rêvez.");
			Label message3 = new Label("La métacognition est atteinte. Vous êtes lucide.");
			Label message4 = new Label("Dans cet état de conscience onirique, tout est possible.");
			Label message5 = new Label("Vous vous réveillez naurellement, enrichi de cette expérience...");
			Label message6 = new Label("Cette capacité à prendre des décisions consciente dans vos rêves reflète votre pouvoir de choix dans la vie éveillée.");
			Label message7 = new Label("Quel que soit votre niveau de conscience, vous êtes décisionnaire.");
			
			vbox.getChildren().addAll(message1, message2, message3, message4, message5, message6, message7);
		}
		
		Label scoreFinal = new Label("Score : " + score + "\n");
		
		Button nouvellePartie = new Button("Nouvelle Partie");
		Button accueil = new Button("Accueil");
		
		vbox.getChildren().add(scoreFinal);
		vbox.getChildren().add(nouvellePartie);
		vbox.getChildren().add(accueil);
		
		vbox.setPadding(new Insets(30));
		vbox.setMaxWidth(350);
		vbox.setAlignment(Pos.CENTER);
		
		nouvellePartie.setOnAction(e -> {
			new ControleurEcranDeFin(1);
		});
		
		accueil.setOnAction(e -> {
			new ControleurEcranDeFin(2);
		});
	}
}
