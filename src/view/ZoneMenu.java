package view;
import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ZoneMenu extends Pane {
	
	public ZoneMenu() {
		
		Button boptions = new Button("Options");
		VBox.setMargin(boptions, new Insets(50, 0, 0, 0));
		
		boptions.setOnAction(e -> {
			System.out.println("boptions cliqué, version nouvelle fenêtre");
			
			Label test = new Label("Test");
			Button bquitter = new Button("Quitter");
			
			VBox vboxoptions = new VBox();
			vboxoptions.getChildren().addAll(test, bquitter);
			vboxoptions.setAlignment(Pos.CENTER);
			
			Scene scenetest = new Scene(vboxoptions, 200, 100);
			Stage stageoptions = new Stage();
			stageoptions.setTitle("fenetre de test");
			stageoptions.setScene(scenetest);
			
			Stage stage = (Stage) boptions.getScene().getWindow();
			stageoptions.initOwner(stage);
			
			bquitter.setOnAction(f -> {
				System.out.println("bquitter cliqué, version fenêtre");
				
				Alert alerte = new Alert(Alert.AlertType.CONFIRMATION,	//petite fenetre qui demande confirmation
						"Voulez-vous vraiment quitter ?",
						ButtonType.YES,
						ButtonType.NO);
				
				alerte.setTitle("Vous allez quitter le jeu");
				alerte.setHeaderText(null);
				
				alerte.initOwner(stage);
				
				if (alerte.showAndWait().get() == ButtonType.YES) {		//si on clique oui sur la fenetre
					Platform.exit();	//ferme l'application
				}
			});
			
			stageoptions.show();
		});
		
		this.getChildren().add(boptions);
		

		
	}

}
