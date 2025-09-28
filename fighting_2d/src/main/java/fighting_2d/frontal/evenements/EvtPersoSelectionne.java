package fighting_2d.frontal.evenements;

import ca.ntro.app.events.Event;

public class EvtPersoSelectionne extends Event {
    private int index;

    public int getIndex() {
        return index;
    }

    public EvtPersoSelectionne setIndex(int index) {
        this.index = index;
        return this;
    }
}