package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.CarteJeu;
import view.ZoneSeb;

public class ControleurPartie {
    
	//Début code Allan
		private ZoneSeb zoneSebAfficheCombinaison;
		private ArrayList<Integer> cartesselectionnees = new ArrayList<Integer>();
	//	private HashMap<String, Integer> cartespourcombinaison = new HashMap<String, Integer>();

		public ControleurPartie (ZoneSeb zoneSebAfficheCombinaison) { //Le constructeur du controleurpartie, va probablement s'étoffer à mesure que le code se construit
			this.zoneSebAfficheCombinaison = zoneSebAfficheCombinaison;
			
	}
 
 
    public void carteCliquee(CarteJeu carte) { //La methode carteCliquee qui permet d'appeler la fonction afficherCarte de la ZoneSeb
    											//A terme, elle sera remplacée par la fonction "afficher combinaison"
      
    	System.out.println("Carte reçue : " + carte.getRecto() +"");
        zoneSebAfficheCombinaison.afficherCarte(carte);
        System.out.println(cartesselectionnees);
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
	//Fin code Allan
    
}
