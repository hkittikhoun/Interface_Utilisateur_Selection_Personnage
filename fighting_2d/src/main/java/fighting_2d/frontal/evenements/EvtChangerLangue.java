package fighting_2d.frontal.evenements;

import ca.ntro.app.Ntro;
import ca.ntro.app.events.Event;
import ca.ntro.core.locale.Locale;

public class EvtChangerLangue extends Event {

    private String langue;

    public String getLangue() {
        return langue;
    }

    public EvtChangerLangue setLangue(String langue) {
        this.langue = langue;

        return this;
    }

    public void appliquer() {

        Locale newLocale = Ntro.buildLocale(langue);

        Ntro.changeLocale(newLocale);
    }

}