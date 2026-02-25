package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.MainApp;
import connection.DAOAcces;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import view.TableauScore;


	    
public class ControleurAccueil {

    private int direction;
    
    ArrayList scores = new ArrayList();
    ArrayList noms = new ArrayList();

    public ControleurAccueil(int direction) {
        this.direction = direction;

        if (direction == 1) {
            System.out.println("Vous êtes arrivés sur le profil");
        }
        if (direction == 2) {
            System.out.println("Vous lancez une nouvelle partie");
        }
        if (direction == 3) {
            System.out.println("Vous êtes sur le tableau des scores");
            //HashMap<String, Integer> scores = new HashMap<>();
            DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", ""); 
            	try {
            		
            		Connection conn = dao.getConn();
            		conn.setAutoCommit(false);

            		String sql = "SELECT nom, score FROM utilisateur ORDER BY score DESC LIMIT 5;";
            		
            		PreparedStatement psScore = conn.prepareStatement(sql);
            		
            		ResultSet rsScore = psScore.executeQuery();
            		
            		while (rsScore.next()) {	
            			scores.add(rsScore.getInt("score"));
            			noms.add(rsScore.getString("nom"));
            			
            		}
            	}
            		catch (SQLException e1) {
            			e1.printStackTrace();
            		} 
            		
            		dao.closeConnection();
            		//TableauScore tableau = new TableauScore(new VBox(), noms, scores);
                	MainApp.jeu.setScene(new TableauScore(new GridPane(), noms, scores));
                	MainApp.jeu.show();
            		
        }            		
        
        if (direction == 4) {
            System.out.println("Vous quittez le jeu");
        }
        
    }
}
	    