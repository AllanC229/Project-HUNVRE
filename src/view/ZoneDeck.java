package view;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ZoneDeck extends Pane {

    private ImageView dosCarte;
    private Label compteur;

    private int cartesRestantes = 52;
    private final int totalCartes = 52;

    public ZoneDeck() {

        VBox layout = new VBox(8);
        layout.setAlignment(Pos.CENTER);

        layout.prefWidthProperty().bind(widthProperty());
        layout.prefHeightProperty().bind(heightProperty());

        Image image = new Image(getClass().getResourceAsStream("/dos-de-cartejaune.jpg"));
        dosCarte = new ImageView(image);

        dosCarte.setPreserveRatio(true);
        dosCarte.setFitHeight(140);

        compteur = new Label(cartesRestantes + "/" + totalCartes);

        compteur.setStyle("""
            -fx-font-size: 20px;
            -fx-font-weight: bold;
            -fx-text-fill: white;
        """);

        layout.getChildren().addAll(dosCarte, compteur);
        getChildren().add(layout);

        dosCarte.setOnMouseClicked(e -> distribuerCarte());
    }

    private void distribuerCarte() {

        if (cartesRestantes <= 0) return;

        // déplacement
        TranslateTransition move = new TranslateTransition(Duration.millis(450), dosCarte);
        move.setByX(-250);
        move.setByY(-40);

        // rotation
        RotateTransition rotate = new RotateTransition(Duration.millis(450), dosCarte);
        rotate.setByAngle(-25);

        // disparition
        FadeTransition fade = new FadeTransition(Duration.millis(450), dosCarte);
        fade.setToValue(0);

        ParallelTransition animation = new ParallelTransition(move, rotate, fade);
        //déplacement + rotation + disparition

        animation.setOnFinished(e -> {

            cartesRestantes--;
            compteur.setText(cartesRestantes + "/" + totalCartes);

            // reset carte
            dosCarte.setTranslateX(0);
            dosCarte.setTranslateY(0);
            dosCarte.setRotate(0);
            dosCarte.setOpacity(1);
        });

        animation.play();
    }
}