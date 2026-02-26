package view;

import app.MainApp;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class Partie extends Scene {
	
	

	public Partie() {
        super(new GridPane(), 1200, 900);

        GridPane partie = (GridPane) getRoot();
		partie.setGridLinesVisible(true);
		
	int colonne = 3;
	int ligne = 3;
	
	for (int i = 0; i < colonne; i++) {
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(100.0 / colonne);
		partie.getColumnConstraints().add(col);
	}
	
	for (int i = 0; i < ligne; i++) {
		RowConstraints lig = new RowConstraints();
		lig.setPercentHeight(100.0 / ligne);
		partie.getRowConstraints().add(lig);
	}

	for (int col = 0; col < ligne ; col++) {
	    for (int lig = 0; ligne < colonne; lig++) {
	        Pane cell = new Pane();
	        partie.add(cell, col, lig);
	    }
	}
}
}
