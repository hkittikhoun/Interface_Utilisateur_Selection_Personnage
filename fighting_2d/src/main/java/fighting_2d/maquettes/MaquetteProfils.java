package fighting_2d.maquettes;

import java.util.List;

import ca.ntro.app.Ntro;
import fighting_2d.commun.modeles.ModeleCreationProfil;
import fighting_2d.commun.valeurs.Profil;

public class MaquetteProfils {

    public static Profil creerProfil(String id) {
        Profil profil = new Profil();

        if (id.equals("alice")) {

            profil = creerprofil(id, "Alice Aram");

        } else if (id.equals("bob")) {

            profil = creerprofil(id, "Bob Bérancourt");

        } else if (id.equals("charlie")) {

            profil = creerprofil(id, "Charlie Chen");

        } else if (id.equals("hugo")) {

            profil = creerprofil(id, "Hugo Kittikhoun");

        } else {

            profil = profilAleatoire(id);

        }

        return profil;
    }

    public static Profil creerprofil(String id, String nom) {
        Profil usager = new Profil();

        usager.setId(id);
        usager.setNom(nom);

        return usager;
    }

    public static Profil creerprofil(String id, String nom, int indexPersoSelectionne) {
        Profil usager = new Profil();
        usager.setId(id);
        usager.setNom(nom);

        if (indexPersoSelectionne >= 0 && indexPersoSelectionne < ModeleCreationProfil.getCaracteres().size()) {
            usager.setCaractere(ModeleCreationProfil.getCaracteres().get(indexPersoSelectionne));
        }

        return usager;
    }

    public static Profil profilAleatoire(String id) {
        Profil usager = new Profil();

        usager.setId(id);
        usager.setNom(nomAleatoire());

        return usager;
    }

    public static Profil profilAleatoire() {
        Profil usager = new Profil();

        usager.setId(idAleatoire());
        usager.setNom(nomAleatoire());

        return usager;
    }

    private static String idAleatoire() {
        return Ntro.random().nextId(4);
    }

    private static String nomAleatoire() {
        List<String> choixDeNoms = List.of(
                "Alice Abdenouri",
                "Bob Ahmadi",
                "Chaaya Lavallé",
                "Dominic Changrin",
                "Élisabeth Chassué",
                "Firas Delisle",
                "Gregson Heer",
                "Mehdi Lagrois",
                "Louis Daveerna",
                "Marcel Gonzales",
                "Ashwin Castillo",
                "Ichiro Josan",
                "John Doe",
                "Jane Doe",
                "Jun Yi");

        return Ntro.random().choice(choixDeNoms);
    }
}
