package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.DAOAcces;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.CarteJeu;
import model.DeckJoueur;
import view.ZoneMain;
import view.ZoneScore;
import model.Utilisateur;
import view.ZoneSeb;

public class ControleurPartie {
    
	//Début code Allan
		private ZoneSeb zoneSebAfficheCombinaison;
		private ZoneScore zoneAfficheScore;
		public ArrayList<Integer> cartesjouables = new ArrayList<Integer>();	//Ce tableau sert au calcul des scores : il contient les valeurs des cartes qu'on a selectionnées et est utilisé dans la fonction combinaisonactive
		public List<CarteJeu> cartesSelectionnees = new ArrayList<>(); //Definition d'un tableau qui contiendra les cartes qu'on a selectionnees; ce tableau ne sert qu'à vérifier qu'on a bien 5 cartes ou moins selectionnées
		public List<ImageView> cartesaffichees = new ArrayList<>();
		
	//Début des fonctions associées à la zoneSeb
		
		public ControleurPartie (ZoneSeb zoneSebAfficheCombinaison, ZoneScore zonescore) { //Le constructeur du controleurpartie, va probablement s'étoffer à mesure que le code se construit
			this.zoneSebAfficheCombinaison = zoneSebAfficheCombinaison;	
			this.zoneAfficheScore = zoneAfficheScore;
		}
		
		public String combinaisonactive() {	//Fonction qui permet de terminer la combinaison de poker avec les cartes selectionnées

		    String combinaison = "carte haute";

		    HashMap<Integer, Integer> valeurs = new HashMap<>();
		    HashMap<Integer, Integer> couleurs = new HashMap<>();

		    // Comptage
		    for (int carte : cartesjouables) {
		        int valeur = carte % 13;
		        int couleur = carte / 13;

		        valeurs.put(valeur, valeurs.getOrDefault(valeur, 0) + 1);
		        couleurs.put(couleur, couleurs.getOrDefault(couleur, 0) + 1);
		    }

		    boolean paire = false;
		    boolean brelan = false;
		    boolean carre = false;
		    int nbPaires = 0;

		    for (int count : valeurs.values()) {
		        if (count == 2) {
		            paire = true;
		            nbPaires++;
		        } else if (count == 3) {
		            brelan = true;
		        } else if (count == 4) {
		            carre = true;
		        }
		    }

		    // Détection couleur
		    boolean couleur = couleurs.containsValue(5);

		    // Détection suite
		    ArrayList<Integer> vals = new ArrayList<>(valeurs.keySet());
		    Collections.sort(vals);

		    boolean suite = false;

		    if (vals.size() == 5) {
		        suite = true;
		        for (int i = 0; i < vals.size() - 1; i++) {
		            if (vals.get(i) + 1 != vals.get(i + 1)) {
		                suite = false;
		                break;
		            }
		        }

		        // Cas spécial A-2-3-4-5
		        if (!suite && vals.contains(12)) { // As
		            ArrayList<Integer> special = new ArrayList<>(vals);
		            special.remove(Integer.valueOf(12));
		            special.add(-1);
		            Collections.sort(special);

		            suite = true;
		            for (int i = 0; i < special.size() - 1; i++) {
		                if (special.get(i) + 1 != special.get(i + 1)) {
		                    suite = false;
		                    break;
		                }
		            }
		        }
		    }

		    // Classement (ordre IMPORTANT)
		    if (suite && couleur) {
		        combinaison = "quinte flush";
		    } else if (carre) {
		        combinaison = "carré";
		    } else if (brelan && paire) {
		        combinaison = "full";
		    } else if (couleur) {
		        combinaison = "couleur";
		    } else if (suite) {
		        combinaison = "suite";
		    } else if (brelan) {
		        combinaison = "brelan";
		    } else if (nbPaires == 2) {
		        combinaison = "double paire";
		    } else if (paire) {
		        combinaison = "une paire";
		    }
		    
		    return combinaison;
		}
		
	    public void affichercombinaisonzoneseb(String combinaison) { //Permet d'afficher la chaine de caractères donnée en paramêtre dans la zone seb
	    	
	    	zoneSebAfficheCombinaison.getChildren().clear();
	    	HBox affiche = new HBox();
	    	affiche.setAlignment(Pos.CENTER);
	    	Label affichecombinaison = new Label(combinaison);
	    	
	    	affichecombinaison.setStyle("""
	    		    -fx-font-size: 28px;
	    		    -fx-font-weight: bold;
	    		    -fx-text-fill: linear-gradient(to right, #3a3a3a, #0f0f0f);
	    		    -fx-effect: dropshadow(gaussian, rgba(50, 0, 80, 0.7), 20, 0.9, 0, 0);
	    		""");
	    	
	    	affiche.getChildren().add(affichecombinaison);
	    	zoneSebAfficheCombinaison.getChildren().add(affiche);   	
	    }
		
		//Fin des fonctions associées à la zoneSeb
	    
	    //Debut des fonctions associées à la zonescore
	    

	    
	    //Fin des fonctions associées à la zonescore
		
		//Début des fonctions associées à la zoneMain
	    
	    
	public DeckJoueur chargernouveaudeck() {	//La fonction qui permet de chercher toutes les cartes de la BDD et de s'en servir pour crée une instance de l'objet deck
		
		   DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", ""); //Début de la requête SQL qui va chercher toutes les cartes dans la BDD
		   DeckJoueur deck = new DeckJoueur();
		   
		   try {
		   		
		   		Connection conn = dao.getConn();
		   		conn.setAutoCommit(false);

		   		String sql = "SELECT * FROM carte;"; //On prend toutes les cartes de la table carte
		   		
		   		
		   		PreparedStatement psDeck = conn.prepareStatement(sql);
		   		
		   		ResultSet rsDeck = psDeck.executeQuery();
		   		
		   		while (rsDeck.next()) {	 
		   			
		   			CarteJeu carte = new CarteJeu(rsDeck.getInt("id_carte"), //On remplit notre deck avec des instances de CarteJeu
		   								  rsDeck.getInt("valeur"), 
		   								  rsDeck.getString("recto"), 
		   								  rsDeck.getInt("ref_visuel"), 
		   								  rsDeck.getString("couleur"));  
		   			deck.ajoutercarte(carte);
		   		}
		   	}
		   		catch (SQLException e1) {
		   			e1.printStackTrace();
		   		} 
		   		
		   		dao.closeConnection();	//On ferme la connexion
		   		
		   		return deck;
	}
	
	public void jetercartes(List<ImageView> cartes, HBox main) {	//La fonction qui sert à supprimer les cartes sélectionnées
		
		for (ImageView carte : cartes) {
			main.getChildren().remove(carte);
		}
		cartes.clear();
		cartesSelectionnees.clear();
		System.out.println(cartes);
		zoneSebAfficheCombinaison.getChildren().clear(); //
	}
	
	public List<ImageView> tiragecartes(DeckJoueur deck, int nombretirage, int debuttirage) {		//La fonction qui permet de faire un tirage d'un certain nombre de cartes dans le deck, et de les stocker sous forme d'une liste d'imageview qui seront plus tard ajoutées à la HBox de ZoneMain
		
		List<ImageView> cartestirees = new ArrayList<>();	//La liste qui contiendra les cartes tirées et qui sera retournée à la fin de la fonction
		int n = debuttirage + nombretirage; //ça correspond à l'index jusqu'auquel on va tirer des cartes
		ZoneMain.setIndex(n); //Permet de garder une trace d'où on est rendu dans le tirage des cartes du deck
		for (int i= debuttirage; i < n; i++) {	//On rentre dans la boucle qui fait le tirage

		    CarteJeu cartejeu = (CarteJeu) deck.get(i);	//On récupère l'objet CarteJeu qui se trouve dans deck à la position i
		    
		    Image image = new Image(getClass().getResourceAsStream("/" + cartejeu.getRecto() + ".jpg")); //On récupère l'image recto associée à la carte
		    ImageView carte = new ImageView(image);			//Et on crée une ImageView qui s'appelle carte avec l'image qu'on à récupérée									
		    
		    carte.setFitWidth(80);
		    carte.setPreserveRatio(true);
		   
		    carte.setUserData(cartejeu);	//TRES IMPORTANT : ici on associe l'ImageView carte avec l'instance de l'objet cartejeu ; c'est ce qui va nous permettre plus loin de récupérer ces objets quand on va choisir la carte en cliquant dessus

		    carte.setOnMouseEntered(e -> carte.setTranslateY(-10));		//Suréleve la carte quand on passe la souris dessus
		    carte.setOnMouseExited(e -> {
		      
		    	if (!cartesSelectionnees.contains(carte.getUserData())) { 	//La redescend quand on retire la souris SI on n'a pas cliqué sur la carte
		            carte.setTranslateY(0);
		        }
		    });

		    carte.setOnMouseClicked(e -> {		//Au clic sur la carte
		    	
		    	System.out.println(carte);
		        ImageView source = (ImageView) e.getSource();
		        CarteJeu cartecliquee = (CarteJeu) source.getUserData();	//On crée une cartecliquee en dupliquant l'objet qu'on a associé à l'image

		        
		        if (cartesSelectionnees.contains(cartecliquee)) {	//Si le tableau cartesselectionnee contient déja cette carte

		            cartesSelectionnees.remove(cartecliquee);	//On la supprime du tableau (déselection)
		            source.setTranslateY(0);
		            source.setStyle("");
		            retirercartejouable(cartecliquee);
		            cartesaffichees.remove(carte);
		            affichercombinaisonzoneseb(combinaisonactive());	//Ca c'est tordu mais marrant : on appelle une fonction en lui donnant pour parametre le resultat d'une fonction directement appelée dans l'argument
		            System.out.println(combinaisonactive());

		        }
		       
		        else if (cartesSelectionnees.size() < 5) {	//Si le tableau ne contient pas la carte (elseif) on vérifie qu'on a bien selectionné moins de 6 cartes

		            cartesSelectionnees.add(cartecliquee); 	//Si oui, on ajoute la carte au tableau
		            ajoutercartejouable(cartecliquee);
		            affichercombinaisonzoneseb(combinaisonactive());
		            cartesaffichees.add(carte);
		            System.out.println(combinaisonactive());
		           	System.out.println("la carte cliquée est :"+ cartecliquee);	               
		            source.setStyle("-fx-effect: dropshadow(gaussian, purple, 10, 0.5, 0, 0);");	//Un petit effet pour indiquer que la carte est selectionnée

		        }
		        

		        System.out.println("Cartes sélectionnées : " + cartesSelectionnees.size());
		    });
			cartestirees.add(carte); 	//Une fois qu'on a défini tout ça pour UNE carte, on l'ajoute à la liste cartestirees
		}

		return cartestirees;
		
	}

    public void ajoutercartejouable (CarteJeu carte) {	//La fonction pour ajouter la carte selectionnée depuis ZoneMain au tableau
    
    	cartesjouables.add(Integer.parseInt(carte.getRecto()));   	
    	
    }
   
    public void retirercartejouable (CarteJeu carte) {	//La fonction pour retirer la carte selectionnée depuis ZoneMain au tableau
    	
    	cartesjouables.remove(Integer.valueOf(Integer.parseInt(carte.getRecto())));
    	
    }
    
    
    //Fin des fonctions associées à la zoneMain

	//Fin code Allan
    
    
    //Début code Jérome
    
    // Fonctions associées à la zoneMenu
    
    public static void sauvegarderPartie(Utilisateur joueur) {
    	int tailleDeck = joueur.getDeck().getListedeck().size();
		System.out.println(tailleDeck);
		
		System.out.println("Liste des cartes (si ça marche) :");
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");
		try {
			int idJoueur = 0;
			int idCarte = 0;
			int qteCarte[] = new int[52];
			
			for(int i = 0; i < 52; i++) qteCarte[i] = 0;
				
			for(int i = 0; i < tailleDeck; i++) {
				idCarte = joueur.getDeck().cherchercarte(i).getId();
				qteCarte[idCarte - 1] += 1;
			}
			
			PreparedStatement pstSauvegarde = dao.getConn().prepareStatement(
					"SELECT id_utilisateur FROM utilisateur WHERE mail = ?");
			pstSauvegarde.setString(1, ControleurConnexion.joueur.getMail());
			ResultSet rsSauvegarde = pstSauvegarde.executeQuery();
			while(rsSauvegarde.next()) {
				idJoueur = rsSauvegarde.getInt(1);
			}
			
			dao.getConn().setAutoCommit(false);
			PreparedStatement insertion = dao.getConn().prepareStatement("DELETE FROM deck_carte WHERE ref_utilisateur = ?");
			insertion.setInt(1, idJoueur);
			insertion.executeUpdate();
			
			for(int i = 0; i < 52; i++) {
				insertion = dao.getConn().prepareStatement("INSERT INTO deck_carte VALUES (?, ?, ?)");
				insertion.setInt(1, idJoueur);
				insertion.setInt(2, i + 1);
				insertion.setInt(3, qteCarte[i]);
				insertion.executeUpdate();
			}
			
			dao.getConn().commit();
			
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		dao.closeConnection();
    }
    
    //Fin des fonctions associées à la zoneMenu
    
    //Fin code Jérome
}
