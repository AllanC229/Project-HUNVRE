package app;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.DAOAcces;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Accueil;
import view.Connexion;



public class MainApp extends Application {
	public static Stage jeu;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	
	public void start(Stage primaryStage) {
		jeu = primaryStage;
				
		jeu.setTitle("HUNVRE");
		/*Accueil accueil = new Accueil(new VBox());
		jeu.setScene(accueil);
		jeu.show();*/
		
		Connexion connexion = new Connexion(new VBox());
		
		jeu.setScene(connexion);
		jeu.show();
	}
		
}