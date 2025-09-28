package fighting_2d.frontal.evenements;

import ca.ntro.app.events.Event;
import ca.ntro.app.services.Window;

public class EvtChangerTaillePolice extends Event {

    private double facteur;

    public EvtChangerTaillePolice setFacteur(double facteur) {
        this.facteur = facteur;

        return this;
    }

    public void appliquerA(Window window) {
        window.multiplyFontSizeBy(facteur);
    }

}