package controller;

import model.CarteJeu;
import view.ZoneSeb;

public class ControleurPartie {
	
	//Récupère les cartes cliquées puis les envoie vers la ZoneSeb qui s'occupe de calcul les combinaisons

    private ZoneSeb zoneSeb;

    public ControleurPartie(ZoneSeb zoneSeb) {
        this.zoneSeb = zoneSeb;
    }

    public void recevoirCarteCliquee(CarteJeu carteCliquee) {
        zoneSeb.afficherCarte(carteCliquee);
    }
	
}
