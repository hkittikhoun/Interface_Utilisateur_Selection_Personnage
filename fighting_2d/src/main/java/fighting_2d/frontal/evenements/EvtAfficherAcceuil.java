package fighting_2d.frontal.evenements;

import ca.ntro.app.events.Event;
import fighting_2d.frontal.SessionFighting2D;
import fighting_2d.frontal.vues.VueAcceuil;
import fighting_2d.frontal.vues.VueRacine;

public class EvtAfficherAcceuil extends Event{

    public EvtAfficherAcceuil appliquerA(VueRacine vueRacine, VueAcceuil vueAcceuil) {
        
        vueRacine.afficherSousVue(vueAcceuil);

        return this;
    }

    public EvtAfficherAcceuil appliquerA(SessionFighting2D session) {

        session.memoriserVueCourante(VueAcceuil.class);

        return this;
    }
}