package view;

import app.MainApp;
import controller.ControleurCreationCompte;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CreationCompte extends Scene {

	VBox vbox;
	
	public CreationCompte(VBox form) {
	 
		super(form, 800, 600);
		vbox = new VBox();
		super.setRoot(vbox);

		 // Création des labels du formulaire d'inscription
        Label pseudoLabel = new Label("Pseudo :*");
        Label mailLabel = new Label("Mail :*");
        Label mdpLabel = new Label("Mot de passe :*");
        Label confirmMdpLabel = new Label("Confirmer le mot de passe :*");

        // Création des champs textes
        TextField pseudoChamps = new TextField();
        TextField mailChamps = new TextField();
        PasswordField mdpChamps = new PasswordField();
        PasswordField confirmMdpChamps = new PasswordField();
        
        // --- Image de fond via CSS ---
        vbox.setStyle("""
                -fx-background-image: url("/accueil.jpg");
                -fx-background-size: cover;
                -fx-background-position: center;
                """);
        
        
        
        
        // Phrase de confirmation ou d'erreurs
        Label confirmationLabel = new Label();
        
        Label champsRequisLabel = new Label("*champs obligatoires");
        champsRequisLabel.setTextFill(Color.RED);
        
        Button soumettreBouton = new Button("Créer le compte");

        // Lors du clic sur le bouton selon les conditions instancie le controleurCreationCompte
        	soumettreBouton.setOnAction(event -> {
            String pseudoInsere = pseudoChamps.getText();
            String mailInsere = mailChamps.getText();
            String mdpInsere = mdpChamps.getText();
            String confirmMdpInsere = confirmMdpChamps.getText();

    		new ControleurCreationCompte(pseudoInsere, mailInsere, mdpInsere, confirmMdpInsere, "joueur", this);
        	System.out.println("controleurCreationCompte instancié!");
        });
		
		
        vbox.setAlignment(Pos.CENTER);
		
	    VBox.setMargin(vbox, new Insets(10.0d) );
        
	    vbox.setPadding(new Insets(25, 25, 25, 25));
        
	    vbox.getChildren().addAll(pseudoLabel, pseudoChamps, mailLabel, mailChamps, mdpLabel, mdpChamps, confirmMdpLabel, 
        		confirmMdpChamps, soumettreBouton, confirmationLabel, champsRequisLabel);

        pseudoChamps.setMaxWidth(150);
        mailChamps.setMaxWidth(150);
        mdpChamps.setMaxWidth(150);
        confirmMdpChamps.setMaxWidth(150);
		
	}

	public void ajouterMessage(String message) {
		Label erreur = new Label(message);
		erreur.setTextFill(Color.RED);
		this.vbox.getChildren().add(erreur);
		MainApp.jeu.show();
		System.out.println("la méthode ajouterMessage marche");
	}
	
	
}



