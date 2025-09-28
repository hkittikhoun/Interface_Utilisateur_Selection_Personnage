package fighting_2d.commun.messages;

import ca.ntro.app.messages.Message;
import fighting_2d.commun.Region;
import fighting_2d.commun.modeles.ModeleCreationProfil;

public class MsgInitialiserCreationProfil extends Message<MsgInitialiserCreationProfil> {

    private Region region;

    public MsgInitialiserCreationProfil setRegion(Region region) {
        this.region = region;

        return this;
    }

    public MsgInitialiserCreationProfil() {

    }

    public void appliquerA(ModeleCreationProfil creationProfil) {
        creationProfil.initialiserRegion(region);
    }
}
