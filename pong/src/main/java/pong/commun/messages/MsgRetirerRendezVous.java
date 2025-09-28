package pong.commun.messages;

import ca.ntro.app.messages.Message;
import pong.commun.modeles.ModeleFileAttente;

public class MsgRetirerRendezVous extends Message<MsgRetirerRendezVous> {

    private String idRendezVous;

    public MsgRetirerRendezVous setIdRendezVous(String idRendezVous) {

        this.idRendezVous = idRendezVous;

        return this;
    }

    public void retirerDe(ModeleFileAttente fileAttente) {

        fileAttente.retirerRendezVous(idRendezVous);

    }
}