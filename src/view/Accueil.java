package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Accueil extends Scene {

	public Accueil(VBox vbox) {
		
		super(vbox, 800, 600);
			
			
			Label titre = new Label("Accueil");
			Label nouvellepartie = new Label("Nouvelle Partie");
			Label tableauscore = new Label("Tableau des scores");
			vbox.getChildren().add(titre);
			vbox.getChildren().add(tableauscore);
			vbox.getChildren().add(nouvellepartie);
			System.out.println(getClass().getResourceAsStream("/lapin.png"));
			Image lapin = new Image(getClass().getResourceAsStream("/lapin.png"));
			
			
		
		vbox.setPadding(new Insets(30));
		vbox.setMaxWidth(350);
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("""
		    -fx-background-image: url("/lapin.png");
		    -fx-background-size: cover;
			-fx-background-position: center;
		""");
		BackgroundPosition bckP = new BackgroundPosition(null, getWidth(), isDepthBuffer(), null, getHeight(), isDepthBuffer());
		BackgroundSize bckS = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		Background bck = new Background(new BackgroundImage(lapin, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, bckP, bckS));
		vbox.setBackground(bck);
			
	}
}
