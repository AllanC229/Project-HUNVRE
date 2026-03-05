package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ZoneMenu extends Pane {
	
	public ZoneMenu() {
		Button bquitter = new Button("Quitter le jeu");		//bouton pour fermer l'application
		VBox.setMargin(bquitter, new Insets(50, 0, 0, 0));
		bquitter.setOnAction(e -> { 
			System.out.println("bquitter cliqué");
		
			Alert alerte = new Alert(Alert.AlertType.CONFIRMATION,	//petite fenetre qui demande confirmation
		        "Voulez-vous vraiment quitter ?",
		        ButtonType.YES,
		        ButtonType.NO);

			alerte.setTitle("Vous allez quitter le jeu");
			alerte.setHeaderText(null);
			
			Stage stage = (Stage) bquitter.getScene().getWindow();
		    alerte.initOwner(stage);

			if (alerte.showAndWait().get() == ButtonType.YES) {		//si on clique oui sur la fenetre
			    Platform.exit();	//ferme l'application
			}
		});
		this.getChildren().add(bquitter);
	}

}
