package model;

public class Utilisateur {

		private String pseudo;
		private String mail;
		private DeckJoueur deck;
		
		
		public String getPseudo() {
			return pseudo;
		}
		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public DeckJoueur getDeck() {
			return deck;
		}
		public void setDeck(DeckJoueur deck) {
			this.deck = deck;
		}
		
}
