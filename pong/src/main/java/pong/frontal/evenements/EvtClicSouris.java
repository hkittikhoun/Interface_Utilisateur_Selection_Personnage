package pong.frontal.evenements;

import ca.ntro.app.events.Event;
import ca.ntro.app.fx.controls.World2dMouseEventFx;
import pong.frontal.donnees.DonneesVuePartie;

public class EvtClicSouris extends Event {

    private World2dMouseEventFx mouseEvent;

    public EvtClicSouris setMouseEvent(World2dMouseEventFx mouseEvent) {
        this.mouseEvent = mouseEvent;

        return this;
    }

    public void appliquerA(DonneesVuePartie donneesVuePartie) {
        donneesVuePartie.reagirClicSouris(mouseEvent);
    }

}
