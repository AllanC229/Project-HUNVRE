package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.ControleurConnexion;
import javafx.scene.control.Button;

public class ZoneMain extends Pane {
	
	public ZoneMain() {
	
	Label test = new Label("hunvre");
	this.getChildren().add(test);
	
	// Début code Vitally - Boutons Jouer et Bouton Jeter
	
		// 	--- Boutons ---
		Button jouer = new Button("Jouer");
		Button jeter = new Button("Jeter");
		     
		// --- Taille max des composants ---
		jouer.setMaxWidth(200);
		jeter.setMaxWidth(200);
		
		// --- Événements ---
        jouer.setOnAction(e -> {
            // à écrire
        });

        jeter.setOnAction(e -> {
        	// à écrire
        });

 	// Fin code Vitally - Boutons Jouer et Bouton Jeter

}
}
