package controller;

import model.CarteJeu;
import view.ZoneSeb;

public class ControleurPartie {
	
	/*private ControleurPartie controleurPartie;

	public void setControleurPartie(ControleurPartie cp) {
	    this.controleurPartie = cp;
	}*/
	
    private ZoneSeb zoneSeb = new ZoneSeb(); //On instancie la ZoneSeb

    public ControleurPartie() {
       // this.zoneSeb = zoneSeb;
    }
    public void carteCliquee(CarteJeu carte) { //La methode carteCliquee qui permet d'appeler la fonction afficherCarte de la ZoneSeb

        System.out.println("Carte reçue : " + carte.getRecto() +"");

        zoneSeb.afficherCarte(carte);
    }

   
	
}
