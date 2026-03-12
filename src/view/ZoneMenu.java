package view;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				int tailleDeck = ControleurConnexion.joueur.getDeck().getListedeck().size();
				System.out.println(tailleDeck);
				
				System.out.println("Liste des cartes (si ça marche) :");
				DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");
				try {
					int idJoueur = 0;
					int idCarte = 0;
					int qteCarte[] = new int[52];
					
					for(int i = 0; i < 52; i++) qteCarte[i] = 0;
						
					for(int i = 0; i < tailleDeck; i++) {
						idCarte = ControleurConnexion.joueur.getDeck().getListedeck().get(i).getId();
						qteCarte[idCarte - 1] += 1;
					}
					
					PreparedStatement pstSauvegarde = dao.getConn().prepareStatement(
							"SELECT id_utilisateur FROM utilisateur WHERE mail = ?");
					pstSauvegarde.setString(1, ControleurConnexion.joueur.getMail());
					ResultSet rsSauvegarde = pstSauvegarde.executeQuery();
					while(rsSauvegarde.next()) {
						idJoueur = rsSauvegarde.getInt(1);
					}
					
					dao.getConn().setAutoCommit(false);
					PreparedStatement insertion = dao.getConn().prepareStatement("DELETE FROM deck_carte WHERE ref_utilisateur = ?");
					insertion.setInt(1, idJoueur);
					insertion.executeUpdate();
					
					for(int i = 0; i < 52; i++) {
						insertion = dao.getConn().prepareStatement("INSERT INTO deck_carte VALUES (?, ?, ?)");
						insertion.setInt(1, idJoueur);
						insertion.setInt(2, i + 1);
						insertion.setInt(3, qteCarte[i]);
						insertion.executeUpdate();
					}
					
					dao.getConn().commit();
					
				
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				dao.closeConnection();
				
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
