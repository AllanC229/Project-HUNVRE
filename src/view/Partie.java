package view;

// --- javafx.* ---
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

// --- Classes du projet ---
import controller.ControleurPartie;

public class Partie extends Scene {

    public Partie() {
        super(new GridPane(), 1200, 900);
        GridPane partie = (GridPane) getRoot();

        // TODO : retirer avant soutenance — affiche les bordures de la grille (debug uniquement)
        // partie.setGridLinesVisible(true);

        // Grille 3 colonnes : menu (17%) | zone centrale (66%) | score/deck (17%)
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(17);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(66);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(17);

        // Grille 3 lignes : haut (29%) | centre (42%) | bas (29%)
        RowConstraints l1 = new RowConstraints();
        l1.setPercentHeight(29);
        RowConstraints l2 = new RowConstraints();
        l2.setPercentHeight(42);
        RowConstraints l3 = new RowConstraints();
        l3.setPercentHeight(29);

        partie.getColumnConstraints().addAll(c1, c2, c3);
        partie.getRowConstraints().addAll(l1, l2, l3);

        // Ligne du haut : titre (toute la largeur) + menu (colonne 0) + score (colonne 2)
        partie.add(new ZoneTitre(), 0, 0, 3, 1);
        partie.add(new ZoneMenu(), 0, 0);

        // 2026-04-13 - Vitally Lubin
        // ZoneScore instanciée dans une variable pour être passée à ControleurPartie
        ZoneScore zoneScore = new ZoneScore();
        partie.add(zoneScore, 2, 0);

        // Ligne du milieu : ZoneCentrale sur toute la largeur
        // Ancien code — ZoneCentrale instanciée anonymement, sans référence pour ControleurPartie
        // partie.add(new ZoneCentrale(), 0, 1, 3, 1);

        // 2026-04-23 - Vitally Lubin
        // ZoneCentrale instanciée dans une variable pour être passée à ControleurPartie
        ZoneCentrale zoneCentrale = new ZoneCentrale();
        partie.add(zoneCentrale, 0, 1, 3, 1);

        // Ligne du bas : ZoneSeb (col 0) | ZoneMain (col 1) | ZoneDeck (col 2)
        // 2026-04-13 - Vitally Lubin
        // ZoneSeb instanciée dans une variable pour être passée à ControleurPartie
        ZoneSeb zoneSeb = new ZoneSeb();
        partie.add(zoneSeb, 0, 2);

        ZoneDeck zoneDeck = new ZoneDeck();
        partie.add(zoneDeck, 2, 2);

        // 2026-04-13 - Vitally Lubin
        // ControleurPartie instancié avec toutes les zones dont il a besoin
        // C'est lui qui fait le lien entre ZoneMain, ZoneSeb, ZoneScore, ZoneCentrale, ZoneDeck
        ControleurPartie controleurPartie = new ControleurPartie(zoneSeb, zoneScore, zoneCentrale, zoneDeck);

        partie.add(new ZoneMain(controleurPartie), 1, 2);
    }
}
