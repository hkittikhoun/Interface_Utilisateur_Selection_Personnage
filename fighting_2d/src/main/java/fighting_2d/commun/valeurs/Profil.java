package fighting_2d.commun.valeurs;

import ca.ntro.app.Ntro;
import ca.ntro.app.models.ModelValue;
import fighting_2d.commun.messages.MsgRetirerProfil;

public class Profil implements ModelValue {

    private String id;
    private String nom;
    private Caractere caractere;
    private int nbVictoire;
    private int nbDefaite;

    // Constructeur par défaut
    public Profil() {
    }

    // Constructeur avec tous les attributs
    public Profil(String id, String nom, Caractere caractere, int nbVictoire, int nbDefaite) {
        this.id = id;
        this.nom = nom;
        this.caractere = caractere;
        this.nbVictoire = nbVictoire;
        this.nbDefaite = nbDefaite;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Caractere getCaractere() {
        return caractere;
    }

    public int getNbVictoire() {
        return nbVictoire;
    }

    public int getNbDefaite() {
        return nbDefaite;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCaractere(Caractere caractere) {
        this.caractere = caractere;
    }

    public void setNbVictoire(int nbVictoire) {
        this.nbVictoire = nbVictoire;
    }

    public void setNbDefaite(int nbDefaite) {
        this.nbDefaite = nbDefaite;
    }

    // Méthode pour l'affichage
    @Override
    public String toString() {
        return getNom() + " : " + getNbVictoire() + " - " + getNbDefaite();
    }

    public void envoyerMsgRetirerCeProfil(int index) {

        Ntro.newMessage(MsgRetirerProfil.class)
                .setIndiceProfilARetirer(index)
                .send();
    }

}