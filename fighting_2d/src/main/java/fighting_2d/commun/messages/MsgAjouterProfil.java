package fighting_2d.commun.messages;

import ca.ntro.app.messages.Message;
import fighting_2d.commun.modeles.ModeleCreationProfil;
import fighting_2d.commun.valeurs.Profil;

public class MsgAjouterProfil extends Message<MsgAjouterProfil> {

    private Profil premierProfil;

    public MsgAjouterProfil setPremierProfil(Profil premierProfil) {
        this.premierProfil = premierProfil;

        return this;
    }

    public void ajouterA(ModeleCreationProfil creationProfil) {
        creationProfil.ajouterCreationProfil(premierProfil);
    }

}
