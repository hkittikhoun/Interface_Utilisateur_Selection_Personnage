package fighting_2d.commun.modeles;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.WatchJson;
import ca.ntro.app.models.WriteObjectGraph;
import fighting_2d.commun.Region;
import fighting_2d.commun.valeurs.Caractere;
import fighting_2d.commun.valeurs.Profil;
import fighting_2d.frontal.vues.VueCreationProfil;

public class ModeleCreationProfil implements Model, WatchJson, WriteObjectGraph {

    private static List<Caractere> caracteres = new ArrayList<>();

    private String nomProfil;
    private List<Profil> profils = new ArrayList<>();
    private int profilCourant = 0;
    private Region region = null;

    public ModeleCreationProfil() {

    }

    public void initialiserRegion(Region region) {
        this.region = region;
    }

    public void afficherSur(VueCreationProfil vueCreationProfil) {

        if (profils != null) {
            vueCreationProfil.afficherProfilEnTexte(profils, profilCourant);
        }

    }

    public void ajouterCreationProfil(Profil premierProfil) {
        profils.add(0, premierProfil);
        profilCourant = profils.indexOf(premierProfil);
    }

    public void ajouterProfilCourant(Profil profil) {
        profilCourant = profils.indexOf(profil);
    }

    public void selectionnerProfilCourant(int indiceProfilCourant) {
        profilCourant = indiceProfilCourant;
    }

    public void retirerProfil(int indiceProfilARetirer) {
        if (indiceProfilARetirer >= 0) {
            profils.remove(indiceProfilARetirer);
        }
    }

    public static List<Caractere> getCaracteres() {
        return caracteres;
    }

    public static void setCaracteres(List<Caractere> nouveauxCaracteres) {
        caracteres = nouveauxCaracteres;
    }

    public static void ajouterCaractere(Caractere caractere) {
        caracteres.add(caractere);
    }

    public static void initialiserCaracteres() {
        List<Caractere> liste = new ArrayList<>();
        liste.add(new Caractere("perso1", "/images/perso1.jpg", "Nom 1", 100, 10));
        liste.add(new Caractere("perso2", "/images/perso2.jpg", "Nom 2", 120, 8));
        liste.add(new Caractere("perso3", "/images/perso3.jpg", "Nom 3", 90, 12));
        liste.add(new Caractere("perso4", "/images/perso4.jpg", "Nom 4", 110, 9));
        setCaracteres(liste);
    }
}
