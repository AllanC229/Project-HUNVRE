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

/**
 * ZoneSeb — Vue qui affiche la combinaison détectée et le détail du calcul.
 *
 * ATTENTION ÉQUIPE — 2026-04-13 - Vitally Lubin
 * Cette classe fait à la fois la détection des combinaisons ET le calcul du score.
 * Idéalement, le calcul du score devrait être dans ControleurPartie (pattern MVC).
 * TODO : déplacer le calcul du score vers ControleurPartie lors de la prochaine refactorisation.
 *
 * La colonne "recto" en BDD stocke un numéro unique de 2 à 53 :
 * - Trèfle  : 2  (deux) → 14 (As)
 * - Carreau : 15 (deux) → 27 (As)
 * - Cœur    : 28 (deux) → 40 (As)
 * - Pique   : 41 (deux) → 53 (As)
 *
 * Pour détecter les combinaisons, on utilise recto % 13 :
 * - 2→2, 3→3, ..., 9→9, 10→10, Valet→11, Dame→12, Roi→0, As→1
 * - L'As (valeur 1) est bilatéral : il peut être bas (As-2-3-4-5) ou haut (10-V-D-R-A)
 *
 * La colonne "valeur" en BDD stocke la valeur en points pour le calcul du score :
 * - As=11, 2 à 10=valeur nominale, Valet/Dame/Roi=10
 * - Cette valeur est utilisée dans getValeurbase() et ne sert PAS à la détection des combinaisons
 */
public class ZoneSeb extends Pane {

    // 2026-04-13 - Vitally Lubin
    // Labels d'affichage de la combinaison détectée et du détail du calcul
    private Label labelCombinaison;
    private Label labelCalcul;

    // ATTENTION ÉQUIPE — 2026-04-13 - Vitally Lubin
    // Le constructeur de ZoneSeb n'a pas de paramètre.
    // C'est ControleurPartie qui fait le lien entre ZoneSeb et ZoneScore
    // en passant zoneScore à calculerScore() à chaque coup joué.
    public ZoneSeb() {
        labelCombinaison = new Label("Combinaison : -");
        labelCalcul = new Label("Calcul : -");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(labelCombinaison, labelCalcul);

        // Centrage horizontal et vertical dans le Pane
        vbox.layoutXProperty().bind(this.widthProperty().subtract(vbox.widthProperty()).divide(2));
        vbox.layoutYProperty().bind(this.heightProperty().subtract(vbox.heightProperty()).divide(2));

        this.getChildren().add(vbox);
    }

    /**
     * Reçoit les cartes jouées et ZoneScore depuis ControleurPartie.
     * Détecte la combinaison, calcule le score, met à jour le joueur en mémoire,
     * affiche le résultat dans ZoneSeb et met à jour ZoneScore.
     *
     * Appelée par ControleurPartie au clic du bouton Jouer dans ZoneMain.
     *
     * TODO (refactorisation MVC) : le calcul du score et la mise à jour de joueur.setScore()
     * devraient être dans ControleurPartie, pas dans une vue.
     * ZoneSeb devrait uniquement détecter la combinaison et afficher.
     */
    public void calculerScore(List<CarteJeu> cartes, ZoneScore zoneScore) {

        // On calcule la valeur de chaque carte pour la détection : recto % 13
        // 2→2, 3→3, ..., 9→9, 10→10, Valet→11, Dame→12, Roi→0, As→1
        List<Integer> valeurs = new ArrayList<>();
        for (CarteJeu carte : cartes) {
            valeurs.add(Integer.parseInt(carte.getRecto()) % 13);
        }

        // Tri des valeurs avant détection — indépendant de l'ordre de sélection du joueur
        Collections.sort(valeurs);

        // Somme des valeurs en points de chaque carte (colonne "valeur" en BDD)
        // utilisée dans la formule : (chips + sommeValeurs) × multi
        int sommeValeurs = 0;
        for (CarteJeu carte : cartes) {
            sommeValeurs += carte.getValeurbase();
        }

        // Détection de la combinaison dans l'ordre du plus fort au plus faible.
        // Dès qu'une combinaison est trouvée on sort de la cascade.
        // TODO : externaliser chips et multi dans la BDD table combinaison
        int chips, multi;
        String nomCombinaison;

        if      (estQuinteFlushRoyale(cartes, valeurs)) { chips = 100; multi = 8; nomCombinaison = "Quinte Flush Royale"; }
        else if (estQuinteFlush(cartes, valeurs))        { chips = 100; multi = 8; nomCombinaison = "Quinte Flush"; }
        else if (estCarre(valeurs))                      { chips = 60;  multi = 7; nomCombinaison = "Carré"; }
        else if (estMainPleine(valeurs))                 { chips = 40;  multi = 4; nomCombinaison = "Main Pleine"; }
        else if (estCouleur(cartes))                     { chips = 35;  multi = 4; nomCombinaison = "Couleur"; }
        else if (estSuite(valeurs))                      { chips = 30;  multi = 4; nomCombinaison = "Suite"; }
        else if (estBrelan(valeurs))                     { chips = 30;  multi = 3; nomCombinaison = "Brelan"; }
        else if (estDoublePaire(valeurs))                { chips = 20;  multi = 2; nomCombinaison = "Double Paire"; }
        else if (estPaire(valeurs))                      { chips = 10;  multi = 2; nomCombinaison = "Paire"; }
        else                                             { chips = 5;   multi = 1; nomCombinaison = "Carte Haute"; }

        // Calcul du score du coup : (chips de base + somme des valeurs des cartes) × multiplicateur
        int scoreCombo = (chips + sommeValeurs) * multi;

        // Mise à jour du score total du joueur en mémoire
        // On additionne le score existant avec le score de ce coup
        ControleurConnexion.joueur.setScore(
            ControleurConnexion.joueur.getScore() + scoreCombo
        );

        // Affichage dans ZoneSeb : nom de la combinaison et détail du calcul
        labelCombinaison.setText("Combinaison : " + nomCombinaison);
        labelCalcul.setText("Calcul : (" + chips + " + " + sommeValeurs + ") x " + multi);

        // Mise à jour de ZoneScore :
        // - scoreCombo : score de ce coup (affiché en "score blinde")
        // - joueur.getScore() : score total cumulé
        // TODO : "score blinde" est un mauvais nom — c'est le score du coup, pas l'objectif blinde
        // TODO : implémenter la vérification de l'objectif blinde dans ControleurPartie
        zoneScore.majScore(scoreCombo, ControleurConnexion.joueur.getScore());

        System.out.println(nomCombinaison + " - Score combo : " + scoreCombo);
        System.out.println("Score total : " + ControleurConnexion.joueur.getScore());
    }

    // Compte combien de fois chaque valeur apparaît dans la main du joueur.
    // Utilisée par estCarre(), estMainPleine(), estBrelan(), estDoublePaire(), estPaire().
    private Map<Integer, Integer> compterOccurrences(List<Integer> valeurs) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int v : valeurs) {
            occurrences.put(v, occurrences.getOrDefault(v, 0) + 1);
        }
        return occurrences;
    }

    // Vérifie que toutes les cartes sont de la même couleur.
    // On prend la couleur de la première carte et on compare toutes les autres.
    private boolean estCouleur(List<CarteJeu> cartes) {
        String couleur = cartes.get(0).getCouleur();
        for (CarteJeu carte : cartes) {
            if (!carte.getCouleur().equals(couleur)) return false;
        }
        return true;
    }

    /**
     * Détecte si les cartes forment une suite.
     * L'As est bilatéral : il peut être en bas (As-2-3-4-5) ou en haut (10-V-D-R-A).
     *
     * Avec recto % 13 : 2→2, 3→3, ..., Roi→0, As→1
     * - Suite basse As-2-3-4-5 : valeurs {1, 2, 3, 4, 5} → consécutive naturellement
     * - Suite haute 10-V-D-R-A : valeurs {10, 11, 12, 0, 1} → cas particulier traité ci-dessous
     *
     * On utilise le for classique (avec index i) car on compare chaque carte
     * avec la précédente (i - 1) — impossible avec un for-each.
     *
     * 2026-04-23 - Vitally Lubin
     * Correction : ancienne version utilisait recto % 13 sans gérer l'As bilatéral.
     * La suite haute 10-V-D-R-A n'était jamais détectée correctement.
     */
    private boolean estSuite(List<Integer> valeurs) {
        // Cas normal : on vérifie que chaque carte vaut exactement 1 de plus que la précédente
        // Couvre aussi la suite basse As-2-3-4-5 car As=1 et 2=2 : consécutifs
        boolean suiteNormale = true;
        for (int i = 1; i < valeurs.size(); i++) {
            if (valeurs.get(i) != valeurs.get(i - 1) + 1) {
                suiteNormale = false;
                break; // Inutile de continuer, on sait déjà que ce n'est pas une suite normale
            }
        }
        if (suiteNormale) return true;

        // Cas particulier : suite haute 10-Valet-Dame-Roi-As
        // Avec recto % 13, ces cartes donnent les valeurs {10, 11, 12, 0, 1}
        // Elles ne sont pas consécutives dans l'ordre normal,
        // donc on les compare directement à la liste attendue après tri des deux côtés
        List<Integer> suiteHaute = new ArrayList<>(List.of(0, 1, 10, 11, 12));
        List<Integer> valeursCopie = new ArrayList<>(valeurs);
        Collections.sort(valeursCopie);
        Collections.sort(suiteHaute);
        return valeursCopie.equals(suiteHaute);
    }

    /**
     * Détecte la Quinte Flush Royale : toutes les cartes de même couleur
     * ET elles forment la suite haute 10-Valet-Dame-Roi-As.
     * Avec recto % 13, ces cartes donnent les valeurs {0, 1, 10, 11, 12}.
     *
     * 2026-04-23 - Vitally Lubin
     * Correction : ancienne version utilisait valeurs.contains(0) pour détecter l'As,
     * ce qui était incorrect — 0 correspond au Roi, pas à l'As.
     */
    private boolean estQuinteFlushRoyale(List<CarteJeu> cartes, List<Integer> valeurs) {
        List<Integer> suiteHaute = new ArrayList<>(List.of(0, 1, 10, 11, 12));
        List<Integer> valeursCopie = new ArrayList<>(valeurs);
        Collections.sort(valeursCopie);
        Collections.sort(suiteHaute);
        return estCouleur(cartes) && valeursCopie.equals(suiteHaute);
    }

    // Quinte Flush : même couleur + suite (couvre aussi la suite haute grâce à estSuite())
    private boolean estQuinteFlush(List<CarteJeu> cartes, List<Integer> valeurs) {
        return estCouleur(cartes) && estSuite(valeurs);
    }

    // Carré : 4 cartes de même valeur
    private boolean estCarre(List<Integer> valeurs) {
        return compterOccurrences(valeurs).containsValue(4);
    }

    // Main Pleine : un brelan (3 cartes identiques) + une paire (2 cartes identiques)
    private boolean estMainPleine(List<Integer> valeurs) {
        Map<Integer, Integer> occ = compterOccurrences(valeurs);
        return occ.containsValue(3) && occ.containsValue(2);
    }

    // Brelan : exactement 3 cartes de même valeur
    private boolean estBrelan(List<Integer> valeurs) {
        return compterOccurrences(valeurs).containsValue(3);
    }

    // Double Paire : deux fois 2 cartes de même valeur
    private boolean estDoublePaire(List<Integer> valeurs) {
        int paires = 0;
        for (int occ : compterOccurrences(valeurs).values()) {
            if (occ == 2) paires++;
        }
        return paires == 2;
    }

    // Paire : exactement 2 cartes de même valeur (une seule paire)
    private boolean estPaire(List<Integer> valeurs) {
        int paires = 0;
        for (int occ : compterOccurrences(valeurs).values()) {
            if (occ == 2) paires++;
        }
        return paires == 1;
    }
}
