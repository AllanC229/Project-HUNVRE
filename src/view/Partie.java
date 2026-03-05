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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Partie extends Scene {
	
	

	public Partie() {
        super(new GridPane(), 1200, 900);

        GridPane partie = (GridPane) getRoot();
		partie.setGridLinesVisible(true);
		
		
	ColumnConstraints c1 = new ColumnConstraints();		//Lignes 21 à 36 : définition du quadrillage qui segmente les différentes zones d'affichage
	c1.setPercentWidth(17);
	ColumnConstraints c2 = new ColumnConstraints();
	c2.setPercentWidth(66);
	ColumnConstraints c3 = new ColumnConstraints();
	c3.setPercentWidth(17);
	
	RowConstraints l1 = new RowConstraints();
	l1.setPercentHeight(29);
	RowConstraints l2 = new RowConstraints();
	l2.setPercentHeight(42);
	RowConstraints l3 = new RowConstraints();
	l3.setPercentHeight(29);
	
	partie.getColumnConstraints().addAll(c1, c2, c3);  //On ajoute le quadrillage ainsi défini au GridPane "partie"
	partie.getRowConstraints().addAll(l1, l2, l3);
	
	partie.add(new ZoneTitre(), 0, 0, 3, 1);
	
	partie.add(new ZoneMenu(), 0, 0);
	
	partie.add(new ZoneScore(), 2, 0);

	partie.add(new ZoneCentrale(), 0, 1, 3, 1);	
	
	partie.add(new ZoneSeb(), 0, 2);
	
	partie.add(new ZoneMain(), 1, 2);	
	
	partie.add(new ZoneDeck(), 2, 2);
	
	

	
}
}
