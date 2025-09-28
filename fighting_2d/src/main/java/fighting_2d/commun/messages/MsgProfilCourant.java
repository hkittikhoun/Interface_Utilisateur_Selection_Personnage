package fighting_2d.commun.messages;

import ca.ntro.app.messages.Message;
import fighting_2d.commun.modeles.ModeleCreationProfil;
import fighting_2d.commun.valeurs.Profil;

public class MsgProfilCourant extends Message<MsgProfilCourant> {

    private int indiceProfilCourant;

    public MsgProfilCourant setProfilCourant(int indiceProfilCourant) {
        this.indiceProfilCourant = indiceProfilCourant;

        return this;
    }

    public void ajouterA(ModeleCreationProfil creationProfil) {
        creationProfil.selectionnerProfilCourant(indiceProfilCourant);
    }
}
