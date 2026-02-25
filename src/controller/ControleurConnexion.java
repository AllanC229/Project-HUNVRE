package controller;

// WIP (Work In Progress) : connexion BDD pas encore implémentée
import java.sql.*;

/**
 * Contrôleur de connexion : gère les actions depuis la vue Connexion.
 * Reçoit un choix (1 = connexion, 2 = création de compte).
 */
public class ControleurConnexion {

    public ControleurConnexion(int choix) {

        // Bouton "connexion" de la vue Connexion.java
        if (choix == 1) {
            System.out.println("totototototottatatatatatttttitititiititit");
            // TODO : comparer identifiant/mdp avec la BDD
            // TODO : si OK → afficher la vue Accueil
        }

        // Bouton "creation" de la vue Connexion.java
        if (choix == 2) {
            // TODO : afficher la vue Création de Compte
        }
    }
}