package pong.frontal.utils;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.app.Ntro;
import pong.commun.enums.Action;
import pong.commun.enums.Position;
import pong.frontal.evenements.EvtActionJoueur;

public class ActionsActives {

    private Position position;
    private Set<Action> actions = new HashSet<>();

    public ActionsActives(Position position) {
        this.position = position;
    }

    public void activer(Action action) {
        actions.add(action);

        Ntro.newEvent(EvtActionJoueur.class)
                .setPosition(position)
                .setAction(action)
                .trigger();
    }

    public void desactiver(Action action) {
        actions.remove(action);

        if (actions.isEmpty()) {

            Ntro.newEvent(EvtActionJoueur.class)
                    .setPosition(position)
                    .setAction(Action.ARRETER)
                    .trigger();

        } else {

            for (Action actionRestante : actions) {

                Ntro.newEvent(EvtActionJoueur.class)
                        .setPosition(position)
                        .setAction(actionRestante)
                        .trigger();
            }
        }
    }
}