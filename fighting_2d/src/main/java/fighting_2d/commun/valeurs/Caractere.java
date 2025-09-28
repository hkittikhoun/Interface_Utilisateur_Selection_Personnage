package fighting_2d.commun.valeurs;

import ca.ntro.app.models.ModelValue;

public class Caractere implements ModelValue {

    private String id;
    private String cheminImg;
    private String nom;
    private int hp;
    private int dmg;

    // Constructeur par défaut
    public Caractere() {
    }

    // Constructeur avec tous les attributs
    public Caractere(String id, String cheminImg, String nom, int hp, int dmg) {
        this.id = id;
        this.cheminImg = cheminImg;
        this.nom = nom;
        this.hp = hp;
        this.dmg = dmg;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCheminImg() {
        return cheminImg;
    }

    public String getNom() {
        return nom;
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setCheminImg(String cheminImg) {
        this.cheminImg = cheminImg;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}