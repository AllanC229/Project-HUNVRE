package view;

import java.awt.Font;

import controller.ControleurPartie;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ZoneScore extends Pane {
	
	private HBox afficherscore = new HBox();
	public Label score = new Label("0");
	
	public ZoneScore (ControleurPartie controleurpartie) {

		
		afficherscore.setSpacing(10);	
		afficherscore.setAlignment(Pos.CENTER);
		afficherscore.setLayoutX(20);
		afficherscore.setLayoutY(20);
		afficherscore.getChildren().add(score);
		this.getChildren().add(afficherscore);
		
		
	}

}
