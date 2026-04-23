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

	//Fin code Allan
    
    
    //Début code Jérome
    
    // Fonctions associées à la zoneMenu
    
    public static void sauvegarderPartie(Utilisateur joueur) {	//La fonction pour sauvegarder le deck du joueur dans la base de données
    	int tailleDeck = joueur.getDeck().getListedeck().size();	//On récupère le nombre de cartes dans le deck
		System.out.println(tailleDeck);
		
		System.out.println("Liste des cartes (si ça marche) :");
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");	//Connexion à la bdd
		try {
			int idJoueur = 0;
			int idCarte = 0;
			int qteCarte[] = new int[52]; //Un tableau de 52 int, un pour chaque carte de base
			
			for(int i = 0; i < 52; i++) qteCarte[i] = 0; //On s'assure que le compte de chaque carte de base commence bien à 0
				
			for(int i = 0; i < tailleDeck; i++) {	//Boucle qui ajoute 1 à la quantité d'une carte (
				idCarte = joueur.getDeck().cherchercarte(i).getId();
				qteCarte[idCarte - 1] += 1;
			}
			
			PreparedStatement pstSauvegarde = dao.getConn().prepareStatement(
					"SELECT id_utilisateur FROM utilisateur WHERE mail = ?");	//Récupération de l'id du joueur actuel
			pstSauvegarde.setString(1, ControleurConnexion.joueur.getMail());
			ResultSet rsSauvegarde = pstSauvegarde.executeQuery();
			while(rsSauvegarde.next()) {
				idJoueur = rsSauvegarde.getInt(1);
			}
			
			dao.getConn().setAutoCommit(false);	//Désactivation de l'auto-commit, afin de pouvoir préparer plusieurs requêtes avant envoi
			PreparedStatement insertion = dao.getConn().prepareStatement("DELETE FROM deck_carte WHERE ref_utilisateur = ?");	//On supprime l'ancien deck sauvegardé
			insertion.setInt(1, idJoueur);
			insertion.executeUpdate();
			
			/*
			 * La boucle permet de préparer une requête pour chaque carte de base.
			 * On insère dans la table deck_carte un enregistrement avec comme valeurs :
			 * - l'id du joueur
			 * - l'id de la carte
			 * - le nombre d'exemplaires de la carte
			 */
			for(int i = 0; i < 52; i++) {
				insertion = dao.getConn().prepareStatement("INSERT INTO deck_carte VALUES (?, ?, ?)");
				insertion.setInt(1, idJoueur);
				insertion.setInt(2, i + 1);
				insertion.setInt(3, qteCarte[i]);
				insertion.executeUpdate();
			}
			
			dao.getConn().commit();	//Envoie en une seule fois toutes les requêtes péparées
			
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		dao.closeConnection();
    }
    
    //Fin des fonctions associées à la zoneMenu
    
    //Fin code Jérome
    
    
}
