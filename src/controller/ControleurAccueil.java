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
import model.CarteJeu;
import model.DeckJoueur;
import view.Partie;
import view.TableauScore;

public class ControleurAccueil {
	
	static Partie partie = new Partie();
    private int direction;
    
    ArrayList<Integer> scores = new ArrayList<Integer>();
    ArrayList<String> pseudos = new ArrayList<String>();

    public ControleurAccueil(int direction) {
        this.direction = direction;

        if (direction == 1) {
            System.out.println("Vous êtes arrivés sur le profil");
        }

        if (direction == 2) {
            System.out.println("Vous lancez une nouvelle partie");
            
            DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", "");
            
            try {
                ResultSet listeCarte = dao.getStatement().executeQuery(
                    "SELECT id_carte, valeur, recto, couleur FROM carte;"
                );
                
                ControleurConnexion.joueur.setDeck(new DeckJoueur());
                
                while(listeCarte.next()) {
                    ControleurConnexion.joueur.getDeck().add(new CarteJeu(
                        listeCarte.getInt(1),
                        listeCarte.getInt(2),
                        listeCarte.getString(3),
                        1,
                        listeCarte.getString(4)
                    ));
                }
            }
            catch(SQLException e) {
                System.out.println("Accueil - Recup deck - Ereur SQL");
                e.printStackTrace();
            }
            
            MainApp.jeu.setScene(partie);
            MainApp.jeu.setFullScreen(true);
            MainApp.jeu.show();
        }

        if (direction == 3) {
            System.out.println("Vous êtes sur le tableau des scores");
            
            DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "hunvre", "root", ""); 
            
            try {
                Connection conn = dao.getConn();
                conn.setAutoCommit(false);

                String sql = "SELECT pseudo, score FROM utilisateur ORDER BY score DESC LIMIT 5;";
                
                PreparedStatement psScore = conn.prepareStatement(sql);
                ResultSet rsScore = psScore.executeQuery();
                
                while (rsScore.next()) {    
                    scores.add(rsScore.getInt("score"));
                    pseudos.add(rsScore.getString("pseudo"));
                }
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            } 
            
            dao.closeConnection();

            MainApp.jeu.setScene(new TableauScore(new GridPane(), pseudos, scores));
            MainApp.jeu.show();
        }            

        if (direction == 4) {
            System.out.println("Vous quittez le jeu");
        }
    }
} 