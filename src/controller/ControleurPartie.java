package controller;

// --- java.* ---
import java.util.List;

// --- Classes du projet ---
import model.CarteJeu;
import view.ZoneCentrale;
import view.ZoneDeck;
import view.ZoneScore;
import view.ZoneSeb;

/**
 * ControleurPartie — Contrôleur central qui relie toutes les zones de Partie.java.
 *
 * 2026-04-13 - Vitally Lubin
 * C'est lui qui fait le lien entre ZoneMain, ZoneSeb, ZoneScore, ZoneCentrale, ZoneDeck.
 * Instancié dans Partie.java et passé à ZoneMain qui l'appelle au clic des boutons.
 *
 * TODO : après chaque appel à calculerScore(), vérifier si joueur.getScore() >= objectif blinde.
 * Si oui → déclencher la victoire de la blinde (changer de scène ou passer à la blinde suivante).
 * La valeur cible de la blinde est dans la colonne "blinde" de la table utilisateur en BDD.
 */
public class ControleurPartie {

    private ZoneSeb zoneSeb;
    private ZoneScore zoneScore;
    private ZoneCentrale zoneCentrale;
    private ZoneDeck zoneDeck;

    public ControleurPartie(ZoneSeb zoneSeb, ZoneScore zoneScore,
                            ZoneCentrale zoneCentrale, ZoneDeck zoneDeck) {
        this.zoneSeb = zoneSeb;
        this.zoneScore = zoneScore;
        this.zoneCentrale = zoneCentrale;
        this.zoneDeck = zoneDeck;
    }

    // Appelée par ZoneMain au clic du bouton Jouer.
    // Déclenche le calcul de la combinaison et la mise à jour de ZoneScore.
    // TODO : vérifier l'objectif blinde après le calcul (voir javadoc de la classe)
    public void jouer(List<CarteJeu> cartesSelectionnees) {
        zoneSeb.calculerScore(cartesSelectionnees, zoneScore);
    }

    // Appelée par ZoneMain au clic du bouton Jeter.
    // TODO : implémenter la logique de défausse
    public void jeter(List<CarteJeu> cartesSelectionnees) {
        // TODO
    }
}
