package fighting_2d.frontal.evenements;

import ca.ntro.app.events.Event;
import fighting_2d.commun.Region;
import fighting_2d.frontal.SessionFighting2D;

public class EvtChangerRegion extends Event {

    private Region region;

    public EvtChangerRegion setRegion(Region region) {

        this.region = region;

        return this;

    }

    public void appliquerA(SessionFighting2D session) {
        session.memoriserRegionCourante(region)
                .observerRegionCourante();
    }
}
