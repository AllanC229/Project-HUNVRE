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
			Label lignePerdu1 = new Label("Vous perdez le contrôle de la situation.");
			Label lignePerdu2 = new Label("La forêt se déforme, les branches se tordent.");
			Label lignePerdu3 = new Label("Le rêve s'assombrit.");
			Label lignePerdu4 = new Label("Vous ne parvenez plus à vous mouvoir.");
			Label lignePerdu5 = new Label("Le haut devient le bas, les couleurs s'échappent.");
			Label lignePerdu6 = new Label("Vous vous réveillez brusquement, marqué-e par cette expérience...");

			vbox.getChildren().addAll(lignePerdu1, lignePerdu2, lignePerdu3, lignePerdu4, lignePerdu5, lignePerdu6);
		}
		
		else {			
			Label ligneGagne1 = new Label("Vous ouvrez les yeux dans votre rêve.");
			Label ligneGagne2 = new Label("Vous savez que vous rêvez.");
			Label ligneGagne3 = new Label("La métacognition est atteinte. Vous êtes lucide.");
			Label ligneGagne4 = new Label("Dans cet état de conscience onirique, tout est possible.");
			Label ligneGagne5 = new Label("Vous vous réveillez naurellement, enrichi de cette expérience...");
			Label ligneGagne6 = new Label("Cette capacité à prendre des décisions consciente dans vos rêves reflète votre pouvoir de choix dans la vie éveillée.");
			Label ligneGagne7 = new Label("Quel que soit votre niveau de conscience, vous êtes décisionnaire.");
			
			vbox.getChildren().addAll(ligneGagne1, ligneGagne2, ligneGagne3, ligneGagne4, ligneGagne5, ligneGagne6, ligneGagne7);
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
