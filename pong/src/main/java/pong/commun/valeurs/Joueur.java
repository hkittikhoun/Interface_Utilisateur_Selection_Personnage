package pong.commun.valeurs;

import ca.ntro.app.models.ModelValue;
import pong.commun.enums.Position;
import pong.frontal.vues.VuePartie;

public class Joueur implements ModelValue {

    private String id;
    private String prenom;
    private String nom;

    public Joueur() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String nomComplet() {
        return prenom + " " + nom;
    }

    public String nomCourt() {
        return prenom;
    }

    public void afficherSur(VuePartie vuePartie, Position position) {
        vuePartie.afficherNomJoueur(position, nomCourt());
    }
}
