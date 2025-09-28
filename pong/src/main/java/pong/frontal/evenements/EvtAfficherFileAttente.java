package pong.frontal.evenements;

import ca.ntro.app.events.Event;
import pong.frontal.SessionPong;
import pong.frontal.vues.VueFileAttente;
import pong.frontal.vues.VueRacine;

public class EvtAfficherFileAttente extends Event {

  public EvtAfficherFileAttente appliquerA(VueRacine vueRacine, VueFileAttente vueFileAttente) {

    vueRacine.afficherSousVue(vueFileAttente);

    return this;
  }

  public EvtAfficherFileAttente appliquerA(SessionPong session) {

    session.memoriserVueCourante(VueFileAttente.class);

    return this;

  }

  public EvtAfficherFileAttente appliquerA(SessionPong session, VueFileAttente vueFileAttente) {
    session.memoriserVueCourante(VueFileAttente.class);

    return this;
  }

}
