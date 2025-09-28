package pong.dorsal.taches;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.backend.BackendTasks;
import pong.commun.messages.MsgAjouterPoint;
import pong.commun.messages.MsgCreerPartie;
import pong.commun.modeles.ModelePartie;

public class ModifierPartie {

    public static void creerTaches(BackendTasks tasks) {

        tasks.taskGroup("ModifierPartie")

                .waitsFor(model(ModelePartie.class))

                .contains(subTasks -> {

                    creerPartie(subTasks);

                    ajouterPoint(subTasks);

                    envoyerMsgAjouterScoreAuRendezVous(subTasks);

                });
    }

    private static void envoyerMsgAjouterScoreAuRendezVous(BackendTasks subTasks) {
        subTasks.task("envoyerMsgAjouterScoreAuRendezVous")

                .waitsFor(model(ModelePartie.class))

                .waitsFor("ajouterPoint")

                .executes(inputs -> {

                    ModelePartie partie = inputs.get("ajouterPoint");

                    partie.envoyerMsgAjouterScoreAuRendezVous();

                });
    }

    private static void creerPartie(BackendTasks subTasks) {

        subTasks.task("creerPartie")

                .waitsFor(model(ModelePartie.class))

                .waitsFor(message(MsgCreerPartie.class))

                .executes(inputs -> {

                    MsgCreerPartie msgCreerPartie = inputs.get(message(MsgCreerPartie.class));
                    ModelePartie partie = inputs.get(model(ModelePartie.class));

                    msgCreerPartie.appliquerA(partie);

                });
    }

    private static void ajouterPoint(BackendTasks tasks) {

        tasks.task("ajouterPoint")

                .waitsFor(model(ModelePartie.class))

                .waitsFor(message(MsgAjouterPoint.class))

                .executesAndReturnsValue(inputs -> {

                    MsgAjouterPoint msgAjouterPoint = inputs.get(message(MsgAjouterPoint.class));
                    ModelePartie partie = inputs.get(model(ModelePartie.class));

                    msgAjouterPoint.ajouterPointA(partie);

                    return partie;
                });
    }

}