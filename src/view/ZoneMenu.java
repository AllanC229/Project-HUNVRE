package view;
import java.sql.SQLException;
import java.util.Optional;

import app.MainApp;
import connection.DAOAcces;
import controller.ControleurConnexion;
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
		
		boptions.setOnAction(ee -> {
			System.out.println("boptions cliqué");
			
			Button bsauvegarder = new Button("Sauvegarder");
			Button baccueil = new Button("Accueil");
			Button bquitter = new Button("Quitter");
			Button breprendre = new Button("Reprendre");
			
			VBox vboxoptions = new VBox();						// VBox pour la fenêtre Options
			vboxoptions.getChildren().addAll(bsauvegarder, baccueil, bquitter, breprendre);
			vboxoptions.setAlignment(Pos.CENTER);
			
			Scene scenetest = new Scene(vboxoptions, 120, 160);	// Scene pour la fenêtre Options
			Stage stageoptions = new Stage();					// La fenêtre Options
			stageoptions.setTitle("Options");
			stageoptions.setResizable(false);
			stageoptions.setScene(scenetest);
			
			Stage stage = (Stage) boptions.getScene().getWindow();
			stageoptions.initOwner(stage);						// Cette ligne permet d'éviter la disparition du jeu
			
			bsauvegarder.setOnAction(ef -> {
				System.out.println("bsauvegarder cliqué");
				
				System.out.println(ControleurConnexion.joueur.getDeck());
				/*
				 * 
				 * 
				 *DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "sandman", "bringme4dream");
				 *try {
				 *	dao.getStatement().executeUpdate("");
				 *}
				 *catch(SQLException e) {
				 *	System.out.println("Sauvegarde - Erreur SQL");
				 *	e.printStackTrace();
				 *}
				 *dao.closeConnection();
				 */ 
			});
			
			baccueil.setOnAction(eg -> {
				System.out.println("baccueil cliqué");
				Alert accueilalerte = new Alert(Alert.AlertType.CONFIRMATION,
						"Voulez-vous vraiment retourner à l'accueil ?",
						ButtonType.YES,
						ButtonType.NO);
				accueilalerte.setTitle("Retour à l'accueil");
				accueilalerte.setHeaderText(null);
				accueilalerte.initOwner(stage);
				
				if (accueilalerte.showAndWait().get() == ButtonType.YES) {
					Accueil accueil = new Accueil(new VBox());
					MainApp.jeu.setScene(accueil);
					MainApp.jeu.show();
					stageoptions.close();
				}
			});
			
			bquitter.setOnAction(eh -> {
				System.out.println("bquitter cliqué");
				Alert alerte = new Alert(Alert.AlertType.CONFIRMATION,	//petite fenetre qui demande confirmation
						"Voulez-vous vraiment fermer complètement le jeu ?",
						ButtonType.YES,
						ButtonType.NO);
				
				alerte.setTitle("Vous allez quitter le jeu");
				alerte.setHeaderText(null);
				alerte.initOwner(stage);
				
				if (alerte.showAndWait().get() == ButtonType.YES) {		//si on clique oui sur la fenetre
					Platform.exit();	//ferme l'application
				}
			});
			
			breprendre.setOnAction(ei -> {
				System.out.println("breprendre cliqué");
				stageoptions.close();
			});
			
			stageoptions.show();
		});
		
		this.getChildren().add(boptions);
		

		
	}

}
