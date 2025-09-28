package pong.commun.messages;

import ca.ntro.app.messages.Message;
import pong.commun.modeles.ModeleFileAttente;
import pong.commun.valeurs.Joueur;

public class MsgAjouterRendezVous extends Message<MsgAjouterRendezVous> {

    private Joueur premierJoueur;

    public MsgAjouterRendezVous setPremierJoueur(Joueur premierJoueur) {
        this.premierJoueur = premierJoueur;

        return this;
    }

    public void ajouterA(ModeleFileAttente fileAttente) {

        fileAttente.ajouterRendezVous(premierJoueur);
    }
}