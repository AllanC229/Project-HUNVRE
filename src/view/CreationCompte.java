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
import javafx.scene.paint.Color;

public class CreationCompte extends Scene {

	public CreationCompte(VBox form) {
		super(form, 800, 600);
		
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

    		new ControleurCreationCompte(pseudoInsere, mailInsere, mdpInsere, confirmMdpInsere);
        	System.out.println("controleurCreatioCompte instancié!");

        });
		
		
		
		// VBox form = new VBox();
	    form.setAlignment(Pos.CENTER);
		
	    VBox.setMargin(form, new Insets(10.0d) );
        
        form.setPadding(new Insets(25, 25, 25, 25));
        
        form.getChildren().addAll(pseudoLabel, pseudoChamps, mailLabel, mailChamps, mdpLabel, mdpChamps, confirmMdpLabel, 
        		confirmMdpChamps, soumettreBouton, confirmationLabel, champsRequisLabel);

        pseudoChamps.setMaxWidth(150);
        mailChamps.setMaxWidth(150);
        mdpChamps.setMaxWidth(150);
        confirmMdpChamps.setMaxWidth(150);
		
	}


}
