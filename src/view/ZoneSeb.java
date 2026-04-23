package view;

// --- java.* ---
import java.util.ArrayList;    // Liste dynamique pour stocker les valeurs des cartes
import java.util.Collections;  // Tri des valeurs pour la détection des combinaisons
import java.util.HashMap;      // Map utilisée dans compterOccurrences()
import java.util.List;         // Type générique des listes reçues en paramètre
import java.util.Map;          // Type générique retourné par compterOccurrences()

// --- javafx.* ---
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

// --- Classes du projet ---
import controller.ControleurConnexion;
import model.CarteJeu;


public class ZoneSeb extends Pane {


    public ZoneSeb() {
 

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
      
        // Centrage horizontal et vertical dans le Pane
        vbox.layoutXProperty().bind(this.widthProperty().subtract(vbox.widthProperty()).divide(2));
        vbox.layoutYProperty().bind(this.heightProperty().subtract(vbox.heightProperty()).divide(2));

        this.getChildren().add(vbox);
    }

}
