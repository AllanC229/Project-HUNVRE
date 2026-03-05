package view;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class ZoneTitre extends Pane {
	public ZoneTitre() {
		Image image = new Image(getClass().getResource("/bandoHunvre.jpg").toExternalForm());

		BackgroundImage bg = new BackgroundImage(
		        image,
		        BackgroundRepeat.NO_REPEAT,
		        BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.CENTER,
		        new BackgroundSize(
		                100, 100,
		                true, true,
		                true, false
		        )
		);

		this.setBackground(new Background(bg));
	}

}
