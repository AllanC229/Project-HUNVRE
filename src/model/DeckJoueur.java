package model;

import java.util.ArrayList;

public class DeckJoueur {
	
	private ArrayList<CarteJeu> deck;
	
	public DeckJoueur() {
	
			this.deck = new ArrayList<CarteJeu>();	
	}
	
	public ArrayList<CarteJeu> getListedeck() { 
		return this.deck;
	}
	/*public CarteJeu getCartedejeubyid(int id) {
		Cartedejeu.getId();
	}*/
	
	public void add(CarteJeu c) { // Surcharge de la méthode add() de ArrayList
		this.deck.add(c);
	}

}
