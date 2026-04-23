package view;

// --- javafx.* ---
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

// --- Classes du projet ---
import controller.ControleurConnexion;

/**
 * ZoneScore — Vue qui affiche le score du coup en cours et le score total.
 *
 * 2026-04-13 - Vitally Lubin
 * Cette classe était vide dans le code du 12 avril.
 *
 * TODO : ajouter un label "Objectif blinde : X"
 * La valeur cible est stockée dans la colonne "blinde" de la table utilisateur en BDD.
 * Elle doit être chargée à l'initialisation de la partie
 * et comparée à joueur.getScore() après chaque coup.
 * La vérification doit se faire dans ControleurPartie.jouer(), pas ici.
 *
 * TODO : renommer labelScoreBlinde en labelScoreCoup — "score blinde" est trompeur,
 * c'est le score du coup joué, pas l'objectif blinde à atteindre.
 */
public class ZoneScore extends Pane {

    // Labels d'affichage
    private Label labelScoreBlinde; // Score du coup qui vient d'être joué
    private Label labelScoreTotal;  // Score total cumulé depuis le début de la partie

    public ZoneScore() {
        labelScoreBlinde = new Label("Score blinde : 0");

        // Au lancement de la partie, le score total est 0 (ou le score existant si reprise)
        labelScoreTotal = new Label("Score total : " + ControleurConnexion.joueur.getScore());

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(labelScoreBlinde, labelScoreTotal);

        // Centrage horizontal et vertical dans le Pane
        vbox.layoutXProperty().bind(this.widthProperty().subtract(vbox.widthProperty()).divide(2));
        vbox.layoutYProperty().bind(this.heightProperty().subtract(vbox.heightProperty()).divide(2));

        this.getChildren().add(vbox);
    }

    // Mise à jour de l'affichage — appelée par ControleurPartie après chaque combinaison jouée.
    // scoreCombo : score du coup qui vient d'être joué
    // scoreTotal  : score total cumulé depuis le début de la partie
    public void majScore(int scoreCombo, int scoreTotal) {
        labelScoreBlinde.setText("Score blinde : " + scoreCombo);
        labelScoreTotal.setText("Score total : " + scoreTotal);
    }
}
