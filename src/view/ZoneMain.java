package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connection.DAOAcces;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.ControleurConnexion;
import controller.ControleurPartie;
import javafx.scene.control.Button;
import model.CarteJeu;
import model.DeckJoueur;

public class ZoneMain extends Pane {
	
	private ControleurPartie controleurpartie;
	public void setControleur(ControleurPartie controleurpartie) { //La fonction qui permet d'associer  la zonemain le controleurpartie instancié dans la vue Partie
		this.controleurpartie = controleurpartie;
	}
	
	private HBox mainCartes;
	
	public ZoneMain() {

		
		
		Image bandofond = new Image(getClass().getResource("/BandobaHunvre3.jpg").toExternalForm());

		BackgroundImage bg = new BackgroundImage(
		        bandofond,
		        BackgroundRepeat.NO_REPEAT,
		        BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.CENTER,
		        new BackgroundSize(
		                100, 100,
		                true, true,
		                true, false
		        )
		);

		this.setBackground(new Background(bg));
	
	
	// Début code Vitally - Boutons Jouer et Bouton Jeter
	
		// 	--- Boutons ---
		Button jouer = new Button("Jouer");
		Button jeter = new Button("Jeter");
		     
		// --- Taille max des composants ---
		jouer.setMaxWidth(200);
		jeter.setMaxWidth(200);
		
		// --- Événements ---
        jouer.setOnAction(e -> {
            // à écrire
        });

        jeter.setOnAction(e -> {
        	// à écrire
        });

 	// Fin code Vitally - Boutons Jouer et Bouton Jeter

	
	//Debut code d'Allan
	
	
	
	//DeckJoueur deck = new DeckJoueur();

	List<CarteJeu> deck = new ArrayList<>(); //On instancie une entité deck

	
	   DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", ""); //Début de la requête SQL qui va chercher toutes les cartes dans la BDD
	   	try {
	   		
	   		Connection conn = dao.getConn();
	   		conn.setAutoCommit(false);

	   		String sql = "SELECT * FROM carte;";	//On prend toutes les cartes de la table carte
	   		
	   		PreparedStatement psDeck = conn.prepareStatement(sql);
	   		
	   		ResultSet rsDeck = psDeck.executeQuery();
	   		
	   		while (rsDeck.next()) {	 
	   			
	   			deck.add(new CarteJeu(rsDeck.getInt("id_carte"), 	//On remplit notre deck avec des instances de CarteJeu
	   								  rsDeck.getInt("valeur"), 
	   								  rsDeck.getString("recto"), 
	   								  rsDeck.getInt("ref_visuel"), 
	   								  rsDeck.getString("couleur")));   			
	   		}
	   	}
	   		catch (SQLException e1) {
	   			e1.printStackTrace();
	   		} 
	   		
	   		dao.closeConnection();	//On ferme la connexion
	
	

	Collections.shuffle(deck);	//Mélange aléatoirement les cartes du deck : notre entité deck contient maintenant les CarteJeu dans un ordre aléatoire
	
	mainCartes = new HBox(); //Lignes 72 à 79 : on définit les parametres de la HBox qui contiendra l'affichage de nos cartes
	mainCartes.setSpacing(10);
	mainCartes.setAlignment(Pos.CENTER);
	
	mainCartes.setLayoutX(20);
	mainCartes.setLayoutY(20);
	
	this.getChildren().add(mainCartes);
	
	List<CarteJeu> cartesSelectionnees = new ArrayList<>(); //Definition d'un tableau qui contiendra les cartes qu'on a selectionnees
	
	for (int i = 0; i < 8; i++) {	//On rentre dans la boucle qui fait le tirage

	    CarteJeu cartejeu = deck.get(i);	//On récupère l'objet CarteJeu qui se trouve dans deck à la position i

	    Image image = new Image(getClass().getResourceAsStream("/" + cartejeu.getRecto() + ".jpg")); //On récupère l'image recto associée à la carte
	    ImageView carte = new ImageView(image);				//Et on crée une ImageView qui s'appelle carte avec l'image qu'on à récupérée									

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
	    	
	    	
	        ImageView source = (ImageView) e.getSource();
	        CarteJeu cartecliquee = (CarteJeu) source.getUserData();	//On crée une cartecliquee en dupliquant l'objet qu'on a associé à l'image

	        
	        if (cartesSelectionnees.contains(cartecliquee)) {	//Si le tableau cartesselectionnee contient déja cette carte

	            cartesSelectionnees.remove(cartecliquee);	//On la supprime du tableau (déselection)
	            source.setTranslateY(0);
	            source.setStyle("");
	            controleurpartie.retirercarteselection(cartecliquee);
	            controleurpartie.affichercombinaisonzoneseb(controleurpartie.combinaisonactive());	//Ca c'est tordu mais marrant : on appelle une fonction en lui donnant pour parametre le resultat d'une fonction directement appelée dans l'argument
	            System.out.println(controleurpartie.combinaisonactive());

	        }
	       
	        else if (cartesSelectionnees.size() < 5) {	//Si le tableau ne contient pas la carte (elseif) on vérifie qu'on a bien selectionné moins de 6 cartes

	            cartesSelectionnees.add(cartecliquee); 	//Si oui, on ajoute la carte au tableau
	            controleurpartie.ajoutercarteselection(cartecliquee);
	            controleurpartie.affichercombinaisonzoneseb(controleurpartie.combinaisonactive());
	            System.out.println(controleurpartie.combinaisonactive());
	               
	            source.setStyle("-fx-effect: dropshadow(gaussian, purple, 10, 0.5, 0, 0);");	//Un petit effet pour indiquer que la carte est selectionnée

	        }
	        

	        System.out.println("Cartes sélectionnées : " + cartesSelectionnees.size());
	    });

	    mainCartes.getChildren().add(carte); 	//Une fois qu'on a défini tout ça pour UNE carte, on l'ajoute à la HBox mainCartes
	}	//Fin de la boucle
	
	
}
	
	//Fin code d'Allan

}

