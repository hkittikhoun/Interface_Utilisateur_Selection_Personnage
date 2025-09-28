package pong.frontal;

import ca.ntro.app.Ntro;
import ca.ntro.app.session.Session;
import pong.commun.enums.Region;
import pong.commun.messages.MsgAjouterRendezVous;
import pong.commun.messages.MsgRejoindreRendezVous;
import pong.commun.modeles.ModeleFileAttente;
import pong.frontal.evenements.EvtAfficherFileAttente;
import pong.frontal.evenements.EvtAfficherPartie;
import pong.frontal.vues.VueFileAttente;
import pong.frontal.vues.VuePartie;
import pong.maquettes.MaquetteJoueurs;

public class SessionPong extends Session<SessionPong> {

    private Class<?> vueCourante = VueFileAttente.class;

    private Region regionCourante = Region.AMERIQUE;

    public SessionPong() {
        super();
        observerRegionCourante();
    }

    public SessionPong memoriserVueCourante(Class<?> vueCourante) {

        this.vueCourante = vueCourante;

        return this;
    }

    public void envoyerEvtPourAfficherVueCourante() {

        if (vueCourante.equals(VuePartie.class)) {

            Ntro.newEvent(EvtAfficherPartie.class)
                    .trigger();

        } else {

            Ntro.newEvent(EvtAfficherFileAttente.class)
                    .trigger();
        }
    }

    public void envoyerMsgAjouterRendezVous() {
        Ntro.newMessage(MsgAjouterRendezVous.class)
                .setPremierJoueur(MaquetteJoueurs.joueurAleatoire(this.sessionId()))
                .send();
    }

    public SessionPong envoyerMsgRejoindreRendezVous(String idRendezVous) {
        Ntro.newMessage(MsgRejoindreRendezVous.class)
                .setIdRendezVous(idRendezVous)
                .setJoueur(MaquetteJoueurs.joueurAleatoire(this.sessionId()))
                .send();

        return this;
    }

    public SessionPong memoriserRegionCourante(Region region) {
        this.regionCourante = region;

        return this;
    }

    public SessionPong observerRegionCourante() {

        this.setModelSelection(ModeleFileAttente.class, regionCourante.name());

        return this;
    }

    public SessionPong afficherRegionCourante(VueFileAttente vueFileAttente) {

        vueFileAttente.afficherRegionCourante(regionCourante.name());

        return this;
    }
}
