package pong.commun.messages;

import ca.ntro.app.messages.Message;
import pong.commun.modeles.ModeleFileAttente;
import pong.commun.valeurs.Joueur;
import pong.commun.valeurs.RendezVousComplet;

public class MsgRejoindreRendezVous extends Message<MsgRejoindreRendezVous> {

    private String idRendezVous;
    private Joueur joueur;

    public MsgRejoindreRendezVous setIdRendezVous(String idRendezVous) {
        this.idRendezVous = idRendezVous;

        return this;
    }

    public MsgRejoindreRendezVous setJoueur(Joueur joueur) {
        this.joueur = joueur;

        return this;
    }

    public RendezVousComplet appliquerA(ModeleFileAttente fileAttente) {

        return fileAttente.creerRendezVousComplet(idRendezVous, joueur);

    }

}
