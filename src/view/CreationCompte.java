package view;

import controller.ControleurCreationCompte;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreationCompte extends Scene {

	public CreationCompte(VBox form) {
		super(form, 800, 600);
		
		 // Création des labels du formulaire d'inscription
        Label nomLabel = new Label("Nom :");
        Label mailLabel = new Label("Mail :");
        Label mdpLabel = new Label("Mot de passe :");

        // Création des champs textes
        TextField nomChamps = new TextField();
        TextField mailChamps = new TextField();
        PasswordField mdpChamps = new PasswordField();
        
        // Phrase de confirmation
        Label resultLabel = new Label();

        // Create a "Login" button.
        Button creerButton = new Button("Créer le compte");

        // Set an action for the "Login" button to validate the credentials.
        creerButton.setOnAction(event -> {
            String nomInsere = nomChamps.getText();
            String mailInsere = mailChamps.getText();
            String mdpInsere = mdpChamps.getText();


            if (nomInsere !="" && mailInsere !="" && mdpInsere !="" ) {
                new ControleurCreationCompte(nomInsere, mailInsere, mdpInsere);
            	
            	System.out.println("controleurCreatioCompte instancié!");
            } else {
                resultLabel.setText("Veuillez remplir tout les champs");
            }
        });
		
		
		
		// VBox form = new VBox();
	    form.setAlignment(Pos.CENTER);
		
	    VBox.setMargin(form, new Insets(10.0d) );
        
        form.setPadding(new Insets(25, 25, 25, 25));
        
        form.getChildren().addAll(nomLabel, nomChamps, mailLabel, mailChamps, mdpLabel, mdpChamps, creerButton, resultLabel);

        nomChamps.setMaxWidth(150);
        mailChamps.setMaxWidth(150);
        mdpChamps.setMaxWidth(150);
		
	}


}
