package fighting_2d.frontal.evenements;

import ca.ntro.app.events.Event;
import fighting_2d.frontal.SessionFighting2D;
import fighting_2d.frontal.vues.VueCreationProfil;
import fighting_2d.frontal.vues.VueRacine;

public class EvtAfficherCreationProfil extends Event{

    public EvtAfficherCreationProfil appliquerA(VueRacine vueRacine, VueCreationProfil vueCreationProfil) {
        
        vueRacine.afficherSousVue(vueCreationProfil);

        return this;
    }

    public EvtAfficherCreationProfil appliquerA(SessionFighting2D session) {

        session.memoriserVueCourante(VueCreationProfil.class);

        return this;
    }
}
