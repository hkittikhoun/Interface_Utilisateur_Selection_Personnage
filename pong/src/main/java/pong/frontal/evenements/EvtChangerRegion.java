package pong.frontal.evenements;

import ca.ntro.app.events.Event;
import pong.commun.enums.Region;
import pong.frontal.SessionPong;

public class EvtChangerRegion extends Event {

    private Region region;

    public EvtChangerRegion setRegion(Region region) {
        this.region = region;

        return this;
    }

    public void appliquerA(SessionPong session) {

        session.memoriserRegionCourante(region)
                .observerRegionCourante();
    }
}
