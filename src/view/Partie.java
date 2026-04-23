package view;

// --- javafx.* ---
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

// --- Classes du projet ---
import controller.ControleurPartie;

public class Partie extends Scene {

    public Partie() {
        super(new GridPane(), 1200, 900);
        ZoneSeb zoneSebAfficheCombinaison = new ZoneSeb();
        ZoneScore zonescore = new ZoneScore();
        ControleurPartie controleurpartie = new ControleurPartie(zoneSebAfficheCombinaison, zonescore); //Initialise le controleurpartie avec la zoneseb qui vient d'être instanciée
        ZoneMain zonemain = new ZoneMain(controleurpartie);		//Instancie les vues ZoneMain et ZoneSeb

        GridPane partie = (GridPane) getRoot();
		partie.setGridLinesVisible(true);
		
		
	ColumnConstraints c1 = new ColumnConstraints();		//Lignes 21 à 36 : définition du quadrillage qui segmente les différentes zones d'affichage
	c1.setPercentWidth(17);
	ColumnConstraints c2 = new ColumnConstraints();
	c2.setPercentWidth(66);
	ColumnConstraints c3 = new ColumnConstraints();
	c3.setPercentWidth(17);
	
	RowConstraints l1 = new RowConstraints();
	l1.setPercentHeight(29);
	RowConstraints l2 = new RowConstraints();
	l2.setPercentHeight(42);
	RowConstraints l3 = new RowConstraints();
	l3.setPercentHeight(29);
	
	partie.getColumnConstraints().addAll(c1, c2, c3);  //On ajoute le quadrillage ainsi défini au GridPane "partie"
	partie.getRowConstraints().addAll(l1, l2, l3);
	
	
	partie.add(new ZoneTitre(),0, 0, 3, 1);
	
	partie.add(new ZoneMenu(), 0, 0);
	
	partie.add(zonescore, 2, 0);

	partie.add(new ZoneCentrale(), 0, 1, 3, 1);	
	
	
	partie.add(zoneSebAfficheCombinaison, 0, 2);
	
	partie.add(zonemain, 1, 2);	
	
	partie.add(new ZoneDeck(), 2, 2);
	
}
}
