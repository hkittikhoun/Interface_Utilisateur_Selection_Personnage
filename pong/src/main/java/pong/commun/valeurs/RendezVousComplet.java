package pong.commun.valeurs;

import java.util.Map;

import ca.ntro.app.frontend.ViewLoader;
import pong.commun.enums.Position;
import pong.frontal.fragments.FragmentRendezVous;
import pong.frontal.fragments.FragmentRendezVousComplet;

public class RendezVousComplet extends RendezVous {

    private Joueur deuxiemeJoueur;
    private int scorePremierJoueur = 0;
    private int scoreDeuxiemeJoueur = 0;

    public RendezVousComplet() {
        super();
    }

    public RendezVousComplet(String idRendezVous, Joueur premierJoueur, Joueur deuxiemeJoueur) {
        super(idRendezVous, premierJoueur);

        this.deuxiemeJoueur = deuxiemeJoueur;
    }

    @Override
    public String toString() {
        return premierJoueur().nomCourt() + " Vs " + deuxiemeJoueur.nomCourt();
    }

    @Override
    public FragmentRendezVous creerFragment(ViewLoader<FragmentRendezVous> viewLoaderRendezVous,
            ViewLoader<FragmentRendezVousComplet> viewLoaderRendezVousComplet) {

        return viewLoaderRendezVousComplet.createView();
    }

    @Override
    public void afficherSur(FragmentRendezVous fragmentRendezVous) {
        super.afficherSur(fragmentRendezVous);

        FragmentRendezVousComplet fragmentRendezVousComplet = (FragmentRendezVousComplet) fragmentRendezVous;
        fragmentRendezVousComplet.afficherNomDeuxiemeJoueur(deuxiemeJoueur.nomCourt());

        fragmentRendezVousComplet.afficherScorePremierJoueur(scorePremierJoueur);
        fragmentRendezVousComplet.afficherScoreDeuxiemeJoueur(scoreDeuxiemeJoueur);
    }

    public void envoyerMsgCreerPartie() {

        creerMsgCreerPartie().setDeuxiemeJoueur(deuxiemeJoueur)
                .send();

    }

    public void ajouterScore(Map<Position, Integer> scoreParPosition) {
        this.scorePremierJoueur = scoreParPosition.get(Position.GAUCHE);
        this.scoreDeuxiemeJoueur = scoreParPosition.get(Position.DROITE);

    }
}