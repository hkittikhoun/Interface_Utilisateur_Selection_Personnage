package pong.commun.valeurs;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.models.ModelValue;
import pong.commun.messages.MsgCreerPartie;
import pong.frontal.fragments.FragmentRendezVous;
import pong.frontal.fragments.FragmentRendezVousComplet;

public class RendezVous implements ModelValue {

    private String idRendezVous;
    private Joueur premierJoueur;

    public RendezVous() {

    }

    public RendezVous(String idRendezVous, Joueur premierJoueur) {
        this.idRendezVous = idRendezVous;
        this.premierJoueur = premierJoueur;
    }

    protected Joueur premierJoueur() {
        return premierJoueur;
    }

    @Override
    public String toString() {
        return premierJoueur().nomComplet();
    }

    public FragmentRendezVous creerFragment(ViewLoader<FragmentRendezVous> viewLoaderRendezVous,
            ViewLoader<FragmentRendezVousComplet> viewLoaderRendezVousComplet) {

        return viewLoaderRendezVous.createView();
    }

    public void afficherSur(FragmentRendezVous fragmentRendezVous) {
        fragmentRendezVous.memoriserIdRendezVous(idRendezVous);

        fragmentRendezVous.afficherNomPremierJoueur(premierJoueur.nomCourt());
    }

    public boolean siIdEst(String idRendezVous) {
        return this.idRendezVous.equals(idRendezVous);
    }

    public RendezVousComplet creerRendezVousComplet(Joueur deuxiemeJoueur) {
        RendezVousComplet rendezVousComplet = new RendezVousComplet(idRendezVous, premierJoueur, deuxiemeJoueur);

        return rendezVousComplet;
    }

    protected MsgCreerPartie creerMsgCreerPartie() {
        return Ntro.newMessage(MsgCreerPartie.class)
                .setIdRendezVous(idRendezVous)
                .setPremierJoueur(premierJoueur);

    }
}
