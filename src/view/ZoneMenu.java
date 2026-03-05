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
			
			
			VBox vboxtest = new VBox();
			vboxtest.getChildren().addAll(test, bquitter);
			vboxtest.setAlignment(Pos.CENTER);
			
			Scene scenetest = new Scene(vboxtest, 200, 100);
			Stage stagetest = new Stage();
			stagetest.setTitle("fenetre de test");
			stagetest.setScene(scenetest);
			
			Stage stage = (Stage) boptions.getScene().getWindow();
			stagetest.initOwner(stage);
			
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
			
			
			stagetest.show();
		});
		
		/*
		boptions.setOnAction(e -> {
			
			
			
			System.out.println("boptions cliqué");
			
			Alert options = new Alert(Alert.AlertType.CONFIRMATION);
			options.setTitle("Options");
			options.setHeaderText(null);
			
			Stage stage = (Stage) boptions.getScene().getWindow();
			options.initOwner(stage);
			ButtonType bquitter = new ButtonType("Quitter");
			ButtonType breprendre = new ButtonType("Reprendre", ButtonData.CANCEL_CLOSE);
			
			options.getButtonTypes().setAll(bquitter, breprendre);
			
			
			Optional<ButtonType> choix = options.showAndWait();
			if(choix.get() == bquitter) {
				Alert quitter = new Alert(Alert.AlertType.CONFIRMATION,
						"Voulez-vous vraiment quitter ?",
						ButtonType.YES,
						ButtonType.NO);
				
				quitter.setTitle("Vous allez quitter le jeu");
				quitter.setHeaderText(null);
				quitter.initOwner(stage);
				
				if(quitter.showAndWait().get() == ButtonType.YES) Platform.exit();
				
			}
		});
		*/
		
		this.getChildren().add(boptions);
		
		/*
		Button bquitter = new Button("Quitter");
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
		*/
		
	}

}
