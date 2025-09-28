package pong.commun.modeles;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.Ntro;
import ca.ntro.app.models.Model;
import pong.commun.enums.Position;
import pong.commun.messages.MsgAjouterScoreAuRendezVous;
import pong.commun.valeurs.InfoJoueur;
import pong.commun.valeurs.Joueur;
import pong.frontal.vues.VuePartie;
import pong.maquettes.MaquetteJoueurs;

public class ModelePartie implements Model {

    private String idRendezVous = null;
    private Map<Position, InfoJoueur> infoJoueurParPosition = new HashMap<>();

    public ModelePartie() {
        ajouterJoueur(Position.GAUCHE, MaquetteJoueurs.joueurAleatoire());
        ajouterJoueur(Position.DROITE, MaquetteJoueurs.joueurAleatoire());
    }

    public ModelePartie setIdRendezVous(String idRendezVous) {
        this.idRendezVous = idRendezVous;

        return this;
    }

    public ModelePartie ajouterJoueur(Position position, Joueur joueur) {
        infoJoueurParPosition.put(position, new InfoJoueur(joueur));

        return this;
    }

    public void afficherSur(VuePartie vuePartie) {
        for (Map.Entry<Position, InfoJoueur> entry : infoJoueurParPosition.entrySet()) {

            Position position = entry.getKey();
            InfoJoueur infoJoueur = entry.getValue();

            infoJoueur.afficherSur(vuePartie, position);
        }
    }

    public void ajouterPointPour(Position position) {

        InfoJoueur infoJoueur = infoJoueurParPosition.get(position);

        if (infoJoueur != null) {
            infoJoueur.incrementerScore();
        }
    }

    private Map<Position, Integer> creerScoreParPosition() {
        Map<Position, Integer> scoreParPosition = new HashMap<>();

        for (Position position : infoJoueurParPosition.keySet()) {
            InfoJoueur infoJoueur = infoJoueurParPosition.get(position);
            infoJoueur.copierScoreDans(scoreParPosition, position);
        }

        return scoreParPosition;
    }

    public void envoyerMsgAjouterScoreAuRendezVous() {
        Ntro.newMessage(MsgAjouterScoreAuRendezVous.class)
                .setIdRendezVous(idRendezVous)
                .setScoreParPosition(creerScoreParPosition())
                // .setModelSelection(ModeleFileAttente.class, idRegion(idRendezVous))
                .send();

    }

    private String idRegion(String idRendezVous) {
        return idRendezVous;
    }

}
