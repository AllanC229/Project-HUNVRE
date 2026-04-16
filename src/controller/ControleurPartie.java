package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.CarteJeu;
import view.ZoneSeb;

public class ControleurPartie {
    
	//Début code Allan
		private ZoneSeb zoneSebAfficheCombinaison;
		private ArrayList<Integer> cartesselectionnees = new ArrayList<Integer>();

		public ControleurPartie (ZoneSeb zoneSebAfficheCombinaison) { //Le constructeur du controleurpartie, va probablement s'étoffer à mesure que le code se construit
			this.zoneSebAfficheCombinaison = zoneSebAfficheCombinaison;			
		}
    
		public String combinaisonactive() { //Fonction qui permet de définir la combinaison actuelle en fonction des cartes que l'on a selectionnées
    	
	    	String combinaison = null;
	    	HashMap<Integer, Integer> calculcombinaison = new HashMap<>();
	    	
	    	for (int carte : cartesselectionnees) {
	    		int valeur = carte%13;
	    		calculcombinaison.put(valeur, calculcombinaison.getOrDefault(valeur,  0) +1);   		
    	}
    	
    	boolean paire = false;
    	boolean brelan = false;
    	boolean carre = false;
    	int nbpaire = 0;
    	
    	for (int calcul : calculcombinaison.values()) {
    		if (calcul == 2) {
    			paire = true;
    			nbpaire++;
    			combinaison = "une paire";
    		}
    		else if (calcul == 3) {
    			brelan = true;
    			combinaison = "brelan";
    		}
    		else if (calcul == 4) {
    			carre = true;
    			combinaison = "carré";
    		}
    	}
    	 	
    	return combinaison;
    }

    public void ajoutercarteselection (CarteJeu carte) {	//La fonction pour ajouter la carte selectionnée depuis ZoneMain au tableau
    
    	cartesselectionnees.add(Integer.parseInt(carte.getRecto()));
    	//cartespourcombinaison.put(carte.getRecto(), (Integer.parseInt(carte.getRecto()))%13);
    	//System.out.println(cartespourcombinaison);
    	
    }
   
    public void retirercarteselection (CarteJeu carte) {	//La fonction pour retirer la carte selectionnée depuis ZoneMain au tableau
    	
    	cartesselectionnees.remove(Integer.valueOf(Integer.parseInt(carte.getRecto())));
    	//cartespourcombinaison.remove(carte.getRecto());
    	//System.out.println(cartespourcombinaison);
    	
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
	//Fin code Allan
    
}
