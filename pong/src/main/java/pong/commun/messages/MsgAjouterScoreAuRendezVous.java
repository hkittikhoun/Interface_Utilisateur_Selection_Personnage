package pong.commun.messages;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.messages.Message;
import pong.commun.enums.Position;
import pong.commun.modeles.ModeleFileAttente;

public class MsgAjouterScoreAuRendezVous extends Message<MsgAjouterScoreAuRendezVous> {

    private String idRendezVous;
    private Map<Position, Integer> scoreParPosition = new HashMap<>();

    public MsgAjouterScoreAuRendezVous setIdRendezVous(String idRendezVous) {
        this.idRendezVous = idRendezVous;
        return this;
    }

    public MsgAjouterScoreAuRendezVous setScoreParPosition(Map<Position, Integer> scoreParPosition) {
        this.scoreParPosition = scoreParPosition;
        return this;
    }

    public void ajouterPointA(ModeleFileAttente fileAttente) {
        fileAttente.ajouterScoreAuRendezVous(idRendezVous, scoreParPosition);
    }

}
