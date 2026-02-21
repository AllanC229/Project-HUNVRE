package model;

public class CarteModif {
	String nom;
	int id;
	int valeur;
	String couleur;
	String rareté;
	
	public CarteModif(String nom, int id, int valeur, String couleur, String rareté) {
		this.nom = nom;
		this.id = id;
		this.valeur = valeur;
		this.couleur = couleur;
		this.rareté = rareté;
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
		public int getValeur() {
			return valeur;
		}
		public void setValeur(int valeur) {
			this.valeur = valeur;
		}
		public String getCouleur() {
			return couleur;
		}
		public void setCouleur(String couleur) {
			this.couleur = couleur;
		}
		public String getRareté() {
			return rareté;
		}
		public void setRareté(String rareté) {
			this.rareté = rareté;
		}
	}


