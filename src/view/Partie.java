package view;

import app.MainApp;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class Partie extends Scene {
	
	

	public Partie() {
        super(new GridPane(), 1200, 900);

        GridPane partie = (GridPane) getRoot();
		partie.setGridLinesVisible(true);
		
	ColumnConstraints c1 = new ColumnConstraints();		//Lignes 21 à 36 : définition du quadrillage qui segmente les différentes zones d'affichage
	c1.setPercentWidth(15);
	ColumnConstraints c2 = new ColumnConstraints();
	c2.setPercentWidth(70);
	ColumnConstraints c3 = new ColumnConstraints();
	c3.setPercentWidth(15);
	
	RowConstraints l1 = new RowConstraints();
	l1.setPercentHeight(29);
	RowConstraints l2 = new RowConstraints();
	l2.setPercentHeight(42);
	RowConstraints l3 = new RowConstraints();
	l3.setPercentHeight(29);
	
	partie.getColumnConstraints().addAll(c1, c2, c3);  //On ajoute le quadrillage ainsi défini au GridPane "partie"
	partie.getRowConstraints().addAll(l1, l2, l3);
	
	
	Pane zonecentrale = new Pane();			//Ces deux lignes servent à regrouper les 3 cases de la ligne du milieu  en une seule grand case
	partie.add(zonecentrale, 0, 1, 3, 1);	//On l'ajoute directement ici  à "partie"
	
	
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

		if (alerte.showAndWait().get() == ButtonType.YES) {		//si on clique oui sur la fenetre
		    Platform.exit();	//ferme l'application
		}
	});
	
	partie.add(bquitter,  2, 0); //ici on ajoute le bouton "quitter" dans la case du GridPane ayant les coordonnées 0, 2

	
	
}
}
