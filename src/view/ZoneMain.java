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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.CarteJeu;
import model.DeckJoueur;

public class ZoneMain extends Pane {
	
	private HBox mainCartes;
	
	public ZoneMain() {
	
	Label test = new Label("hunvre");
	this.getChildren().add(test);

	
	//Debut code d'Allan
	
	
	
	//DeckJoueur deck = new DeckJoueur();

	List<CarteJeu> deck = new ArrayList<>();
	ArrayList cartestirees = new ArrayList<>();

	
	   DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", ""); 
	   	try {
	   		
	   		Connection conn = dao.getConn();
	   		conn.setAutoCommit(false);

	   		String sql = "SELECT * FROM carte;";
	   		
	   		PreparedStatement psDeck = conn.prepareStatement(sql);
	   		
	   		ResultSet rsDeck = psDeck.executeQuery();
	   		
	   		while (rsDeck.next()) {	 
	   			
	   			deck.add(new CarteJeu(rsDeck.getInt("id_carte"), 
	   								  rsDeck.getInt("valeur"), 
	   								  rsDeck.getInt("recto"), 
	   								  rsDeck.getInt("ref_visuel"), 
	   								  rsDeck.getString("couleur")));   			
	   		}
	   	}
	   		catch (SQLException e1) {
	   			e1.printStackTrace();
	   		} 
	   		
	   		dao.closeConnection();
	
	

	Collections.shuffle(deck);
	
	mainCartes = new HBox();
	mainCartes.setSpacing(10);
	mainCartes.setAlignment(Pos.CENTER);
	
	mainCartes.setLayoutX(20);
	mainCartes.setLayoutY(20);
	
	this.getChildren().add(mainCartes);

	for (int i= 0; i <= 7; i++) {
	System.out.println(deck.get(i).getCouleur()); // tirage
	cartestirees.add(deck.get(i).getId());
	Image image = new Image(getClass().getResourceAsStream("/"+ deck.get(i).getId() +".jpg"));
	ImageView carte = new ImageView(image);
	
	carte.setFitWidth(80);     
    carte.setPreserveRatio(true);
    
    carte.setOnMouseEntered(e -> carte.setTranslateY(-10));
    carte.setOnMouseExited(e -> carte.setTranslateY(0));
    
   /* carte.setOnAction(e -> {
    	cartechoisies.add(carte.getId());   	
    });*/
    
    
	mainCartes.getChildren().add(carte);
	
	}
	
	//Fin code d'Allan

}
}
