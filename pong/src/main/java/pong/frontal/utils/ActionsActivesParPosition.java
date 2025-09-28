package pong.frontal.utils;

import java.util.HashMap;
import java.util.Map;

import pong.commun.enums.Action;
import pong.commun.enums.Position;

public class ActionsActivesParPosition {

    private Map<Position, ActionsActives> actionsActives = new HashMap<>();

    public ActionsActivesParPosition() {
        for (Position position : Position.values()) {
            actionsActives.put(position, new ActionsActives(position));
        }
    }

    public void activer(Position position, Action action) {
        if (position != null
                && action != null) {

            actionsActives.get(position).activer(action);
        }
    }

    public void desactiver(Position position, Action action) {
        if (position != null
                && action != null) {

            actionsActives.get(position).desactiver(action);
        }
    }
}