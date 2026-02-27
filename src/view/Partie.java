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
		
	ColumnConstraints c1 = new ColumnConstraints();
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
	
	partie.getColumnConstraints().addAll(c1, c2, c3);
	partie.getRowConstraints().addAll(l1, l2, l3);
}
}
