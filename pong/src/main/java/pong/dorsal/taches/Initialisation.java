package pong.dorsal.taches;

import ca.ntro.app.Ntro;
import ca.ntro.app.tasks.backend.BackendTasks;
import pong.commun.enums.Region;
import pong.commun.messages.MsgInitialiserFileAttente;
import pong.commun.modeles.ModeleFileAttente;

public class Initialisation {

    public static void creerTaches(BackendTasks tasks) {

        tasks.taskGroup("Initialisation")

                .contains(subTasks -> {

                    initialiserFilesAttentes(subTasks);

                });
    }

    private static void initialiserFilesAttentes(BackendTasks subTasks) {

        subTasks.task("initialiserFilesAttentes")

                .executes(inputs -> {

                    for (Region region : Region.values()) {

                        Ntro.newMessage(MsgInitialiserFileAttente.class)
                                .setModelSelection(ModeleFileAttente.class, region.name())
                                .setRegion(region)
                                .send();
                    }
                });
    }
}