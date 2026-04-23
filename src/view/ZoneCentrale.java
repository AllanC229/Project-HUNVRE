package view;

import javafx.scene.layout.*;
import javafx.scene.image.Image;

public class ZoneCentrale extends Pane {
	
	public ZoneCentrale() {
		
		Image image = new Image(getClass().getResource("/images/bandomilieu1.jpg").toExternalForm());
		
		BackgroundSize backgroundSize = new BackgroundSize(
				100, 100, true, true, true, false);
		
		BackgroundImage backgroundImage = new BackgroundImage(
				image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				backgroundSize);
		
		this.setBackground(new Background(backgroundImage));
	}
}