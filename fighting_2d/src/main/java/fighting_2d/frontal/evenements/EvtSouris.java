package fighting_2d.frontal.evenements;

import ca.ntro.app.events.Event;
import ca.ntro.app.fx.controls.World2dMouseEventFx;
import fighting_2d.frontal.donnees.DonneesVueCreationProfil;

public class EvtSouris extends Event {

    private World2dMouseEventFx mouseEvent;

    public EvtSouris setMouseEvent(World2dMouseEventFx mouseEvent) {
        this.mouseEvent = mouseEvent;

        return this;
    }

    public void appliquerA(DonneesVueCreationProfil donneesVueCreationProfil) {
        donneesVueCreationProfil.reagirClicSouris(mouseEvent);
    }
}
