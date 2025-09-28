package pong.maquettes;

import java.util.List;

import ca.ntro.app.Ntro;
import pong.commun.valeurs.Joueur;

public class MaquetteJoueurs {

    public static Joueur creerJoueur(String id) {
        Joueur joueur = new Joueur();

        if(id.equals("alice")) {

            joueur = creerJoueur(id, "Alice", "Aram");


        }else if(id.equals("bob")) {

            joueur = creerJoueur(id, "Bob", "Bérancourt");

        }else if(id.equals("charlie")) {

            joueur = creerJoueur(id, "Charlie", "Chen");

        }else {

            joueur = joueurAleatoire(id);

        }

        return joueur;
    }

    public static Joueur creerJoueur(String id, 
                                     String prenom, 
                                     String nom) {

        Joueur usager = new Joueur();

        usager.setId(id);
        usager.setPrenom(prenom);
        usager.setNom(nom);

        return usager;

    }

    public static Joueur joueurAleatoire(String id) {
        Joueur usager = new Joueur();

        usager.setId(id);
        usager.setPrenom(prenomAleatoire());
        usager.setNom(nomAleatoire());

        return usager;
    }

    public static Joueur joueurAleatoire() {
        return joueurAleatoire(idAleatoire());
    }

    private static String idAleatoire() {
        return Ntro.random().nextId(4);
    }

    private static String prenomAleatoire() {

        List<String> choixDeNoms = List.of("Alice", 
                                           "Bob", 
                                           "Chaaya", 
                                           "Dominic", 
                                           "Élisabeth", 
                                           "Firas", 
                                           "Gregson",
                                           "Mehdi",
                                           "Louis",
                                           "Marcel",
                                           "Ashwin",
                                           "Ichiro",
                                           "Jun");

        return Ntro.random().choice(choixDeNoms);
    }

    private static String nomAleatoire() {

        List<String> choixDeNoms = List.of("Abdenouri", 
                                           "Ahmadi", 
                                           "Augustin", 
                                           "Chaussé", 
                                           "Delisle", 
                                           "Heer", 
                                           "Lagrois",
                                           "Daverna",
                                           "Gonzales",
                                           "Medjoubi",
                                           "Castillo",
                                           "Josan",
                                           "Yi");

        return Ntro.random().choice(choixDeNoms);
    }

}
