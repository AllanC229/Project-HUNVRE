package view;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import app.MainApp;
import connection.DAOAcces;
import controller.ControleurConnexion;
import controller.ControleurPartie;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CarteJeu;
import model.DeckJoueur;

public class ZoneMenu extends Pane {
	
	public ZoneMenu() {
		
		Button boptions = new Button("Options");
		
		boptions.setOnAction(ee -> {
			System.out.println("boptions cliqué");
			
			Button bsauvegarder = new Button("Sauvegarder");
			Button baccueil = new Button("Accueil");
			Button bquitter = new Button("Quitter");
			Button breprendre = new Button("Reprendre");
			
			VBox vboxoptions = new VBox();						// VBox pour la fenêtre Options
			vboxoptions.getChildren().addAll(bsauvegarder, baccueil, bquitter, breprendre);
			vboxoptions.setSpacing(8);
			vboxoptions.setAlignment(Pos.CENTER);
			
			Scene sceneoptions = new Scene(vboxoptions, 120, 160);	// Scene pour la fenêtre Options
			Stage stageoptions = new Stage();					// La fenêtre Options
			stageoptions.setTitle("Options");
			stageoptions.setResizable(false);
			stageoptions.setScene(sceneoptions);
			
			Stage stage = (Stage) boptions.getScene().getWindow();
			stageoptions.initOwner(stage);						// Cette ligne permet d'éviter la disparition du jeu
			
			bsauvegarder.setOnAction(ef -> {
				System.out.println("bsauvegarder cliqué");
				stageoptions.close();
				//Fonction de ControleurPartie pour sauvegarder
				ControleurPartie.sauvegarderPartie(ControleurConnexion.joueur);
			});
			
			baccueil.setOnAction(eg -> {
				System.out.println("baccueil cliqué");
				stageoptions.close();
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
				}
			});
			
			bquitter.setOnAction(eh -> {
				System.out.println("bquitter cliqué");
				stageoptions.close();
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
		
		Button binfos = new Button("Infos");
		
		binfos.setOnAction(ej -> {
			System.out.println("binfos cliqué");
			
			Button bcartes = new Button("Cartes");
			Button bcombinaisons = new Button("Combinaisons");
			Button breprendreInfos = new Button("Reprendre");
			
			VBox vboxinfos = new VBox();
			vboxinfos.getChildren().addAll(bcartes, bcombinaisons, breprendreInfos);
			vboxinfos.setSpacing(8);
			vboxinfos.setAlignment(Pos.CENTER);
			
			Scene sceneinfos = new Scene(vboxinfos, 120, 160);
			Stage stageinfos = new Stage();
			stageinfos.setTitle("Infos");
			stageinfos.setResizable(false);
			stageinfos.setScene(sceneinfos);
			
			Stage infosstage = (Stage) binfos.getScene().getWindow();
			stageinfos.initOwner(infosstage);
			
			bcartes.setOnAction(ek -> {
				System.out.println("bcartes cliqué");
				stageinfos.close();
				DeckJoueur deckListe = ControleurConnexion.joueur.getDeck();
				int tailleDeckInfos = deckListe.size();
				System.out.println(tailleDeckInfos + " cartes dans le deck");
				
				HBox ligneTrefle = new HBox();
				ligneTrefle.setSpacing(5);
				ligneTrefle.setAlignment(Pos.CENTER);
				int compteTrefle = 0;
				HBox ligneCarreau = new HBox();
				ligneCarreau.setSpacing(5);
				ligneCarreau.setAlignment(Pos.CENTER);
				int compteCarreau = 0;
				HBox ligneCoeur = new HBox();
				ligneCoeur.setSpacing(5);
				ligneCoeur.setAlignment(Pos.CENTER);
				int compteCoeur = 0;
				HBox lignePique = new HBox();
				lignePique.setSpacing(5);
				lignePique.setAlignment(Pos.CENTER);
				int comptePique = 0;
				
				// Compte du nombre de cartes par couleur
				for(int j = 0; j < tailleDeckInfos; j++) {
					CarteJeu carte = (CarteJeu) deckListe.get(j);
					switch (carte.getCouleur()) {
					case "trefle":
						compteTrefle += 1;
						break;
					case "carreau":
						compteCarreau += 1;
						break;
					case "coeur":
						compteCoeur += 1;
						break;
					case "pique":
						comptePique += 1;
						break;
					}
				}
				
				for(int i = 0; i < tailleDeckInfos; i++) {
					
					CarteJeu carteListe = (CarteJeu) deckListe.get(i);
					Image imageCarte = new Image(getClass().getResourceAsStream("/" + carteListe.getRecto() + ".jpg"));
					ImageView lacarte = new ImageView(imageCarte);
					lacarte.setFitWidth(60);
					lacarte.setPreserveRatio(true);
					
					switch (carteListe.getCouleur()) {
					case "trefle":
						ligneTrefle.getChildren().add(lacarte);
						break;

					case "carreau":
						ligneCarreau.getChildren().add(lacarte);
						break;
					
					case "coeur":
						ligneCoeur.getChildren().add(lacarte);
						break;
						
					case "pique":
						lignePique.getChildren().add(lacarte);
						break;
					}
				}
				
				Label nombreCartes = new Label(
						tailleDeckInfos + " cartes ("
								+ compteTrefle + " de trèfle, "
								+ compteCarreau + " de carreau, "
								+ compteCoeur + " de cœur, "
								+ comptePique + " de pique)");
				Button bfermerInfoCartes = new Button("Fermer");
				
				VBox ligneCartes = new VBox();
				ligneCartes.setSpacing(10);
				ligneCartes.setAlignment(Pos.CENTER);
				ligneCartes.getChildren().addAll(ligneTrefle, ligneCarreau, ligneCoeur, lignePique, nombreCartes, bfermerInfoCartes);
				
				Scene sceneListeCartes = new Scene(ligneCartes, 950, 450);
				Stage stageListeCartes = new Stage();
				stageListeCartes.setTitle("Liste des cartes");
				stageListeCartes.setResizable(false);
				stageListeCartes.setScene(sceneListeCartes);
				
				Stage stageCartes = (Stage) binfos.getScene().getWindow();
				stageListeCartes.initOwner(stageCartes);
				
				bfermerInfoCartes.setOnAction(efermer -> {
					System.out.println("bouton bfermerInfoCartes cliqué");
					stageListeCartes.close();
				});
				
				stageListeCartes.show();
			});
			
			bcombinaisons.setOnAction(em -> {
				System.out.println("bcombinaisons cliqué");
				stageinfos.close();
				
				Label combo1 = new Label("Carte haute : Si aucune autre combinaison n'est présente, alors la carte ayant la plus haute valeur sera prise en compte - placeholderpoints");
				Label combo2 = new Label("Paire : Deux cartes de même valeur - placeholderpoints");
				Label combo3 = new Label("Double paire : Deux cartes de même valeur avec deux autres cartes de même valeur - placeholderpoints");
				Label combo4 = new Label("Brelan : Trois cartes de même valeur - placeholderpoints");
				Label combo5 = new Label("Carré : Quatre cartes de même valeur - placeholderpoints");
				Label combo6 = new Label("Suite : Cinq cartes à la suite avec au moins une couleur différente - placeholderpoints");
				Label combo7 = new Label("Couleur : Cinq cartes de même couleur - placeholderpoints");
				Label combo8 = new Label("Main pleine : Trois cartes de même valeur et deux cartes de même valeur. Les trois cartes et les deux cartes ne sont pas de la même couleur - placeholderpoints");
				Label combo9 = new Label("Quinte Flush : Cinq cartes à la suite et de même couleur - placeholderpoints");
				Label combo10 = new Label("Quinte Flush Royale : Une suite avec l'As, le Roi, la Dame, le Valet et le 10, tous de la même couleur - placeholderpoints");
				
				
				Button bfermerInfoCombo = new Button("Fermer");
				VBox ligneCombo = new VBox();
				ligneCombo.setSpacing(10);
				ligneCombo.setAlignment(Pos.CENTER);
				ligneCombo.getChildren().addAll(combo1,combo2,combo3,combo4,combo5,combo6,combo7,combo8,combo9,combo10,bfermerInfoCombo);
				
				Scene sceneInfoCombo = new Scene(ligneCombo, 950, 450);
				Stage stageInfoCombo = new Stage();
				stageInfoCombo.setTitle("Liste des cartes");
				stageInfoCombo.setResizable(false);
				stageInfoCombo.setScene(sceneInfoCombo);
				
				Stage stageCombo = (Stage) binfos.getScene().getWindow();
				stageInfoCombo.initOwner(stageCombo);
				
				bfermerInfoCombo.setOnAction(efermer -> {
					System.out.println("bouton bfermerInfoCartes cliqué");
					stageInfoCombo.close();
				});
				
				stageInfoCombo.show();
				
			});
			
			breprendreInfos.setOnAction(el -> {
				stageinfos.close();
			});
			
			stageinfos.show();
		});
		
		
		this.getChildren().addAll(boptions, binfos);
		
		// Centrage des boutons Options et Infos
		boptions.layoutXProperty().bind(this.widthProperty().subtract(boptions.widthProperty()).divide(2));
		boptions.layoutYProperty().bind(this.heightProperty().subtract(boptions.heightProperty()).multiply(0.4));
		binfos.layoutXProperty().bind(this.widthProperty().subtract(binfos.widthProperty()).divide(2));;
		binfos.layoutYProperty().bind(this.heightProperty().subtract(binfos.heightProperty()).multiply(0.6));
		

		
	}

}
