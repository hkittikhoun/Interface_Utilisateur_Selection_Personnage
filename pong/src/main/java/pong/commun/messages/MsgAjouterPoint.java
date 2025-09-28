package pong.commun.messages;

import ca.ntro.app.messages.Message;
import pong.commun.enums.Position;
import pong.commun.modeles.ModelePartie;

public class MsgAjouterPoint extends Message<MsgAjouterPoint> {

    private Position position;

    public MsgAjouterPoint setPosition(Position position) {
        this.position = position;

        return this;
    }

    public MsgAjouterPoint ajouterPointA(ModelePartie partie) {
        partie.ajouterPointPour(position);

        return this;
    }

}