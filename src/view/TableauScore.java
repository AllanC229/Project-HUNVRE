package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TableauScore extends Scene {
	ArrayList<String> noms;
	ArrayList<Integer> scores;
	
	public TableauScore(GridPane grid, ArrayList<String> noms, ArrayList<Integer> scores) {
		
		super(grid, 800, 600);
		grid.setAlignment(Pos.CENTER);
		this.noms = noms;
		this.scores = scores;
		
		
		
	
		grid.setHgap(20);
		grid.setVgap(20);
		
		for(int i = 0; i < scores.size(); i++) {
			Label name = new Label(""+ noms.get(i) +"");
			Label points = new Label(""+ scores.get(i) +"");
			
			grid.add(name, 0, i);
			grid.add(points, 1, i);
			
			System.out.println(noms.get(i));
			System.out.println(scores.get(i));
		
		}
		
		Button retour = new Button("Retour à l'accueil");
		grid.add(retour, 0, 5);
		retour.setOnAction(e -> {
			MainApp.jeu.setScene(new Accueil(new VBox()));
			MainApp.jeu.show();
		});
	}
}

