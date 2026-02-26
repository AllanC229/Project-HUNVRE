package model;


public class Utilisateur {

		private String pseudo;
		private String mail;
		private DeckJoueur deck;
		private String role = "joueur"; 
		
		public Utilisateur(String pseudo, String mail, DeckJoueur deck, String role) {
			this.pseudo = pseudo;
			this.mail = mail;
			this.deck = deck;
			this.role = role;
		}
		
		
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


		public String getRole() {
			return role;
		}


		public void setRole(String role) {
			this.role = role;
		}
		
}
