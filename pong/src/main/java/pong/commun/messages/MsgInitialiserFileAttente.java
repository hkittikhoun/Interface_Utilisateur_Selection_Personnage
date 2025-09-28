package pong.commun.messages;

import ca.ntro.app.messages.Message;
import pong.commun.enums.Region;
import pong.commun.modeles.ModeleFileAttente;

public class MsgInitialiserFileAttente extends Message<MsgInitialiserFileAttente> {

    private Region region;

    public MsgInitialiserFileAttente setRegion(Region region) {
        this.region = region;

        return this;
    }

    public MsgInitialiserFileAttente() {
    }

    public void appliquerA(ModeleFileAttente fileAttente) {
        fileAttente.initialiserRegion(region);
    }
}
