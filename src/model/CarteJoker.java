package model;

public class CarteJoker {
	
		private String nom;
		private int id;
		private float multscore;
		private int jetonsbonus;
		
		public CarteJoker(String nom, int id, float multscore, int jetonbonus) {
			this.nom = nom;
			this.id = id;
			this.multscore = multscore;
			this.jetonsbonus = jetonbonus;
		}
		
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public float getMultscore() {
			return multscore;
		}
		public void setMultscore(float multscore) {
			this.multscore = multscore;
		}
		public int getJetonsbonus() {
			return jetonsbonus;
		}
		public void setJetonsbonus(int jetonsbonus) {
			this.jetonsbonus = jetonsbonus;
		}
}
