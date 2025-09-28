package pong.frontal.evenements;

import ca.ntro.app.events.Event;
import pong.frontal.SessionPong;
import pong.frontal.vues.VuePartie;
import pong.frontal.vues.VueRacine;

public class EvtAfficherPartie extends Event{

    public EvtAfficherPartie appliquerA(VueRacine vueRacine, VuePartie vuePartie) {
        vueRacine.afficherSousVue(vuePartie);

        return this;
    }

    public EvtAfficherPartie appliquerA(SessionPong session) {

        session.memoriserVueCourante(VuePartie.class);

        return this;
    }
}
