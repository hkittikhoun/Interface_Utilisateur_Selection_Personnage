package fighting_2d.commun.messages;

import ca.ntro.app.messages.Message;
import fighting_2d.commun.modeles.ModeleCreationProfil;

public class MsgRetirerProfil extends Message<MsgRetirerProfil> {

    private int indiceProfilARetirer;

    public MsgRetirerProfil setIndiceProfilARetirer(int indiceProfil) {
        this.indiceProfilARetirer = indiceProfil;
        return this;
    }

    public void retirerDe(ModeleCreationProfil creationProfil) {
        creationProfil.retirerProfil(indiceProfilARetirer);
    }
}
