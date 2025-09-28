package pong.commun.messages;

import ca.ntro.app.messages.Message;
import pong.commun.enums.Position;
import pong.commun.modeles.ModelePartie;
import pong.commun.valeurs.Joueur;

public class MsgCreerPartie extends Message<MsgCreerPartie> {

    private String idRendezVous;
    private Joueur premierJoueur;
    private Joueur deuxiemeJoueur;

    public MsgCreerPartie setIdRendezVous(String idRendezVous) {

        this.idRendezVous = idRendezVous;

        return this;
    }

    public MsgCreerPartie setPremierJoueur(Joueur premierJoueur) {
        this.premierJoueur = premierJoueur;

        return this;
    }

    public MsgCreerPartie setDeuxiemeJoueur(Joueur deuxiemeJoueur) {
        this.deuxiemeJoueur = deuxiemeJoueur;

        return this;
    }

    public void appliquerA(ModelePartie partie) {

        partie.setIdRendezVous(idRendezVous)
                .ajouterJoueur(Position.GAUCHE, premierJoueur)
                .ajouterJoueur(Position.DROITE, deuxiemeJoueur);

    }
}
